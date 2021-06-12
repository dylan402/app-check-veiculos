package br.checkveiculos.appcheckveiculos.api;

import java.util.List;

import br.checkveiculos.appcheckveiculos.entidades.Veiculo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface VeiculoService {
    @Headers({
            "Accept: application/json",
            "User-Agent: AppCheckVeiculos"
    })
    @GET("veiculo")
    Call<List<Veiculo>> getVeiculos();

    @Headers({
            "Accept: application/json",
            "User-Agent: AppCheckVeiculos"
    })
    @GET("veiculo/{id}")
    Call<Veiculo> getVeiculo(@Path("id") String id);

    @Headers({
            "Accept: application/json",
            "User-Agent: AppCheckVeiculos"
    })
    @POST("veiculo")
    Call<Veiculo> criarVeiculo(@Body Veiculo veiculo);

    @Headers({
            "Accept: application/json",
            "User-Agent: AppCheckVeiculos"
    })
    @PUT("veiculo/{id}")
    Call<Veiculo> atualizarVeiculo(@Path("id") String id, @Body Veiculo veiculo);

    @Headers({
            "Accept: application/json",
            "User-Agent: AppCheckVeiculos"
    })
    @DELETE("veiculo/{id}")
    Call<Boolean> deletarVeiculo(@Path("id") String id);
}
