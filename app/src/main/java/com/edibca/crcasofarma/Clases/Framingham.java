package com.edibca.crcasofarma.Clases;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import com.google.common.collect.Lists;

/**
 * Created by Edibca on 09/11/2015.
 */
public class Framingham {
    private Double sGenero;
    private Double iEdad;
    private Double iPresionSistolica;
    private Double bTratamiento;
    private Double bFumador;
    private Double bDiabetico;
    private Double iHDL;
    private Double iColesterolTotal;

    public Framingham() {
    }

    public Framingham(Double sGenero, Double iEdad, Double iPresionSistolica, Double bTratamiento, Double bFumador, Double bDiabetico, Double iHDL, Double iColesterolTotal) {
        this.sGenero = sGenero;
        this.iEdad = iEdad;
        this.iPresionSistolica = iPresionSistolica;
        this.bTratamiento = bTratamiento;
        this.bFumador = bFumador;
        this.bDiabetico = bDiabetico;
        this.iHDL = iHDL;
        this.iColesterolTotal = iColesterolTotal;
    }

    public void setsGenero(Double sGenero) {
        this.sGenero = sGenero;
    }

    public void setiEdad(Double iEdad) {
        this.iEdad = iEdad;
    }

    public void setiPresionSistolica(Double iPresionSistolica) {
        this.iPresionSistolica = iPresionSistolica;
    }

    public void setbTratamiento(Double bTratamiento) {
        this.bTratamiento = bTratamiento;
    }

    public void setbFumador(Double bFumador) {
        this.bFumador = bFumador;
    }

    public void setbDiabetico(Double bDiabetico) {
        this.bDiabetico = bDiabetico;
    }

    public void setiHDL(Double iHDL) {
        this.iHDL = iHDL;
    }

    public void setiColesterolTotal(Double iColesterolTotal) {
        this.iColesterolTotal = iColesterolTotal;
    }

    public double[] calculaFramingham() {
        Double iGenero;
        Double dEdad;
        Double dPresionSistolica;
        Double iFumador;
        Double iDiabetico;
        Double dHDL;
        Double dColesterolTotal;

        Double dCoef;
        Double dCoefOptimo;
        Double dCoefNormal;
        Double dRiesgo;
        Double dRiesgoOptimo;
        Double dRiesgoNormal;
        double dRiesgos[] = new double[3];

        iGenero = this.sGenero;
        dEdad = Math.log(this.iEdad);
        dPresionSistolica = Math.log(this.iPresionSistolica);
        dColesterolTotal = Math.log(this.iColesterolTotal);
        dHDL = Math.log(this.iHDL);
        iFumador = this.bFumador;
        iDiabetico = this.bDiabetico;

        //Hombres
        if (iGenero == 0.0) {
            //Con tratamiento
            if (this.bTratamiento == 1.0) {
                dCoef = (dEdad * 3.06117) + (dPresionSistolica * 1.99881) + (dColesterolTotal * 1.1237) + (dHDL * -0.93263) + (iFumador * 0.65451)
                        + (iDiabetico * 0.57367);
            }
            //Sin tratamiento
            else {
                dCoef = (dEdad * 3.06117) + (dPresionSistolica * 1.93303) + (dColesterolTotal * 1.1237) + (dHDL * -0.93263) + (iFumador * 0.65451)
                        + (iDiabetico * 0.57367);
            }
            dCoefOptimo = (dEdad * 3.06117) + (Math.log(110) * 1.93303) + (Math.log(160) * 1.1237) + (Math.log(60) * -0.93263);
            dCoefNormal = (dEdad * 3.06117) + (Math.log(125) * 1.93303) + (Math.log(180) * 1.1237) + (Math.log(45) * -0.93263);

            dRiesgo = 1 - Math.pow(0.88936, Math.exp(dCoef - 23.9802));
            dRiesgoOptimo = 1 - Math.pow(0.88936, Math.exp(dCoefOptimo - 23.9802));
            dRiesgoNormal = 1 - Math.pow(0.88936, Math.exp(dCoefNormal - 23.9802));
        }
        //Mujeres
        else {
            //Con tratamiento
            if (this.bTratamiento == 1.0) {
                dCoef = (dEdad * 2.32888) + (dPresionSistolica * 2.82263) + (dColesterolTotal * 1.20904) + (dHDL * -0.70833) + (iFumador * 0.52873)
                        + (iDiabetico * 0.69154);
            }
            //Sin tratamiento
            else {
                dCoef = (dEdad * 2.32888) + (dPresionSistolica * 2.76157) + (dColesterolTotal * 1.20904) + (dHDL * -0.70833) + (iFumador * 0.52873)
                        + (iDiabetico * 0.69154);
            }
            dCoefOptimo = (dEdad * 2.32888) + (Math.log(110) * 2.76157) + (Math.log(160) * 1.20904) + (Math.log(60) * -0.70833);
            dCoefNormal = (dEdad * 2.32888) + (Math.log(125) * 2.76157) + (Math.log(180) * 1.20904) + (Math.log(45) * -0.70833);

            dRiesgo = 1 - Math.pow(0.95012, Math.exp(dCoef - 26.1931));
            dRiesgoOptimo = 1 - Math.pow(0.95012, Math.exp(dCoefOptimo - 26.1931));
            dRiesgoNormal = 1 - Math.pow(0.95012, Math.exp(dCoefNormal - 26.1931));
        }
        /*Log.i("Riesgo: ", String.valueOf(dRiesgo));
        Log.i("Riesgo óptimo: ", String.valueOf(dRiesgoOptimo));
        Log.i("Riesgo normal: ", String.valueOf(dRiesgoNormal));*/

        dRiesgos[1] = Math.round(dRiesgo * 10000.0) / 100.0;
        dRiesgos[2] = Math.round(dRiesgoOptimo * 10000.0) / 100.0;
        dRiesgos[0] = Math.round(dRiesgoNormal * 10000.0) / 100.0;

        return dRiesgos;
    }



