package com.example.tamagotchi.project_tamagotchi;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Creature {

    private String name;

    private int age;            //Every day will increase the age by one.
    private long birthday;    //Date for creation
    private int level;

    private int hunger;         // All of these attributes will
    private int happiness;      // range from 0-20.
    private int health;         //




    public Creature(String name) {

        this.name = name;
        this.birthday = System.nanoTime();

        this.age = 0;
        this.hunger = 19;
        this.happiness = 7;
        this.health = 15;
        this.level = 1;

    }


    public String getName() {
        return name;
    }

    public long getBirthday() {
        return birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
