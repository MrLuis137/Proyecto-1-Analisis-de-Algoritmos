/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backtracking;


import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import qwirkle.BoardMatrix;
import qwirkle.BoardMatrix;
import qwirkle.PlayerTiles;
import qwirkle.PlayerTiles;
/**
 *
 * @author lalem
 */
public class BackTracking {
    
    BoardMatrix matrix = BoardMatrix.getBoardMatrix();
    PlayerTiles tiles = PlayerTiles.getPlayerTiles();
    boolean color = false;
    boolean shape = false;
    
    public void correrBackTracking(){
        backTracking( tiles.getBactackingTiles(), 0, 0, 1,false, new ArrayList<Insertion>());
    }
    
    private void backTracking(ArrayList<String> hand, int line, int column, int action,boolean lookShape, ArrayList<Insertion> insertList){
        System.out.println(action);
        if(hand.isEmpty()){
            return;
        }
        //Dado que BoardMatrix utiliza el patron Singleton, se hace una copia de la estructura original para ser restaurada al finalizar
        String[][] matrixCopy = matrix.getCopyOfStructure();
        int lines = matrix.getLines();
        int columns = matrix.getColumns();
            //action: 1 = recorrer toda la matriz
        if(action == 1){
                for(int i = 0; i < matrix.getLines(); i++){
                    for(int j = 0; j < matrix.getColumns(); j++){
                        //Si en la posición i,j hay una "t", reviza si es posible insertar la ficha.
                        if(matrix.getTile(i, j).equals("t") && isAValidPosition(i,j)){
                            //Para cada ficha en la mano, intenta insertar
                            for(String tile: hand){
                                //comprueba si es posible insertar la ficha
                                if(!isAValidInsertion(i, j, tile)){continue;}
                                int l = i;
                                //Vertical hacia arriba
                                verticalInsertion(tile, hand,i, j, insertList, lookShape, -3);
                                //Restaura la estructura de la matriz
                                matrix.setStructure(matrixCopy, lines, columns); 
                                
                                //vertical hacia abajo
                                verticalInsertion(tile, hand,i, j, insertList, lookShape, 3);
                                matrix.setStructure(matrixCopy, lines, columns); 
                               
                                //INSERCIÓN HORIZONTAL DERECHA
                                horizontalInsertion(tile, hand,i, j, insertList, lookShape, 2);
                                matrix.setStructure(matrixCopy, lines, columns); 

                                //INSERCIÓN HORIZONTAL IZQUIERDA
                                horizontalInsertion(tile, hand,i, j, insertList, lookShape, -2);
                                matrix.setStructure(matrixCopy, lines, columns); 

                            }//For each tile in hand
                            
                        }//If "t"
                    }//For j
                }//For i
            }
            //action: 2 = insertar hacia la derecha, -2 = insetrar a la izquierda. 
            //El signo representa la operación necesaria para mover el indice
            if(action == 2 || action == -2){
                if(isAValidPosition(line,column)){
                        for(String tile: hand){
                            if(isAValidInsertion(line, column, tile)){
                                horizontalInsertion(tile, hand, line, column, insertList, lookShape, action);
                                matrix.setStructure(matrixCopy, lines, columns); 
                            }
                        }
                    }
                }
            //action: 3 = insertar hacia abajo, -3 = insetrar a la arriba.
            if(action == 3 || action == -3){
                if(isAValidPosition(line,column)){
                            for(String tile: hand){
                                if(isAValidInsertion(line, column, tile)){
                                    verticalInsertion(tile, hand, line, column, insertList, lookShape, action);
                                    matrix.setStructure(matrixCopy, lines, columns);
                                }
                            }
                }
            }
        //Switch
    }//end of function
    
