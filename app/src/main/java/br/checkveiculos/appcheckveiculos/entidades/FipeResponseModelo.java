package br.checkveiculos.appcheckveiculos.entidades;

import java.util.List;

public class FipeResponseModelo {
    private List<FipeAnoModelo> Anos;
    private List<FipeModelo> Modelos;

    public FipeResponseModelo(List<FipeAnoModelo> anos, List<FipeModelo> modelos) {
        Anos = anos;
        Modelos = modelos;
    }

    public List<FipeAnoModelo> getAnos() {
        return Anos;
    }

    public void setAnos(List<FipeAnoModelo> anos) {
        Anos = anos;
    }

    public List<FipeModelo> getModelos() {
        return Modelos;
    }

    public void setModelos(List<FipeModelo> modelos) {
        Modelos = modelos;
    }
}
