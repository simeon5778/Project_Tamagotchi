package com.example.tamagotchi.project_tamagotchi;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Creature {

    private String name;
    private long birthday;    //Date for creation
    private int level;

    private int hunger;         // All of these attributes will
    private int happiness;      // range from 0-20.
    private int health;         //




    public Creature(String name) {

        this.name = name;
        this.birthday = System.nanoTime();

        this.hunger = 15;
        this.happiness = 15;
        this.health = 20;
        this.level = 1;

    }


    public String getName() {
        return name;
    }

    public long getBirthday() {
        return birthday;
    }

    public int getLevel() {
        return level;
    }

    public int getHunger() {
        return hunger;
    }

    public int getHappiness() {
        return happiness;
    }

    public int getHealth() {
        return health;
    }

}
