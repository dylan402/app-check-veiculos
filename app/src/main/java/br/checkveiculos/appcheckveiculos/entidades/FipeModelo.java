package br.checkveiculos.appcheckveiculos.entidades;

public class FipeModelo {
    private String Label;
    private String Value;

    public FipeModelo() {
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    @Override
    public String toString() {
        return this.getLabel();
    }
}
