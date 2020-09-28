/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backtracking;


import java.util.ArrayList;
import qwirkle.BoardMatrix;
import qwirkle.BoardMatrix;
import qwirkle.PlayerTiles;
import qwirkle.PlayerTiles;
/**
 *
 * @author lalem
 */
public class BackTracking {
    
    BoardMatrix matrix = BoardMatrix.getBoardMatrix();
    PlayerTiles tiles = PlayerTiles.getPlayerTiles();
    
    public void correrBackTracking(){
        
        
    }
    
    private void backTracking(BoardMatrix matrix, ArrayList<String> hand, int line, int column, int action, ArrayList<Insertion> insertList){
        if(hand.isEmpty()){
            return;
        }
        //Dado que BoardMatrix utiliza el patron Singleton, se hace una copia de la estructura original para ser restaurada al finalizar
        // No se utiliza el arreglo "crudo" por la necesidad de
        String[][] matrixCopy = matrix.getStructure();
        int lines = matrix.getLines();
        int columns = matrix.getColumns();
        switch(action){
            
            //Caso 1, recorre toda la matriz buscando una posición donde pueda insertar
            case 1:
                for(int i = 0; i < matrix.getLines(); i++){
                    for(int j = 0; j < matrix.getColumns(); j++){
                        //Si en la posición i,j hay una "t", reviza si es posible insertar la ficha.
                        if(matrix.getTile(i, j).equals("t")){
                            for(String tile: hand){
                                
                                int l = j;
                                boolean color = false;
                                boolean shape = false;
                                while(matrix.getTile(l, j) != null && matrix.getTile(l, j).length() > 1){
                                    color = tile.contains(matrix.getTile(l, j).subSequence(0,1)) && tile.contains(matrix.getTile(l, j).subSequence(0,1));
                                    shape = tile.contains(matrix.getTile(l, j).subSequence(2,3)) && tile.contains(matrix.getTile(l, j).subSequence(2,3));
                                }
                                if((color && !shape) || (!color && shape)){
                                    //BoardMatrix matrixCopy = matrix
                                }
                            } 
                            
                        }
                    }
                }
        }
    }
    
}
