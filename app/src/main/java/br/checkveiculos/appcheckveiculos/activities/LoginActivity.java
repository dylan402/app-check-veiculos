package br.checkveiculos.appcheckveiculos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import br.checkveiculos.appcheckveiculos.R;

public class LoginActivity extends AppCompatActivity {

    private TextView textViewCadastro ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();
        this.iniciarComponentes();
        this.iniciarListeners();
    }

    private void iniciarComponentes() {
        this.textViewCadastro = findViewById(R.id.textViewCadastro);
    }

    private void iniciarListeners() {
        this.textViewCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });
    }
}