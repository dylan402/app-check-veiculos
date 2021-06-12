package br.checkveiculos.appcheckveiculos.api;

import java.util.List;

import br.checkveiculos.appcheckveiculos.entidades.Cliente;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ClienteService {
    @Headers({
            "Accept: application/json",
            "User-Agent: AppCheckVeiculos"
    })
    @GET("cliente")
    Call<List<Cliente>> getClientes();

    @Headers({
            "Accept: application/json",
            "User-Agent: AppCheckVeiculos"
    })
    @GET("cliente/{id}")
    Call<Cliente> getCliente(@Path("id") String id);

    @Headers({
            "Accept: application/json",
            "User-Agent: AppCheckVeiculos"
    })
    @POST("cliente")
    Call<Cliente> criarCliente(@Body Cliente cliente);

    @Headers({
            "Accept: application/json",
            "User-Agent: AppCheckVeiculos"
    })
    @PUT("cliente/{id}")
    Call<Cliente> atualizarCliente(@Path("id") String id, @Body Cliente Cliente);

    @Headers({
            "Accept: application/json",
            "User-Agent: AppCheckVeiculos"
    })
    @DELETE("cliente/{id}")
    Call<Boolean> deletarCliente(@Path("id") String id);

    @Headers({
            "Accept: application/json",
            "User-Agent: AppCheckVeiculos"
    })
    @POST("cliente/login")
    Call<Cliente> login(@Body Cliente cliente);
}
