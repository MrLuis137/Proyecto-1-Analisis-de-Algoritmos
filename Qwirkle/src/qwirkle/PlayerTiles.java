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
    
    public static PlayerTiles getPlayerTiles(){
        return playerTiles;
    }
    
    public String[] getHumanTiles(){
        return humanTiles;
    }
    
    public void setHumanTiles(String[] tiles){
        humanTiles = tiles;
    }
    
    public String[] getBactackingTiles(){
        return bacrackingTiles;
    }
    
    public void setBactackingTiles(String[] tiles){
        bacrackingTiles = tiles;
    }
    
    public String[] getSmartBacktrackingTiles(){
        return smartBactrakingTiles;
    }
    
    public void setSmartBacktrackingTiles(String[] tiles){
        smartBactrakingTiles =  tiles;
    }
}
