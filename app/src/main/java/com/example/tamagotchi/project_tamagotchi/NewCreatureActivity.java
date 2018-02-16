package com.example.tamagotchi.project_tamagotchi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NewCreatureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_creature);
    }

    public void goToStartScreen(View view) {

        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }


}
