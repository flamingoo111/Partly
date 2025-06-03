package com.example.myapplication;

public class Vacation {
    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public int getViwers() {
        return viwers;
    }

    public void setViwers(int viwers) {
        this.viwers = viwers;
    }

    int viwers = 0;
    String name = "";
    String place = "";
    String requirements = "";
    int date = 0;
}