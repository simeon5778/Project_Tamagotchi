package com.example.tamagotchi.project_tamagotchi;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class GameSession extends AppCompatActivity {

    SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_session);

        sharedPref = getSharedPreferences("data", Context.MODE_PRIVATE);


        String name = sharedPref.getString("name", "");
        String birthday = sharedPref.getString("birthday", "");
        int age = sharedPref.getInt("age", 0);
        int level = sharedPref.getInt("level", 0);
        int hunger = sharedPref.getInt("hunger", 0);
        int health = sharedPref.getInt("health", 0);
        int happiness = sharedPref.getInt("happiness", 0);



    }

    //Här när man trycker bakåt så vill man spara alla variabler ifrån vårat creature? Singleton krävs?




}
