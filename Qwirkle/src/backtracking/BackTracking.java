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
        switch(action){
            case 1:
                for(int i = 0; i < matrix.getLines(); i++){
                    for(int j = 0; j < matrix.getColumns(); j++){
                        if(matrix.getTile(i, j).equals("t")){
                            String tileUp = matrix.getTile(i - 1, j);
                            String tileDown = matrix.getTile(i + 1, j);
                            String tileLeft = matrix.getTile(i, j - 1);
                            String tileRight = matrix.getTile(i, j + 1);
                                
                            hand.forEach(tile -> {
                                int tUpVal;
                                int tDownVal;
                            });
                        }
                    }
                }
        }
    }
    
}
