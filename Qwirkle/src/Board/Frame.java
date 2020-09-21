/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Board;

import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 
import Board.Board;
import java.util.Hashtable;
import qwirkle.PlayerTiles;
/**
 *
 * @author lalem
 */
public class Frame{
    
    private PApplet applet;
    private PlayerTiles pTiles;
    private Hashtable icons;
    
    public Frame(PApplet applet, Hashtable icons){
        this.applet =  applet;
        pTiles = PlayerTiles.getPlayerTiles();
        this.icons = icons;
    }
    
    public void run(){
        applet.fill(157,191,209);
        applet.rect(0,applet.height -90, applet.width, applet.height);
        playerTiles();
        manoBacktracking();
        manoBacktrackingMejorado();
    }
     
    public void playerTiles(){
        int x = 26;
        applet.fill(255,255,255);
        applet.rect(x,660, 276, 46);
        String[]tiles = pTiles.getHumanTiles();
        for(int i = 0; i < 6; i++){
            if(tiles[i].length() == 3){
                 String key = tiles[i];
                //System.out.println(key);
                PImage icon = (PImage)icons.get(key);
                applet.image(icon, x +(i*46),660);
            }
        }
    }
    
    public void manoBacktracking(){
        int x = 459;
        applet.fill(260,255,260);
        applet.rect(x,660, 276, 46);
        String[]tiles = pTiles.getHumanTiles();
        for(int i = 0; i < 6; i++){
            if(tiles[i].length() == 3){
                 String key = tiles[i];
                //System.out.println(key);
                PImage icon = (PImage)icons.get(key);
                applet.image(icon, x +(i*46),660);
            }
        }
    }
    
    public void manoBacktrackingMejorado(){
        int x = 892;
        applet.fill(260,255,260);
        applet.rect(x,660, 276, 46);
        String[]tiles = pTiles.getHumanTiles();
        for(int i = 0; i < 6; i++){
            if(tiles[i].length() == 3){
                 String key = tiles[i];
                //System.out.println(key);
                PImage icon = (PImage)icons.get(key);
                applet.image(icon, x +(i*46),660);
            }
        }
    }
}
