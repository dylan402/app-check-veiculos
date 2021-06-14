package br.checkveiculos.appcheckveiculos.entidades;

public class FipeModelo {
    private String Label;
    private int Value;

    public FipeModelo() {
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public int getValue() {
        return Value;
    }

    public void setValue(int value) {
        Value = value;
    }

    @Override
    public String toString() {
        return this.getLabel();
    }
}
