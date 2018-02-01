package com.edibca.crcasofarma.Clases;

import android.util.Log;

/**
 * Created by Edibca on 10/11/2015.
 */
public class RiesgoEC {
    private Double sGenero;
    private Double iEdad;
    private Double iPresionSistolica;
    private Double bTratamiento;
    private Double bFumador;
    private Double iHDL;
    private Double iColesterolTotal;

    public RiesgoEC() {
    }

    public RiesgoEC(Double sGenero, Double iEdad, Double iPresionSistolica, Double bTratamiento, Double bFumador, Double iHDL, Double iColesterolTotal) {
        this.sGenero = sGenero;
        this.iEdad = iEdad;
        this.iPresionSistolica = iPresionSistolica;
        this.bTratamiento = bTratamiento;
        this.bFumador = bFumador;
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

    public void setiHDL(Double iHDL) {
        this.iHDL = iHDL;
    }

    public void setiColesterolTotal(Double iColesterolTotal) {
        this.iColesterolTotal = iColesterolTotal;
    }

    public Double calculaRiesgo() {
        Double dRiesgo = 0.0;
        Double puntaje = 0.0;

        //Hombres
        if (this.sGenero == 0.0) {
            //Edad
            if (this.iEdad >= 20 && this.iEdad <= 34) puntaje += -9;
            else if (this.iEdad >= 35 && this.iEdad <= 39) puntaje += -4;
            else if (this.iEdad >= 40 && this.iEdad <= 44) puntaje += 0;
            else if (this.iEdad >= 45 && this.iEdad <= 49) puntaje += 3;
            else if (this.iEdad >= 50 && this.iEdad <= 54) puntaje += 6;
            else if (this.iEdad >= 55 && this.iEdad <= 59) puntaje += 8;
            else if (this.iEdad >= 60 && this.iEdad <= 64) puntaje += 10;
            else if (this.iEdad >= 65 && this.iEdad <= 69) puntaje += 11;
            else if (this.iEdad >= 70 && this.iEdad <= 74) puntaje += 12;
            else if (this.iEdad >= 75 && this.iEdad <= 79) puntaje += 13;

            //Colesterol total vs. Edad y Fumador/No fumador
            if (this.iEdad >= 20 && this.iEdad <= 39) {
                if (this.iColesterolTotal < 160) {
                    puntaje += 0;
                } else if (this.iColesterolTotal >= 160 && this.iColesterolTotal <= 199) {
                    puntaje += 4;
                } else if (this.iColesterolTotal >= 200 && this.iColesterolTotal <= 239) {
                    puntaje += 7;
                } else if (this.iColesterolTotal >= 240 && this.iColesterolTotal <= 279) {
                    puntaje += 9;
                } else if (this.iColesterolTotal >= 280) {
                    puntaje += 11;
                }
                if (this.bFumador == 1.0) puntaje += 8;
            } else if (this.iEdad >= 40 && this.iEdad <= 49) {
                if (this.iColesterolTotal < 160) {
                    puntaje += 0;
                } else if (this.iColesterolTotal >= 160 && this.iColesterolTotal <= 199) {
                    puntaje += 3;
                } else if (this.iColesterolTotal >= 200 && this.iColesterolTotal <= 239) {
                    puntaje += 5;
                } else if (this.iColesterolTotal >= 240 && this.iColesterolTotal <= 279) {
                    puntaje += 6;
                } else if (this.iColesterolTotal >= 280) {
                    puntaje += 8;
                }
                if (this.bFumador == 1.0) puntaje += 5;
            } else if (this.iEdad >= 50 && this.iEdad <= 59) {
                if (this.iColesterolTotal < 160) {
                    puntaje += 0;
                } else if (this.iColesterolTotal >= 160 && this.iColesterolTotal <= 199) {
                    puntaje += 2;
                } else if (this.iColesterolTotal >= 200 && this.iColesterolTotal <= 239) {
                    puntaje += 3;
                } else if (this.iColesterolTotal >= 240 && this.iColesterolTotal <= 279) {
                    puntaje += 4;
                } else if (this.iColesterolTotal >= 280) {
                    puntaje += 5;
                }
                if (this.bFumador == 1.0) puntaje += 3;
            } else if (this.iEdad >= 60 && this.iEdad <= 69) {
                if (this.iColesterolTotal < 160) {
                    puntaje += 0;
                } else if (this.iColesterolTotal >= 160 && this.iColesterolTotal <= 199) {
                    puntaje += 1;
                } else if (this.iColesterolTotal >= 200 && this.iColesterolTotal <= 239) {
                    puntaje += 1;
                } else if (this.iColesterolTotal >= 240 && this.iColesterolTotal <= 279) {
                    puntaje += 2;
                } else if (this.iColesterolTotal >= 280) {
                    puntaje += 3;
                }
                if (this.bFumador == 1.0) puntaje += 1;
            } else if (this.iEdad >= 70 && this.iEdad <= 79) {
                if (this.iColesterolTotal < 160) {
                    puntaje += 0;
                } else if (this.iColesterolTotal >= 160 && this.iColesterolTotal <= 199) {
                    puntaje += 0;
                } else if (this.iColesterolTotal >= 200 && this.iColesterolTotal <= 239) {
                    puntaje += 0;
                } else if (this.iColesterolTotal >= 240 && this.iColesterolTotal <= 279) {
                    puntaje += 1;
                } else if (this.iColesterolTotal >= 280) {
                    puntaje += 1;
                }
                if (this.bFumador == 1.0) puntaje += 1;
            }

            //HDL
            if (this.iHDL >= 60) puntaje += -1;
            else if (this.iHDL >= 50 && this.iHDL <= 59) puntaje += 0;
            else if (this.iHDL >= 40 && this.iHDL <= 49) puntaje += 1;
            else if (this.iHDL < 40) puntaje += 2;

            //Presión sistólica vs. Tratamiento
            if (this.iPresionSistolica < 120) {
                if (this.bTratamiento == 1.0) {
                    puntaje += 0;
                } else {
                    puntaje += 0;
                }
            } else if (this.iPresionSistolica >= 120 && this.iPresionSistolica <= 129) {
                if (this.bTratamiento == 1.0) {
                    puntaje += 0;
                } else {
                    puntaje += 1;
                }
            } else if (this.iPresionSistolica >= 130 && this.iPresionSistolica <= 139) {
                if (this.bTratamiento == 1.0) {
                    puntaje += 1;
                } else {
                    puntaje += 2;
                }
            } else if (this.iPresionSistolica >= 140 && this.iPresionSistolica <= 159) {
                if (this.bTratamiento == 1.0) {
                    puntaje += 1;
                } else {
                    puntaje += 2;
                }
            } else if (this.iPresionSistolica >= 160) {
                if (this.bTratamiento == 1.0) {
                    puntaje += 2;
                } else {
                    puntaje += 3;
                }
            }

            //Riesgo según puntaje
            if (puntaje < 0) dRiesgo = 0.0;//	< 1
            if (puntaje == 0) dRiesgo = 1.0;
            if (puntaje == 1) dRiesgo = 1.0;
            if (puntaje == 2) dRiesgo = 1.0;
            if (puntaje == 3) dRiesgo = 1.0;
            if (puntaje == 4) dRiesgo = 1.0;
            if (puntaje == 5) dRiesgo = 2.0;
            if (puntaje == 6) dRiesgo = 2.0;
            if (puntaje == 7) dRiesgo = 3.0;
            if (puntaje == 8) dRiesgo = 4.0;
            if (puntaje == 9) dRiesgo = 5.0;
            if (puntaje == 10) dRiesgo = 6.0;
            if (puntaje == 11) dRiesgo = 8.0;
            if (puntaje == 12) dRiesgo = 10.0;
            if (puntaje == 13) dRiesgo = 12.0;
            if (puntaje == 14) dRiesgo = 16.0;
            if (puntaje == 15) dRiesgo = 20.0;
            if (puntaje == 16) dRiesgo = 25.0;
            if (puntaje >= 17) dRiesgo = 30.0;//≥ 30
        }

        //Mujeres
        else {
            //Edad
            if (this.iEdad >= 20 && this.iEdad <= 34) puntaje += -7;
            else if (this.iEdad >= 35 && this.iEdad <= 39) puntaje += -3;
            else if (this.iEdad >= 40 && this.iEdad <= 44) puntaje += 0;
            else if (this.iEdad >= 45 && this.iEdad <= 49) puntaje += 3;
            else if (this.iEdad >= 50 && this.iEdad <= 54) puntaje += 6;
            else if (this.iEdad >= 55 && this.iEdad <= 59) puntaje += 8;
            else if (this.iEdad >= 60 && this.iEdad <= 64) puntaje += 10;
            else if (this.iEdad >= 65 && this.iEdad <= 69) puntaje += 12;
            else if (this.iEdad >= 70 && this.iEdad <= 74) puntaje += 14;
            else if (this.iEdad >= 75 && this.iEdad <= 79) puntaje += 16;

            //Colesterol total vs. Edad y Fumador/No fumador
            if (this.iEdad >= 20 && this.iEdad <= 39) {
                if (this.iColesterolTotal < 160) {
                    puntaje += 0;
                } else if (this.iColesterolTotal >= 160 && this.iColesterolTotal <= 199) {
                    puntaje += 4;
                } else if (this.iColesterolTotal >= 200 && this.iColesterolTotal <= 239) {
                    puntaje += 8;
                } else if (this.iColesterolTotal >= 240 && this.iColesterolTotal <= 279) {
                    puntaje += 11;
                } else if (this.iColesterolTotal >= 280) {
                    puntaje += 13;
                }
                if (this.bFumador == 1.0) puntaje += 9;
            } else if (this.iEdad >= 40 && this.iEdad <= 49) {
                if (this.iColesterolTotal < 160) {
                    puntaje += 0;
                } else if (this.iColesterolTotal >= 160 && this.iColesterolTotal <= 199) {
                    puntaje += 3;
                } else if (this.iColesterolTotal >= 200 && this.iColesterolTotal <= 239) {
                    puntaje += 6;
                } else if (this.iColesterolTotal >= 240 && this.iColesterolTotal <= 279) {
                    puntaje += 8;
                } else if (this.iColesterolTotal >= 280) {
                    puntaje += 10;
                }
                if (this.bFumador == 1.0) puntaje += 7;
            } else if (this.iEdad >= 50 && this.iEdad <= 59) {
                if (this.iColesterolTotal < 160) {
                    puntaje += 0;
                } else if (this.iColesterolTotal >= 160 && this.iColesterolTotal <= 199) {
                    puntaje += 2;
                } else if (this.iColesterolTotal >= 200 && this.iColesterolTotal <= 239) {
                    puntaje += 4;
                } else if (this.iColesterolTotal >= 240 && this.iColesterolTotal <= 279) {
                    puntaje += 5;
                } else if (this.iColesterolTotal >= 280) {
                    puntaje += 7;
                }
                if (this.bFumador == 1.0) puntaje += 4;
            } else if (this.iEdad >= 60 && this.iEdad <= 69) {
                if (this.iColesterolTotal < 160) {
                    puntaje += 0;
                } else if (this.iColesterolTotal >= 160 && this.iColesterolTotal <= 199) {
                    puntaje += 1;
                } else if (this.iColesterolTotal >= 200 && this.iColesterolTotal <= 239) {
                    puntaje += 2;
                } else if (this.iColesterolTotal >= 240 && this.iColesterolTotal <= 279) {
                    puntaje += 3;
                } else if (this.iColesterolTotal >= 280) {
                    puntaje += 4;
                }
                if (this.bFumador == 1.0) puntaje += 2;
            } else if (this.iEdad >= 70 && this.iEdad <= 79) {
                if (this.iColesterolTotal < 160) {
                    puntaje += 0;
                } else if (this.iColesterolTotal >= 160 && this.iColesterolTotal <= 199) {
                    puntaje += 1;
                } else if (this.iColesterolTotal >= 200 && this.iColesterolTotal <= 239) {
                    puntaje += 1;
                } else if (this.iColesterolTotal >= 240 && this.iColesterolTotal <= 279) {
                    puntaje += 2;
                } else if (this.iColesterolTotal >= 280) {
                    puntaje += 2;
                }
                if (this.bFumador == 1.0) puntaje += 1;
            }

            //HDL
            if (this.iHDL >= 60) puntaje += -1;
            else if (this.iHDL >= 50 && this.iHDL <= 59) puntaje += 0;
            else if (this.iHDL >= 40 && this.iHDL <= 49) puntaje += 1;
            else if (this.iHDL < 40) puntaje += 2;

            //Presión sistólica vs. Tratamiento
            if (this.iPresionSistolica < 120) {
                if (this.bTratamiento == 1.0) {
                    puntaje += 0;
                } else {
                    puntaje += 0;
                }
            } else if (this.iPresionSistolica >= 120 && this.iPresionSistolica <= 129) {
                if (this.bTratamiento == 1.0) {
                    puntaje += 1;
                } else {
                    puntaje += 3;
                }
            } else if (this.iPresionSistolica >= 130 && this.iPresionSistolica <= 139) {
                if (this.bTratamiento == 1.0) {
                    puntaje += 2;
                } else {
                    puntaje += 4;
                }
            } else if (this.iPresionSistolica >= 140 && this.iPresionSistolica <= 159) {
                if (this.bTratamiento == 1.0) {
                    puntaje += 3;
                } else {
                    puntaje += 5;
                }
            } else if (this.iPresionSistolica >= 160) {
                if (this.bTratamiento == 1.0) {
                    puntaje += 4;
                } else {
                    puntaje += 6;
                }
            }

            //Riesgo según puntaje
            if (puntaje < 9) dRiesgo = 0.0;//	< 1
            if (puntaje == 9) dRiesgo = 1.0;
            if (puntaje == 10) dRiesgo = 1.0;
            if (puntaje == 11) dRiesgo = 1.0;
            if (puntaje == 12) dRiesgo = 1.0;
            if (puntaje == 13) dRiesgo = 2.0;
            if (puntaje == 14) dRiesgo = 2.0;
            if (puntaje == 15) dRiesgo = 3.0;
            if (puntaje == 16) dRiesgo = 4.0;
            if (puntaje == 17) dRiesgo = 5.0;
            if (puntaje == 18) dRiesgo = 6.0;
            if (puntaje == 19) dRiesgo = 8.0;
            if (puntaje == 20) dRiesgo = 11.0;
            if (puntaje == 21) dRiesgo = 14.0;
            if (puntaje == 22) dRiesgo = 17.0;
            if (puntaje == 23) dRiesgo = 22.0;
            if (puntaje == 24) dRiesgo = 27.0;
            if (puntaje >= 25) dRiesgo = 30.0;//≥ 30
        }

        Log.i("Puntaje", String.valueOf(puntaje));
        Log.i("Riesgo", String.valueOf(dRiesgo));

        return dRiesgo;
    }
}