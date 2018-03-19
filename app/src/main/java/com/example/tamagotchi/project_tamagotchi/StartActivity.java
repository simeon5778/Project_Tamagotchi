package com.example.tamagotchi.project_tamagotchi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    SharedPreferences sharedPref;
    int health;
    long exitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = getSharedPreferences("data", Context.MODE_PRIVATE);

        health = sharedPref.getInt("health", 0);
        exitTime = sharedPref.getLong("exitTime", 0);
    }

    public void goToCreateCreatureActivity(View view) {

        Intent intent = new Intent(this, NewCreatureActivity.class);
        startActivity(intent);
    }

    public void exitProgram(View view) {

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void continueGame(View view) {

        //If statement that checks if we have a creature already.
        if (exitTime > 0) {

            if (health > 0) {

                Intent intent = new Intent(this, GameSession.class);
                startActivity(intent);

            } else {

                Intent intent = new Intent(this, Gameover.class);
                startActivity(intent);

            }
        } else {

            Context context = getApplicationContext();
            CharSequence text = "You need to create a creature first!";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            View viewContinue = toast.getView();
            viewContinue.getBackground().setColorFilter(Color.rgb(255, 209, 209), PorterDuff.Mode.SRC_IN);
            TextView textView = viewContinue.findViewById(android.R.id.message);
            textView.setTextColor(Color.BLACK);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

        }

    }

}
