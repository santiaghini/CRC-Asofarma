package com.edibca.crcasofarma;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

    LinearLayout resultWheelContainer;
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
        setSupportActionBar(myChildToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        setTitle("Resultados");

        initializeCommonViews();

        position = getIntent().getIntExtra("position", 0);
        referencesText = getIntent().getStringExtra("references");

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
        resultImageContainer.setVisibility(View.GONE);

        resultLabelCenter = (TextView)findViewById(R.id.results_result_label_center);
        resultLabelCenter.setTextColor(getResources().getColor(R.color.asofarmaRed));
        resultLabelCenter.setVisibility(View.GONE);

        resultWheelContainer = (LinearLayout)findViewById(R.id.results_result_wheel_container);
        resultWheelContainer.setVisibility(View.GONE);
        wheelLeft = (ProgressWheel)findViewById(R.id.results_result_wheel_left);
        wheelCenter = (ProgressWheel)findViewById(R.id.results_result_wheel_center);
        wheelRight = (ProgressWheel)findViewById(R.id.results_result_wheel_right);
        wheelTextLeft = (TextView)findViewById(R.id.results_result_wheel_text_left);
        wheelTextCenter = (TextView)findViewById(R.id.results_result_wheel_text_center);
        wheelTextRight = (TextView)findViewById(R.id.results_result_wheel_text_right);

        resultLabelDown = (TextView)findViewById(R.id.results_result_label_down);
        resultLabelDown.setVisibility(View.GONE);

        commentInput = (EditText) findViewById(R.id.results_comments_input);

        referencesTextView = (TextView)findViewById(R.id.results_references_text);
        referencesTextView.setText(referencesText);

        infoTextView = (TextView)findViewById(R.id.results_info_text);
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
        double[] results = getIntent().getDoubleArrayExtra("results");
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

    }
    public void imc(){
        resultImageContainer.setVisibility(View.VISIBLE);
        resultLabelDown.setVisibility(View.VISIBLE);
        resultTitleView.setText(getString(R.string.imc_result_title));
        Double result = getIntent().getDoubleExtra("result", 0.0);
        String textResult = result + " kg/m2";
        resultLabelRight.setText(textResult);

    }
    public void rcp(){
        resultWheelContainer.setVisibility(View.VISIBLE);
        wheelTextLeft.setText("Complicaciones");
        wheelTextCenter.setText("Complicaciones graves");
        wheelTextRight.setText("Muerte");
        double[] results = getIntent().getDoubleArrayExtra("results");
    }
}
