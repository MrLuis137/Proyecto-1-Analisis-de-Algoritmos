/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Board;

import java.util.Hashtable;
import processing.core.PApplet;
import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

/**
 *
 * @author lalem
 */
public class Tile{

    private PApplet sketch;
    Hashtable icons;
    int x;
    int y;
    char  type;
    char  color;
    int   ancho; 
    int   alto ;
  
  //CONSTRUCTOR
  
  //==================================================================================================================================================================//
  Tile(PApplet sketch,Hashtable icons, int x, int y, int alto, int ancho, char color, char type){
    this.icons = icons;
    this.sketch = sketch;
    this.x     = x    ;
    this.y     = y    ;
    this.type  = type;
    this.alto  = alto ;
    this.ancho = ancho;
    this.color = color; 
    //run();
  
  }


  //==================================================================================================================================================================//

  void run(){
    //stem.out.println(r + g + b);
    if(color != 'n' && type != 'n'){
        renderTile();
    }
    else{
        sketch.fill(255);
        sketch.rect(x,y,alto,ancho);
    }

  }
  
  //==================================================================================================================================================================//
 
  private void renderTile(){
      String key = color + "-" + type;
      //System.out.println(key);
      PImage icon = (PImage)icons.get(key);
      
      sketch.image(icon, x, y);
  }
  
  //==================================================================================================================================================================//
  
//COMPRUEBA SI HA SIDO CLICKEADO
void isPressed(int mX, int mY ){
    if( mX >= x && mX <= (x +ancho) && mY >= y && mY <= (y + ancho)){
        System.out.println("Click funciona");
     //SI mensajeDatos(la clsae Datos)NO ESTÁ ACTIVO ENTONCES CAMBIA LOS DATOS Y LO ACTIVA
      /*if (!mensajeDatos.isActivo()){
        mensajeDatos.setDatos(nodo1, nodo2, peso);
        mensajeDatos.setActivo(true);
      */}
      
      //SI ESTA ACTIVO, LO DESACTIVA;
      else{
        //mensajeDatos.setActivo(false);
      }
    }
  //}
  
  //==================================================================================================================================================================// 
}
