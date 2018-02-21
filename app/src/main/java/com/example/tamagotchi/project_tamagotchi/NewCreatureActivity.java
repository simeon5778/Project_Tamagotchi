package com.example.tamagotchi.project_tamagotchi;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class NewCreatureActivity extends AppCompatActivity {


    Button buttonMALE; //= (Button) findViewById(R.id.buttonMale);
    Button buttonFEMALE;// = (Button) findViewById(R.id.buttonFemale);

    private boolean genderMale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_creature);
        buttonMALE = (Button) findViewById(R.id.buttonMale);
        buttonFEMALE = (Button) findViewById(R.id.buttonFemale);
    }

    public void goToStartScreen(View view) {

        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }

    public void setGenderMale(View view) {
        genderMale = true;
        buttonMALE.setBackgroundColor(Color.rgb(30,145,88));
        buttonFEMALE.setBackgroundColor(Color.rgb(217,215,217));


    }

    public void setGenderFemale(View view) {
        genderMale = false;
        buttonFEMALE.setBackgroundColor(Color.rgb(30,145,88));
        buttonMALE.setBackgroundColor(Color.rgb(217,215,217));

    }


}
