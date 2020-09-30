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
    //______________METODOS PARA CONTAR LOS PUNTOS______________
    public static ArrayList<Integer> puntos = new ArrayList<>();
    
    public static boolean esFila(Insertion ficha1, Insertion ficha2){
        return ficha1.line==ficha2.line;     
    }
        
    public static void contarPuntos(BoardMatrix matrix,ArrayList<Insertion> insertList){
        //Variables que llevan los puntos de la fila/columna principal
        //y sus filas/columnas secundarias
        int principal=1,secundario=1;
        int ayuda,fila,colum;
        String celda;
        
        if(insertList.size()==1){//Si solo es una ficha.
            fila=insertList.get(0).line;
            colum=insertList.get(0).column;
            celda=matrix.getTile(fila, colum+1);
            if(!"n".equals(celda)&&!"t".equals(celda)){//Ver si tiene vecino derecha
                for (int i = colum+1; i < matrix.getColumns(); i++){
                    celda=matrix.getTile(fila, i);
                    if(!"n".equals(celda)&&!"t".equals(celda))principal++;
                    else break;}
                
            }else{celda=matrix.getTile(fila, colum-1);
                if(!"n".equals(celda)&&!"t".equals(celda)){//Ver si tiene vecino izq
                    for (int i = colum-1; i > -1; i--){
                        celda=matrix.getTile(fila, i);
                        if(!"n".equals(celda)&&!"t".equals(celda))principal++;
                        else break;}      
                }
            }           
            if( principal==6)principal+=6;
            celda=matrix.getTile(fila+1, colum);
            if(!"n".equals(celda)&&!"t".equals(celda)){//Ver si tiene vecino abajo
                for (int i = fila+1; i < matrix.getLines(); i++){
                    celda=matrix.getTile(i, colum);
                    if(!"n".equals(celda)&&!"t".equals(celda))secundario++;
                    else break;} 
            }else{celda=matrix.getTile(fila-1, colum);
                if(!"n".equals(celda)&&!"t".equals(celda)){//Ver si tiene vecino arriba
                    for (int i = fila-1; i >-1; i--){
                        celda=matrix.getTile(i, colum);
                        if(!"n".equals(celda)&&!"t".equals(celda))secundario++;
                        else break;}
                }
            }if(secundario==6)secundario+=6;
            
            if(principal==1)puntos.add(secundario);
            else if(secundario==1)puntos.add(principal);
            else puntos.add(principal+secundario);
            
        }else{//-----------------------------------Para cuando hay varias fichas
              //----------------------------------------------------------------
            secundario=0;
            principal=insertList.size();
            fila=insertList.get(0).line;
            colum=insertList.get(0).column;        
            if(esFila(insertList.get(0),insertList.get(1))){//~~~~~~~~~~~~~~~~~~Cuando es una fila
                System.out.println("Es fila");
                //=============================Contar los puntos de toda la fila
                //==============================================================
                celda=matrix.getTile(fila, colum+1);  
                if(!"n".equals(celda)&&!"t".equals(celda)){//Cuando las fichas en la matriz están derecha
                    for (int i = colum+1; i < matrix.getColumns(); i++){
                        celda=matrix.getTile(fila, i);
                        if(!"n".equals(celda)&&!"t".equals(celda))principal++;
                        else break;}     
                }celda=matrix.getTile(fila, colum-1);
                if(!"n".equals(celda)&&!"t".equals(celda)){//Cuando las fichas en la matriz están izq
                    for (int i = colum-1; i > -1; i--){
                        celda=matrix.getTile(fila, i);
                        if(!"n".equals(celda)&&!"t".equals(celda))principal++;
                        else break;
                        //System.out.println("puntos de principal "+principal);
                    }   
                }if(principal==6)principal+=6;
                //***********************Contar los puntos de todas las columnas
                //**************************************************************
                for(Insertion ficha:insertList ){
                    ayuda=1;
                    fila=ficha.line;
                    colum=ficha.column;
                    celda=matrix.getTile(fila+1, colum);
                    if(!"n".equals(celda)&&!"t".equals(celda)){//+++++++++++++++Ver si tiene vecino abajo
                        for (int i = fila+1; i < matrix.getLines(); i++){
                            celda=matrix.getTile(i, colum);
                            if(!"n".equals(celda)&&!"t".equals(celda))ayuda++;
                            else break;}
                    }else{celda=matrix.getTile(fila-1, colum);
                        if(!"n".equals(celda)&&!"t".equals(celda)){//+++++++++++Ver si tiene vecino arriba
                            for (int i = fila-1; i >-1; i--){
                                celda=matrix.getTile(i, colum);
                                if(!"n".equals(celda)&&!"t".equals(celda))ayuda++;
                                else break;}
                        }
                    }
                    if(ayuda==6)ayuda+=6;
                    if(ayuda!=1)secundario+=ayuda;
                    //System.out.println("puntos de secundario "+secundario);
                }  
            }else{//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Cuando es una columna           
                //==========================Contar los puntos de toda la columna
                //==============================================================
                System.out.println("Es columna");
                celda=matrix.getTile(fila+1, colum);  
                if(!"n".equals(celda)&&!"t".equals(celda)){//___________________Cuando las fichas en la matriz están arriba 
                    for (int i = fila+1; i < matrix.getLines(); i++){
                        celda=matrix.getTile(i, colum);
                        if(!"n".equals(celda)&&!"t".equals(celda))principal++;
                        else break;}  
                }celda=matrix.getTile(fila-1, colum);
                if(!"n".equals(celda)&&!"t".equals(celda)){//___________________Cuando las fichas en la matriz están abajo
                    for (int i = fila-1; i >-1; i--){
                        celda=matrix.getTile(i, colum);
                        if(!"n".equals(celda)&&!"t".equals(celda))principal++;
                        else break;}   
                }if(principal==6)principal+=6;
                //*********************Contar los puntos de las filas adyacentes
                //**************************************************************
                for(Insertion ficha:insertList ){
                    ayuda=1;
                    fila=ficha.line;
                    colum=ficha.column;
                    celda=matrix.getTile(fila, colum+1);
                    if(!"n".equals(celda)&&!"t".equals(celda)){//_______________Ver si tiene vecino derecha
                        for (int i = colum+1; i < matrix.getColumns(); i++){
                            celda=matrix.getTile(fila, i);
                            if(!"n".equals(celda)&&!"t".equals(celda))ayuda++;
                            else break;}  
                    }else{celda=matrix.getTile(fila, colum-1);
                        if(!"n".equals(celda)&&!"t".equals(celda)){//___________Ver si tiene vecino izq
                            for (int i = colum-1; i > -1; i--){
                                celda=matrix.getTile(fila, i);
                                if(!"n".equals(celda)&&!"t".equals(celda))ayuda++;
                                else break;}      
                        }
                    }
                    if(ayuda==6)ayuda+=6;
                    if(ayuda!=1)secundario+=ayuda;
                }
            }
            if(secundario==1)puntos.add(principal);
            else puntos.add(principal+secundario);
        }     
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
        
        switch(action){
            //Caso 1, recorre toda la matriz buscando una posición donde pueda insertar
            case 1:
                for(int i = 0; i < matrix.getLines(); i++){
                    for(int j = 0; j < matrix.getColumns(); j++){
                        //Si en la posición i,j hay una "t", reviza si es posible insertar la ficha.
                        if(matrix.getTile(i, j).equals("t") && isAValidPosition(i,j)){
                            for(String tile: hand){
                                if(!isAValidInsertion(i, j, tile)){continue;}
                                int l = i;
                                //Vertical hacia arriba
                                verticalInsertion(tile, hand,i, j, insertList, lookShape, 3);
                                matrix.setStructure(matrixCopy, lines, columns); 
                                
                                //vertical hacia abajo
                                
                                verticalInsertion(tile, hand,i, j, insertList, lookShape, -3);
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
                break;
            case 2:
                if(isAValidPosition(line,column)){
                            for(String tile: hand){
                                horizontalInsertion(tile, hand, line, column, insertList, lookShape, action);
                                matrix.setStructure(matrixCopy, lines, columns); 
                            }
                }
            case -2:
                if(isAValidPosition(line,column)){
                            for(String tile: hand){
                                horizontalInsertion(tile, hand, line, column, insertList, lookShape, action);
                                matrix.setStructure(matrixCopy, lines, columns); 
                            }
                }
            default:
                break;
        }//Switch
    }//end of function
    
    private void verticalInsertion( String tile, ArrayList<String> hand, int line, int column, ArrayList<Insertion> insertList, boolean lookShape, int action ){
        int l;
        if(action == 3){
            for(l = line+1; l <= line+7; l++){
                String tempTile = matrix.getTile(l, column);
               if(tempTile != null && tempTile.length() == 3){
                   if(tempTile.equals(tile)) {return;}
                       if(!lookShape){
                       color = tile.contains(tempTile.subSequence(0,1)) && tile.contains(tempTile.subSequence(0,1));
                       } else{ color= false; }
                       if(lookShape || action == 0){
                       shape = tile.contains(tempTile.subSequence(2,3)) && tile.contains(tempTile.subSequence(2,3));
                       } else{ shape = false; }
               }else{l= line+7;}
           }
        }
        else{
            for(l = line-1; l > line-7; l--){
                String tempTile = matrix.getTile(l, column);
               if(tempTile != null && tempTile.length() == 3){
                   if(tempTile.equals(tile)){return;}
                       if(!lookShape){
                       color = tile.contains(tempTile.subSequence(0,1)) && tile.contains(tempTile.subSequence(0,1));
                       } else{ color= false; }
                       if(lookShape || action == 0){
                       shape = tile.contains(tempTile.subSequence(2,3)) && tile.contains(tempTile.subSequence(2,3));
                       } else{ shape = false; }
               }else{l= line-7;}
           }
        }
        //Vertical hacia arriba
        if((color && !shape) || (!color && shape) ){
            insertList.add( new Insertion(tile,line,column));
            matrix.setTileWithoutGrow(tile, line, column);
            callNextIteration(hand, line-1 , column, 3,shape, tile, insertList);
            try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(BackTracking.class.getName()).log(Level.SEVERE, null, ex);
        }
            callNextIteration(hand, line , (action == 2)? column+1:column-1, 2,shape, tile, insertList);
        }
        
    }
    
    private void horizontalInsertion(String tile,ArrayList<String> hand, int line,int column,ArrayList<Insertion> insertList, boolean lookShape,int action){
            //INSERCIÓN HORIZONTAL DERECHA
        int c;
        if(action == 2){
            for(c = column-1; c >= column-7; c--){
                //System.out.println(j);
                String tempTile = matrix.getTile(line, c);
                if(tempTile != null && tempTile.length() == 3){
                    if(tempTile.equals(tile)){return;}
                    if(!lookShape){
                    color = tile.contains(tempTile.subSequence(0,1)) && tile.contains(tempTile.subSequence(0,1));
                    } else{ color= false; }
                    if(lookShape || action == 0){
                    shape = tile.contains(tempTile.subSequence(2,3)) && tile.contains(tempTile.subSequence(2,3));
                    } else{ shape = false; }
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
                    if(lookShape || action == 0){
                    shape = tile.contains(tempTile.subSequence(2,3)) && tile.contains(tempTile.subSequence(2,3));
                    } else{ shape = false; }
                }else{c= column+7;}
            }
        }
        if((color && !shape) || (!color && shape)){
            insertList.add( new Insertion(tile,line,column));
            matrix.setTileWithoutGrow(tile, line, column);
            //SOLO CON PROPOSITO DE PROBAR
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(BackTracking.class.getName()).log(Level.SEVERE, null, ex);
        }
            callNextIteration(hand, line , (action == 2)? column+1:column-1, 2,shape, tile, insertList);
        }

    }
    
    private void callNextIteration(ArrayList<String> hand,int i,int j, int action, boolean lookShape, String tile, ArrayList<Insertion> insertList){
         ArrayList<String> subHand = (ArrayList<String>)hand.clone();
        subHand.remove(tile);
        color = false; shape = false; 
        backTracking((subHand), i, j,action,false,insertList);
    }
    
    private boolean isAValidPosition(int i, int j){
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
        if(u && l && !(tileUp.charAt(0) == tileLeft.charAt(0) || tileUp.charAt(2) == tileLeft.charAt(2))){
            valid = false;
        }
        if(u && r && !(tileUp.charAt(0) == tileRight.charAt(0) || tileUp.charAt(2) == tileRight.charAt(2))){
            valid = false;
        }
        if(u && d && !(tileUp.charAt(0) == tileDown.charAt(0) || tileUp.charAt(2) == tileDown.charAt(2))){
            valid = false;
        }
        if(l && d && !(tileLeft.charAt(0) == tileDown.charAt(0) || tileLeft.charAt(2) == tileDown.charAt(2))){
            valid = false;
        }
        if(r && d && !(tileRight.charAt(0) == tileDown.charAt(0) || tileRight.charAt(2) == tileDown.charAt(2))){
            valid = false;
        }
        if(l && r && !(tileLeft.charAt(0) == tileRight.charAt(0) || tileLeft.charAt(2) == tileRight.charAt(2))){
            valid = false;
        }
        return valid;
    }
    
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
        if(u && !(tileUp.charAt(0) == tile.charAt(0) || tileUp.charAt(2) == tile.charAt(2))){
            valid = false;
        }
        if(u && !(tileUp.charAt(0) == tile.charAt(0) || tileUp.charAt(2) == tile.charAt(2))){
            valid = false;
        }
        if(d && !(tile.charAt(0) == tileDown.charAt(0) || tile.charAt(2) == tileDown.charAt(2))){
            valid = false;
        }
        if(l && !(tileLeft.charAt(0) == tile.charAt(0) || tileLeft.charAt(2) == tile.charAt(2))){
            valid = false;
        }
        if(r&& !(tileRight.charAt(0) == tile.charAt(0) || tileRight.charAt(2) == tile.charAt(2))){
            valid = false;
        }
        return valid;
            
        
    }
}
