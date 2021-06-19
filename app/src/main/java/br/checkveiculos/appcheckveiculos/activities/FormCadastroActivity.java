package br.checkveiculos.appcheckveiculos.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import br.checkveiculos.appcheckveiculos.R;
import br.checkveiculos.appcheckveiculos.api.ClienteService;
import br.checkveiculos.appcheckveiculos.api.RestServiceGenerator;
import br.checkveiculos.appcheckveiculos.entidades.Cliente;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormCadastroActivity extends AppCompatActivity {
    private ClienteService clienteService;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);

        getSupportActionBar().hide();

        this.clienteService = RestServiceGenerator.createServiceCliente(ClienteService.class);
        this.sharedPreferences = getSharedPreferences("ClienteData", Context.MODE_PRIVATE);

        this.iniciarListeners();
    }

    private void iniciarListeners() {
        AppCompatButton buttonCadastrar = findViewById(R.id.clienteButtonCadastrar);
        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cliente cliente = recuperaInformacoesFormulario();
                criarCliente(cliente);
            }
        });
    }

    private Cliente recuperaInformacoesFormulario() {
        Cliente cliente = new Cliente();

        EditText nome = findViewById(R.id.clienteEditTextNome);
        EditText email = findViewById(R.id.clienteEditTextEmail);
        EditText senha = findViewById(R.id.clienteEditTextSenha);

        cliente.setNome(nome.getText().toString());
        cliente.setEmail(email.getText().toString());
        cliente.setSenha(senha.getText().toString());

        return cliente;
    }

    private boolean validarFormulario(Cliente cliente) {
        boolean valido = true;

        EditText clienteEditTextNome = findViewById(R.id.clienteEditTextNome);
        EditText clienteEditTextEmail = findViewById(R.id.clienteEditTextEmail);
        EditText clienteEditTextSenha = findViewById(R.id.clienteEditTextSenha);

        GradientDrawable clienteGradientNome = (GradientDrawable) clienteEditTextNome.getBackground();
        GradientDrawable clienteGradientEmail = (GradientDrawable) clienteEditTextEmail.getBackground();
        GradientDrawable clienteGradientSenha = (GradientDrawable) clienteEditTextSenha.getBackground();

        if (cliente.getNome() == null || cliente.getNome().trim().length() == 0) {
            clienteGradientNome.setStroke(6, Color.RED);
            valido = false;
        } else {
            clienteGradientNome.setStroke(6, Color.BLACK);
        }

        if (cliente.getEmail() == null || cliente.getEmail().trim().length() == 0) {
            clienteGradientEmail.setStroke(6, Color.RED);
            valido = false;
        } else {
            clienteGradientEmail.setStroke(6, Color.BLACK);
        }

        if (cliente.getSenha() == null || cliente.getSenha().trim().length() == 0) {
            clienteGradientSenha.setStroke(6, Color.RED);
            valido = false;
        } else {
            clienteGradientSenha.setStroke(6, Color.BLACK);
        }

        return valido;
    }

    private void exibirLoading(boolean exibir) {
        ProgressBar clienteProgressBar = findViewById(R.id.clienteProgressBar);
        AppCompatButton clienteButtonCadastrar = findViewById(R.id.clienteButtonCadastrar);

        if (exibir) {
            clienteButtonCadastrar.setVisibility(View.GONE);
            clienteProgressBar.setVisibility(View.VISIBLE);
        } else {
            clienteProgressBar.setVisibility(View.GONE);
            clienteButtonCadastrar.setVisibility(View.VISIBLE);
        }
    }

    private void criarCliente(Cliente cliente) {
        if (!this.validarFormulario(cliente)) {
            Toast.makeText(getApplicationContext(), "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        Call<Cliente> call = this.clienteService.criarCliente(cliente);
        this.exibirLoading(true);

        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                if (response.isSuccessful()) {
                    Log.i("ClienteService", "Cliente criado com sucesso.");

                    Toast.makeText(getApplicationContext(), "Cadastro concluído com sucesso.", Toast.LENGTH_LONG).show();

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("id", response.body().getId());
                    editor.putString("nome", response.body().getNome());
                    editor.putString("email", response.body().getEmail());
                    editor.commit();

                    Intent intent = new Intent(FormCadastroActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    exibirLoading(false);
                } else {
                    exibirLoading(false);
                    Log.e("ClienteService", "" + response.toString());
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