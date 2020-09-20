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
    private int columns = 4;
    private int lines = 4;
    private static String[][] matrix;
    private boolean hasChanges = true;
    
    private BoardMatrix(){
        this.matrix = new String [lines][columns];
        fillMatrix();
        
        //******************SOLO PARA TESTEAR*****************
        matrix [0][0] = "R-1";
        matrix [1][1] = "B-2";
        matrix [1][2] = "G-3";
        matrix [3][2] = "Y-4";
        matrix [1][3] = "O-5";
        matrix [2][2] = "P-6";
        //******************SOLO PARA TESTEAR*****************/
    }
    private void fillMatrix(){
        for(int i = 0; i < lines; i++){
            for(int j = 0; j < columns; j++){
                matrix[i][j] = "";
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
        return lines;
    }
    public int getColumns(){
        return columns;
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

