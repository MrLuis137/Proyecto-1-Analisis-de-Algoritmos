package backtracking;


import com.sun.accessibility.internal.resources.accessibility;
import java.util.ArrayList;
import java.util.logging.Level; //unicamente sirve para pausar el hilo
import java.util.logging.Logger;//unicamente sirve para pausar el hilo
import qwirkle.BoardMatrix;
import qwirkle.PlayerTiles;
/**
 *
 * @author lalem
 */
public class Backtracking {
    
    public static boolean pasoAPaso = false;
    private static BoardMatrix  matrix = BoardMatrix.getBoardMatrix();
    private static PlayerTiles tiles = PlayerTiles.getPlayerTiles();
    private static ArrayList posibilities = new ArrayList();
    private static boolean isStarted = false; 
    public static ArrayList<Integer> puntos = new ArrayList<>();
    public static ArrayList<Integer> qwirkles = new ArrayList<>();
    
    public static int correrBackTracking(){
        backTracking(tiles.getBactackingTiles());
        for(Object o : posibilities){
            ArrayList<Insertion> posibilitie = (ArrayList<Insertion>) o;
            contarPuntos(matrix, posibilitie);
        }
        int higestScoreIndex = 0, highScore = 0;
        for(int i = 0; i < puntos.size();i++){
            if(puntos.get(i) > highScore){
                highScore = puntos.get(i);
                higestScoreIndex = i;
                
            }
        }
        if(posibilities.size() > 0){
            ArrayList<Insertion> movement = (ArrayList<Insertion>) posibilities.get(higestScoreIndex);
           matrix.setTiles(movement);
           for(Insertion ins : movement){
               tiles.popBactrackingTiles(ins.tile);
           }
        }
        posibilities.clear();
        puntos.clear();
        qwirkles.clear();
        return highScore;
    }
    
    public static int correrBackTrackingInteligente(){
        if(!isStarted){
            for(int i = 0; i < matrix.getLines(); i++ ){
                for(int j = 0; j < matrix.getColumns(); j++){
                    if(matrix.getTile(i, j).length() > 1){
                        isStarted = true;
                        j = matrix.getColumns();
                        i = matrix.getLines();
                    }
                }
            }
            if(!isStarted){
                String tile = tiles.popSmartBactrackingTiles(0);
                matrix.setTile(tile, 6, 6);
                return 1;
            }
        }
        backTracking( tiles.getSmartBacktrackingTiles() );
        for(Object o : posibilities){
            ArrayList<Insertion> posibilitie = (ArrayList<Insertion>) o;
            contarPuntos(matrix, posibilitie);
            
        }
        int higestScoreIndex = 0, highScore = 0;
        for(int i = 0; i < puntos.size();i++){
            int tempScore = puntos.get(i) - (qwirkles.get(i)*4);
            boolean bloks = blocksAPlay((ArrayList<Insertion>)posibilities.get(i), tiles.getSmartBacktrackingTiles());
            if(bloks == true){
                tempScore--;
            }
            if(tempScore > highScore){
                highScore = tempScore;
                higestScoreIndex = i;    
            }
            /*/Para pruebas
            System.out.println("Jugada: "+i+" Pts: "+puntos.get(i)+" Qwk: "+qwirkles.get(i));
            System.out.println("Valor tem: "+tempScore);
            System.out.println("Alto: "+higestScoreIndex);
            System.out.println("");
            /*/
        }
        
        int pts = 0;
        if(posibilities.size() > 0){
            pts = puntos.get(higestScoreIndex);
            ArrayList<Insertion> movement = (ArrayList<Insertion>) posibilities.get(higestScoreIndex);
            matrix.setTiles(movement);
            for(Insertion ins : movement){
               tiles.popSmartBactrackingTiles(ins.tile);
            }
        }
        posibilities.clear();
        puntos.clear();
        qwirkles.clear();
        return pts;
    }
    
