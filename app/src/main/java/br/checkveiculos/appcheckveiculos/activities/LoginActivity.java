package br.checkveiculos.appcheckveiculos.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import br.checkveiculos.appcheckveiculos.R;
import br.checkveiculos.appcheckveiculos.api.ClienteService;
import br.checkveiculos.appcheckveiculos.api.RestServiceGenerator;
import br.checkveiculos.appcheckveiculos.entidades.Cliente;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ClienteService clienteService;
    private SharedPreferences sharedPreferences;
    private TextView textViewCadastro;
    private AppCompatButton buttonEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        this.clienteService = RestServiceGenerator.createServiceCliente(ClienteService.class);
        this.sharedPreferences = getSharedPreferences("ClienteData", Context.MODE_PRIVATE);

        this.iniciarComponentes();
        this.iniciarListeners();
    }

    private void iniciarComponentes() {
        this.textViewCadastro = findViewById(R.id.loginTextViewCadastro);
        this.buttonEntrar = findViewById(R.id.loginButtonEntrar);
    }

    private void iniciarListeners() {
        this.textViewCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, FormCadastroActivity.class));
            }
        });

        this.buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private Cliente recuperaInformacoesFormulario () {
        Cliente cliente = new Cliente();

        EditText email = findViewById(R.id.loginEditTextEmail);
        EditText senha = findViewById(R.id.loginEditTextSenha);

        cliente.setEmail(email.getText().toString());
        cliente.setSenha(senha.getText().toString());

        return cliente;
    }

    private boolean validarFormulario(Cliente cliente) {
        boolean valido = true;

        if (cliente.getEmail() == null || cliente.getEmail().trim().length() == 0) {
            valido = false;
        }

        if (cliente.getSenha() == null || cliente.getSenha().trim().length() == 0) {
            valido = false;
        }

        return valido;
    }

    private void exibirLoading(boolean exibir) {
        AppCompatButton loginButtonEntrar = findViewById(R.id.loginButtonEntrar);
        ProgressBar loginProgressBar = findViewById(R.id.loginProgressBar);

        if (exibir == true) {
            loginButtonEntrar.setVisibility(View.GONE);
            loginProgressBar.setVisibility(View.VISIBLE);
        } else {
            loginButtonEntrar.setVisibility(View.VISIBLE);
            loginProgressBar.setVisibility(View.GONE);
        }
    }

    private void login() {
        Cliente cliente = this.recuperaInformacoesFormulario();

        if (!this.validarFormulario(cliente)) {
            Toast.makeText(getApplicationContext(), "Preencha com os seus dados de acesso.", Toast.LENGTH_SHORT).show();
            return;
        }

        Call<Cliente> call = this.clienteService.login(cliente);
        this.exibirLoading(true);

        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                if (response.isSuccessful()) {
                    Log.i("ClienteService", "Cliente logado com sucesso.");
                    Log.i("teste", "" + response.body().toString());

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("id", response.body().getId());
                    editor.commit();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    exibirLoading(false);
                } else {
                    exibirLoading(false);
                    Log.e("ClienteService", "" + response.message());
                    Toast.makeText(getApplicationContext(), "Erro: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                exibirLoading(false);
                Log.e("Error", "" + t.getMessage());
            }
        });
    }
}