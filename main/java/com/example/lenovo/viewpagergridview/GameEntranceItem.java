package com.example.lenovo.viewpagergridview;

public class GameEntranceItem {
    private String name = "";
    private int image;

    public GameEntranceItem(String name, int image) {
        this.image = image;
        this.name = name;
    }
    public int getImage() {
        return image;
    }
    public String getName() {
        return name;
    }

}