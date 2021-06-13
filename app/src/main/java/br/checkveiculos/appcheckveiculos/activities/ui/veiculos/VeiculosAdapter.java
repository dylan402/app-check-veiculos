package br.checkveiculos.appcheckveiculos.activities.ui.veiculos;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.checkveiculos.appcheckveiculos.R;
import br.checkveiculos.appcheckveiculos.entidades.Veiculo;

public class VeiculosAdapter extends ArrayAdapter<Veiculo> {
    private Context context;
    private List<Veiculo> veiculos;

    public VeiculosAdapter(Context context, List<Veiculo> veiculos) {
        super(context, 0, veiculos);
        this.context = context;
        this.veiculos = veiculos;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) view = LayoutInflater.from(context).inflate(R.layout.item_veiculo_list, null);

        Veiculo veiculo = veiculos.get(position);
        TextView veiculoMarca = view.findViewById(R.id.text_veiculo_list);
        veiculoMarca.setText(veiculo.getMarca() + " / " + veiculo.getModelo() + " / " + veiculo.getPlaca());

        return view;
    }
}
