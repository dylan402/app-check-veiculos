package br.checkveiculos.appcheckveiculos.api;

import java.util.List;

import br.checkveiculos.appcheckveiculos.entidades.FipeAnoRerefencia;
import br.checkveiculos.appcheckveiculos.entidades.FipeConsultarMarcas;
import br.checkveiculos.appcheckveiculos.entidades.FipeMarca;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface FipeService {
    @Headers({
            "Accept: application/json",
            "User-Agent: AppCheckVeiculos"
    })
    @POST("veiculos/ConsultarTabelaDeReferencia")
    Call<List<FipeAnoRerefencia>> getTabelaDeReferencia();

    @Headers({
            "Accept: application/json",
            "User-Agent: AppCheckVeiculos"
    })
    @POST("veiculos/ConsultarMarcas")
    Call<List<FipeMarca>> getMarcas(@Body FipeConsultarMarcas fipeConsultarMarcas);
}
