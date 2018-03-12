package com.example.tamagotchi.project_tamagotchi;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class NewCreatureActivity extends AppCompatActivity {

    Creature creature;
    EditText nameField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_creature);
        nameField = (EditText) findViewById(R.id.nameField);

    }

    public void startGame(View view) {

        creature = new Creature(nameField.getText().toString());

        Intent intent = new Intent(this, GameSession.class);
        startActivity(intent);



    }





}
