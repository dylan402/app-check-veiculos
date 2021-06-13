package br.checkveiculos.appcheckveiculos.activities.ui.veiculos;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

import br.checkveiculos.appcheckveiculos.R;
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

        buscarVeiculos();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
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
                } else {
                    Log.e("", "");
                    Toast.makeText(getContext().getApplicationContext(), "Erro: " + response.message(), Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onFailure(Call<List<Veiculo>> call, Throwable t) {
                Log.e("Error", "" + t.getMessage());
            }
        });
    }
}