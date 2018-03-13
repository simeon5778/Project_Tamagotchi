package com.example.tamagotchi.project_tamagotchi;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;


public class GameSession extends AppCompatActivity {

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    ProgressBar healthBar;
    ProgressBar hungerBar;
    ProgressBar happinessBar;

    String name;
    String birthday;
    int age;
    int level;
    int hunger;
    int health;
    int happiness;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_session);

        healthBar = (ProgressBar) findViewById(R.id.healthBar);
        hungerBar = (ProgressBar) findViewById(R.id.hungerBar);
        happinessBar = (ProgressBar) findViewById(R.id.happinessBar);

        sharedPref = getSharedPreferences("data", Context.MODE_PRIVATE);
        editor = sharedPref.edit();


        name = sharedPref.getString("name", "");
        birthday = sharedPref.getString("birthday", "");
        age = sharedPref.getInt("age", 0);
        level = sharedPref.getInt("level", 0);
        hunger = sharedPref.getInt("hunger", 0);
        health = sharedPref.getInt("health", 0);
        happiness = sharedPref.getInt("happiness", 0);

        healthBar.setProgress(health);
        hungerBar.setProgress(hunger);
        happinessBar.setProgress(happiness);


    }

    public void feed(View view) {

        hunger = hunger + 1;

        hungerBar.setProgress(hunger);

        editor.putInt("hunger",hunger);
        editor.apply();

    }

    public void play() {

    }






}
