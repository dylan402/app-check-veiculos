package br.checkveiculos.appcheckveiculos.activities.ui.fipeconsulta;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.checkveiculos.appcheckveiculos.R;
import br.checkveiculos.appcheckveiculos.entidades.FipeConsulta;

public class FipeConsultaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fipe_consulta);

        getSupportActionBar().hide();

        FipeConsulta fipeConsulta = (FipeConsulta) getIntent().getSerializableExtra("consulta");

        TextView consultaTextViewMesReferenciaValor = findViewById(R.id.consultaTextViewMesReferenciaValor);
        TextView consultaTextViewCodigoFipeValor = findViewById(R.id.consultaTextViewCodigoFipeValor);
        TextView consultaTextViewMarcaValor = findViewById(R.id.consultaTextViewMarcaValor);
        TextView consultaTextViewModeloValor = findViewById(R.id.consultaTextViewModeloValor);
        TextView consultaTextViewAnoModeloValor = findViewById(R.id.consultaTextViewAnoModeloValor);
        TextView consultaTextViewDataConsultaValor = findViewById(R.id.consultaTextViewDataConsultaValor);
        TextView consultaTextViewPrecoMedioValor = findViewById(R.id.consultaTextViewPrecoMedioValor);

        consultaTextViewMesReferenciaValor.setText(fipeConsulta.getMesReferencia());
        consultaTextViewCodigoFipeValor.setText(fipeConsulta.getCodigoFipe());
        consultaTextViewMarcaValor.setText(fipeConsulta.getMarca());
        consultaTextViewModeloValor.setText(fipeConsulta.getModelo());
        consultaTextViewAnoModeloValor.setText(fipeConsulta.getAnoModelo());
        consultaTextViewDataConsultaValor.setText(fipeConsulta.getDataConsulta());
        consultaTextViewPrecoMedioValor.setText(fipeConsulta.getValor());
    }
}