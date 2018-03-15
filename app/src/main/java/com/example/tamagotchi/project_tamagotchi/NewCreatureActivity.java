package com.example.tamagotchi.project_tamagotchi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;



public class NewCreatureActivity extends AppCompatActivity {

    SharedPreferences sharedPref;

    Creature creature;
    EditText nameField;
    CheckBox checkBox;
    boolean overwrite = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_creature);

        sharedPref = getSharedPreferences("data", Context.MODE_PRIVATE);
        nameField = (EditText) findViewById(R.id.nameField);
        checkBox = (CheckBox) findViewById(R.id.overwriteCheckBox);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (overwrite) {
                    overwrite = false;
                } else {
                    overwrite = true;
                }
            }
        });

    }

    public void startGame(View view) {

        if (overwrite) {

            creature = new Creature(nameField.getText().toString());

            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("name", creature.getName().toString());
            editor.putLong("birthday", creature.getBirthday());
            editor.putLong("levelProgress", 0);
            editor.putInt("age", creature.getAge());
            editor.putInt("level", creature.getLevel());
            editor.putInt("health", creature.getHealth());
            editor.putInt("hunger", creature.getHunger());
            editor.putInt("happiness", creature.getHappiness());
            editor.putLong("exitTime", 0);
            editor.apply();

            Intent intent = new Intent(this, GameSession.class);
            startActivity(intent);

        }

    }

}
