package com.jose.simplemacros;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class BMR extends AppCompatActivity {

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmr);

        //EditText
        final EditText years = (EditText) findViewById(R.id.et_years);
        final EditText feet = (EditText) findViewById(R.id.et_feet);
        final EditText inches = (EditText) findViewById(R.id.et_inches);
        final EditText pounds = (EditText) findViewById(R.id.et_pounds);
        //Radio Buttons
        final RadioButton rb_male = (RadioButton) findViewById(R.id.rb_male);
        final RadioButton rb_female = (RadioButton) findViewById(R.id.rb_female);

        Button calc = (Button) findViewById(R.id.btn_calculate);
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get values from editText
                if (isEmpty(years) || isEmpty(feet) || isEmpty(inches) || isEmpty(pounds)){
                    Toast.makeText(getApplicationContext(),"Please fill all fields!",Toast.LENGTH_LONG).show();
                }
                else if (validInput(years,feet,inches,pounds) != 4){
                    Toast.makeText(getApplicationContext(),"One or more fields are not valid",Toast.LENGTH_LONG).show();
                }
                else if(validInput(years,feet,inches,pounds) == 4){
                    double bmr_value = 0;
                    if (rb_male.isChecked()){
                        bmr_value = (88.4 +  ( 13.4 * (Integer.parseInt(pounds.getText().toString()) *.454))) +
                                (4.8 * ((((Integer.parseInt(feet.getText().toString()) * 12))+ Integer.parseInt(inches.getText().toString())) * 2.54)) -
                                (5.68 * Integer.parseInt(years.getText().toString()));
                    }
                    else if (rb_female.isChecked()){
                        bmr_value = (447.6 +  ( 9.25 * (Integer.parseInt(pounds.getText().toString()) *.454))) +
                                (3.10 * ((((Integer.parseInt(feet.getText().toString()) * 12))+ Integer.parseInt(inches.getText().toString())) * 2.54)) -
                                (4.33 * Integer.parseInt(years.getText().toString())) ;
                    }

                    // custom dialog
                    final Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.bmr_dialog);
                    dialog.setTitle("Title...");

                    // set the custom dialog components
                    TextView text = (TextView) dialog.findViewById(R.id.tv_results);
                    text.setText(String.valueOf(Math.round(bmr_value)));

                    Button tryagain = (Button) dialog.findViewById(R.id.btn_tryagain);
                    Button home = (Button) dialog.findViewById(R.id.btn_home);

                    // if button is clicked, close the custom dialog
                    tryagain.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    home.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(BMR.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });
                    dialog.show();
                }
            }
        });
    }

    public int validInput(EditText age, EditText feet, EditText inches, EditText pounds)  {
        Integer a = Integer.parseInt(age.getText().toString());
        Integer b = Integer.parseInt(feet.getText().toString());
        Integer c = Integer.parseInt(inches.getText().toString());
        Integer d = Integer.parseInt(pounds.getText().toString());

        int counter = 0;
        if (a > 0 && a <100) {
            counter+=1;
        }
        if (b > 0 && b < 9){
            counter+=1;
        }
        if(c >=0 && c <12){
            counter+=1;
        }
        if(d > 0 && d < 401){
            counter+=1;
        }
        return counter;
    }

    private boolean isEmpty(EditText etText)  {
        return etText.getText().toString().trim().length() == 0;
    }
}
