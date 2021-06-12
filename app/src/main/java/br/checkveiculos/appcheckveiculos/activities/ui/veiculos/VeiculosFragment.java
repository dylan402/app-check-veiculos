package br.checkveiculos.appcheckveiculos.activities.ui.veiculos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import br.checkveiculos.appcheckveiculos.databinding.FragmentVeiculosBinding;

public class VeiculosFragment extends Fragment {

    private VeiculosViewModel veiculosViewModel;
    private FragmentVeiculosBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        veiculosViewModel =
                new ViewModelProvider(this).get(VeiculosViewModel.class);

        binding = FragmentVeiculosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textVeiculos;
        veiculosViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}