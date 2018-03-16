package com.example.tamagotchi.project_tamagotchi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Gameover extends AppCompatActivity {

    ImageView ghost;
    ImageView tombstone;
    TextView finshText;
    Button share;
    Button mainMenu;

    AnimationDrawable ghostAnimation;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    int level;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);

        //initiate the objects
        finshText = findViewById(R.id.finishText);
        ghost = (ImageView) findViewById(R.id.ghost_id);
        tombstone = (ImageView) findViewById(R.id.tombstone_id);
        mainMenu = (Button) findViewById(R.id.mainmenuButton);
        share = (Button) findViewById(R.id.shareButton);


        //Start the ghost animation
        ghost.setBackgroundResource(R.drawable.ghost);
        ghostAnimation = (AnimationDrawable) ghost.getBackground();
        ghostAnimation.start();


        //set the text for our finishing text
        sharedPref = getSharedPreferences("data", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        level = sharedPref.getInt("level", 0);

        finshText.setText("Your pet has died of neglect. \n You reached level :  " + level + " ! \n congrats");




    }


    public void goToMainMenu (View view) {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }

    public void shareOnFb (View view) {
        Intent myIntent = new Intent(Intent.ACTION_SEND);
        myIntent.setType("text/plain");
        String shareBody = "Your body here";
        String shareSub = "Your Subject here";
        myIntent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
        myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
        startActivity(Intent.createChooser(myIntent, "Share using"));

    }
}
