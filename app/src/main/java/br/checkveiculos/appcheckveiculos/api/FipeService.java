package br.checkveiculos.appcheckveiculos.api;

import java.util.List;

import br.checkveiculos.appcheckveiculos.entidades.FipeAnoModelo;
import br.checkveiculos.appcheckveiculos.entidades.FipeMarca;
import br.checkveiculos.appcheckveiculos.entidades.FipeMesRerefencia;
import br.checkveiculos.appcheckveiculos.entidades.FipeRequestBody;
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
    Call<List<FipeMarca>> getMarcas(@Body FipeRequestBody fipeRequestBody);

    @Headers({
            "Accept: application/json",
            "User-Agent: AppCheckVeiculos"
    })
    @POST("veiculos/ConsultarModelos")
    Call<FipeResponseModelo> getModelos(@Body FipeRequestBody fipeRequestBody);

    @Headers({
            "Accept: application/json",
            "User-Agent: AppCheckVeiculos"
    })
    @POST("veiculos/ConsultarAnoModelo")
    Call<List<FipeAnoModelo>> getAnoModelo(@Body FipeRequestBody fipeRequestBody);
}
