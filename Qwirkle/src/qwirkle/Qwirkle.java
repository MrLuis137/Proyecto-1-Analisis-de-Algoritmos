/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qwirkle;
import processing.core.PApplet;
import Board.Frame;
import Board.Board;
import Board.Tools;
import java.util.Hashtable;
import processing.core.PImage;
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
    Frame frame = new Frame(this);
    boolean presionado = false;
    
    
    
    
    public static void main(String[] args) {
        
        String[] appletArgs = new String[] { "qwirkle.Qwirkle" };
        PApplet.main(appletArgs);
        BoardMatrix matriz = BoardMatrix.getBoardMatrix();
        //System.out.println(matriz.getTile(0, 0));
    }
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
      
        board.click(mouseX, mouseY);
    }
  }
    
    private void loadIcons(){
      String[] colors = {"r", "g", "b", "p", "y", "o"};
      for (int i = 0; i < 6; i++){
          for(int j = 0; j < 6; j++ ){
              PImage image = loadImage("/assets/"+ colors[i] + "-" + (j+1) + ".png");
              tilesIcons.put(j, image);
              //System.out.println("key: " + colors[i] + "-" + (j+1));
              tilesIcons.put( (colors[i] + "-" + (j+1)), image);
          }
      }
  }
    
}
