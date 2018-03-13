package com.example.tamagotchi.project_tamagotchi;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class GameSession extends AppCompatActivity {

    SharedPreferences sharedPref;
    FragmentManager manager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_session);

        manager = getFragmentManager();
        sharedPref = getSharedPreferences("data", Context.MODE_PRIVATE);


        String name = sharedPref.getString("name", "");
        String birthday = sharedPref.getString("birthday", "");
        int age = sharedPref.getInt("age", 0);
        int level = sharedPref.getInt("level", 0);
        int hunger = sharedPref.getInt("hunger", 0);
        int health = sharedPref.getInt("health", 0);
        int happiness = sharedPref.getInt("happiness", 0);

        chooseAnimationAndStartIt();



    }

    //Här när man trycker bakåt så vill man spara alla variabler ifrån vårat creature? Singleton krävs?



    public void chooseAnimationAndStartIt() {
        IdleAnimation idleAnimation = new IdleAnimation();
        FragmentTransaction transaction=manager.beginTransaction();

        IdleAnimation idleAnimation1 = new IdleAnimation();

        transaction.add(R.id.framelayout,idleAnimation,"Idleanimation");

        transaction.commit();

    }


}
