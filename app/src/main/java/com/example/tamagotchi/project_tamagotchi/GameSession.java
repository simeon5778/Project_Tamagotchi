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
import android.widget.TextView;

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
    TextView levelLabel;

    String name;
    long birthday;
    long levelProgress;
    long startTime;
    long exitTime;
    int age;
    int level;
    int hunger;
    int health;
    int happiness;
    int ableToFeed = 0;
    int ableToPlay = 0;
    boolean isBusy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_session);

        initiateStartup();

        creatureDegeneration();

        goToIdle();

    }

    @Override
    protected void onStop() {
        super.onStop();

        exitTime = System.nanoTime();

        levelProgress = levelProgress + (exitTime - startTime);

        editor.putInt("health", health);
        editor.putInt("hunger", hunger);
        editor.putInt("happiness", happiness);
        editor.putLong("exitTime", exitTime);
        editor.putLong("levelProgress", levelProgress);
        editor.apply();

    }

    public void feed(View view) throws InterruptedException {

        if (!isBusy) {
            isBusy =true;

            if (hunger < 20 && ableToFeed < 5 && health > 0) {

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
                        isBusy =false;
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

        if (!isBusy) {
            isBusy = true;

            SlimeplayingAnimation slimeplayingAnimation = new SlimeplayingAnimation();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.framelayout, slimeplayingAnimation, "eatingAnimation");
            transaction.commit();

            final Handler feedHandler = new Handler();
            feedHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    goToIdle();
                    isBusy =false;
                }
            }, 3550);

            if (happiness < 20 && ableToPlay < 5 && health > 0) {

            ableToPlay++;

            happiness++;

            happinessBar.setProgress(happiness);

            editor.putInt("happiness", happiness);
            editor.apply();
        }
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
        levelLabel = (TextView) findViewById(R.id.levelLabel);

        name = sharedPref.getString("name", "");
        birthday = sharedPref.getLong("birthday", 0);
        levelProgress = sharedPref.getLong("levelProgress", 0);
        age = sharedPref.getInt("age", 0);
        level = sharedPref.getInt("level", 0);
        hunger = sharedPref.getInt("hunger", 0);
        health = sharedPref.getInt("health", 0);
        happiness = sharedPref.getInt("happiness", 0);
        exitTime = sharedPref.getLong("exitTime", 0);
        startTime = System.nanoTime();

        if (exitTime != 0) {

            calculateDegeneration(startTime, exitTime);

        }

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

    public void calculateDegeneration(long startTime, long exitTime) {

        long timeDifference = startTime - exitTime;

        //Convert nanoseconds to seconds
        timeDifference = timeDifference / 1000000000;

        //Convert seconds to minutes
        timeDifference = timeDifference / 4;

        int newHunger = hunger - (int) timeDifference;
        int newHappiness = happiness - (int) timeDifference;

        while (timeDifference > 0) {

            if (hunger > 14) {
                health++;
                hunger--;
                happiness--;

            } else {

                if (health > 0) {

                    if (newHunger < 0 && newHappiness < 0) {

                        health = health - 2;
                        newHunger++;
                        newHappiness++;

                    } else if (newHunger < 0) {

                        health--;
                        newHunger++;

                    }
                    timeDifference--;
                } else {
                    timeDifference = 0;
                    newHunger = 0;
                    newHappiness = 0;
                    // MAke something that stops the whole thing when he dies. TODO
                }
                hunger = newHunger;
                happiness = newHappiness;
            }
        }

        //Progress for leveling up
        if (exitTime > 0) {

            if (level < 5) {

                levelProgress = levelProgress + (startTime - exitTime);

                long convertedLevelProgress = levelProgress / 1000000000;

                if (convertedLevelProgress > 80) {

                    levelLabel.setText("Lvl. 5");
                    level = 5;

                } else if (convertedLevelProgress > 60) {

                    levelLabel.setText("Lvl. 4");
                    level = 4;

                } else if (convertedLevelProgress > 40) {

                    levelLabel.setText("Lvl. 3");
                    level = 3;

                } else if (convertedLevelProgress > 20) {

                    levelLabel.setText("Lvl. 2");
                    level = 2;

                }
            }
        }

        editor.putInt("health", health);
        editor.putInt("happiness", happiness);
        editor.putInt("hunger", hunger);
        editor.putLong("levelProgress", levelProgress);
        editor.putInt("level", level);
        editor.apply();

    }



}
