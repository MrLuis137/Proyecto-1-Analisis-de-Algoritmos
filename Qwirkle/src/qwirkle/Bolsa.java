/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qwirkle;

import java.util.ArrayList;

/**
 *
 * @author Jacob
 */
public class Bolsa {
    public static ArrayList<String> fichas = new ArrayList<>();
    
    // B=Azul, P=Morado, R=Rojo, O=Naranja, Y=Amarillo, G=Verde.
    // 1=Flor, 2=Estella(8), 3=Cuadrado, 4=Rombo, 5=Estrella(4), 6=Circulo.
    public static String[] combi = {"B-1", "B-2", "B-3", "B-4", "B-5", "B-6",
                                    "P-1", "P-2", "P-3", "P-4", "P-5", "P-6",
                                    "R-1", "R-2", "R-3", "R-4", "R-5", "R-6",
                                    "O-1", "O-2", "O-3", "O-4", "O-5", "O-6",
                                    "Y-1", "Y-2", "Y-3", "Y-4", "Y-5", "Y-6",
                                    "G-1", "G-2", "G-3", "G-4", "G-5", "G-6"};
    
    /*Metodo que genera un arraylist con 36 fichas diferentes 
    ordenadas aleatoriamente
    */
    public static ArrayList<String> generar(){
        ArrayList<String> lista = new ArrayList<>();
        int cont=0;
        while(cont<36){
            //Extrae una ficha aleatoria del array de combinaciones
            String ficha= combi[(int)(Math.random()*36)];
            //Si no está lo agrega
            if(!lista.contains(ficha)){
                lista.add(ficha);
                cont++;}     
        }
        return lista;   
    }
    
    /*
    Metodo que agrega en 3 ocaciones las 36 fichas ordenadas aleatoriamente
    al arraylist principal es decir un total de 108 fichas
    */
    public static void llenarBolsa(){
        int cont=0;
        while(cont<3){
            fichas.addAll(generar());
            cont++;
        }
    }
    
    /*
    Devuelve un arraylist con los ultimos n elementos de las fichas
    Para agregarlos se debe usar el metodo .addAll(pop(n));
    */
    public static ArrayList<String> pop (int cantidad){
        ArrayList<String> valores = new ArrayList<>();
        //if para cuando la cantidad de fichas necesitades es mayor a la 
        //cantidad de fichas en bolsa.
        if(fichas.size()<cantidad){
            cantidad=fichas.size();
            for (int i = 0; i < cantidad; i++) {
                valores.add(fichas.remove(fichas.size()-1));} 
        }else{
            for (int i = 0; i < cantidad; i++) {
                valores.add(fichas.remove(fichas.size()-1));}
        }
        return valores;
    }
    
    
}
