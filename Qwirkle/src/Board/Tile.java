/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Board;

import processing.core.PApplet;
/**
 *
 * @author lalem
 */
public class Tile extends PApplet{
  int x;
  int y;
  
  int r;
  int g;
  int b;
  char  type;
  String  uno  ;
  String  dos  ;
  int     ancho; 
  int     alto ;
  
  //CONSTRUCTOR
  
  //==================================================================================================================================================================//
  Tile(int x, int y, int alto, int ancho, char color, char type){
    
    this.x     = x    ;
    this.y     = y    ;
    this.type  = type;
    this.alto  = alto ;
    this.ancho = ancho;
    setColorByChar(color);
    
    fill( r,g,b       );
    rect(x,y,alto,ancho);
  }
  

  
  private void setColorByChar(char color){
      switch(color){
        case 'r':
            this.r = 255;
            this.g = 0;
            this.b = 0;
            break;
        case 'g':
            this.r = 0;
            this.g = 255;
            this.b = 0;
            break;
        case 'b':
            this.r = 0;
            this.g = 0;
            this.b = 255;
            break;
        case 'y':
            this.r = 237;
            this.g = 230;
            this.b = 28;
            break;
        case 'p':
            this.r = 142;
            this.g = 79;
            this.b = 194;
            break;
        case 'o':
            this.r = 217;
            this.g = 153;
            this.b = 26;
            break;
        default:
            this.r = 0;
            this.g = 0;
            this.b = 0;
    }
  }


  //==================================================================================================================================================================//

  void run(){
    //stem.out.println(r + g + b); 
    fill(r,g,b         );
    rect(x,y,alto,ancho);

  }
  
  //==================================================================================================================================================================//
 
// CAMBIA EL COLOR
void cambiarColor(int r, int g, int b ){
    this.r = r;
    this.g = g;
    this.b = b;
  }
  
  //==================================================================================================================================================================//
  
//COMPRUEBA SI HA SIDO CLICKEADO
void isPressed(int mX, int mY ){
    if( mX >= x && mX <= (x +ancho) && mY >= y && mY <= (y + ancho)){
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
  
/*  float getPeso(){
    return peso;
  }*/
    
}