    private void verticalInsertion( String tile, ArrayList<String> hand, int line, int column,
                ArrayList<Insertion> insertList, boolean lookShape, int action ){
        //Se asegura que las banderas esten en un valor neutro
        color = false; shape = false; 
        //comprueba las fichas hacia abajo
        if(action == -3){
            for(int l = line+1; l <= line+7; l++){
                String tempTile = matrix.getTile(l, column);
               if(tempTile != null && tempTile.length() == 3){
                   //Si la ficha a insertar y la ficha temporal son iguales, se retorna sin insertar. 
                   if(tempTile.equals(tile)) {return;}
                       //Si no se está insertando por forma, se revisa el color
                       if(!lookShape){
                       color = tile.contains(tempTile.subSequence(0,1)) && tile.contains(tempTile.subSequence(0,1));
                       }
                       //Si la lista de inserciones esta vacía, se ignora la bandera lookShpape,
                       //Eso sucede en caso de que sea la primera ficha a insertar.
                       if(lookShape || insertList.size() == 0){
                       shape = tile.contains(tempTile.subSequence(2,3)) && tile.contains(tempTile.subSequence(2,3));
                       }
               }else{l= line+7;}
           }
        }
        else{
            for(int l = line-1; l > line-7; l--){
                String tempTile = matrix.getTile(l, column);
               if(tempTile != null && tempTile.length() == 3){
                   if(tempTile.equals(tile)){return;}
                       if(!lookShape){
                       color = tile.contains(tempTile.subSequence(0,1)) && tile.contains(tempTile.subSequence(0,1));
                       }
                       if(lookShape || insertList.size() == 0){
                       shape = tile.contains(tempTile.subSequence(2,3)) && tile.contains(tempTile.subSequence(2,3));
                       }
               }else{l= line-7;}
           }
        }
        //Vertical hacia arriba
        if((color && !shape) || (!color && shape) ){
            insertList.add( new Insertion(tile,line,column));
            matrix.setTileWithoutGrow(tile, line, column);
            
            //|||||||||||||||||SOLO CON PROPOSITO DE PROBAR||||||||||||||||||||||||||||||||||
            try {                                                                            //
                Thread.sleep(1000);                                                          //
            } catch (InterruptedException ex) {                                              //
                Logger.getLogger(BackTracking.class.getName()).log(Level.SEVERE, null, ex);  //
            }                                                                                //
            //|||||||||||||||||SOLO CON PROPOSITO DE PROBAR|||||||||||||||||||||||||||||||||||
            
            callNextIteration(hand,(action == 3)? line + 1: line -1 , column, action,shape, tile, insertList);
            insertList.remove(insertList.size()-1);
        }
        
    }
    
    private void horizontalInsertion(String tile,ArrayList<String> hand, int line,int column,ArrayList<Insertion> insertList, boolean lookShape,int action){
            //INSERCIÓN HORIZONTAL DERECHA
        int c;
        color = false; shape = false; 
        if(action == 2){
            for(c = column-1; c >= column-7; c--){
                //System.out.println(j);
                String tempTile = matrix.getTile(line, c);
                if(tempTile != null && tempTile.length() == 3){
                    if(tempTile.equals(tile)){return;}
                    if(!lookShape){
                    color = tile.contains(tempTile.subSequence(0,1)) && tile.contains(tempTile.subSequence(0,1));
                    }
                    if(lookShape || insertList.size() == 0){
                    shape = tile.contains(tempTile.subSequence(2,3)) && tile.contains(tempTile.subSequence(2,3));
                    }
                }else{c= column-7;}
            }
        }else{
            for(c = column+1; c <= column+7 ; c++){
                //System.out.println(j);
                String tempTile = matrix.getTile(line, c);
                if(tempTile != null && tempTile.length() == 3){
                    if(tempTile.equals(tile)){return;}
                    if(!lookShape){
                    color = tile.contains(tempTile.subSequence(0,1)) && tile.contains(tempTile.subSequence(0,1));
                    } else{ color= false; }
                    if(lookShape || insertList.size() == 0){
                    shape = tile.contains(tempTile.subSequence(2,3)) && tile.contains(tempTile.subSequence(2,3));
                    } else{ shape = false; }
                }else{c= column+7;}
            }
        }
        if((color && !shape) || (!color && shape)){
            insertList.add( new Insertion(tile,line,column));
            matrix.setTileWithoutGrow(tile, line, column);
            
           //|||||||||||||||||SOLO CON PROPOSITO DE PROBAR||||||||||||||||||||||||||||||||||
            try {                                                                            //
                Thread.sleep(1000);                                                          //
            } catch (InterruptedException ex) {                                              //
                Logger.getLogger(BackTracking.class.getName()).log(Level.SEVERE, null, ex);  //
            }                                                                                //
            //|||||||||||||||||SOLO CON PROPOSITO DE PROBAR|||||||||||||||||||||||||||||||||||
            
            callNextIteration(hand, line , (action == 2)? column+1:column-1, action,shape, tile, insertList);
            insertList.remove(insertList.size()-1);
        }

    }
    
