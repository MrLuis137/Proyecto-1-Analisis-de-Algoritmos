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
    
    private void fillPlayersTiles(){
        ///****SOLO PARA TESTEO
        ArrayList<String> t = new ArrayList<String>();
        t.add("O-1");
        t.add("R-1");
        //t.add("Y-2");
        //t.add("Y-4");
        //t.add("Y-5");
        //t.add("R-5");
        //smartBactrakingTiles = t;
        ///****SOLO PARA TESTEO
        
        
        humanTiles = Bolsa.pop(6);
        bacrackingTiles = Bolsa.pop(6);
        
        smartBactrakingTiles = Bolsa.pop(6);
     
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
    public ArrayList<String> getHumanTiles(){
        return humanTiles;
    }
    
    //===========================================================//
    //                  Setea als fichas del humano
    //===========================================================//
    public void setHumanTiles(ArrayList<String> tiles){
        humanTiles = tiles;
    }
    
    public String popHumanTile(int position){
        String tile = humanTiles.get(position);
        humanTiles.remove(tile);
        return tile;
    }
    
    public void refillPlayerTiles(){
        int human = 6 - humanTiles.size();
        int backTracking = 6 - bacrackingTiles.size();
        int SmartbackTracking = 6 - smartBactrakingTiles.size();
        for(String tile : Bolsa.pop(human)){
            humanTiles.add(tile);
        }
        for(String tile : Bolsa.pop(backTracking)){
            bacrackingTiles.add(tile);
        }
        for(String tile : Bolsa.pop(SmartbackTracking)){
            smartBactrakingTiles.add(tile);
        }
    }
    
    
    public String popBactrackingTiles(int position){
        String tile = bacrackingTiles.get(position);
        humanTiles.remove(tile);
        return tile;
    }
    public String popBactrackingTiles(String tile){
        bacrackingTiles.remove(tile);
        return tile;
    }
    
    
    public String popSmartBactrackingTiles(int position){
        String tile = smartBactrakingTiles.get(position);
        smartBactrakingTiles.remove(tile);
        return tile;
    }
    
    public String popSmartBactrackingTiles(String tile){
        smartBactrakingTiles.remove(tile);
        return tile;
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
