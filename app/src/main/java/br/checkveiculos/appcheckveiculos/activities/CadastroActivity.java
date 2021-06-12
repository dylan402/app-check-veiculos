package br.checkveiculos.appcheckveiculos.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import br.checkveiculos.appcheckveiculos.R;

public class CadastroActivity extends AppCompatActivity {
    private AppCompatButton buttonCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        getSupportActionBar().hide();
        this.iniciarComponentes();
        this.iniciarListeners();
    }

    private void iniciarComponentes() {
        this.buttonCadastrar = findViewById(R.id.buttonCadastrar);
    }

    private void iniciarListeners() {
        this.buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CadastroActivity.this, "Teste click!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}