package com.edibca.crcasofarma;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.todddavies.components.progressbar.ProgressWheel;

import org.w3c.dom.Text;

public class ResultsActivity extends AppCompatActivity {
    int position;
    Intent intent;
    String referencesText;

    TextView resultTitleView;
    ConstraintLayout resultContainer;
    ImageView resultImage;
    ConstraintLayout resultImageContainer;
    ConstraintLayout resultInner;
    TextView resultLabelRight;
    TextView resultLabelCenter;
    TextView resultLabelDown;

    ConstraintLayout resultWheelContainer;
    ProgressWheel wheelLeft;
    ProgressWheel wheelRight;
    ProgressWheel wheelCenter;
    TextView wheelTextLeft;
    TextView wheelTextCenter;
    TextView wheelTextRight;

    EditText commentInput;

    TextView referencesTextView;

    TextView infoTextView;
    ConstraintLayout infoContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Toolbar myChildToolbar =
                (Toolbar) findViewById(R.id.results_toolbar);
        myChildToolbar.setBackgroundColor(getResources().getColor(R.color.asofarmaRed));
        myChildToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        setTitle("Resultados");

        position = getIntent().getIntExtra("position", 0);
        referencesText = getIntent().getStringExtra("references");
        Log.d("Reference", referencesText);
        initializeCommonViews();

