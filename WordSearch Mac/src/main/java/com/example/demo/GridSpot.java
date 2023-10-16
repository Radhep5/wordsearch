package com.example.demo;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GridSpot {
//    Image o,x,back;
//
//    Image pick;
//    //represents the turn of the player which is their spot
    int spot;
    private String btnText = "-";
    public GridSpot(){
        btnText = "-";
    }

    public String getBtnText() {
        return btnText;
    }

    public void changeBtnText(String letter){
        btnText = letter;
    }

}
