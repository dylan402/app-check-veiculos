package br.checkveiculos.appcheckveiculos.activities.ui.veiculos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import java.util.ArrayList;
import java.util.List;

import br.checkveiculos.appcheckveiculos.R;
import br.checkveiculos.appcheckveiculos.activities.FormVeiculoActivity;
import br.checkveiculos.appcheckveiculos.api.RestServiceGenerator;
import br.checkveiculos.appcheckveiculos.api.VeiculoService;
import br.checkveiculos.appcheckveiculos.databinding.FragmentVeiculosBinding;
import br.checkveiculos.appcheckveiculos.entidades.Veiculo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VeiculosFragment extends Fragment {

    private VeiculosViewModel veiculosViewModel;
    private FragmentVeiculosBinding binding;
    private VeiculoService veiculoService;
    private SharedPreferences sharedPreferences;

    private List<Veiculo> veiculos = new ArrayList<Veiculo>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        veiculosViewModel =
                new ViewModelProvider(this).get(VeiculosViewModel.class);

        binding = FragmentVeiculosBinding.inflate(inflater, container, false);
        this.veiculoService = RestServiceGenerator.createServiceVeiculo(VeiculoService.class);
        this.sharedPreferences = this.getActivity().getSharedPreferences("ClienteData", Context.MODE_PRIVATE);

        View root = binding.getRoot();
        View view = inflater.inflate(R.layout.fragment_veiculos, container, false);

        this.iniciarListeners();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        buscarVeiculos();
    }

    private void iniciarListeners() {
        binding.floatingCadastrarVeiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), FormVeiculoActivity.class));
            }
        });
    }

    private void buscarVeiculos() {
        Call<List<Veiculo>> call = this.veiculoService.getVeiculos(this.sharedPreferences.getString("id", ""));

        call.enqueue(new Callback<List<Veiculo>>() {
            @Override
            public void onResponse(Call<List<Veiculo>> call, Response<List<Veiculo>> response) {
                if (response.isSuccessful()) {
                    Log.i("VeiculoService", "Retornou " + response.body().size() + " veículos.");

                    veiculos = response.body();

                    ListView listViewVeiculos = binding.listViewVeiculos;

                    VeiculosAdapter veiculosAdapter = new VeiculosAdapter(getActivity(), veiculos);
                    listViewVeiculos.setAdapter(veiculosAdapter);

                    listViewVeiculos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Log.i("ListViewVeiculos", "Selecionou o objeto de posição " + position);
                            Veiculo veiculoSelecionado = veiculos.get(position);
                            Intent intent = new Intent(getActivity(), FormVeiculoActivity.class);
                            intent.putExtra("veiculo", veiculoSelecionado);
                            startActivity(intent);
                        }
                    });

                    listViewVeiculos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                            Log.i("ListViewVeiculos", "Selecionou com clique longo o objeto de posição " + position);
                            Veiculo veiculoSelecionado = (Veiculo) parent.getAdapter().getItem(position);

                            new AlertDialog.Builder(parent.getContext())
                                    .setTitle("Removendo veículo")
                                    .setMessage("Tem certeza que deseja remover o veículo de placa " + veiculoSelecionado.getPlaca() + "?")
                                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            deletarVeiculo(veiculoSelecionado);
                                        }
                                    })
                                    .setNegativeButton("Não", null).show();
                            return true;
                        }
                    });
                } else {
                    Log.e("Error", "" + response.message());
                    Toast.makeText(getContext().getApplicationContext(), "Erro: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Veiculo>> call, Throwable t) {
                Log.e("Error", "" + t.getMessage());
            }
        });
    }

    private void deletarVeiculo(Veiculo veiculo) {
        Call<Boolean> call = this.veiculoService.deletarVeiculo(veiculo.getId());

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    Log.i("VeiculoService", "Veículo removido com sucesso.");
                    Toast.makeText(getContext().getApplicationContext(), "Veículo de placa " + veiculo.getPlaca() + " foi removido com sucesso.", Toast.LENGTH_SHORT).show();
                    onResume();
                } else {
                    Log.e("Error", "" + response.message());
                    Toast.makeText(getContext().getApplicationContext(), "Erro: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.e("Error", "" + t.getMessage());
            }
        });
    }
}