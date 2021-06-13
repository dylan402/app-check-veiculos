package br.checkveiculos.appcheckveiculos.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.checkveiculos.appcheckveiculos.R;
import br.checkveiculos.appcheckveiculos.api.RestServiceGenerator;
import br.checkveiculos.appcheckveiculos.api.VeiculoService;
import br.checkveiculos.appcheckveiculos.entidades.Veiculo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormVeiculoActivity extends AppCompatActivity {

    private VeiculoService veiculoService;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_veiculo);

        getSupportActionBar().hide();

        this.veiculoService = RestServiceGenerator.createServiceVeiculo(VeiculoService.class);
        this.sharedPreferences = getApplicationContext().getSharedPreferences("ClienteData", Context.MODE_PRIVATE);

        this.iniciarListeners();
    }

    private void iniciarListeners() {
        AppCompatButton buttonCadastrarVeiculo = findViewById(R.id.buttonCadastrarVeiculo);
        buttonCadastrarVeiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criarVeiculo();
            }
        });
    }

    private Veiculo recuperaInformacoesFormulario () {
        Veiculo veiculo = new Veiculo();

        EditText marca = findViewById(R.id.editTextMarca);
        EditText modelo = findViewById(R.id.editTextModelo);
        EditText ano = findViewById(R.id.editTextAno);
        EditText placa = findViewById(R.id.editTextPlaca);

        this.sharedPreferences = this.getSharedPreferences("ClienteData", Context.MODE_PRIVATE);
        String idCliente = this.sharedPreferences.getString("id", "");

        veiculo.setIdCliente(idCliente);
        veiculo.setMarca(marca.getText().toString());
        veiculo.setModelo(modelo.getText().toString());
        veiculo.setAno(ano.getText().toString());
        veiculo.setPlaca(placa.getText().toString());

        return veiculo;
    }

    private boolean validarFormulario(Veiculo veiculo) {
        boolean valido = true;

        if (veiculo.getMarca() == null || veiculo.getMarca().trim().length() == 0) {
            valido = false;
            Toast.makeText(this, "Não foi possível identificar o usuário.", Toast.LENGTH_SHORT).show();
        }

        if (veiculo.getMarca() == null || veiculo.getMarca().trim().length() == 0) {
            valido = false;
        }

        if (veiculo.getModelo() == null || veiculo.getModelo().trim().length() == 0) {
            valido = false;
        }

        if (veiculo.getAno() == null || veiculo.getAno().trim().length() == 0) {
            valido = false;
        }

        if (veiculo.getPlaca() == null || veiculo.getPlaca().trim().length() == 0) {
            valido = false;
        }

        return valido;
    }

    private void criarVeiculo() {
        Veiculo veiculo = this.recuperaInformacoesFormulario();

        if (!this.validarFormulario(veiculo)) {
            Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        Call<Veiculo> call = this.veiculoService.criarVeiculo(veiculo);

        call.enqueue(new Callback<Veiculo>() {
            @Override
            public void onResponse(Call<Veiculo> call, Response<Veiculo> response) {
                if (response.isSuccessful()) {
                    Log.i("VeiculoService", "Veículo criado com sucesso.");
                    Toast.makeText(getApplicationContext(), "Veículo criado com sucesso.", Toast.LENGTH_SHORT).show();

                    finish();
                } else {
                    Log.e("VeiculoService", "Erro: " + response.message());
                    Toast.makeText(getApplicationContext(), "Erro:" + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Veiculo> call, Throwable t) {
                Log.e("Error", "" + t.getMessage());
            }
        });
    }
}
