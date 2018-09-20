package com.jose.simplemacros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calc_bmr(View view){
        Intent intent = new Intent(MainActivity.this, BMR.class);
        startActivity(intent);
    }


    public void calc_macros(View view){
        Intent intent = new Intent(MainActivity.this, Macros.class);
        startActivity(intent);
    }

    /*
    public void calc_keto(View view){
        Intent intent = new Intent(MainActivity.this, Keto.class);
        startActivity(intent);
    }
    */


}
