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
            for(int j = 0; j < columns; j++){
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
    
    
    //Para cuando la nueva picha está en matriz[?][n-1]
    public static String[][] aumentarDerecha(String[][] original){
        String nueva[][] = new String[original.length][original[0].length+1];
        for (int i = 0; i < original.length; i++) {
            System.arraycopy(original[i], 0, nueva[i], 0, original[i].length);}
        return nueva;  
    }
    
    //Para cuando la nueva picha está en matriz[n-1][?]
    public static String[][] aumentarAbajo(String[][] original){
        String nueva[][] = new String[original.length+1][original[0].length];
        for (int i = 0; i < original.length; i++) {
            System.arraycopy(original[i], 0, nueva[i], 0, original[i].length);}
        return nueva;  
    }
    
    //Para cuando la nueva picha está en matriz[?][0]
    public static String[][] aumentarIzquierda(String[][] original){
        String nueva[][] = new String[original.length][original[0].length+1];
        for (int i = 0; i < original.length; i++) {
            System.arraycopy(original[i], 0, nueva[i], 1, original[i].length);}
        return nueva;  
    }
    
    //Para cuando la nueva picha está en matriz[0][?]
    public static String[][] aumentarArriba(String[][] original){
        String nueva[][] = new String[original.length+1][original[0].length];
        for (int i = 0; i < original.length; i++) {
            System.arraycopy(original[i], 0, nueva[i+1], 0, original[i].length);}
        return nueva;  
    }
    
}



