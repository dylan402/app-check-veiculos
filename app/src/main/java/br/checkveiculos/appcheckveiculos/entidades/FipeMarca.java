package br.checkveiculos.appcheckveiculos.entidades;

import java.util.Objects;

public class FipeMarca {
    private String Label;
    private String Value;

    public FipeMarca() {
    }

    @Override
    public String toString() {
        return this.getLabel();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FipeMarca that = (FipeMarca) o;
        return Objects.equals(Label, that.Label) &&
                Objects.equals(Value, that.Value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Label, Value);
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
}
