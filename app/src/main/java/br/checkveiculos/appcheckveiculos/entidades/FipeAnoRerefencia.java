package br.checkveiculos.appcheckveiculos.entidades;

import java.util.Objects;

public class FipeAnoRerefencia {
    private int Codigo;
    private String Mes;

    public FipeAnoRerefencia() {
    }

    @Override
    public String toString() {
        return this.getMes();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FipeAnoRerefencia that = (FipeAnoRerefencia) o;
        return Codigo == that.Codigo &&
                Objects.equals(Mes, that.Mes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Codigo, Mes);
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int codigo) {
        Codigo = codigo;
    }

    public String getMes() {
        return Mes;
    }

    public void setMes(String mes) {
        Mes = mes;
    }
}
