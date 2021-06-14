package br.checkveiculos.appcheckveiculos.activities.ui.fipeconsulta;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FipeConsultaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FipeConsultaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}