package br.checkveiculos.appcheckveiculos.entidades;

import java.io.Serializable;

public class FipeConsulta implements Serializable {
    private String MesReferencia;
    private String CodigoFipe;
    private String Marca;
    private String Modelo;
    private String AnoModelo;
    private String DataConsulta;
    private String Valor;

    public FipeConsulta() {
    }

    public String getMesReferencia() {
        return MesReferencia;
    }

    public void setMesReferencia(String mesReferencia) {
        MesReferencia = mesReferencia;
    }

    public String getCodigoFipe() {
        return CodigoFipe;
    }

    public void setCodigoFipe(String codigoFipe) {
        CodigoFipe = codigoFipe;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public String getAnoModelo() {
        return AnoModelo;
    }

    public void setAnoModelo(String anoModelo) {
        AnoModelo = anoModelo;
    }

    public String getDataConsulta() {
        return DataConsulta;
    }

    public void setDataConsulta(String dataConsulta) {
        DataConsulta = dataConsulta;
    }

    public String getValor() {
        return Valor;
    }

    public void setValor(String valor) {
        Valor = valor;
    }

    @Override
    public String toString() {
        return "FipeConsulta{" +
                "MesReferencia='" + MesReferencia + '\'' +
                ", CodigoFipe='" + CodigoFipe + '\'' +
                ", Marca='" + Marca + '\'' +
                ", Modelo='" + Modelo + '\'' +
                ", AnoModelo='" + AnoModelo + '\'' +
                ", DataConsulta='" + DataConsulta + '\'' +
                ", Valor='" + Valor + '\'' +
                '}';
    }
}
