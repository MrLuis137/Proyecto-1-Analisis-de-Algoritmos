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
public class Frame{
    
    PApplet applet;
    
    public Frame(PApplet applet){
        this.applet =  applet;
    }
    
    public void run(){
        applet.fill(157,191,209);
        applet.rect(0,applet.height -90, applet.width, applet.height);
    }
}
