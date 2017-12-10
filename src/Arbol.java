import java.io.Serializable;
/*
 *  Creado por: David Pérez Sánchez
 *  Matrícula: 163202
 *  Materia: Estructura de Datos
 *  Universidad Politécnica de Chiapas.
 *  Fecha de Creación: 30/10/2017
 */

/**
 * Clase Arbol.
 * <p>Esta clase implementa la interfaz >> Serializable.</p>
 * @author David Pérez S.
 */
public class Arbol implements Serializable {
      
      private Nodo raiz;
      private int size;
      private Nodo temporalRecorrido= new Nodo();
      private Nodo temporalRespuesta= new Nodo();
      private int nivel;
      private boolean ramaIzquierdaDerecha;
      private boolean arbolInicializado;
      private int nivelRamaIzquierda;
      private int nivelRamaDerecha;
      /**
       * <b>Constructor de la clase Arbol.</b>
       */
      public Arbol(){
            raiz= null;
            size= 0;
            temporalRecorrido= raiz;
            temporalRespuesta= raiz;
            nivel= 0;
            arbolInicializado= false;
            nivelRamaDerecha= 0;
            nivelRamaIzquierda= 0;
      }
      /**
       * <b>Recorrer árbol adivinador.</b>
       * <p>Únicamente recorre los nodos del árbol uno a uno, dependiendo del lado que eligió el usuario.</p>
       * @param ladoDelArbol <br><ul><li>1: Lado derecho del árbol (TRUE)</li><li>-1: Lado izquierdo del árbol (FALSE)</li><li>0: Ambos lados del árbol (NO SÉ)</li></ul>
       * @return Retorna el nodo siguiente del recorrido, dependiendo del lado que el usuario eligió. Puede ser un nodo de tipo PREGUNTA o RESPUESTA.
       */
      public Nodo recorrerAdivinador(int ladoDelArbol){
            Nodo n= new Nodo();
            try{
                  if(raiz != null){
                        if(nivel == 0){
                              temporalRecorrido = raiz;
                              n= temporalRecorrido;
                        }else{
                              if(!isHoja(temporalRecorrido)){   // El nodo es de tipo pregunta
                                    switch(ladoDelArbol){
                                          case 1:{
                                                temporalRecorrido= temporalRecorrido.getDerecho();
                                                n= temporalRecorrido;
                                                ramaIzquierdaDerecha= true;
                                                break;}
                                          case -1:{
                                                temporalRecorrido= temporalRecorrido.getIzquierdo();
                                                n= temporalRecorrido;
                                                ramaIzquierdaDerecha= false;
                                                break;}
                                          case 0:{
                                                n.setTexto(temporalRecorrido.getIzquierdo().getTexto()+ "/" + temporalRecorrido.getDerecho().getTexto());
                                                break;}
                                    }
                              }else
                                    n= temporalRecorrido;   // El nodo es de tipo respuesta (Hoja)
                        }
                        nivel++;
                  }else
                        System.out.println("\t[ No existe ningún nodo aún ]");
            }catch(Exception e){
                  System.err.println("\t[ No se pudo mostrar el nodo ] " + e);
            }
            return n;
      }
       /**
       * <b>Recorrer árbol adivinador.</b>
       * <p>Únicamente recorre los nodos del árbol uno a uno.</p>
       * @return Retorna el nodo siguiente del recorrido, dependiendo del lado que el usuario eligió. Puede ser un nodo de tipo pregunta o respuesta.
       */
      public Nodo recorrerAdivinador(){
            return recorrerAdivinador(1);
      } 
      /**
       * <b>Inserta un nodo pregunta en el árbol actual.</b>
       * <p> Las preguntas siempre de introducirán un nivel arriba del nodo respuesta en el que estemos actualmente.</p>
       * @param valor Cadena de texto a introducir.
       * @return Retorna TRUE si la respuesta pudo ser introducida correctamente al árbol, FALSE en caso contrario.
       */
      public boolean añadirPregunta(String valor){            // TRUE: lado derecho del árbol, FALSE: lado izquierdo del árbol
            boolean bandera= true;
            try{
                  Nodo n= new Nodo(valor);
                  if(raiz == null){
                        raiz= n;
                        temporalRespuesta= raiz;
                        nivelRamaDerecha++;
                        nivelRamaIzquierda++;
                  }else{
                        Nodo temporal= temporalRecorrido.getPadre();
                        n.setDerecho(temporalRecorrido);
                        temporalRecorrido.setPadre(n);
                        temporalRecorrido= temporalRecorrido.getPadre();
                        temporalRespuesta= temporalRecorrido;
                        if(ramaIzquierdaDerecha == true){
                              temporal.setDerecho(n);
                              nivelRamaDerecha++;
                        }else{
                              temporal.setIzquierdo(n);
                              nivelRamaIzquierda++;
                        }
                        n.setPadre(temporal);
                  }
                  size++;
            }catch(Exception e){
                  bandera= false;
                  System.err.println("\t[ No se pudo añadir la pregunta ]");
            }
            return bandera;
      }
      /**
       * <b>Inserta un nodo respuesta en el árbol actual.</b>
       * <p> Las respuestas siempre de introducirán del lado IZQUIERDO del nodo pregunta en el que estemos actualmente.</p>
       * @param valor Cadena de texto a introducir.
       * @param ruta Ruta de la imágen que el nodo contendrá
       * @return Retorna true si la respuesta pudo ser introducida correctamente al árbol, FALSE en caso contrario.
       */
      public boolean añadirRespuesta(String valor, String ruta){         // Las respuestas siempre se añaden del lado izquierdo del nodo
            boolean bandera= true;
            try{
                  if(raiz == null){
                        System.err.println("\t[ Cree primero una pregunta ]");
                  }else{
                        Nodo nuevo= new Nodo(valor,ruta);
                        if(temporalRespuesta.getDerecho() == null)
                              temporalRespuesta.setDerecho(nuevo);
                        else
                              temporalRespuesta.setIzquierdo(nuevo);
                        nuevo.setPadre(temporalRespuesta);
                        size++;
                  }
                  arbolInicializado= true;
            }catch(Exception e){
                  bandera= false;
                  System.err.println("\t[ No se pudo añadir la respuesta ] " + e);
            }
            return bandera;
      }
      /**
       * <b>Resetear Nodos.</b>
       * <p>El nodo "temporalRecorrido es igualado al nodo "raiz".<br>La variable "nivel" es igualada a 0.<br>Se resetea a como estaban al principio.</p>
       */
      public void resetTemporalRecorrido(){
            temporalRecorrido= raiz;
            nivel= 0;
      }
      /**
       * <b>Imprimir el árbol en PreOrden.</b>
       * <p>Imprime el árbol en PreOrden a partir del nodo pasado como parámetro en la función.</p>
       * @param nodo A partir de este nodo introducido se imprimirá el árbol en el orden especificado anteriormente.
       * <p>Orden:<br><ul><li>Nodo izquierdo</li><li>Nodo derecho</li><li>Raíz</li></ul></p>
       */
      public void preOrder (Nodo nodo)
      {
          if (nodo != null)
          {
              System.out.print(nodo.getTexto());
              preOrder (nodo.getIzquierdo());
              preOrder (nodo.getDerecho());
          }
      }
      /**
       * <b>Imprimir el árbol en PreOrden.</b>
       * <p>Orden:<br><ul><li>Raíz</li><li>Nodo izquierdo</li><li>Nodo derecho</li></ul></p>
       */
      public void preOrder ()
      {
          preOrder (raiz);
          System.out.println();
      }
      /**
       * <b>Imprimir el árbol en InOrden.</b>
       * <p>Imprime el árbol en Orden Normal a partir del nodo pasado como parámetro en la función.</p>
       * @param nodo A partir de este nodo introducido se imprimirá el árbol en el orden especificado anteriormente.
       * <p>Orden:<br><ul><li>Nodo izquierdo</li><li>Raíz</li><li>Nodo derecho</li></ul></p>
       */
      public void inOrder (Nodo nodo)
      {
          if (nodo != null)
          {    
              inOrder (nodo.getIzquierdo());
              System.out.print(nodo.getTexto());
              inOrder (nodo.getDerecho());
          }
      }
      /**
       * <b>Imprimir el árbol en InOrden.</b>
       * <p>Orden:<br><ul><li>Nodo izquierdo</li><li>Raíz</li><li>Nodo derecho</li></ul></p>
       */
      public void inOrder ()
      {
          inOrder (raiz);
          System.out.println();
      }
      /**
       * <b>Imprimir el árbol en PostOrden.</b>
       * <p>Imprime el árbol en PostOrden a partir del nodo pasado como parámetro en la función.</p>
       * @param nodo A partir de este nodo introducido se imprimirá el árbol en el orden especificado anteriormente.
       * <p>Orden:<br><ul><li>Nodo izquierdo</li><li>Nodo derecho</li><li>Raíz</li></ul></p>
       */
      public void postOrder (Nodo nodo)
      {
          if (nodo != null)
          {
              postOrder (nodo.getIzquierdo());
              postOrder (nodo.getDerecho());
              System.out.print(nodo.getTexto());
          }
      }
      /**
       * <b>Imprimir el árbol en PostOrden.</b>
       * <p>Orden:<br><ul><li>Nodo izquierdo</li><li>Nodo derecho</li><li>Raíz</li></ul></p>
       */
      public void postOrder ()
      {
          postOrder (raiz);
          System.out.println();
      }
      /**
       * <b>Obtener el número de nodos del árbol.</b>
       * <p>Obtenemos el número de nodos que el árbol contiene en este momento.<br>No confundir con el niveñ del árbol.</p>
       * @return Retorna el número de nodos que existen en el árbol.
       */
      public int size(){
            return size;
      }
      /**
       * <b>Obtener el nivel de la hoja.</b>
       * <p>Obtenemos el nivel de la hoja en el momento actual, dependiendo en que momento nos encontremos.</p>
       * @return Retorna el nivel en el árbol de la pregunta o respuesta actual en el recorrido.
       */
      public int getNivelRecorridoActual(){
            return nivel;
      }
      /**
       * <b>Comprobar si es hoja.</b>
       * <p>Comprueba si el nodo pasado como parámetro es una hoja del árbol o no.</p>
       * @param nodo Nodo a comprobar si es hoja o no.
       * @return Retorna TRUE si el nodo introducido es una hoja y FALSE si no es una hoja.
       */
      public boolean isHoja(Nodo nodo){
            return nodo.getDerecho()== null && nodo.getIzquierdo()== null;
      }
      /**
       * <b>Borrar árbol actual.</b>
       * <p>La raíz del árbol se hace nula para perder todos los nodos adyacentes.</p>
       * @return Retorna TRUE si el árbol pudo ser borrado.
       */
      public boolean borrarArbol(){
            
            boolean bandera= true;
            try{
                  raiz= null;
            }catch(Exception e){
                  bandera= false;
            }
            return bandera;
      }
      /**
       * <b>Comprobar Árbol Inicializado</b>
       * <p>Comprueba si el árbol ya fue inicializado con la pregunta inicial y sus dos respuestas adyacentes</p>
       * @return Retorna TRUE si el árbol ya ha sido inicializado, y FALSE en caso contrario.
       */
      public boolean arbolInicializado(){
            return arbolInicializado;
      }
            /**
       * <b>Obtener Nivel de Rama Izquierda.</b>
       * @return Retorna el número de niveles de la Rama Izquierda del Árbol actual.
       */
      public int obtenerNivelRamaIzquierda(){
            
            return nivelRamaIzquierda;
      }
      /**
       * <b>Obtener Nivel de Rama Derecha.</b>
       * @return Retorna el número de niveles de la Rama Derecha del Árbol actual.
       */
      public int obtenerNivelRamaDerecha(){
            
            return nivelRamaDerecha;
      }
      /**
       * <b>Obtener el nivel del Árbol actual.</b>
       * <p>No importa si la rama izquierda o la derecha es más grande que la otra, siempre retornará la más grande.</p>
       * @return Retorna el nivel nivel más grande de niveles que tiene el árbol.
       */
      public int obtenerNivelArbol(){
            
            int nivelMayor;
            
            if(obtenerNivelRamaIzquierda() > obtenerNivelRamaDerecha()){
                  nivelMayor= obtenerNivelRamaIzquierda();
                  return nivelMayor;
            }else
                  nivelMayor= obtenerNivelRamaDerecha();
            return nivelMayor;
      }
      
      public String getTemporalRecorrido(){
            return temporalRecorrido.getTexto();
      }
}