package com.jose.simplemacros;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Macros extends AppCompatActivity {

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_macros);

        //EditText
        final EditText years = (EditText) findViewById(R.id.et_years);
        final EditText feet = (EditText) findViewById(R.id.et_feet);
        final EditText inches = (EditText) findViewById(R.id.et_inches);
        final EditText pounds = (EditText) findViewById(R.id.et_pounds);
        //Gender Radio Buttons
        final RadioButton rb_male = (RadioButton) findViewById(R.id.rb_male);
        final RadioButton rb_female = (RadioButton) findViewById(R.id.rb_female);
        //Goal Radio Buttons
        final RadioButton rb_lose = (RadioButton) findViewById(R.id.rb_lose);
        final RadioButton rb_maintain = (RadioButton) findViewById(R.id.rb_maintain);
        final RadioButton rb_build = (RadioButton) findViewById(R.id.rb_build);
        //Activity Radio Buttons
        final RadioButton rb_sedentary = (RadioButton) findViewById(R.id.rb_sedentary);
        final RadioButton rb_light = (RadioButton) findViewById(R.id.rb_light);
        final RadioButton rb_moderate = (RadioButton) findViewById(R.id.rb_moderate);
        final RadioButton rb_active = (RadioButton) findViewById(R.id.rb_active);

        //Button
        final Button calc = (Button) findViewById(R.id.btn_calculate);
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

                    //Values for macronutrients
                    double bmr_value = 0;
                    double protein = 0;
                    double carbs = 0;
                    double fat = 0;
                    double calories = 0;

                    //male
                    if (rb_male.isChecked()){
                        bmr_value = (88.4 +  ( 13.4 * (Integer.parseInt(pounds.getText().toString()) *.454))) +
                                (4.8 * ((((Integer.parseInt(feet.getText().toString()) * 12))+ Integer.parseInt(inches.getText().toString())) * 2.54)) -
                                (5.68 * Integer.parseInt(years.getText().toString()));

                        //if lose weight is selected
                        if (rb_lose.isChecked()){
                            //if sedentary is selected
                            if(rb_sedentary.isChecked()){
                                calories = (bmr_value * 1.1)-500;
                                protein = (calories * .4)/4;
                                carbs = (calories *.37)/4;
                                fat = (calories * .23)/9;
                            }
                            //if lightly active is selected
                            if(rb_light.isChecked()){
                                calories = (bmr_value * 1.2)-500;
                                protein = (calories * .4)/4;
                                carbs = (calories *.37)/4;
                                fat = (calories * .23)/9;
                            }
                            //if moderately is selected
                            if(rb_moderate.isChecked()){
                                calories = (bmr_value * 1.3)-500;
                                protein = (calories * .4)/4;
                                carbs = (calories *.37)/4;
                                fat = (calories * .23)/9;
                            }
                            //if very active is selected
                            if(rb_active.isChecked()){
                                calories = (bmr_value * 1.4)-500;
                                protein = (calories * .4)/4;
                                carbs = (calories *.37)/4;
                                fat = (calories * .23)/9;
                            }
                        }

                        //if maintenance is selected
                        if (rb_maintain.isChecked()){
                            //if sedentary is selected
                            if(rb_sedentary.isChecked()){
                                calories = bmr_value * 1.2;
                                protein = (calories * .35)/4;
                                carbs = (calories *.45)/4;
                                fat = (calories * .2)/9;
                            }
                            //if lightly active is selected
                            if(rb_light.isChecked()){
                                calories = bmr_value * 1.3;
                                protein = (calories * .34)/4;
                                carbs = (calories *.45)/4;
                                fat = (calories * .21)/9;
                            }
                            //if moderately is selected
                            if(rb_moderate.isChecked()){
                                calories = bmr_value * 1.4;
                                protein = (calories * .33)/4;
                                carbs = (calories *.45)/4;
                                fat = (calories * .22)/9;
                            }
                            //if very active is selected
                            if(rb_active.isChecked()){
                                calories = bmr_value * 1.5;
                                protein = (calories * .32)/4;
                                carbs = (calories *.45)/4;
                                fat = (calories * .23)/9;
                            }
                        }

                        //if build muscle is selected
                        if (rb_build.isChecked()){
                            //if sedentary is selected
                            if(rb_sedentary.isChecked()){
                                calories = (bmr_value * 1.1)+500;
                                protein = (calories * .34)/4;
                                carbs = (calories *.45)/4;
                                fat = (calories * .21)/9;
                            }
                            //if lightly active is selected
                            if(rb_light.isChecked()){
                                calories = (bmr_value * 1.2)+500;
                                protein = (calories * .33)/4;
                                carbs = (calories *.46)/4;
                                fat = (calories * .21)/9;
                            }
                            //if moderately is selected
                            if(rb_moderate.isChecked()){
                                calories = (bmr_value * 1.3)+500;
                                protein = (calories * .32)/4;
                                carbs = (calories *.46)/4;
                                fat = (calories * .22)/9;
                            }
                            //if very active is selected
                            if(rb_active.isChecked()){
                                calories = (bmr_value * 1.4)+500;
                                protein = (calories * .31)/4;
                                carbs = (calories *.46)/4;
                                fat = (calories * .23)/9;
                            }
                        }
                    }
                    //female
                    else if (rb_female.isChecked()){
                        bmr_value = (447.6 +  ( 9.25 * (Integer.parseInt(pounds.getText().toString()) *.454))) +
                                (3.10 * ((((Integer.parseInt(feet.getText().toString()) * 12))+ Integer.parseInt(inches.getText().toString())) * 2.54)) -
                                (4.33 * Integer.parseInt(years.getText().toString())) ;

                        //if lose weight is selected
                        if (rb_lose.isChecked()){
                            //if sedentary is selected
                            if(rb_sedentary.isChecked()){
                                calories = (bmr_value * 1.2)-350;
                                protein = (calories * .4)/4;
                                carbs = (calories *.36)/4;
                                fat = (calories * .24)/9;
                            }
                            //if lightly active is selected
                            if(rb_light.isChecked()){
                                calories = (bmr_value * 1.3)-350;
                                protein = (calories * .4)/4;
                                carbs = (calories *.36)/4;
                                fat = (calories * .24)/9;
                            }
                            //if moderately is selected
                            if(rb_moderate.isChecked()){
                                calories = (bmr_value * 1.4)-350;
                                protein = (calories * .4)/4;
                                carbs = (calories *.36)/4;
                                fat = (calories * .24)/9;
                            }
                            //if very active is selected
                            if(rb_active.isChecked()){
                                calories = (bmr_value * 1.5)-350;
                                protein = (calories * .4)/4;
                                carbs = (calories *.36)/4;
                                fat = (calories * .24)/9;
                            }
                        }

                        //if maintenance is selected
                        if (rb_maintain.isChecked()){
                            //if sedentary is selected
                            if(rb_sedentary.isChecked()){
                                calories = bmr_value * 1.2;
                                protein = (calories * .34)/4;
                                carbs = (calories *.43)/4;
                                fat = (calories * .23)/9;
                            }
                            //if lightly active is selected
                            if(rb_light.isChecked()){
                                calories = bmr_value * 1.3;
                                protein = (calories * .33)/4;
                                carbs = (calories *.43)/4;
                                fat = (calories * .24)/9;
                            }
                            //if moderately is selected
                            if(rb_moderate.isChecked()){
                                calories = bmr_value * 1.4;
                                protein = (calories * .32)/4;
                                carbs = (calories *.43)/4;
                                fat = (calories * .25)/9;
                            }
                            //if very active is selected
                            if(rb_active.isChecked()){
                                calories = bmr_value * 1.5;
                                protein = (calories * .31)/4;
                                carbs = (calories *.43)/4;
                                fat = (calories * .26)/9;
                            }
                        }

                        //if build muscle is selected
                        if (rb_build.isChecked()) {
                            //if sedentary is selected
                            if (rb_sedentary.isChecked()) {
                                calories = (bmr_value * 1.2) + 350;
                                protein = (calories * .33) / 4;
                                carbs = (calories * .44) / 4;
                                fat = (calories * .23) / 9;
                            }
                            //if lightly active is selected
                            if (rb_light.isChecked()) {
                                calories = (bmr_value * 1.3) + 400;
                                protein = (calories * .33) / 4;
                                carbs = (calories * .45) / 4;
                                fat = (calories * .22) / 9;
                            }
                            //if moderately is selected
                            if (rb_moderate.isChecked()) {
                                calories = (bmr_value * 1.4) + 450;
                                protein = (calories * .32) / 4;
                                carbs = (calories * .45) / 4;
                                fat = (calories * .23) / 9;
                            }
                            //if very active is selected
                            if (rb_active.isChecked()) {
                                calories = (bmr_value * 1.5) + 500;
                                protein = (calories * .31) / 4;
                                carbs = (calories * .47) / 4;
                                fat = (calories * .24) / 9;
                            }
                        }
                    }

                    // custom dialog
                    final Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.macros_dialog);
                    dialog.setTitle("Title...");

                    // set the custom dialog components - text, image and button
                    TextView p = (TextView) dialog.findViewById(R.id.tv_protein);
                    TextView c = (TextView) dialog.findViewById(R.id.tv_carbs);
                    TextView f = (TextView) dialog.findViewById(R.id.tv_fat);
                    p.setText(String.valueOf(Math.round(protein)) + " grams");
                    c.setText(String.valueOf(Math.round(carbs)) + " grams");
                    f.setText(String.valueOf(Math.round(fat)) + " grams");

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
                            Intent intent = new Intent(Macros.this, MainActivity.class);
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
