package br.checkveiculos.appcheckveiculos.entidades;

public class FipeRequestBody {
    private int codigoTipoVeiculo;
    private int codigoTabelaReferencia;
    private String codigoMarca;
    private String codigoModelo;
    private String anoModelo;
    private String codigoTipoCombustivel;
    private String tipoVeiculo;
    private String tipoConsulta;

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

    public FipeRequestBody(int codigoTipoVeiculo, int codigoTabelaReferencia, String codigoMarca, String codigoModelo, String anoModelo, String codigoTipoCombustivel, String tipoVeiculo, String tipoConsulta) {
        this.codigoTipoVeiculo = codigoTipoVeiculo;
        this.codigoTabelaReferencia = codigoTabelaReferencia;
        this.codigoMarca = codigoMarca;
        this.codigoModelo = codigoModelo;
        this.anoModelo = anoModelo;
        this.codigoTipoCombustivel = codigoTipoCombustivel;
        this.tipoVeiculo = tipoVeiculo;
        this.tipoConsulta = tipoConsulta;
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

    public String getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(String anoModelo) {
        this.anoModelo = anoModelo;
    }

    public String getCodigoTipoCombustivel() {
        return codigoTipoCombustivel;
    }

    public void setCodigoTipoCombustivel(String codigoTipoCombustivel) {
        this.codigoTipoCombustivel = codigoTipoCombustivel;
    }

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }
}
