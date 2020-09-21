/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qwirkle;
import java.util.ArrayList;
import qwirkle.Bolsa;
/**
 *
 * @author lalem
 */
public class PlayerTiles {
    
    private ArrayList<String> humanTiles, bacrackingTiles, smartBactrakingTiles;
    private static PlayerTiles  playerTiles =  new PlayerTiles();
    
    private PlayerTiles(){
        humanTiles = new ArrayList<String>();
        bacrackingTiles = new ArrayList<String>();
        smartBactrakingTiles = new ArrayList<String>();
        fillPlayersTiles();
    }
    
    public void fillPlayersTiles(){
        humanTiles = Bolsa.pop(6);
        bacrackingTiles = Bolsa.pop(6);
        smartBactrakingTiles = Bolsa.pop(6);
        System.out.println(humanTiles.toString());
     
    }
    
    //===========================================================//
    //                  Obtiene el objeto. Usa singleton
    //===========================================================//
    public static PlayerTiles getPlayerTiles(){
        return playerTiles;
    }
    
    public static PlayerTiles getPlayerTiles(Bolsa bolsa){
        return playerTiles;
    }
    //===========================================================//
    
    //===========================================================//
    //                  Obtiene las fichas del humano
    //===========================================================//
    public ArrayList<String> getHumanTiles(){
        return humanTiles;
    }
    
    //===========================================================//
    //                  Setea als fichas del humano
    //===========================================================//
    public void setHumanTiles(ArrayList<String> tiles){
        humanTiles = tiles;
    }
    
    //===========================================================//
    //          Obtiene las fichas del bactracking simple
    //===========================================================//
    public ArrayList<String> getBactackingTiles(){
        return bacrackingTiles;
    }
    
    //===========================================================//
    //        Setea las fichas del backtracking simple
    //===========================================================//
    public void setBactackingTiles(ArrayList<String> tiles){
        bacrackingTiles = tiles;
    }
    
    //===========================================================//
    //       Obtiene el las fichas del backtracking inteligente
    //===========================================================//
    public ArrayList<String> getSmartBacktrackingTiles(){
        return smartBactrakingTiles;
    }
    
    //===========================================================//
    //          Setea las fichas del backtracking inteligente
    //===========================================================//
    public void setSmartBacktrackingTiles(ArrayList<String> tiles){
        smartBactrakingTiles =  tiles;
    }
}
