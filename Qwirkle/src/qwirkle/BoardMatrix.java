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
    
    //Limpia el  tablero
    public void clearBoard(){
        board = new BoardMatrix();  
    }
    
    //Setea el valor de un Tile
    public void setTile(String piece, int line, int column){
        this.print();
        /****aumento Lineas*******/
        if(line == 0){ aumentarArriba(); }
        else if(line == lines -1){ aumentarAbajo(); }
        
        /****aumento Columnas*******/
        if(column == 0){ aumentarIzquierda(); }
        else if(column == columns -1){
            System.out.println("si funco");
            aumentarDerecha();}
        matrix[line][column] = piece;
        this.print();
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
    
    //Para cuando la nueva ficha está en matriz[?][n-1]
    public void aumentarDerecha(){
        String nueva[][] = new String[matrix.length][matrix[0].length+1];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, nueva[i], 0, matrix[i].length);}
        matrix=nueva;
        columns++;
        fillMatrix();
    }
    
    //Para cuando la nueva ficha está en matriz[n-1][?]
    public void aumentarAbajo(){
        String nueva[][] = new String[matrix.length+1][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, nueva[i], 0, matrix[i].length);}
        matrix=nueva;
        lines++;
        fillMatrix();
    }
    
    //Para cuando la nueva ficha está en matriz[?][0]
    public void aumentarIzquierda(){
        String nueva[][] = new String[matrix.length][matrix[0].length+1];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, nueva[i], 1, matrix[i].length);}
        matrix=nueva;
        columns++;
        fillMatrix();
    }
    
    //Para cuando la nueva ficha está en matriz[0][?]
    public void aumentarArriba(){
        String nueva[][] = new String[matrix.length+1][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, nueva[i+1], 0, matrix[i].length);}
        matrix=nueva;
        lines++;
        fillMatrix();
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

