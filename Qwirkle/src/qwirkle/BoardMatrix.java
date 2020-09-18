/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
package qwirkle;

/**
 *
 * @author Luis Diego Alemán
 */
public class BoardMatrix {
    
    
    private BoardMatrix board = new BoardMatrix();
    private int width = 2;
    private int height = 2;
    private String[][] matrix;
    
    private BoardMatrix(){
        matrix = new String [height][width];
    }
    
    public BoardMatrix getBoardMatrix(){
        return board;
    }
    
    public void clearBoard(){
        board = new BoardMatrix();  
    }
    
    public void setTile(String piece, int x, int y){
        matrix[y][x] = piece;
    }
    
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    
    public String getTile(int x,int y){
        return matrix[y][x]; 
    }
    //*************************************
    //*****TO-DO Programar el resize*******
    //*************************************
    
}

