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
/**
 *
 * @author lalem
 */
public class Frame extends PApplet{
    @Override
    public void settings() {
        size(640, 480);
    }

    @Override
    public void setup() {
        fill(120,50,240);
    }

    @Override
    public void draw(){
        ellipse(width/2,height/2,second(),second());
    }

    
    /*Board board = new Board();
    
    @Override
    public void settings(){
        size(1300,730);
    }
    
    @Override
    public void draw(){
        clear();
        stroke(0);
        strokeWeight(1);
        board.run();
       
        
    }*/ 
}
