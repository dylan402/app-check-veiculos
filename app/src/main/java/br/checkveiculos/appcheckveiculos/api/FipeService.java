package br.checkveiculos.appcheckveiculos.api;

import java.util.List;

import br.checkveiculos.appcheckveiculos.entidades.FipeConsultarMarcas;
import br.checkveiculos.appcheckveiculos.entidades.FipeConsultarModelo;
import br.checkveiculos.appcheckveiculos.entidades.FipeMarca;
import br.checkveiculos.appcheckveiculos.entidades.FipeMesRerefencia;
import br.checkveiculos.appcheckveiculos.entidades.FipeResponseModelo;
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
    Call<List<FipeMesRerefencia>> getTabelaDeReferencia();

    @Headers({
            "Accept: application/json",
            "User-Agent: AppCheckVeiculos"
    })
    @POST("veiculos/ConsultarMarcas")
    Call<List<FipeMarca>> getMarcas(@Body FipeConsultarMarcas fipeConsultarMarcas);

    @Headers({
            "Accept: application/json",
            "User-Agent: AppCheckVeiculos"
    })
    @POST("veiculos/ConsultarModelos")
    Call<FipeResponseModelo> getModelos(@Body FipeConsultarModelo fipeConsultarModelo);
}
