package com.example.myapplication;

public class JobFormSet {
    public String name;
    public String category;
    public String place;
    public String shortDescription;
    public String longDescription;
    public Long price;
    public Long number;
    public  String user;
//    public int id;
//    /*    public boolean isLiked;*/

    public JobFormSet(String name, String category, String place, String shortDescription, String longDescription, Long price, Long number, String user) {
        this.name = name;
        this.category = category;
        this.place = place;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.price = price;
        this.number = number;
        this.user = user;
    }
    public JobFormSet(){}
}
