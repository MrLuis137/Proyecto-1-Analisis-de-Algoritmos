/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qwirkle.board;

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
  
  String  nodo1;
  String  nodo2;
  String  uno  ;
  String  dos  ;
  float   peso ;
  int     ancho; 
  int     alto ;
  
  float   elpeso = 0    ;
  
  //CONSTRUCTOR
  
  //==================================================================================================================================================================//
  Seccion(int x, int y, int alto, int ancho, int r, int g, int b, float peso, String nodo1, String nodo2){
    
    this.x     = x    ;
    this.y     = y    ;
    this.r     = r    ;
    this.g     = g    ;
    this.b     = b    ;
    this.alto  = alto ;
    this.peso  = peso ;    
    this.ancho = ancho;    
    this.nodo1 = nodo1;
    this.nodo2 = nodo2;
    
    fill(r,g,b         );
    rect(x,y,alto,ancho);
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
