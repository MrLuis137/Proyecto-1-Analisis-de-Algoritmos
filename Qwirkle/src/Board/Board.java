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
import java.util.Hashtable;
import processing.core.PApplet;
import processing.core.PImage;

public class Board{
  ArrayList<Tile> tilees = new ArrayList<>(); 
  public Hashtable tilesIcons;
  private PApplet sketch;
  BoardMatrix matrix = BoardMatrix.getBoardMatrix();
  float limMin  = 0  ;
  float limMed  = 7  ;
  float limMax  = 16 ;
  int margenX   = 10;
  int margenY   = 20 ;
  int espaciado = 5 ;
  int tamanio   = 46 ;
 
  


  //==================================================================================================================================================================//

  //CONSTRUCTOR
  public Board (PApplet sketch, Hashtable tilesIcons) {
      this.tilesIcons = tilesIcons;
      this.sketch = sketch;
  }
  
  //==================================================================================================================================================================//

  // DIBUJA
  public void run() {
    //SI NO HAY NINGUNA SECCIÓN LAS GENERA
    if (matrix.hasChanges() || matrix.hasChanges()) {  
      this.generarElementos();
      matrix.setHasChanges(false);
    } 
    else {
      int x = margenX + 5;
      //DIBUJA LAS ETIQUETAS EN LAS COLUMNAS
      for (int i =0; i < matrix.getColumns(); i++) {
      
        x= x+ tamanio +espaciado;
        sketch.fill(0);
        sketch.text(i, x, margenY - 10);
      
      }
      //DIBUJA LAS ETIQUETAS EN LAS FILAS
      int y = margenY - 10;
    
      for (int i =0; i < matrix.getLines(); i++) {
        y= y + tamanio +espaciado;
        sketch.fill(0);
        sketch.text(i, margenX, y);
      }
      //LLAMA AL DIBUJADO DE LAS SECCIONES
      for (Tile s : tilees) {
        s.run();
      }
    }
  }
   
   //==================================================================================================================================================================//

  //CAMBIA LAS POSICIONES
  
  public void arrastrado(){
    int distX = sketch.mouseX - sketch.pmouseX ;
    int distY = sketch.mouseY - sketch.pmouseY;
    
    if(tilees.size() > 0){
    Tile primero = tilees.get(0);
    Tile ultimo = tilees.get(tilees.size() - 1);
    
    if(primero.x < 1300 - tamanio && primero.y < 730 - tamanio  && ultimo.x > 0 && ultimo.y > 0){
   
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
    
    else if(ultimo.x <= 0){
      int diferencia = 2 - ultimo.x;
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
    
    int lines = matrix.getLines();
    int columns = matrix.getColumns();
    
      //System.out.println(lines + " " + columns);
    //float[][] mAdyacencia = g.getMatrizAdyacencias();
    int y = margenY; 
     //
      for (int i = 0; i < lines; i++) {
      // i = Y
        int x = margenX;

        for (int j = 0; j < columns; j++) { 
          Tile s;          
          x  = x + tamanio + espaciado   ;
 
          
          if(matrix.getTile(i, j).length() > 1 ) {
            String tile =  matrix.getTile(i, j);
            //System.out.println(i + " " + j + " " + tile );
            s = new Tile(sketch, tilesIcons,x, y, tamanio, tamanio, tile.charAt(0),tile.charAt(2));
            tilees.add(s);
          }
          else {
            
            //System.out.println("entre");
            s = new Tile(sketch,tilesIcons,x, y, tamanio, tamanio, 'n','n');
            tilees.add(s);
            
          }
        }
        y = y + tamanio + espaciado;
      }
  }
}
