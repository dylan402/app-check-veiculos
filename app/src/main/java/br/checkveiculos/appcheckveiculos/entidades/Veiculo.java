package br.checkveiculos.appcheckveiculos.entidades;

import java.io.Serializable;
import java.util.Objects;

public class Veiculo implements Serializable {
    String id;
    String idCliente;
    String marca;
    String modelo;
    String ano;
    String placa;

    public Veiculo() {
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "id='" + id + '\'' +
                ", idCliente='" + idCliente + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", ano='" + ano + '\'' +
                ", placa='" + placa + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return Objects.equals(id, veiculo.id) &&
                Objects.equals(idCliente, veiculo.idCliente) &&
                Objects.equals(marca, veiculo.marca) &&
                Objects.equals(modelo, veiculo.modelo) &&
                Objects.equals(ano, veiculo.ano) &&
                Objects.equals(placa, veiculo.placa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idCliente, marca, modelo, ano, placa);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
