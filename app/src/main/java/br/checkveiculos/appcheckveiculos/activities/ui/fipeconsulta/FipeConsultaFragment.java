package br.checkveiculos.appcheckveiculos.activities.ui.fipeconsulta;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;

import br.checkveiculos.appcheckveiculos.api.FipeService;
import br.checkveiculos.appcheckveiculos.api.RestServiceGenerator;
import br.checkveiculos.appcheckveiculos.databinding.FragmentFipeConsultaBinding;
import br.checkveiculos.appcheckveiculos.entidades.FipeAnoModelo;
import br.checkveiculos.appcheckveiculos.entidades.FipeConsulta;
import br.checkveiculos.appcheckveiculos.entidades.FipeMarca;
import br.checkveiculos.appcheckveiculos.entidades.FipeMesRerefencia;
import br.checkveiculos.appcheckveiculos.entidades.FipeModelo;
import br.checkveiculos.appcheckveiculos.entidades.FipeRequestBody;
import br.checkveiculos.appcheckveiculos.entidades.FipeResponseModelo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FipeConsultaFragment extends Fragment {

    private FipeConsultaViewModel fipeConsultaViewModel;
    private FragmentFipeConsultaBinding binding;
    private FipeService fipeService;

    private FipeMesRerefencia mesRerefenciaSelecionado;
    private FipeMarca marcaSelecionada;
    private FipeModelo modeloSelecionado;
    private FipeAnoModelo anoModeloSelecionado;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        fipeConsultaViewModel =
                new ViewModelProvider(this).get(FipeConsultaViewModel.class);

        binding = FragmentFipeConsultaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        this.fipeService = RestServiceGenerator.createServiceFipe(FipeService.class);

        binding.fipeSpinnerMesReferencia.setTitle("Selecione um mês");
        binding.fipeSpinnerMarca.setTitle("Selecione uma marca");
        binding.fipeSpinnerModelo.setTitle("Selecione um modelo");
        binding.fipeSpinnerAno.setTitle("Selecione o ano do modelo");
        binding.fipeButtonConsulta.setAlpha(0.5f);
        binding.fipeButtonConsulta.setEnabled(false);

        this.buscarMesesReferencia();

        binding.fipeButtonConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String anoModelo = anoModeloSelecionado.getValue().substring(0, 4);
                String tipoCombustivel = anoModeloSelecionado.getValue().substring(5);

                FipeRequestBody fipeRequestBody = new FipeRequestBody(1, mesRerefenciaSelecionado.getCodigo(), marcaSelecionada.getValue(), modeloSelecionado.getValue(), anoModelo, tipoCombustivel, "carro", "tradicional");

                Call<FipeConsulta> call = fipeService.getFipeVeiculo(fipeRequestBody);
                exibirLoading(true);

                call.enqueue(new Callback<FipeConsulta>() {
                    @Override
                    public void onResponse(Call<FipeConsulta> call, Response<FipeConsulta> response) {
                        if (response.isSuccessful()) {
                            Log.i("FipeService", "Consultou um veículo na api da fipe.");
                            Log.i("FipeService", "Consulta=" + response.body().toString());


                            Intent intent = new Intent(getActivity(), FipeConsultaActivity.class);
                            intent.putExtra("consulta", response.body());
                            startActivity(intent);
                            exibirLoading(false);
                        } else {
                            Log.e("Error", "" + response.message());
                            exibirLoading(false);

                            Toast.makeText(getContext().getApplicationContext(), "Erro: " + response.message(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<FipeConsulta> call, Throwable t) {
                        Log.e("Error", "" + t.getMessage());
                        exibirLoading(false);
                    }
                });
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void exibirLoading(boolean exibir) {
        AppCompatButton fipeButtonConsulta = binding.fipeButtonConsulta;
        ProgressBar fipeProgressBar = binding.fipeProgressBar;

        if (exibir == true) {
            fipeButtonConsulta.setVisibility(View.GONE);
            fipeProgressBar.setVisibility(View.VISIBLE);
        } else {
            fipeButtonConsulta.setVisibility(View.VISIBLE);
            fipeProgressBar.setVisibility(View.GONE);
        }
    }

    private void buscarMesesReferencia() {
        Call<List<FipeMesRerefencia>> call = this.fipeService.getTabelaDeReferencia();

        call.enqueue(new Callback<List<FipeMesRerefencia>>() {
            @Override
            public void onResponse(Call<List<FipeMesRerefencia>> call, Response<List<FipeMesRerefencia>> response) {
                if (response.isSuccessful()) {
                    Log.i("FipeService", "Retornou " + response.body().size() + " meses de referencia.");

                    List<FipeMesRerefencia> mesesReferencia = new ArrayList<FipeMesRerefencia>();
                    FipeMesRerefencia mesDefault = new FipeMesRerefencia();
                    mesDefault.setMes("Selecione...");
                    mesesReferencia.add(mesDefault);
                    mesesReferencia.addAll(response.body());

                    SearchableSpinner fipeSpinnerMesReferencia = binding.fipeSpinnerMesReferencia;
                    fipeSpinnerMesReferencia.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, mesesReferencia));

                    fipeSpinnerMesReferencia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Log.i("onItemSelected", "Selecionou o mês de referência na posição " + position + ".");
                            if (position > 0) {
                                mesRerefenciaSelecionado = (FipeMesRerefencia) parent.getItemAtPosition(position);
                                buscarMarcas();
                            } else {
                                mesRerefenciaSelecionado = null;
                                binding.fipeSpinnerMarca.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, new ArrayList<>()));
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                } else {
                    Log.e("Error", "" + response.message());
                    Toast.makeText(getContext().getApplicationContext(), "Erro: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<FipeMesRerefencia>> call, Throwable t) {
                Log.e("Error", "" + t.getMessage());
            }
        });
    }

    private void buscarMarcas() {
        if (mesRerefenciaSelecionado == null) {
            return;
        }

        FipeRequestBody fipeRequestBody = new FipeRequestBody(1, mesRerefenciaSelecionado.getCodigo());

        Call<List<FipeMarca>> call = this.fipeService.getMarcas(fipeRequestBody);

        call.enqueue(new Callback<List<FipeMarca>>() {
            @Override
            public void onResponse(Call<List<FipeMarca>> call, Response<List<FipeMarca>> response) {
                if (response.isSuccessful()) {
                    Log.i("FipeService", "Retornou " + response.body().size() + " marcas.");

                    List<FipeMarca> marcas = new ArrayList<FipeMarca>();
                    FipeMarca marcaDefault = new FipeMarca();
                    marcaDefault.setLabel("Selecione...");
                    marcas.add(marcaDefault);
                    marcas.addAll(response.body());

                    SearchableSpinner fipeSpinnerMarca = binding.fipeSpinnerMarca;
                    fipeSpinnerMarca.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, marcas));

                    fipeSpinnerMarca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Log.i("onItemSelected", "Selecionou a marca na posição " + position + ".");
                            if (position > 0) {
                                marcaSelecionada = (FipeMarca) parent.getItemAtPosition(position);
                                buscarModelos();
                            } else {
                                marcaSelecionada = null;
                                binding.fipeSpinnerModelo.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, new ArrayList<>()));
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                } else {
                    Log.e("Error", "" + response.message());
                    Toast.makeText(getContext().getApplicationContext(), "Erro: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<FipeMarca>> call, Throwable t) {
                Log.e("Error", "" + t.getMessage());
            }
        });
    }

    private void buscarModelos() {
        if (marcaSelecionada == null) {
            return;
        }

        FipeRequestBody fipeRequestBody = new FipeRequestBody(1, mesRerefenciaSelecionado.getCodigo(), marcaSelecionada.getValue());

        Call<FipeResponseModelo> call = this.fipeService.getModelos(fipeRequestBody);

        call.enqueue(new Callback<FipeResponseModelo>() {
            @Override
            public void onResponse(Call<FipeResponseModelo> call, Response<FipeResponseModelo> response) {
                if (response.isSuccessful()) {
                    Log.i("FipeService", "Retornou " + response.body().getModelos().size() + " modelos.");

                    if (response.body().getModelos().size() == 0) {
                        Toast.makeText(getActivity(), "Nenhum modelo foi encontrado.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    List<FipeModelo> modelos = new ArrayList<FipeModelo>();
                    FipeModelo modeloDefault = new FipeModelo();
                    modeloDefault.setLabel("Selecione...");
                    modelos.add(modeloDefault);
                    modelos.addAll(response.body().getModelos());

                    SearchableSpinner fipeSpinnerModelo = binding.fipeSpinnerModelo;
                    fipeSpinnerModelo.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, modelos));

                    fipeSpinnerModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Log.i("onItemSelected", "Selecionou o modelo na posição " + position + ".");
                            if (position > 0) {
                                modeloSelecionado = (FipeModelo) parent.getItemAtPosition(position);
                                buscarAnoModelo();
                            } else {
                                modeloSelecionado = null;
                                binding.fipeSpinnerAno.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, new ArrayList<>()));
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                } else {
                    Log.e("Error", "" + response.message());
                    Toast.makeText(getContext().getApplicationContext(), "Erro: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<FipeResponseModelo> call, Throwable t) {
                Log.e("Error", "" + t.getMessage());
            }
        });
    }

    private void buscarAnoModelo() {
        if (modeloSelecionado == null) {
            return;
        }

        FipeRequestBody fipeRequestBody = new FipeRequestBody(1, mesRerefenciaSelecionado.getCodigo(), marcaSelecionada.getValue(), modeloSelecionado.getValue());

        Call<List<FipeAnoModelo>> call = this.fipeService.getAnoModelo(fipeRequestBody);

        call.enqueue(new Callback<List<FipeAnoModelo>>() {
            @Override
            public void onResponse(Call<List<FipeAnoModelo>> call, Response<List<FipeAnoModelo>> response) {
                if (response.isSuccessful()) {
                    Log.i("FipeService", "Retornou " + response.body().size() + " anos de modelo.");

                    if (response.body().size() == 0) {
                        Toast.makeText(getActivity(), "Nenhum ano de modelo foi encontrado.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    List<FipeAnoModelo> anosModelo = new ArrayList<FipeAnoModelo>();
                    FipeAnoModelo anoModeloDefault = new FipeAnoModelo();
                    anoModeloDefault.setLabel("Selecione...");
                    anosModelo.add(anoModeloDefault);
                    anosModelo.addAll(response.body());

                    SearchableSpinner fipeSpinnerAno = binding.fipeSpinnerAno;
                    fipeSpinnerAno.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, anosModelo));

                    fipeSpinnerAno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Log.i("onItemSelected", "Selecionou o ano de modelo na posição " + position + ".");
                            if (position > 0) {
                                anoModeloSelecionado = (FipeAnoModelo) parent.getItemAtPosition(position);
                                binding.fipeButtonConsulta.setAlpha(1f);
                                binding.fipeButtonConsulta.setEnabled(true);
                            } else {
                                anoModeloSelecionado = null;
                                binding.fipeButtonConsulta.setAlpha(0.5f);
                                binding.fipeButtonConsulta.setEnabled(false);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                } else {
                    Log.e("Error", "" + response.message());
                    Toast.makeText(getContext().getApplicationContext(), "Erro: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<FipeAnoModelo>> call, Throwable t) {
                Log.e("Error", "" + t.getMessage());
            }
        });
    }
}