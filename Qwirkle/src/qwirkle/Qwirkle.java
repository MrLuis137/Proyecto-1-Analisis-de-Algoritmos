/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qwirkle;
import processing.core.PApplet;
import Board.Frame;
import Board.Board;
/**
 *
 * @author Jacob
 */
public class Qwirkle extends PApplet{

    /**
     * @param args the command line arguments
     */
    Board board = new Board();
    
    public static void main(String[] args) {
        
        PApplet.main("qwirkle.Qwirkle");
        BoardMatrix matriz = BoardMatrix.getBoardMatrix();
        System.out.println(matriz.getTile(0, 0));
    }
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
       
        
    }
    
}