    private static boolean blocksAPlay(ArrayList<Insertion> play, ArrayList<String> actualHand){
        //Obtiene una copia de la matriz
        String[][] matrixCopy = matrix.getCopyOfStructure();
        int lines = matrix.getLines();
        int columns = matrix.getColumns();
        //Copia la mano actual
        ArrayList<String> subHand = (ArrayList<String>)actualHand.clone();
        //Elimina las fichas en la copia de la mano
        //esto con el fin de que coincidan con la mano resultante luego de aplicar la jugada
        for(Insertion ins: play){
            subHand.remove(ins.tile);
        }
        //Si no quedan fichas para insertar entonces no bloquea futuras jugadas y retorna false
        if(subHand.size() == 0){return false;}
        //Si tiene fichas en la mano, prueba para cada una
        for(Insertion ins : play){
           int tLine = ins.line;
           int tColumn = ins.column;
           String tile = ins.tile;
           //Inserta la ficha correspondiente en la jugada
           matrix.setTileWithoutGrow(tile, tLine, tColumn);
           //Hace una comprovación de los espacios adyacentes, si hay una ficha en el espacio adyacente
           //se detiene si no hay ficha
           String tempTile = matrix.getTile(tLine - 1, tColumn);
            //Si hay un esacio vacio en la ficha adyacente
           if(tempTile == "n" || tempTile == "t"){
               tempTile = matrix.getTile(tLine - 2, tColumn);
               // Si el espacio siguiente en la misma direccion existe y no está vacio
               if(tempTile != null && tempTile != "n" && tempTile != "t"){
                   //Comprueba para cada ficha en la mano si puede hacer una jugada en esa posición
                   for(String t: subHand){
                       boolean res = isAValidInsertion(tLine - 1, tColumn, t);
                       //Si la inserción de la ficha es valida, entonces la jugada no está bloqueada
                       if(res == true){
                           //reestablece la matriz y retorna false(No hay bloqueo)
                           matrix.setStructure(matrixCopy, lines, columns);
                           return false;
                       }
                   }
               }
            }
            tempTile = matrix.getTile(tLine + 1, tColumn);
            if(tempTile == "n" || tempTile == "t"){
                tempTile = matrix.getTile(tLine + 2, tColumn);
                if(tempTile != null && tempTile != "n" && tempTile != "t"){
                    for(String t: subHand){
                        boolean res = isAValidInsertion(tLine + 1, tColumn, t);
                        if(res == true){
                            matrix.setStructure(matrixCopy, lines, columns);
                            return false;
                        }
                    }
                }
            }
            tempTile = matrix.getTile(tLine, tColumn -1);
            if(tempTile == "n" || tempTile == "t"){
               tempTile = matrix.getTile(tLine, tColumn -2);
               if(tempTile != null && tempTile != "n" && tempTile != "t"){
                   for(String t: subHand){
                       boolean res = isAValidInsertion(tLine, tColumn - 1, t);
                       if(res == true){
                           matrix.setStructure(matrixCopy, lines, columns);
                           return false;
                       }
                   }
               }
            }

            tempTile = matrix.getTile(tLine, tColumn + 1);
            if(tempTile == "n" || tempTile == "t"){
               tempTile = matrix.getTile(tLine, tColumn + 2);
               if(tempTile != null && tempTile != "n" && tempTile != "t"){
                   for(String t: subHand){
                       boolean res = isAValidInsertion(tLine, tColumn -1, t);
                       if(res == true){
                           matrix.setStructure(matrixCopy, lines, columns);
                           return false;
                       }
                   }
               }
            }
        }
        //reestablece la matriz
        matrix.setStructure(matrixCopy, lines, columns);
        //retorna true(Hay bloqueo);
        return true;
    }
        
    private static void delay(){
        /*************************DESCOMENTAR PARA*************************/
        /*********************VER LAS JUGADAS DEL BT***********************/
       
        //|||||||||||||||||SOLO CON PROPOSITO DE PROBAR|||||||||||||||||||||||||||||||||||
        if(pasoAPaso == false){return;}
        try {                                                                            //
            Thread.sleep(1000);                                                          //
        } catch (InterruptedException ex) {                                              //
            Logger.getLogger(Backtracking.class.getName()).log(Level.SEVERE, null, ex);  //
        }                                                                                //
        //|||||||||||||||||SOLO CON PROPOSITO DE PROBAR|||||||||||||||||||||||||||||||||||/
    }
    
    
    //=======================================================================================================================//
    //                      INICIO DE LAS FUNCIONES DEL BACK TRACKING
    //                      CONTAR PUNTOS A PARTIR DE LINEA: 320 
    //=======================================================================================================================//
    
   
    
    
    private static  void backTracking(ArrayList<String> hand){
        if(hand.isEmpty()){
            return;
        }
        //Dado que BoardMatrix utiliza el patron Singleton, se hace una copia de la estructura original para ser restaurada al finalizar
        String[][] matrixCopy = matrix.getCopyOfStructure();
        ArrayList<Insertion> insertList = new ArrayList<Insertion>();
        int lines = matrix.getLines();
        int columns = matrix.getColumns();
        for(int i = 0; i < matrix.getLines(); i++){
            for(int j = 0; j < matrix.getColumns(); j++){
                //Si en la posición i,j hay una "t", reviza si es posible insertar la ficha.
                if(matrix.getTile(i, j).equals("t") && isAValidPosition(i,j)){
                    for(String tile :hand){
                        if(!isAValidInsertion(i, j, tile)){continue;}
                        matrix.setTileWithoutGrow(tile,i, j);
                        //||||||||||||||||||||DELAY DEL HILO||||||||||||||||||||||||||||||||
                                                 delay();                                 //
                        //|||||||||||||||||DELAY DEL HILO||||||||||||||||||||||||||||||||||/
                         insertList.add( new Insertion(tile,i,j));
                         posibilities.add(insertList.clone());
                         ArrayList<String> subHand = (ArrayList<String>)hand.clone();
                         subHand.remove(tile);
                        //Vertical hacia arriba
                        verticalInsertion( subHand,i-1, j, insertList, -3);

                        //vertical hacia abajo
                        verticalInsertion( subHand,i+1, j, insertList, 3);

                        //INSERCIÓN HORIZONTAL DERECHA
                        horizontalInsertion( subHand,i, j+1, insertList, 2);

                        //INSERCIÓN HORIZONTAL IZQUIERDA
                        horizontalInsertion(subHand,i, j-1, insertList, -2);
                        insertList.remove(insertList.size()-1);
                        matrix.setStructure(matrixCopy, lines, columns); 
                    }
                }//If "t"
            }//For j
        }//For i
    }//end of function
    
