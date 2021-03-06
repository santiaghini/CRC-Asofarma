package com.edibca.crcasofarma;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        switch (getResources().getConfiguration().orientation) {
            case Configuration.ORIENTATION_PORTRAIT:
                setContentView(R.layout.activity_main);
                break;
            case Configuration.ORIENTATION_LANDSCAPE:
                setContentView(R.layout.activity_main_land);
                break;
        }


        img = (ImageView) findViewById(R.id.landing_cover);

        img.setOnClickListener(mClickListener);
    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this , MenuActivity.class);
            startActivity(intent);
        }
    };

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){

        }
    }
}
