package com.example.tamagotchi.project_tamagotchi;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Creature {

    private String name;

    private int age;            //Every day will increase the age by one.
    private String birthday;    //Date for creation
    private int level;

    private int hunger;         // All of these attributes will
    private int happiness;      // range from 0-20.
    private int health;         //




    public Creature(String name) {

        this.name = name;
        this.birthday = getDate();

        this.age = 0;
        this.hunger = 10;
        this.happiness = 10;
        this.health = 20;
        this.level = 1;

    }


    private String getDate(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
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
