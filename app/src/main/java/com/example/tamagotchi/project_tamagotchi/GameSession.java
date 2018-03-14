package com.example.tamagotchi.project_tamagotchi;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;


public class GameSession extends AppCompatActivity {

    SharedPreferences sharedPref;
    FragmentManager manager;
    SharedPreferences.Editor editor;
    FragmentTransaction transaction;

    ProgressBar healthBar;
    ProgressBar hungerBar;
    ProgressBar happinessBar;

    String name;
    long birthday;
    int age;
    int level;
    int hunger;
    int health;
    int happiness;
    int ableToFeed = 0;
    int ableToPlay = 0;
    boolean isCurrentlyEating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_session);

        initiateStartup();

        creatureDegeneration();

        goToIdle();


    }

    public void feed(View view) throws InterruptedException {

        if (!isCurrentlyEating) {
            isCurrentlyEating=true;

            if (hunger < 20 && ableToFeed < 5) {

                //Alex lagt till för att byta sen kanske måste Fixas TODO
                SlimeeatingAnimation slimeeatingAnimation = new SlimeeatingAnimation();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.framelayout, slimeeatingAnimation, "eatingAnimation");
                transaction.commit();

                final Handler feedHandler = new Handler();
                feedHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        goToIdle();
                        isCurrentlyEating=false;
                    }
                }, 3300);

                ableToFeed++;

                hunger++;

                hungerBar.setProgress(hunger);

                editor.putInt("hunger", hunger);
                editor.apply();
            }
        }

    }

    public void play(View view) {

        if (happiness < 20 && ableToPlay < 5) {

            ableToPlay++;

            happiness++;

            happinessBar.setProgress(happiness);

            editor.putInt("happiness", happiness);
            editor.apply();
        }

    }


    public void goToIdle() {

        IdleAnimation idleAnimation = new IdleAnimation();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.framelayout,idleAnimation,"Idleanimation");

        transaction.commit();

    }

    public void initiateStartup() {

        sharedPref = getSharedPreferences("data", Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        manager = getFragmentManager();


        healthBar = (ProgressBar) findViewById(R.id.healthBar);
        hungerBar = (ProgressBar) findViewById(R.id.hungerBar);
        happinessBar = (ProgressBar) findViewById(R.id.happinessBar);


        name = sharedPref.getString("name", "");
        birthday = sharedPref.getLong("birthday", 0);
        age = sharedPref.getInt("age", 0);
        level = sharedPref.getInt("level", 0);
        hunger = sharedPref.getInt("hunger", 0);
        health = sharedPref.getInt("health", 0);
        happiness = sharedPref.getInt("happiness", 0);

        hungerBar.setProgress(hunger);
        healthBar.setProgress(health);
        happinessBar.setProgress(happiness);

    }

    public void creatureDegeneration() {

         final Handler degenerationHandler = new Handler();
        Timer timer = new Timer();

        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {

                degenerationHandler.post(new Runnable() {

                    @Override
                    public void run() {

                        try {

                            if (health < 20 && hunger > 14) {

                                    health++;
                                    healthBar.setProgress(health);

                            }

                            if (health > 0) {

                                if (hunger == 0 && happiness == 0) {

                                        health = health - 2;
                                        healthBar.setProgress(health);

                                    } else if (hunger == 0) {

                                        health--;
                                        healthBar.setProgress(health);
                                    }

                                    editor.putInt("health", health);

                                }

                                if (happiness > 0) {

                                    happiness--;
                                    happinessBar.setProgress(happiness);
                                    editor.putInt("happiness", happiness);

                                }

                                if (hunger > 0) {

                                    hunger--;
                                    hungerBar.setProgress(hunger);
                                    editor.putInt("hunger", hunger);

                                }

                            ableToFeed = 0;
                            ableToPlay = 0;

                            editor.apply();

                        } catch(Exception e) {
                            System.out.println("Error in degeneration.");
                        }
                    }
                });
            }
        };
        timer.schedule(timerTask, 0, 8000);

    }



}
