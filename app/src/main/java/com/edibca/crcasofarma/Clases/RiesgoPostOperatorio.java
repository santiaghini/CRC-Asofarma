package com.edibca.crcasofarma.Clases;

/**
 * Created by Edibca on 17/11/2015.
 */
public class RiesgoPostOperatorio {
    private boolean bEdad70;
    private boolean bIAM6M;
    private boolean bR3GalopeIY;
    private boolean bEstenosisAortica;
    private boolean bRitmoNoSinusal;
    private boolean bEVMin5;
    private boolean bMalEstado;
    private boolean bCirugiaToraxAbdomenAorta;
    private boolean bCirugiaEmergencia;

    public RiesgoPostOperatorio() {
    }

    public RiesgoPostOperatorio(boolean bEdad70, boolean bIAM6M, boolean bR3GalopeIY, boolean bEstenosisAortica, boolean bRitmoNoSinusal, boolean bEVMin5, boolean bMalEstado, boolean bCirugiaToraxAbdomenAorta, boolean bCirugiaEmergencia) {
        this.bEdad70 = bEdad70;
        this.bIAM6M = bIAM6M;
        this.bR3GalopeIY = bR3GalopeIY;
        this.bEstenosisAortica = bEstenosisAortica;
        this.bRitmoNoSinusal = bRitmoNoSinusal;
        this.bEVMin5 = bEVMin5;
        this.bMalEstado = bMalEstado;
        this.bCirugiaToraxAbdomenAorta = bCirugiaToraxAbdomenAorta;
        this.bCirugiaEmergencia = bCirugiaEmergencia;
    }

    public void setbEdad70(boolean bEdad70) {
        this.bEdad70 = bEdad70;
    }

    public void setbIAM6M(boolean bIAM6M) {
        this.bIAM6M = bIAM6M;
    }

    public void setbR3GalopeIY(boolean bR3GalopeIY) {
        this.bR3GalopeIY = bR3GalopeIY;
    }

    public void setbEstenosisAortica(boolean bEstenosisAortica) {
        this.bEstenosisAortica = bEstenosisAortica;
    }

    public void setbRitmoNoSinusal(boolean bRitmoNoSinusal) {
        this.bRitmoNoSinusal = bRitmoNoSinusal;
    }

    public void setbEVMin5(boolean bEVMin5) {
        this.bEVMin5 = bEVMin5;
    }

    public void setbMalEstado(boolean bMalEstado) {
        this.bMalEstado = bMalEstado;
    }

    public void setbCirugiaToraxAbdomenAorta(boolean bCirugiaToraxAbdomenAorta) {
        this.bCirugiaToraxAbdomenAorta = bCirugiaToraxAbdomenAorta;
    }

    public void setbCirugiaEmergencia(boolean bCirugiaEmergencia) {
        this.bCirugiaEmergencia = bCirugiaEmergencia;
    }

    public double[] calcularRiesgo() {
        int iPuntaje = 0;
        double[] dResultado = new double[3];

        if (this.bEdad70) iPuntaje += 5;
        if (this.bIAM6M) iPuntaje += 10;
        if (this.bR3GalopeIY) iPuntaje += 11;
        if (this.bEstenosisAortica) iPuntaje += 3;
        if (this.bRitmoNoSinusal) iPuntaje += 7;
        if (this.bEVMin5) iPuntaje += 7;
        if (this.bMalEstado) iPuntaje += 3;
        if (this.bCirugiaToraxAbdomenAorta) iPuntaje += 3;
        if (this.bCirugiaEmergencia) iPuntaje += 4;

        if (iPuntaje <= 5) {
            dResultado[0] = 1;
            dResultado[1] = 0.7;
            dResultado[2] = 0.2;
        } else if (iPuntaje >= 6 && iPuntaje <= 12) {
            dResultado[0] = 2;
            dResultado[1] = 5;
            dResultado[2] = 2;
        } else if (iPuntaje >= 13 && iPuntaje <= 25) {
            dResultado[0] = 3;
            dResultado[1] = 11;
            dResultado[2] = 2;
        } else if (iPuntaje >= 26) {
            dResultado[0] = 4;
            dResultado[1] = 22;
            dResultado[2] = 56;
        }

        return dResultado;
    }
}