        switch (position){
            case FieldsActivity.FRAMINGHAM_POS:
                framingham();
                break;
            case FieldsActivity.REC_POS:
                rec();
                break;
            case FieldsActivity.METABOLIC_POS:
                metabolic();
                break;
            case FieldsActivity.IMC_POS:
                imc();
                break;
            case FieldsActivity.RCP_POS:
                rcp();
                break;
        }

    }

    public void initializeCommonViews(){
        resultTitleView = (TextView)findViewById(R.id.results_result_title);
        resultContainer = (ConstraintLayout)findViewById(R.id.results_result_container);
        resultInner = (ConstraintLayout)findViewById(R.id.results_result_inner_container);

        resultImageContainer = (ConstraintLayout)findViewById(R.id.results_result_image_container);
        resultImage = (ImageView)findViewById(R.id.results_result_image);
        resultLabelRight = (TextView)findViewById(R.id.results_result_label);
        resultLabelRight.setTextColor(getResources().getColor(R.color.asofarmaRed));
        resultLabelRight.setTextSize(28);
        resultImageContainer.setVisibility(View.GONE);

        resultLabelCenter = (TextView)findViewById(R.id.results_result_label_center);
        resultLabelCenter.setTextColor(getResources().getColor(R.color.asofarmaRed));
        resultLabelCenter.setTextSize(28);
        resultLabelCenter.setVisibility(View.GONE);

        resultWheelContainer = (ConstraintLayout)findViewById(R.id.results_result_wheel_container);
        resultWheelContainer.setVisibility(View.GONE);
        wheelLeft = (ProgressWheel)findViewById(R.id.results_result_wheel_left);
        wheelCenter = (ProgressWheel)findViewById(R.id.results_result_wheel_center);
        wheelRight = (ProgressWheel)findViewById(R.id.results_result_wheel_right);
        wheelTextLeft = (TextView)findViewById(R.id.results_result_wheel_text_left);
        wheelTextLeft.setTextSize(12);
        wheelTextCenter = (TextView)findViewById(R.id.results_result_wheel_text_center);
        wheelTextCenter.setTextSize(12);
        wheelTextRight = (TextView)findViewById(R.id.results_result_wheel_text_right);
        wheelTextRight.setTextSize(12);

        resultLabelDown = (TextView)findViewById(R.id.results_result_label_down);
        resultLabelDown.setVisibility(View.GONE);

        commentInput = (EditText) findViewById(R.id.results_comments_input);
        commentInput.setTextSize(14);

        referencesTextView = (TextView)findViewById(R.id.results_references_text);
        referencesTextView.setMovementMethod(new ScrollingMovementMethod());
        referencesTextView.setTextSize(11);
        referencesTextView.setText(referencesText);

        infoTextView = (TextView)findViewById(R.id.results_info_text);
        infoTextView.setTextSize(11);
        infoContainer = (ConstraintLayout)findViewById(R.id.results_information_container);
        infoContainer.setVisibility(View.GONE);
    }

    public void framingham(){
        resultLabelDown.setVisibility(View.VISIBLE);
        resultWheelContainer.setVisibility(View.VISIBLE);
        resultTitleView.setText(getString(R.string.framingham_result_title));
        wheelTextLeft.setText("Normal");
        wheelTextCenter.setText("Riesgo Cardiovascular");
        wheelTextRight.setText("Óptimo");
        double[] results = getIntent().getDoubleArrayExtra("result");
        setWheels(results);
        int heartAge = getIntent().getIntExtra("heartAge", 0);
        String label = "";
        if (heartAge < 30) {
            label = "Tu edad cardiaca es menor a 30 años";
        } else if (heartAge > 80){
            label = "Tu edad cardiaca es mayor a 80 años";
        } else {
            label = "Tu edad cardiaca es " + heartAge + " años";
        }

        resultLabelDown.setText(label);
    }
    public void rec(){
        resultLabelCenter.setVisibility(View.VISIBLE);
        resultTitleView.setText(getString(R.string.rec_result_title));
        Double result = getIntent().getDoubleExtra("result", 0.0);
        resultLabelCenter.setText(result + "%");

    }
    public void metabolic(){
        infoContainer.setVisibility(View.VISIBLE);
        resultLabelCenter.setVisibility(View.VISIBLE);
        resultTitleView.setText(getString(R.string.metabolic_result_title));
        boolean result = getIntent().getBooleanExtra("result",true);
        String resultText = ((result) ? "SÍ" : "NO");
        resultLabelCenter.setText(resultText);
        infoTextView.setText(getString(R.string.metabolic_result_title));

    }
    public void imc(){
        resultImageContainer.setVisibility(View.VISIBLE);
        resultLabelDown.setVisibility(View.VISIBLE);
        resultTitleView.setText(getString(R.string.imc_result_title));
        Double result = getIntent().getDoubleExtra("result", 0.0);
        String textResult = String.format("%.2f", result) + " kg/m2";
        resultLabelRight.setText(textResult);

        String tag = "";

        if (result < 18.5) {
            tag = "bajo peso";
            resultImage.setImageResource(R.drawable.imc_underweight);
        } else if (result >= 18.5 && result <= 24.9) {
            tag = "peso normal";
            resultImage.setImageResource(R.drawable.imc_normal);
        } else if (result >= 25 && result <= 29.9) {
            tag = "sobrepeso";
            resultImage.setImageResource(R.drawable.imc_obesity);
        } else if (result >= 30 && result <= 34.9) {
            tag = "obesidad grado I";
            resultImage.setImageResource(R.drawable.imc_superobesity);
        } else if (result >= 35 && result <= 39.9) {
            tag = "obesidad grado II";
            resultImage.setImageResource(R.drawable.imc_superobesity);
        } else if (result >= 40){
            tag = "obesidad grado III";
            resultImage.setImageResource(R.drawable.imc_superobesity);
        }

        resultLabelDown.setText("Este es un paciente con " + tag);

    }
    public void rcp(){
        resultWheelContainer.setVisibility(View.VISIBLE);
        wheelTextLeft.setText("Complicaciones");
        wheelTextCenter.setText("Complicaciones graves");
        wheelTextRight.setText("Muerte");

        infoContainer.setVisibility(View.VISIBLE);
        infoTextView.setText(getString(R.string.postoperatory_info));

        double[] results = getIntent().getDoubleArrayExtra("result");
        setWheels(results);

        Integer puntaje = (int) results[3];

        String tag = "";

        if (puntaje > 25) {
            tag = "clase IV";
        } else if (puntaje > 12) {
            tag = "clase III";
        } else if (puntaje > 5) {
            tag = "clase II";
        } else {
            tag = "clase I";
        }

        resultTitleView.setText("Paciente " + tag);
    }

    public void setWheels(double[] results){
        wheelLeft.setTextSize(34);
        wheelLeft.setBarColor(getResources().getColor(R.color.asofarmaRed));
        wheelLeft.setBarWidth(10);
        wheelLeft.setCircleRadius(40);
        wheelLeft.setRimColor(getResources().getColor(R.color.wheel_gray));
        wheelLeft.setRimWidth(10);

        wheelCenter.setTextSize(34);
        wheelCenter.setBarColor(getResources().getColor(R.color.asofarmaRed));
        wheelCenter.setBarWidth(10);
        wheelCenter.setCircleRadius(40);
        wheelCenter.setRimColor(getResources().getColor(R.color.wheel_gray));
        wheelCenter.setRimWidth(10);

        wheelRight.setTextSize(34);
        wheelRight.setBarColor(getResources().getColor(R.color.asofarmaRed));
        wheelRight.setBarWidth(10);
        wheelRight.setCircleRadius(40);
        wheelRight.setRimColor(getResources().getColor(R.color.wheel_gray));
        wheelRight.setRimWidth(10);

        wheelLeft.setText(String.valueOf(results[0]) + "%");
        int progressLeft = (int) ((results[0]*360)/100);
        wheelLeft.setProgress(progressLeft);

        wheelCenter.setText(String.valueOf(results[1]) + "%");
        int progressCenter = (int) ((results[1]*360)/100);
        wheelCenter.setProgress(progressCenter);

        wheelRight.setText(String.valueOf(results[2]) + "%");
        int progressRight = (int) ((results[2]*360)/100);
        wheelRight.setProgress(progressRight);
    }
}
/*
 wheelLeft.setProgress(50);
        wheelLeft.setText("80%");
        wheelLeft.setTextSize(34);
        wheelLeft.setBarColor(getResources().getColor(R.color.wheel_gray));
        wheelLeft.setBarLength(85);
        wheelLeft.setCircleColor(getResources().getColor(R.color.button_login));
        wheelLeft.setCircleRadius(8);
        wheelLeft.setContourColor(getResources().getColor(R.color.colorAccent));
        wheelLeft.setContourSize(15);
        wheelLeft.setRimColor(getResources().getColor(R.color.asofarmaRed));
        wheelLeft.setRimWidth(10);
 */
