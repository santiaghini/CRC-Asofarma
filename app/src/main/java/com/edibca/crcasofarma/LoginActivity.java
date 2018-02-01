package com.edibca.crcasofarma;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.edibca.crcasofarma.MainActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText passwordInput;
    private String access;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences preferences = getApplicationContext().getSharedPreferences(getString(R.string.app_name) , Context.MODE_PRIVATE);
        Boolean access = preferences.getBoolean("access", false);

        if (access) {
            Intent intent1 = new Intent(LoginActivity.this , MainActivity.class);
            startActivity(intent1);
        }

        Typeface font = Typeface.createFromAsset(getAssets() , "fonts/avenir_book.ttf");


        Button button = (Button) findViewById(R.id.login_botoninicio);
        button.setTextColor(getResources().getColor(R.color.white));
        button.setText(R.string.login);

        passwordInput = (EditText) findViewById(R.id.login_contra);
        passwordInput.setHintTextColor(getResources().getColor(R.color.primary));
        Drawable passBackground = passwordInput.getBackground();
        passBackground.mutate().setColorFilter(ContextCompat.getColor(getApplicationContext() , R.color.primary) , PorterDuff.Mode.SRC_ATOP);
        passwordInput.setBackground(passBackground);
        passwordInput.setTypeface(font);


        Drawable lock = ResourcesCompat.getDrawable(getResources() , R.drawable.lock_icon , getApplicationContext().getTheme());
        if(lock!= null) {
            lock.setColorFilter(ContextCompat.getColor(getApplicationContext() , R.color.primary) , PorterDuff.Mode.SRC_ATOP);
        }
        ((ImageView) findViewById(R.id.login_lock)).setImageDrawable(lock);


    }

    public void login(View v) {
        String password = passwordInput.getText().toString();
        if (password.equals(getResources().getString(R.string.password))){
            SharedPreferences preferences = getApplicationContext().getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("access", true);
            editor.apply();
            Intent intent = new Intent(LoginActivity.this , MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext() , "Vuelve a intentarlo" , Toast.LENGTH_LONG).show();
        }
    }
}
