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
import retrofit2.http.Query;

public interface VeiculoService {
    @Headers({
            "Accept: application/json",
            "User-Agent: AppCheckVeiculos"
    })
    @GET("/veiculo-api/veiculo")
    Call<List<Veiculo>> getVeiculos(@Query("idCliente") String idCliente);

    @Headers({
            "Accept: application/json",
            "User-Agent: AppCheckVeiculos"
    })
    @GET("/veiculo-api/veiculo/{id}")
    Call<Veiculo> getVeiculo(@Path("id") String id);

    @Headers({
            "Accept: application/json",
            "User-Agent: AppCheckVeiculos"
    })
    @POST("/veiculo-api/veiculo")
    Call<Veiculo> criarVeiculo(@Body Veiculo veiculo);

    @Headers({
            "Accept: application/json",
            "User-Agent: AppCheckVeiculos"
    })
    @PUT("/veiculo-api/veiculo/{id}")
    Call<Veiculo> atualizarVeiculo(@Path("id") String id, @Body Veiculo veiculo);

    @Headers({
            "Accept: application/json",
            "User-Agent: AppCheckVeiculos"
    })
    @DELETE("/veiculo-api/veiculo/{id}")
    Call<Boolean> deletarVeiculo(@Path("id") String id);
}
