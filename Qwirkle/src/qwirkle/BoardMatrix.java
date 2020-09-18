/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
package qwirkle;
/**
 *
 * @author Luis Diego Alemán
 */
public class BoardMatrix {
    
    
    private static BoardMatrix board = new BoardMatrix();
    private int columns = 2;
    private int lines = 2;
    private String[][] matrix;
    
    private BoardMatrix(){
        matrix = new String [lines][columns];
        fillMatrix();
    }
    private void fillMatrix(){
        for(int i = 0; i < lines; i++){
            for(int j = 0; i < columns; j++){
                matrix[i][j] = "";
            }
        }
    }
    
    public static BoardMatrix getBoardMatrix(){
        return board;
    }
    
    public void clearBoard(){
        board = new BoardMatrix();  
    }
    
    public void setTile(String piece, int line, int column){
        matrix[line][column] = piece;
    }
    
    public int getLines(){
        return columns;
    }
    public int getColumns(){
        return lines;
    }
    
    public String getTile(int line,int column){
        return matrix[line][column]; 
    }
    //*************************************
    //*****TO-DO Programar el resize*******
    //*************************************
    
}



