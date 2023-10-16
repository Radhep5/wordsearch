package com.example.demo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.GridPane;

public class HelloController {

    public HelloController() {

    }

    @FXML
    Label lblTurn, lblResult, lblTaken;
    @FXML
    Button btnStart;
    @FXML
    GridPane gdpPlayGrid;

    private int wordSearchSize = 15;
    private GridSpot[][] board = new GridSpot[wordSearchSize][wordSearchSize];
    private Button[][] boardSpotsBTN = new Button[wordSearchSize][wordSearchSize];
    int rowIndex, colIndex;

    int row;
    int column;
    int orientation;
    int horizontal = 1;
    int vertical = 2;
    int diagonal = 3;
//    String word = "cat";
    String[] words = { "monkey", "cat"};

    @FXML
    private void start() {
        gdpPlayGrid.setPadding(new Insets(10));
        gdpPlayGrid.setGridLinesVisible(true);
        gdpPlayGrid.setAlignment(Pos.CENTER);
//        btnStart.setDisable(true);
        for (int i = 0; i < boardSpotsBTN.length; i++) {
            for (int j = 0; j < boardSpotsBTN.length; j++) {
                boardSpotsBTN[i][j] = new Button();
                boardSpotsBTN[i][j].setText("-");
                boardSpotsBTN[i][j].setMinHeight(30);
                boardSpotsBTN[i][j].setMinWidth(30);
                boardSpotsBTN[i][j].setPrefHeight(30);
                boardSpotsBTN[i][j].setPrefWidth(30);
                board[i][j] = new GridSpot();
                gdpPlayGrid.add(boardSpotsBTN[i][j], j, i);
            }
        }

        row = 13; //(int)(Math.random() * board.length);
        column = 13; //(int)(Math.random() * board.length);
        orientation = (int) (Math.random() * 3 + 1);

        for(int k = 0; k < words.length; k++){
            printWords2(words[k]);
        }


//        for (int i = 0; i < boardSpotsBTN.length; i++) {
//            for (int k = 0; k < boardSpotsBTN.length; k++) {
//                if (board[i][k].getBtnText().equals("-")) {
//                    board[i][k].changeBtnText("a");
//                }
//            }
//        }

        EventHandler z = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                rowIndex = GridPane.getRowIndex(((Button) t.getSource()));
                colIndex = GridPane.getColumnIndex(((Button) t.getSource()));
                System.out.println(rowIndex);
                System.out.println(colIndex);
                lblTaken.setText("-");
                display(rowIndex, colIndex, t);

            }
        };

        for (int i = 0; i < boardSpotsBTN.length; i++) {
            for (int j = 0; j < boardSpotsBTN.length; j++) {
                boardSpotsBTN[i][j].setOnAction(z);
            }
        }
    }

    public boolean checkOnGrid(String word) {
        for (int letterIndex = 0; letterIndex < word.length(); letterIndex++) {
            if ((row + letterIndex >= board.length || row + letterIndex < 0) || (column + letterIndex >= board.length || column + letterIndex < 0)) {
                return false;
            }
        }
        //go through each pos of the buttons and compared that to the

        return true;
    }

    public void printWords2(String word) {
        while (!checkOnGrid(word)) {
            System.out.println("not fit");
            row = (int) (Math.random() * board.length);
            column = (int) (Math.random() * board.length);
            checkOnGrid(word);
        }
        printWords(row, column, orientation, word);
        row = (int) (Math.random() * board.length);
        column = (int) (Math.random() * board.length);
        orientation = (int) (Math.random() * 3 + 1);
        System.out.println("after row" + row);
        System.out.println("after col " + column);
    }

    public void reverseWord(String word){
        String newWord = "";
        int letterLength = word.length();
        for(int letterIndex = 0; letterIndex < word.length(); letterIndex++){
            newWord = newWord + word.charAt(letterLength - letterIndex - 1);
        }
        System.out.println(newWord);
        words[0] = newWord;
    }

    public void printWords(int randRow, int randColumn, int randOrientation, String word) {
        for (int letterIndex = 0; letterIndex < word.length(); letterIndex++) {
            if (randOrientation == horizontal) {
                boardSpotsBTN[randRow + letterIndex][randColumn].setText(word.substring(letterIndex, letterIndex + 1)); //down
            }
            if (randOrientation == vertical) {
                boardSpotsBTN[randRow][randColumn + letterIndex].setText(word.substring(letterIndex, letterIndex + 1)); //right
            }
            if (randOrientation == diagonal) {
                boardSpotsBTN[randRow + letterIndex][randColumn + letterIndex].setText(word.substring(letterIndex, letterIndex + 1)); //diagonal down right
            }
        }
    }

    public boolean checkDone() {
        return false;
    }

    public void display(int row, int col, ActionEvent t) {

    }

    public void reset() {

    }
}