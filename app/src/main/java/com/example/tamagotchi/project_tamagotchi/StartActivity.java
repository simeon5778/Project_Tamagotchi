package com.example.tamagotchi.project_tamagotchi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        Intent intent = new Intent(this, GameSession.class);
        startActivity(intent);

    }


}