    public int getHeartAge(double risk, double genero, JSONObject jsonObject) throws JSONException {
        int heartAge = 0;
        int points = 0;

        // HOMBRES
        if (genero == 0.0){
            if (risk < 1){
                points = -3;
            } else if(risk > 30){
                points = 18;
            } else {
                JSONObject riskToPointsMen = jsonObject.getJSONObject("riskToPoints").getJSONObject("men");
                Iterator keys = riskToPointsMen.keys();
                List<String> keysArray = Lists.newArrayList(keys);
                boolean winner = false;
                for (int i = 1; i < keysArray.size(); i++){
                    if (risk < Double.valueOf(keysArray.get(i)) && !winner){
                        points = riskToPointsMen.getInt(keysArray.get(i-1));
                        winner = true;
                    }
                }
            }
            if (points < 0){
                heartAge = 28;
            } else if (points >= 17){
                heartAge = 80;
            } else {
                heartAge = jsonObject.getJSONObject("pointsToHeartAge").getJSONObject("men").getInt(String.valueOf(points));
            }
            // MUJERES
        } else {
            if (risk < 1){
                points = -2;
            } else if(risk > 30){
                points = 21;
            } else {
                JSONObject riskToPointsWomen = jsonObject.getJSONObject("riskToPoints").getJSONObject("women");
                Iterator keys = riskToPointsWomen.keys();
                List<String> keysArray = Lists.newArrayList(keys);
                boolean winner = false;
                for (int i = 1; i < keysArray.size(); i++){
                    if (risk < Double.valueOf(keysArray.get(i)) && !winner){
                        points = riskToPointsWomen.getInt(keysArray.get(i-1));
                        winner = true;
                    }
                }
            }
            if (points < 1){
                heartAge = 30;
            } else if (points >= 15){
                heartAge = 80;
            } else {
                heartAge = jsonObject.getJSONObject("pointsToHeartAge").getJSONObject("women").getInt(String.valueOf(points));
            }
        }

        return heartAge;
    }

}