    private static void verticalInsertion( ArrayList<String> hand, int line, int column,
                ArrayList<Insertion> insertList, int action ){
        if(hand.isEmpty()){
            return;
        }
        //Dado que BoardMatrix utiliza el patron Singleton, se hace una copia de la estructura original para ser restaurada al finalizar
        String[][] matrixCopy = matrix.getCopyOfStructure();
        int lines = matrix.getLines();
        int columns = matrix.getColumns();
        if(isAValidPosition(line,column)){
            for(String tile: hand){
                if(isAValidInsertion(line, column, tile)){
                    insertList.add( new Insertion(tile,line,column));
                    matrix.setTileWithoutGrow(tile, line, column);
                    //||||||||||||||||||||DELAY DEL HILO||||||||||||||||||||||||||||||||
                                             delay();                                 //
                    //|||||||||||||||||DELAY DEL HILO||||||||||||||||||||||||||||||||||/
                    ArrayList<String> subHand = (ArrayList<String>)hand.clone();
                    subHand.remove(tile);
                    verticalInsertion(subHand, (action == 3)? line + 1: line -1, column, insertList, action);
                    posibilities.add(insertList.clone());
                    matrix.setStructure(matrixCopy, lines, columns); 
                    insertList.remove(insertList.size()-1);
                }// if validInsertion
            }// for tile in hand
        }//if validPosition
    }
    
    private static void horizontalInsertion(ArrayList<String> hand, int line,int column,ArrayList<Insertion> insertList,int action){
            //INSERCIÓN HORIZONTAL DERECHA
        if(hand.isEmpty()){
            return;
        }
        //Dado que BoardMatrix utiliza el patron Singleton, se hace una copia de la estructura original para ser restaurada al finalizar
        String[][] matrixCopy = matrix.getCopyOfStructure();
        int lines = matrix.getLines();
        int columns = matrix.getColumns();
        if(isAValidPosition(line,column)){
                for(String tile: hand){
                    if(isAValidInsertion(line, column, tile)){
                        insertList.add( new Insertion(tile,line,column));
                        matrix.setTileWithoutGrow(tile, line, column);

                        //||||||||||||||||||||DELAY DEL HILO||||||||||||||||||||||||||||||||
                                                 delay();                                 //
                        //|||||||||||||||||DELAY DEL HILO||||||||||||||||||||||||||||||||||/
                        ArrayList<String> subHand = (ArrayList<String>)hand.clone();
                        subHand.remove(tile);
                        horizontalInsertion( subHand, line, (action == 2)? column+1:column-1, insertList, action);
                        posibilities.add(insertList.clone());
                        insertList.remove(insertList.size()-1);
                        matrix.setStructure(matrixCopy, lines, columns); 

                }//if validInsertion
            }//for tiles in hand
        }//If validPosition
    }
    
