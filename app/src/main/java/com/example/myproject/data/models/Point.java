package com.example.myproject.data.models;

public class Point {

    private String icon;
    private String name;
    private double latitude;
    private double longitude;
    private String description;

    public Point(String name,  double latitude, double longitude) {
        this.name = name;
        this.icon = "default_question_mark";
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Point(String name, String icon, double latitude, double longitude) {
        this(name, latitude, longitude);
        this.icon = icon;
    }

    public Point(String name, String icon, double latitude, double longitude, String description){
        this(name, icon, latitude, longitude);
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getDescription() {
        return description;
    }
//    private int img;
//    private String name;
//    private String description;
//    private int itemImg;
//    private boolean isChecked;
//
//    public Point(int img, String name, String description, int itemImg) {
//        this.img = img;
//        this.name = name;
//        this.description = description;
//        this.itemImg = itemImg;
//        isChecked = false;
//    }
//
//    public int getItemImg() {
//        return itemImg;
//    }
//
//    public void setItemImg(int itemImg) {
//        this.itemImg = itemImg;
//    }
//
//    public String getTheoryDescription() {
//        return description;
//    }
//
//    public void setTheoryDescription(String description) {
//        this.description = description;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getImg() {
//        return img;
//    }
//
//    public void setImg(int img) {
//        this.img = img;
//    }
//
//    public boolean isChecked() {
//        return isChecked;
//    }
//
//    public void setChecked(boolean checked) {
//        isChecked = checked;
//    }
}
