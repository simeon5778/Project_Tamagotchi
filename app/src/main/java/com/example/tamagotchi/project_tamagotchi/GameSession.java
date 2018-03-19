package com.example.tamagotchi.project_tamagotchi;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
    ImageView poopImage;

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
    int ableToFeed;
    int ableToPlay;
    boolean isBusy;
    boolean poop;


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

    public void feed(View view) {

        try {

            if (hunger < 20 && ableToFeed < 5 && health > 0 && isBusy == false) {

                isBusy = true;

                SlimeeatingAnimation slimeeatingAnimation = new SlimeeatingAnimation();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.framelayout, slimeeatingAnimation, "eatingAnimation");
                transaction.commit();

                final Handler feedHandler = new Handler();
                feedHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        goToIdle();
                        isBusy = false;
                    }
                }, 3200);

                ableToFeed++;

                hunger++;

                hungerBar.setProgress(hunger);

                editor.putInt("hunger", hunger);
                editor.apply();

            } else if (hunger < 20 && ableToFeed > 4 && health > 0 && isBusy == false) {

                Context context = getApplicationContext();
                CharSequence text = "Unable to feed. Please try again later!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                View viewFeed = toast.getView();
                viewFeed.getBackground().setColorFilter(Color.CYAN, PorterDuff.Mode.SRC_IN);
                TextView textView = viewFeed.findViewById(android.R.id.message);
                textView.setTextColor(Color.BLACK);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

            }

        } catch (Exception e) {
            //Only to prevent errors with animations.
        }
    }

    public void play(View view) {

        try {

            if (happiness < 20 && ableToPlay < 5 && health > 0 && isBusy == false) {

                isBusy = true;

                SlimeplayingAnimation slimeplayingAnimation = new SlimeplayingAnimation();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.framelayout, slimeplayingAnimation, "playingAnimation");
                transaction.commit();

                final Handler feedHandler = new Handler();
                feedHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        goToIdle();
                        isBusy = false;
                    }
                }, 3550);

                ableToPlay++;

                happiness++;

                happinessBar.setProgress(happiness);

                editor.putInt("happiness", happiness);
                editor.apply();

            } else if (happiness < 20 && ableToPlay > 4 && health > 0 && isBusy == false) {

                Context context = getApplicationContext();
                CharSequence text = "Unable to play. Please try again later!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                View viewPlay = toast.getView();
                viewPlay.getBackground().setColorFilter(Color.CYAN, PorterDuff.Mode.SRC_IN);
                TextView textView = viewPlay.findViewById(android.R.id.message);
                textView.setTextColor(Color.BLACK);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

            }

        } catch (Exception e) {
            //Only to prevent errors with animations.
        }

    }

    public void clean(View view) {
        poop = false;
        poopImage.setVisibility(View.INVISIBLE);
        editor.putBoolean("poop", poop);
        editor.apply();
    }


    public void goToIdle() {

        try {

            IdleAnimation idleAnimation = new IdleAnimation();
            transaction = manager.beginTransaction();
            transaction.replace(R.id.framelayout, idleAnimation, "Idleanimation");

            transaction.commit();

        } catch (Exception e) {
            //Only to prevent errors with animations.
        }

    }

    public void initiateStartup() {

        sharedPref = getSharedPreferences("data", Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        manager = getFragmentManager();


        healthBar = (ProgressBar) findViewById(R.id.healthBar);
        hungerBar = (ProgressBar) findViewById(R.id.hungerBar);
        happinessBar = (ProgressBar) findViewById(R.id.happinessBar);
        levelLabel = (TextView) findViewById(R.id.levelLabel);
        poopImage = (ImageView) findViewById(R.id.poop);

        name = sharedPref.getString("name", "");
        birthday = sharedPref.getLong("birthday", 0);
        levelProgress = sharedPref.getLong("levelProgress", 0);
        age = sharedPref.getInt("age", 0);
        level = sharedPref.getInt("level", 0);
        hunger = sharedPref.getInt("hunger", 0);
        health = sharedPref.getInt("health", 0);
        happiness = sharedPref.getInt("happiness", 0);
        exitTime = sharedPref.getLong("exitTime", 0);
        poop = sharedPref.getBoolean("poop", false);
        startTime = System.nanoTime();

        levelLabel.setText("Lvl. " + level);


        if (health < 1) {
            Intent intent = new Intent(this, Gameover.class);
            startActivity(intent);
        } else {

            if (exitTime != 0) {

                calculateDegeneration(startTime, exitTime);

            }

            if (poop == true) {

                poopImage.setVisibility(View.VISIBLE);

            }

            hungerBar.setProgress(hunger);
            healthBar.setProgress(health);
            happinessBar.setProgress(happiness);
        }
    }

    public void creatureDegeneration() {

        final Handler degenerationHandler = new Handler();
        final Timer timer = new Timer();

        final TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {

                if (health < 1) {

                    timer.cancel();
                    showGameOverScreen();

                }

                degenerationHandler.post(new Runnable() {

                    @Override
                    public void run() {

                        try {

                            if (hunger == 20) {
                                poop = true;
                                poopImage.setVisibility(View.VISIBLE);
                                editor.putBoolean("poop", poop);
                            }

                            if (health > 0) {

                                if (health < 20 && hunger > 14) {

                                    health++;

                                }

                                if (hunger == 0 && happiness == 0) {

                                    health = health - 2;

                                } else if (hunger == 0) {

                                    health--;

                                }

                                if (happiness > 0 && poop == false) {

                                    happiness--;

                                } else if (happiness > 0 && poop == true) {

                                    happiness = happiness - 2;

                                }

                                if (hunger > 0) {

                                    hunger--;

                                }

                            }

                            ableToFeed = 0;
                            ableToPlay = 0;

                            healthBar.setProgress(health);
                            hungerBar.setProgress(hunger);
                            happinessBar.setProgress(happiness);

                            editor.putInt("hunger", hunger);
                            editor.putInt("happiness", happiness);
                            editor.putInt("health", health);
                            editor.apply();

                        } catch (Exception e) {
                            System.out.println("Error in degeneration.");
                        }
                    }
                });
            }
        };
        timer.schedule(timerTask, 0, 25000);

    }

    public void calculateDegeneration(long startTime, long exitTime) {

        long timeDifference = startTime - exitTime;

        //Convert nanoseconds to seconds
        timeDifference = timeDifference / 1000000000;

        //Convert seconds to minutes
        timeDifference = timeDifference / 25;

        if (hunger == 20) {
            poop = true;
        }

        //Loop to calculate the new stats.
        while (timeDifference > 0) {

            //Statement for checking if our creature should regain health while the app is exited.
            if (hunger > 14) {

                health++;

            }

            if (health < 1) {

                break;

            }

            if (poop == true) {

                happiness = happiness - 2;

            } else {

                happiness--;

            }

            hunger--;

            if (hunger < 1 && happiness < 1) {

                health = health - 2;

            } else if (hunger < 1) {

                health--;

            }

            timeDifference--;
        }

        //If a negative value is reached set the variables to 0.
        if (hunger < 0) {
            hunger = 0;
        }
        if (happiness < 0) {
            happiness = 0;
        }

        //Progress for leveling up
        if (exitTime > 0) {

            boolean levelUp = false;

            if (level < 5) {

                levelProgress = levelProgress + (startTime - exitTime);

                long convertedLevelProgress = levelProgress / 1000000000;

                if (convertedLevelProgress > 80) {

                    levelLabel.setText("Lvl. 5");
                    level = 5;
                    levelUp = true;

                } else if (convertedLevelProgress > 60) {

                    levelLabel.setText("Lvl. 4");
                    level = 4;
                    levelUp = true;

                } else if (convertedLevelProgress > 40) {

                    levelLabel.setText("Lvl. 3");
                    level = 3;
                    levelUp = true;

                } else if (convertedLevelProgress > 20) {

                    levelLabel.setText("Lvl. 2");
                    level = 2;
                    levelUp = true;

                }
            }
            if (levelUp && health > 0) {

                Context context = getApplicationContext();
                CharSequence text = "Congratulations! You reached level " + level + "!";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                View view = toast.getView();
                view.getBackground().setColorFilter(Color.CYAN, PorterDuff.Mode.SRC_IN);
                TextView textView = view.findViewById(android.R.id.message);
                textView.setTextColor(Color.BLACK);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }

        editor.putInt("health", health);
        editor.putInt("happiness", happiness);
        editor.putInt("hunger", hunger);
        editor.putLong("levelProgress", levelProgress);
        editor.putInt("level", level);
        editor.putBoolean("poop", poop);
        editor.apply();

    }

    public void showGameOverScreen() {

        Intent intent = new Intent(this, Gameover.class);
        startActivity(intent);
    }

}
