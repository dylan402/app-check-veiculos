package br.checkveiculos.appcheckveiculos.api;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestServiceGenerator {
    private static final String API_BASE_URL_CLIENTE = "http://10.0.2.2:8081/cliente-api/";
    private static final String API_BASE_URL_VEICULO = "http://10.0.2.2:8080/veiculo-api/";
    //private static final String API_BASE_URL_CLIENTE = "http://load-balancer-backend-cliente-1389281431.us-east-2.elb.amazonaws.com/cliente-api/";
    //private static final String API_BASE_URL_VEICULO = "http://load-balancer-backend-cliente-1389281431.us-east-2.elb.amazonaws.com/veiculo-api/";
    private static final String API_BASE_URL_FIPE = "https://veiculos.fipe.org.br/api/";

    public static <S> S createServiceCliente(Class<S> serviceClass) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL_CLIENTE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Log.i("RestServiceGenerator", "Criada a conexão com a api rest.");
        return retrofit.create(serviceClass);
    }

    public static <S> S createServiceVeiculo(Class<S> serviceClass) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL_VEICULO)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Log.i("RestServiceGenerator", "Criada a conexão com a api rest.");
        return retrofit.create(serviceClass);
    }

    public static <S> S createServiceFipe(Class<S> serviceClass) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL_FIPE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Log.i("RestServiceGenerator", "Criada a conexão com a api rest.");
        return retrofit.create(serviceClass);
    }
}
