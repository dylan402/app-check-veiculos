package br.checkveiculos.appcheckveiculos.api;

import java.util.List;

import br.checkveiculos.appcheckveiculos.entidades.Cliente;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ClienteService {
    @Headers({
            "Accept: application/json",
            "User-Agent: AppCheckVeiculos"
    })
    @GET("cliente")
    Call<List<Cliente>> getClientes();
}