    //Comprueba que en el campo i, j se pueda realizar una inserción
    //En otras palabras, si vale la pena revisar ficha por ficha
    private static boolean isAValidPosition(int i, int j){
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
    private static boolean isAValidInsertion(int i, int j, String tile){
        if(!(matrix.getTile(i, j).equals("n") || matrix.getTile(i, j).equals("t"))){return false;}
        boolean valid= true;

        for(int li = i+1; li <= i+7; li++){
            String tempTile = matrix.getTile(li, j);
            if(tempTile != null && tempTile.length() == 3){
               //Si la ficha a insertar y la ficha temporal son iguales o no coinciden en nada, se retorna sin insertar. 
                if(tempTile.equals(tile)) {return false;}
                if(!(tempTile.charAt(0) == tile.charAt(0) || tempTile.charAt(2) == tile.charAt(2)) || tile.equals(tempTile) ){
                    return false;
                }

           }else{li= i+7;}
       }
        for(int li = i-1; li >= i-7; li--){
            String tempTile = matrix.getTile(li, j);
            if(tempTile != null && tempTile.length() == 3){
               //Si la ficha a insertar y la ficha temporal son iguales o no coinciden en nada, se retorna sin insertar. 
               if(tempTile.equals(tile)) {return false;}
                if(!(tempTile.charAt(0) == tile.charAt(0) || tempTile.charAt(2) == tile.charAt(2)) || tile.equals(tempTile) ){
                    return false;
                }
           }else{li= i-7;}
       }
        //comprobar a la derecha
        for(int c = j+1; c <= j+7 ; c++){
            String tempTile = matrix.getTile(i, c);
            if(tempTile != null && tempTile.length() == 3){
                if(tempTile.equals(tile)){return false;}
                if(!(tempTile.charAt(0) == tile.charAt(0) || tempTile.charAt(2) == tile.charAt(2)) || tile.equals(tempTile) ){
                    return false;
                }
            }else{c= j+7;}
        }
        //comprobar a la izquierda
        for(int c = j-1; c >= j-7 ; c--){
            String tempTile = matrix.getTile(i, c);
            if(tempTile != null && tempTile.length() == 3){
                if(!(tempTile.charAt(0) == tile.charAt(0) || tempTile.charAt(2) == tile.charAt(2)) || tile.equals(tempTile) ){
                    return false;
                }
            }else{c= j-7;}
        }
        return valid;
    }
    
    //=======================================================================================================================//
    //                      FIN DE LAS FUNCIONES DEL BACK TRACKING
    //=======================================================================================================================//
    
    
    //______________METODOS PARA CONTAR LOS PUNTOS______________
    
    public static boolean esFila(Insertion ficha1, Insertion ficha2){
        return ficha1.line==ficha2.line;     
    }
        
    public static void contarPuntos(BoardMatrix matrix,ArrayList<Insertion> insertList){
        //Variables que llevan los puntos de la fila/columna principal
        //y sus filas/columnas secundarias
        int principal=1,secundario=1;
        int ayuda,fila,colum;
        int qwirklesL=0;
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
            if( principal==5)qwirklesL++;
            
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
            if( secundario==5)qwirklesL++;
            
            qwirkles.add(qwirklesL);
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
                    }   
                }if(principal==6)principal+=6;
                if( principal==5)qwirklesL++;
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
                            if(!"n".equals(celda)&&!"t".equals(celda)){
                                ayuda++; 
                            }else break;}
                    }else{celda=matrix.getTile(fila-1, colum);
                        if(!"n".equals(celda)&&!"t".equals(celda)){//+++++++++++Ver si tiene vecino arriba
                            for (int i = fila-1; i >-1; i--){
                                celda=matrix.getTile(i, colum);
                                if(!"n".equals(celda)&&!"t".equals(celda)){
                                   ayuda++; 
                                }else break;}
                        }
                    }
                    if(ayuda==6)ayuda+=6;
                    if( ayuda==5)qwirklesL++;
                    if(ayuda!=1)secundario+=ayuda;
                    
                }  
            }else{//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Cuando es una columna           
                //==========================Contar los puntos de toda la columna
                //==============================================================
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
                if( principal==5)qwirklesL++;
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
                            if(!"n".equals(celda)&&!"t".equals(celda)){
                                ayuda++; 
                            }else break;}  
                    }else{celda=matrix.getTile(fila, colum-1);
                        if(!"n".equals(celda)&&!"t".equals(celda)){//___________Ver si tiene vecino izq
                            for (int i = colum-1; i > -1; i--){
                                celda=matrix.getTile(fila, i);
                                if(!"n".equals(celda)&&!"t".equals(celda)){
                                   ayuda++; 
                                }else break;}      
                        }
                    }
                    if(ayuda==6)ayuda+=6;
                    if( ayuda==5)qwirklesL++;
                    if(ayuda!=1)secundario+=ayuda;
                }
            }
            qwirkles.add(qwirklesL);
            if(secundario==1)puntos.add(principal);
            else puntos.add(principal+secundario);
        }     
    }
}
