/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Board;

import qwirkle.BoardMatrix;
/**
 *
 * @author lalem
 */
import java.util.ArrayList;
import processing.core.PApplet;

public class Board extends PApplet{
  ArrayList<Tile> tilees = new ArrayList<Tile>();  

  String[][] g;
  BoardMatrix matrix = BoardMatrix.getBoardMatrix();
  String v1     = "x";
  String v2     = "x";
  float limMin  = 0  ;
  float limMed  = 7  ;
  float limMax  = 16 ;
  int margenX   = 450;
  int margenY   = 50 ;
  int espaciado = 10 ;
  int tamanio   = 20 ;
  


  //==================================================================================================================================================================//

  //CONSTRUCTOR
  void MatrizAdyacencia(ArrayList<String> board) {
    this.g = g;
  }
  
  //==================================================================================================================================================================//

  // DIBUJA
  public void run() {
    //SI NO HAY NINGUNA SECCIÓN LAS GENERA
    if (tilees.isEmpty()) {  
      this.generarElementos();
    } 
    else {
      
      int x = margenX + 5;
      /*DIBUJA LAS ETIQUETAS DE LOS VERTICES EN X
      for (int i =0; i < g.getNumVertices(); i++) {
      
        x= x+ tamanio +espaciado;
        fill(0);
        text(g.getVertice(i), x, margenY - 10);
      
      }
      //DIBUJA LAS ETIQUETAS DE LOS VERTICES EN Y
      int y = margenY - 10;
    
      for (int i =0; i < g.getNumVertices(); i++) {
        y= y + tamanio +espaciado;
        fill(0);
        text(g.getVertice(i), margenX, y);
      }
      */
      //LLAMA AL DIBUJADO DE LAS SECCIONES
      for (Tile s : tilees) {
        s.run();
      }
    }
  }
  
  //==================================================================================================================================================================//

  //COMPRUEBA EL CLICK
  public void click(int mX, int mY) {
    
    //PARA CADA SECCION COMPRUEBA
    for (Tile s : tilees) {
      s.isPressed(mX, mY);
    }
  }
  
   //==================================================================================================================================================================//

  //CAMBIA LAS POSICIONES
  
  void arrastrado(){
    int distX = mouseX - pmouseX ;
    int distY = mouseY - pmouseY;
    
    if(tilees.size() > 0){
    Tile primero = tilees.get(0);
    Tile ultimo = tilees.get(tilees.size() - 1);
    
    if(primero.x < 1300 - tamanio && primero.y < 730 - tamanio  && ultimo.x > 340 && ultimo.y > 0){
   
      margenX += distX;
      margenY += distY;
      
      for(Tile tile : tilees){
        tile.x += distX;
        tile.y += distY;
      }
    }
    
    
    else if(primero.x >= 1300 - tamanio){
      int diferencia = 1300- primero.x;
      margenX -= diferencia;
      
      tilees.forEach(tile -> {
          tile.x -= diferencia;
        });
    }
    
    else if(primero.y >= 730 - tamanio){
      int diferencia = 730- primero.y;
      margenY -= diferencia;
      
      tilees.forEach(tile -> {
          tile.y -= diferencia;
        });
    }
    
    else if(ultimo.x <= 340){
      int diferencia = 341 - ultimo.x;
      tilees.forEach(tile -> {
          tile.x +=  diferencia;
        });
      if(tilees.size() > 0)
        margenX = tilees.get(0).x - tamanio - espaciado;
    }
    
    else if(ultimo.y <= 0){
      
      int diferencia = 1 - ultimo.y;
      tilees.forEach(tile -> {
          tile.y += diferencia;
        });
      if(tilees.size() > 0)
        margenY = tilees.get(0).y;
    }
    }
  }
  
  //==================================================================================================================================================================//

  // GENERA LOS ELEMETOS
  void generarElementos(){
    
    int lines = matrix.getHeight();
    int columns = matrix.getWidth();
    //float[][] mAdyacencia = g.getMatrizAdyacencias();
    int y = margenY; 
     //
      for (int i = 0; i < columns; i++) {
      // i = Y
        int x = margenX;

        for (int j = 0; j < lines; j++) { 
          Tile s;          
          x  = x + tamanio + espaciado   ;
 
          
          if(!matrix.getTile(j, i).isEmpty() ) {
            String tile =  matrix.getTile(j, i);
            s = new Tile(x, y, tamanio, tamanio, tile.charAt(0),tile.charAt(2));
            tilees.add(s);
          }
          else {
            
            //System.out.println("entre");
            s = new Tile(x, y, tamanio, tamanio, 'n','n');
            tilees.add(s);
            
          }
        }
        y = y + tamanio + espaciado;
      }
  }
  
  //==================================================================================================================================================================//
  
  /*//SETEA LOS LÍMITES
  void setLimites(float min, float med, float max) {
    
    this.limMin = min;
    this.limMed = med;
    this.limMax = max;
    
  }
  
  //==================================================================================================================================================================//
  
  //CALCULA EL COLOR VERDE
  private int calcularG(float peso) {
    float factor = limMax - limMed; 
    int n = Math.round(((peso - limMed) * 255) / factor);
    n = (n > 255)? 255 : n;
    n = (n < 0)? 0 : n;
    return 255 - n;
  }
  
  //==================================================================================================================================================================//

  //CALCULA EL COLOR ROJO
  private int calcularR(float peso) {
    float factor = limMed - limMin; 
    int n = Math.round(((peso - limMin) * 255) / factor);
    n = (n > 255)? 255 : n;
    n = (n < 0)? 0 : n;
    return n;
  }*/
}
