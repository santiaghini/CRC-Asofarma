package com.edibca.crcasofarma.Clases;

import android.util.Log;

/**
 * Created by Edibca on 12/11/2015.
 */
public class SindromeMetabolico {
    private Double sGenero;
    private Double iCircunferencia;
    private Double dTrigliceridos;
    private Double iHDL;
    private Double iPresionSistolica;
    private Double iPresionDiastolica;
    private Double bTratamientoHA;
    private Double dGlucosa;

    /*
    private int iEdad;
    private boolean bFumador;
    private int iColesterolTotal;*/

    public SindromeMetabolico() {
    }

    public SindromeMetabolico(Double sGenero, Double iCircunferencia, Double dTrigliceridos, Double iHDL, Double iPresionSistolica, Double iPresionDiastolica, Double bTratamientoHA, Double dGlucosa) {
        this.sGenero = sGenero;
        this.iCircunferencia = iCircunferencia;
        this.dTrigliceridos = dTrigliceridos;
        this.iHDL = iHDL;
        this.iPresionSistolica = iPresionSistolica;
        this.iPresionDiastolica = iPresionDiastolica;
        this.bTratamientoHA = bTratamientoHA;
        this.dGlucosa = dGlucosa;
    }

    public void setiCircunferencia(Double iCircunferencia) {
        this.iCircunferencia = iCircunferencia;
    }

    public void setdTrigliceridos(Double dTrigliceridos) {
        this.dTrigliceridos = dTrigliceridos;
    }

    public void setiHDL(Double iHDL) {
        this.iHDL = iHDL;
    }

    public void setiPresionSistolica(Double iPresionSistolica) {
        this.iPresionSistolica = iPresionSistolica;
    }

    public void setiPresionDiastolica(Double iPresionDiastolica) {
        this.iPresionDiastolica = iPresionDiastolica;
    }

    public void setbTratamientoHA(Double bTratamientoHA) {
        this.bTratamientoHA = bTratamientoHA;
    }

    public void setdGlucosa(Double dGlucosa) {
        this.dGlucosa = dGlucosa;
    }

    public boolean calcular() {
        boolean bSindrome = false;
        int iNumCriterios = 0;

        //Hombres
        if (this.sGenero == 0.0) {
            if (this.iCircunferencia >= 90) {
                iNumCriterios++;
            }
            if (this.dTrigliceridos >= 150) {
                iNumCriterios++;
            }
            if (this.iHDL < 40) {
                iNumCriterios++;
            }
            if (this.bTratamientoHA == 1.0) {
                iNumCriterios++;
            }
            else{
                if (this.iPresionSistolica >= 130) {
                    iNumCriterios++;
                }
                if (this.iPresionDiastolica >= 85) {
                    iNumCriterios++;
                }
            }
            if (this.dGlucosa >= 100) {
                iNumCriterios++;
            }
        }
        //Mujeres
        else {
            if (this.iCircunferencia >= 80) {
                iNumCriterios++;
            }
            if (this.dTrigliceridos >= 150) {
                iNumCriterios++;
            }
            if (this.iHDL < 50) {
                iNumCriterios++;
            }
            if (this.bTratamientoHA == 1.0) {
                iNumCriterios++;
            }
            else{
                if (this.iPresionSistolica >= 130) {
                    iNumCriterios++;
                }
                if (this.iPresionDiastolica >= 85) {
                    iNumCriterios++;
                }
            }
            if (this.dGlucosa >= 100) {
                iNumCriterios++;
            }
        }

        Log.i("Puntaje", String.valueOf(iNumCriterios));
        if (iNumCriterios >= 2) bSindrome = true;

        return bSindrome;
    }
}