    private void callNextIteration(ArrayList<String> hand,int i,int j, int action, boolean lookShape, String tile, ArrayList<Insertion> insertList){
        ArrayList<String> subHand = (ArrayList<String>)hand.clone();
        subHand.remove(tile);
        backTracking((subHand), i, j,action,lookShape,insertList);
    }
    
    //Comprueba que en el campo i, j se pueda realizar una inserción
    //En otras palabras, si vale la pena revisar ficha por ficha
    private boolean isAValidPosition(int i, int j){
        boolean valid= true;    //Bandera que indica si se puede insertar la ficha
        boolean u= true;        // banderas que indican si la ficha existe en X posición
        boolean d= true;
        boolean l= true;
        boolean r= true;
        String tileUp = matrix.getTile(i-1, j); //Obtiene las fihas en cada posición
        String tileDown = matrix.getTile(i+1, j);
        String tileLeft = matrix.getTile(i, j-1);
        String tileRight = matrix.getTile(i, j+1);
        if(("n".equals(tileUp) || "t".equals(tileUp))){u =false;}//Se comprueba que exista una ficha
        if(("n".equals(tileDown) || "t".equals(tileDown))){d =false;}
        if(("n".equals(tileLeft) || "t".equals(tileLeft))){l =false;}
        if(("n".equals(tileRight) || "t".equals(tileRight))){r =false;}
        //Si a y b existen y NO coiciden en forma o color
        if(u && l && !(tileUp.charAt(0) == tileLeft.charAt(0) || tileUp.charAt(2) == tileLeft.charAt(2))){
            valid = false;
        }
        if(u && r && !(tileUp.charAt(0) == tileRight.charAt(0) || tileUp.charAt(2) == tileRight.charAt(2))){
            valid = false;
        }
        //(Si a y b existen y NO coiciden en forma o color) o existen y son iguales
        if(u && d && !(tileUp.charAt(0) == tileDown.charAt(0) || tileUp.charAt(2) == tileDown.charAt(2)) || u && d && tileUp.equals(tileDown)){
            valid = false;
        }
        if(l && d && !(tileLeft.charAt(0) == tileDown.charAt(0) || tileLeft.charAt(2) == tileDown.charAt(2))){
            valid = false;
        }
        if(r && d && !(tileRight.charAt(0) == tileDown.charAt(0) || tileRight.charAt(2) == tileDown.charAt(2))){
            valid = false;
        }
        if(l && r && !(tileLeft.charAt(0) == tileRight.charAt(0) || tileLeft.charAt(2) == tileRight.charAt(2)) || l && r && tileRight.equals(tileLeft)){
            valid = false;
        }
        return valid;
    }
    
    //Comprueba que la ficha a insertar no "choque" con sus vecinas
    //Su funcionamiento es parecido a isAValidPosition, 
    //con la excepción de que se asume que es posible insertar en i, j
    private boolean isAValidInsertion(int i, int j, String tile){
        boolean valid= true;
        boolean u= true;
        boolean d= true;
        boolean l= true;
        boolean r= true;
        String tileUp = matrix.getTile(i-1, j);
        String tileDown = matrix.getTile(i+1, j);
        String tileLeft = matrix.getTile(i, j-1);
        String tileRight = matrix.getTile(i, j+1);
        if(("n".equals(tileUp) || "t".equals(tileUp))){u =false;}
        if(("n".equals(tileDown) || "t".equals(tileDown))){d =false;}
        if(("n".equals(tileLeft) || "t".equals(tileLeft))){l =false;}
        if(("n".equals(tileRight) || "t".equals(tileRight))){r =false;}
        //(Si a existe y ficha NO coicide en forma o color con a) o ficha y a son iguales
        if((u && !(tileUp.charAt(0) == tile.charAt(0) || tileUp.charAt(2) == tile.charAt(2))) || tile.equals(tileUp)){
            valid = false;
        }
        if((d && !(tile.charAt(0) == tileDown.charAt(0) || tile.charAt(2) == tileDown.charAt(2))) || tile.equals(tileDown)){
            valid = false;
        }
        if((l && !(tileLeft.charAt(0) == tile.charAt(0) || tileLeft.charAt(2) == tile.charAt(2)) || tile.equals(tileLeft))){
            valid = false;
        }
        if(r&& !(tileRight.charAt(0) == tile.charAt(0) || tileRight.charAt(2) == tile.charAt(2)) || tile.equals(tileRight) ){
            valid = false;
        }
        return valid;
    }
}
