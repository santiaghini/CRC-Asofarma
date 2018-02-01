package com.edibca.crcasofarma.Clases;

/**
 * Created by Edibca on 17/11/2015.
 */
public class IMC {
    private Double iEstatura;
    private Double iPeso;

    public IMC() {
    }

    public IMC(Double iEstatura, Double iPeso) {
        this.iEstatura = iEstatura;
        this.iPeso = iPeso;
    }

    public void setiEstatura(Double iEstatura) {
        this.iEstatura = iEstatura;
    }

    public void setiPeso(Double iPeso) {
        this.iPeso = iPeso;
    }

    public Double calcularIMC() {
        double dIMC = 0;
        dIMC = this.iPeso / Math.pow(this.iEstatura, 2);
        return dIMC;
    }
}
