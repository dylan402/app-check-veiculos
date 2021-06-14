package br.checkveiculos.appcheckveiculos.entidades;

public class FipeRequestBody {
    private int codigoTipoVeiculo;
    private int codigoTabelaReferencia;
    private String codigoMarca;
    private String codigoModelo;

    public FipeRequestBody(int codigoTipoVeiculo, int codigoTabelaReferencia) {
        this.codigoTipoVeiculo = codigoTipoVeiculo;
        this.codigoTabelaReferencia = codigoTabelaReferencia;
    }

    public FipeRequestBody(int codigoTipoVeiculo, int codigoTabelaReferencia, String codigoMarca) {
        this.codigoTipoVeiculo = codigoTipoVeiculo;
        this.codigoTabelaReferencia = codigoTabelaReferencia;
        this.codigoMarca = codigoMarca;
    }

    public FipeRequestBody(int codigoTipoVeiculo, int codigoTabelaReferencia, String codigoMarca, String codigoModelo) {
        this.codigoTipoVeiculo = codigoTipoVeiculo;
        this.codigoTabelaReferencia = codigoTabelaReferencia;
        this.codigoMarca = codigoMarca;
        this.codigoModelo = codigoModelo;
    }

    public int getCodigoTipoVeiculo() {
        return codigoTipoVeiculo;
    }

    public void setCodigoTipoVeiculo(int codigoTipoVeiculo) {
        this.codigoTipoVeiculo = codigoTipoVeiculo;
    }

    public int getCodigoTabelaReferencia() {
        return codigoTabelaReferencia;
    }

    public void setCodigoTabelaReferencia(int codigoTabelaReferencia) {
        this.codigoTabelaReferencia = codigoTabelaReferencia;
    }

    public String getCodigoMarca() {
        return codigoMarca;
    }

    public void setCodigoMarca(String codigoMarca) {
        this.codigoMarca = codigoMarca;
    }

    public String getCodigoModelo() {
        return codigoModelo;
    }

    public void setCodigoModelo(String codigoModelo) {
        this.codigoModelo = codigoModelo;
    }
}