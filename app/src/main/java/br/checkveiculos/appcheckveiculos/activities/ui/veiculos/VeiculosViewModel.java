package br.checkveiculos.appcheckveiculos.activities.ui.veiculos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VeiculosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public VeiculosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}