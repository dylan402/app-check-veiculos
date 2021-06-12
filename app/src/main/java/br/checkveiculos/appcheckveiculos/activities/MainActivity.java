package br.checkveiculos.appcheckveiculos.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.checkveiculos.appcheckveiculos.R;
import br.checkveiculos.appcheckveiculos.api.ClienteService;
import br.checkveiculos.appcheckveiculos.api.RestServiceGenerator;
import br.checkveiculos.appcheckveiculos.entidades.Cliente;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {
    private ClienteService service = null;
    private final MainActivity mainActivity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        service = RestServiceGenerator.createService(ClienteService.class);
        buscarClientes();
    }

    public void buscarClientes() {
        Call<List<Cliente>> call = this.service.getClientes();

        call.enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                if (response.isSuccessful()) {
                    Log.i("ClienteService", "Retornou " + response.body().size() + " clientes.");

                    List<String> nomes = new ArrayList<String>();

                    for (Cliente cliente: response.body()) {
                        nomes.add(cliente.getNome());
                    }

                    ListView listView = findViewById(R.id.listViewListaClientes);
                    listView.setAdapter(new ArrayAdapter<String>(mainActivity, android.R.layout.simple_list_item_1, nomes));
                } else {
                    Log.e("ClienteService", "" + response.message());
                    Toast.makeText(getApplicationContext(), "Erro: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Cliente>> call, Throwable t) {
                Log.e("Error", "" + t.getMessage());
            }
        });
    }
}