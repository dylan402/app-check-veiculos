package br.checkveiculos.appcheckveiculos.entidades;

public class FipeConsultarModelo {
    private int codigoTipoVeiculo;
    private int codigoTabelaReferencia;
    private String codigoMarca;

    public FipeConsultarModelo(int codigoTipoVeiculo, int codigoTabelaReferencia, String codigoMarca) {
        this.codigoTipoVeiculo = codigoTipoVeiculo;
        this.codigoTabelaReferencia = codigoTabelaReferencia;
        this.codigoMarca = codigoMarca;
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
}
