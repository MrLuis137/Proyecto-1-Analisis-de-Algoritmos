/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qwirkle;

/**
 *
 * @author lalem
 */
public class PlayerTiles {
    
    private String[] humanTiles, bacrackingTiles, smartBactrakingTiles;
    private static PlayerTiles  playerTiles =  new PlayerTiles();
    
    private PlayerTiles(){
        humanTiles = new String[6];
        bacrackingTiles = new String[6];
        smartBactrakingTiles = new String[6];
    }
    //===========================================================//
    //                  Obtiene el objeto. Usa singleton
    //===========================================================//
    public static PlayerTiles getPlayerTiles(){
        return playerTiles;
    }
    //===========================================================//
    
    //===========================================================//
    //                  Obtiene las fichas del humano
    //===========================================================//
    public String[] getHumanTiles(){
        return humanTiles;
    }
    
    //===========================================================//
    //                  Setea als fichas del humano
    //===========================================================//
    public void setHumanTiles(String[] tiles){
        humanTiles = tiles;
    }
    
    //===========================================================//
    //          Obtiene las fichas del bactracking simple
    //===========================================================//
    public String[] getBactackingTiles(){
        return bacrackingTiles;
    }
    
    //===========================================================//
    //        Setea las fichas del backtracking simple
    //===========================================================//
    public void setBactackingTiles(String[] tiles){
        bacrackingTiles = tiles;
    }
    
    //===========================================================//
    //       Obtiene el las fichas del backtracking inteligente
    //===========================================================//
    public String[] getSmartBacktrackingTiles(){
        return smartBactrakingTiles;
    }
    
    //===========================================================//
    //          Setea las fichas del backtracking inteligente
    //===========================================================//
    public void setSmartBacktrackingTiles(String[] tiles){
        smartBactrakingTiles =  tiles;
    }
}
