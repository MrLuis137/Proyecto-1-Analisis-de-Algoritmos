/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qwirkle;
import processing.core.PApplet;
import Board.Frame;
import Board.Board;
import java.util.ArrayList;
import java.util.Hashtable;
import processing.core.PImage;
import Board.Tools;
import backtracking.BackTracking;
import backtracking.Insertion;
/**
 *
 * @author Jacob
 */
public class Qwirkle extends PApplet{

    /**
     * @param args the command line arguments
     */
    public Hashtable tilesIcons = new Hashtable();
    Board board = new Board(this, tilesIcons);
    Frame frame = new Frame(this, tilesIcons);
    static Tools tools;
    
    boolean presionado = false;
    
    
    
    
    
    public static void main(String[] args) {
        Bolsa.llenarBolsa();
        String[] appletArgs = new String[] { "qwirkle.Qwirkle" };
        PApplet.main(appletArgs);
        BoardMatrix matriz = BoardMatrix.getBoardMatrix();
        tools = new Tools();
        tools.setVisible(true);
        ArrayList<Insertion> i = new ArrayList<Insertion>();
        /*/|||||||||||||||||SOLO CON PROPOSITO DE PROBAR||||||||||||||||||||||||||||||||||
        i.add(new Insertion("P-5", 4, 7));                                              //
        i.add(new Insertion("Y-5", 4, 6));                                              //
        i.add(new Insertion("G-5", 4, 5));                                              //
        i.add(new Insertion("G-3", 3, 5));                                              //
        i.add(new Insertion("G-5", 5, 6));                                              //
        i.add(new Insertion("B-5", 6, 6));                                              //
        i.add(new Insertion("G-5", 6, 5));                                              //
        matriz.setTiles(i);                                                             //
        //|||||||||||||||||SOLO CON PROPOSITO DE PROBAR||||||||||||||||||||||||||||||||||*/
        
        
        BackTracking.correrBackTracking();
        //System.out.println(matriz.getTile(0, 0).subSequence(2, 3));
        //System.out.println(matriz.getTile(0, 0).subSequence(2, 3));
        
        
        
    }
    
    //===========================================================//
    //                  Configuracion 
    //===========================================================//
    @Override
    public void settings(){
        size(1300,730);
    }
    
    @Override
    public void setup(){
        loadIcons();
    }
    
    @Override
    public void draw(){
        clear();
        stroke(0);
        strokeWeight(1);
        background(255);
        //System.out.println(mouseX+ " " + mouseY);
        board.run();
        frame.run();
    }
    
    @Override
    public void mouseDragged() {
        if(presionado) {
            board.arrastrado();
        }
    }
    @Override
    public void mousePressed(){
        presionado = true;
    }
    @Override
    public void mouseReleased(){
    presionado = false;
    }
    
    @Override
    public void mouseClicked(){
    //SI EL MOUSE ESTA DENTRO DEL AREA DE DIBUJADO (COMPROVAR VALOR)
    if(true){
      
        //board.click(mouseX, mouseY);
    }
  }
    //===========================================================//
    
    //===========================================================//
    //                  Carga los iconos en un hash 
    //===========================================================//
    private void loadIcons(){
      String[] colors = {"R", "G", "B", "P", "Y", "O"};
      for (int i = 0; i < 6; i++){
          for(int j = 0; j < 6; j++ ){
              PImage image = loadImage("/assets/"+ colors[i] + "-" + (j+1) + ".png");
              tilesIcons.put(j, image);
              //System.out.println("key: " + colors[i] + "-" + (j+1));
              tilesIcons.put( (colors[i] + "-" + (j+1)), image);
          }
      }
  }
    //===========================================================//
    
}
