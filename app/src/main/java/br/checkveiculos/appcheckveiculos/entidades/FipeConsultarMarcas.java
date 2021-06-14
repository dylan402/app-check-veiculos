package br.checkveiculos.appcheckveiculos.entidades;

public class FipeConsultarMarcas {
    private int codigoTipoVeiculo;
    private int codigoTabelaReferencia;

    public FipeConsultarMarcas(int codigoTipoVeiculo, int codigoTabelaReferencia) {
        this.codigoTipoVeiculo = codigoTipoVeiculo;
        this.codigoTabelaReferencia = codigoTabelaReferencia;
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
}
