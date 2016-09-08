package com.wolberg.burnedcaloriescalculator;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.NumberFormat;

/**this is broken and will not run***/

public class BurnedCaloriesCalculatorActivity extends AppCompatActivity implements TextView.OnEditorActionListener {

    //defining variables for widgets
    private EditText weightTextView;
    private EditText PersonNameEditText;
    private TextView MilesRanTextView;
    private TextView CaloriesBurnedNumber;
    private TextView BmiTextView;
    private SeekBar MilesRanSeekBar;
    private Spinner spinner1;
    private Spinner spinner2;

    //definng instance variables
    private String weightString ="";
    private int milesRan=0;

    //define shared preferences
    private SharedPreferences savedValues;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burned_calories_calculator);

        //get references to widgets
        weightTextView = (EditText) findViewById(R.id.weightTextView);
        PersonNameEditText = (EditText) findViewById(R.id.PersonNameEditText);
        MilesRanTextView = (TextView) findViewById(R.id.MilesRanTextView);
        CaloriesBurnedNumber = (TextView) findViewById(R.id.CaloriesBurnedNumber);
        BmiTextView = (TextView) findViewById(R.id.BmiTextView);
        MilesRanSeekBar = (SeekBar) findViewById(R.id.MilesRanSeekBar);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);

        //set the listeners
        weightTextView.setOnEditorActionListener(this);

    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent)
    {
        if(actionId== EditorInfo.IME_ACTION_DONE ||
                actionId==EditorInfo.IME_ACTION_UNSPECIFIED)
        {calculateAndDisplay();}
        return false;
    }


    private void calculateAndDisplay(){


        weightString = weightTextView.getText().toString();
        float weightAmount;
        if(weightString.equals("")){
            weightAmount = 0;
        }
        else{
            weightAmount = Float.parseFloat(weightString);
        }


        int progress = MilesRanSeekBar.getProgress();
         milesRan =progress;
        MilesRanTextView.setText(milesRan);
        //float caloriesBurned = (float) 0.75*weightAmount*milesRan;

        //float bmi = (float) (weightAmount * 703) / ((12 * feet + inches) * (12 * feet + inches));


    }

    @Override
    protected void onPause(){
        //save the instance variables
        SharedPreferences.Editor editor = savedValues.edit();
        editor.putString("weightString",weightString);
        editor.putString("MilesRanTextView", String.valueOf(MilesRanTextView));
        editor.putString("feet", String.valueOf(spinner1));
        editor.putString("inches", String.valueOf(spinner2));
        editor.commit();
        super.onPause();
    }
    @Override
    protected void onResume(){
        //save the instance variables
        super.onResume();
        //get instance variables
        weightString = savedValues.getString("weightString","");
        //MilesRanTextView = savedValues.getString("MilesRanTextView","");
        //feet = savedValues.getString("feet","");
       //inches = savedValues.getString("inches","");

        //set on widgets (incomplete)
        weightTextView.setText(weightString);


        //calculate and display
        calculateAndDisplay();
    }


}
