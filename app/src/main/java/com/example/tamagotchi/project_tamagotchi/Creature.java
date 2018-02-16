package com.example.tamagotchi.project_tamagotchi;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Creature {

    private String name;

    private int age;            //Every day will increase age by one.
    private String birthday;    //Date for creation

    private int hunger;         //
    private int thirst;         // All of these attributes will
    private int happiness;      // range from 0-20.
    private int health;         //

    private enum Gender {Male, Female};
    private Gender sex;

    public Creature(String name, Gender genderOfChoice ) {
        this.name = name;
        this.age = 0;
        this.birthday = getDate();
        this.hunger = 10;
        this.thirst = 10;
        this.happiness = 10;
        this.health = 20;
        this.sex = genderOfChoice;
    }

    private String getDate(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }

}
