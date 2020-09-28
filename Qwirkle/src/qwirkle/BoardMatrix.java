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

import backtracking.Insertion;
import java.util.ArrayList;

public class BoardMatrix {
    
    private static BoardMatrix board;
    private int columns = 14;
    private int lines = 14;
    private static String[][] matrix;
    private boolean hasChanges = true;
    
    private BoardMatrix(){
        this.matrix = new String [lines][columns];
        fillMatrix();
        
        //******************SOLO PARA TESTEAR*****************
        /*matrix [0][0] = "R-1";
        matrix [0][1] = "B-2";
        matrix [1][2] = "G-3";
        matrix [2][3] = "Y-4";
        matrix [3][3] = "O-5";
        matrix [0][3] = "P-6";
        //******************SOLO PARA TESTEAR*****************/
    }
    private void fillMatrix(){
        for(int i = 0; i < lines; i++){
            for(int j = 0; j < columns; j++){
                if(matrix[i][j] == null)
                matrix[i][j] = "n";
            }
        }
    }
    
    
    //Obtiene un objeto boardMatrix, se hizo con 
    public static BoardMatrix getBoardMatrix(){
        if(board == null){
           board = new BoardMatrix();
        }
        return board;
    }
    
    public String[][]getStructure(){
        return matrix;
    }
    
    public void setStructure(String[][] pMatrix, int pLines, int pColumns){
        matrix  = pMatrix;
        lines = pLines;
        columns = pColumns;
    }
    
    //Limpia el  tablero
    public void clearBoard(){
        board = new BoardMatrix();  
    }
    
    //Setea el valor de un Tile
    public void setTile(String piece, int line, int column){
        matrix[line][column] = piece;
        /****aumento Lineas*******/
        
        if(line <= 7){ 
            for(int i = 7- line; i>0; i--){
                aumentarArriba(); line++;
        }}
        if(line >= lines - 7){
            for(int i = line - (lines - 7) ; i>= 0; i--){
                aumentarAbajo();
        }}
        
        /****aumento Columnas*******/
        if(column <= 7){ 
            for(int i = 7- column; i>0; i--){
                aumentarIzquierda(); column++;
        }}
        if(column >= columns - 7){ 
            for(int i = column- (columns-7)  ; i>=0; i--){
                aumentarDerecha();
        }}
        fillMatrix();
        /****delimitado del tablero*******/
        if( matrix[line +1 ][column].equals("n") ){ matrix[line + 1][column] = "t"; }
        if( matrix[line][column +1].equals("n") ){ matrix[line][column + 1] = "t"; }
        if( matrix[line - 1][column].equals("n") ){ matrix[line - 1][column] = "t"; }
        if( matrix[line][column - 1].equals("n") ){ matrix[line][column - 1] = "t"; }
        hasChanges = true;
    }
    
    //Setea el valor de un Tile sin hacer crecer la matriz !!!USAR CON CUIDADO, PUEDE CONDUCIR A UN INDEX OUT OF BOUNDS!!!
    public void setTileWithoutGrow(String piece, int line, int column){
     matrix[line][column] = piece;       
        /****delimitado del tablero*******/
        if( matrix[line +1 ][column].equals("n") ){ matrix[line + 1][column] = "t"; }
        if( matrix[line][column +1].equals("n") ){ matrix[line][column + 1] = "t"; }
        if( matrix[line - 1][column].equals("n") ){ matrix[line - 1][column] = "t"; }
        if( matrix[line][column - 1].equals("n") ){ matrix[line][column - 1] = "t"; }
        hasChanges = true;
    }
    
    //Setea un grupo ordenado de fichas;
    public void setTiles(ArrayList<Insertion> tiles){
        int lastLine = 0;
        int lastColumn = 0;
        for(Insertion tile : tiles){
            setTileWithoutGrow(tile.tile, tile.line, tile.column);
            lastLine = tile.line;
            lastColumn = tile.column;
        }
        if(lastLine <= 7){ 
            for(int i = 7- lastLine; i>0; i--){
                aumentarArriba();
        }  }
        if(lastLine >= lines - 7){
            for(int i = lastLine - (lines - 7) ; i>=0; i--){
                aumentarAbajo();
        } }
        
        /****aumento Columnas*******/
        if(lastColumn <= 7){ 
            for(int i = 7- lastColumn; i>0; i--){
                aumentarIzquierda();
        } }
        //System.out.println(lastColumn - (columns-6));
        if(lastColumn >= columns - 7){ 
            for(int i = lastColumn - (columns-8); i>0; i--){
                aumentarDerecha();
        }}
        fillMatrix();
    }
    
    public int getLines(){
        return lines;
    }
    public int getColumns(){
        return columns;
    }
    
    public String getTile(int line,int column){
        try{
            return matrix[line][column]; 
        }
        catch(Exception e){
            return null;
        }
        
    }
    
    //Para cuando la nueva ficha está en matriz[?][n-1]
    private void aumentarDerecha(){
        String nueva[][] = new String[matrix.length][matrix[0].length+1];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, nueva[i], 0, matrix[i].length);}
        matrix=nueva;
        columns++;
        fillMatrix();
    }
    
    //Para cuando la nueva ficha está en matriz[n-1][?]
    private void aumentarAbajo(){
        String nueva[][] = new String[matrix.length+1][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, nueva[i], 0, matrix[i].length);}
        matrix=nueva;
        lines++;
        
    }
    
    //Para cuando la nueva ficha está en matriz[?][0]
    private void aumentarIzquierda(){
        String nueva[][] = new String[matrix.length][matrix[0].length+1];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, nueva[i], 1, matrix[i].length);}
        matrix=nueva;
        columns++;
    }
    
    //Para cuando la nueva ficha está en matriz[0][?]
    private void aumentarArriba(){
        String nueva[][] = new String[matrix.length+1][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, nueva[i+1], 0, matrix[i].length);}
        matrix=nueva;
        lines++;
    }
    
    //Dice si hay cambios en el tableto
    //Es necesario mantenerlo actualizado para no estarlo revizando constantemente en la GUI
    public boolean hasChanges(){
        return hasChanges;
    }
    
    //Setea la bandera de cambios
    public void setHasChanges(boolean flag){
        hasChanges = flag;
    }
    public void print(){
        for (int i = 0; i < lines; i++){
            for(int j = 0; j < columns; j++){
                System.out.print("[" + matrix[i][j] + "] ");
            }
            System.out.println("");
        }
        
    }
}

