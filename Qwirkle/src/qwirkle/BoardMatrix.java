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
    
    
    private static BoardMatrix board;
    private int columns = 20;
    private int lines = 20;
    private static String[][] matrix;
    private boolean hasChanges = true;
    
    private BoardMatrix(){
        this.matrix = new String [lines][columns];
        fillMatrix();
        matrix [10][12] = "r-1";
        matrix [10][11] = "b-2";
        matrix [11][12] = "g-3";
        matrix [13][12] = "y-4";
        matrix [11][13] = "o-5";
        matrix [12][12] = "p-6";
    }
    private void fillMatrix(){
        for(int i = 0; i < lines; i++){
            for(int j = 0; j < columns; j++){
                
                matrix[i][j] = "";
                //System.out.println(i + " " + j + ":" + matrix[i][j] );
                
            }
        }
    }
    
    public static BoardMatrix getBoardMatrix(){
        if(board == null){
           board = new BoardMatrix();
        }
        return board;
    }
    
    public void clearBoard(){
        board = new BoardMatrix();  
    }
    
    public void setTile(String piece, int line, int column){
        matrix[line][column] = piece;
        hasChanges = true;
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
    
    public boolean hasChanges(){
        return hasChanges;
    }
    
    public void setHasChanges(boolean flag){
        hasChanges = flag;
    }
    //*************************************
    //*****TO-DO Programar el resize*******
    //*************************************
    
}

