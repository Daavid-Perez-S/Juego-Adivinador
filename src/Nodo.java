import java.io.Serializable;
/*
 *  Creado por: David Pérez Sánchez
 *  Matrícula: 163202
 *  Materia: Estructura de Datos
 *  Universidad Politécnica de Chiapas.
 *  Fecha de Creación: 20/11/2017
 */

/**
 * Clase Nodo.
 * <p>Esta clase implementa la interfaz >> Serializable.</p>
 * @author David Pérez S.
 */
public class Nodo implements Serializable{
      
      private Nodo padre;
      private Nodo derecho;
      private Nodo izquierdo;
      private String texto;
      private String rutaImagen;
      /**
       * <b>Constructor de la clase Nodo.</b>
       * @param texto Texto que el nodo va a contener
       */
      public Nodo (String texto){
        derecho= null;
        izquierdo= null;
        padre= null;
        this.texto= texto;
        this.rutaImagen = null;
      }
      /**
       * <b>Constructor de la clase Nodo.</b>
       */
       public Nodo (){
        derecho= null;
        izquierdo= null;
        padre= null;
        texto= null;
        this.rutaImagen = null;
      }
       
       public Nodo(String texto, String ruta){
        derecho = null;
        izquierdo = null;
        padre = null;
        this.texto = texto;
        this.rutaImagen = ruta;
       }
       /**
        * <b>Obtener texto del nodo.</b>
        * @return Retorna la cadena de texto que contenga el nodo.
        */
      public String getTexto(){
            return texto;
      }
      /**
        * <b>Obtener nodo Padre.</b>
        * @return Retorna el nodo padre del nodo
        */
      public Nodo getPadre(){
            return padre;
      }
      /**
        * <b>Obtener nodo Derecho.</b>
        * @return Retorna el nodo derecho del nodo
        */
      public Nodo getDerecho(){
            return derecho;
      }
      /**
        * <b>Obtener nodo Izquierdo.</b>
        * @return Retorna el nodo izquierdo del nodo
        */
      public Nodo getIzquierdo(){
            return izquierdo;
      }
      /**
        * <b>Establecer texto del nodo.</b>
        * @param texto Texto a establecer dentro del nodo
        */
      public void setTexto(String texto){
            this.texto=  texto;
      }
      /**
        * <b>Establecer nodo Padre.</b>
        * @param padre Nodo Padre a establecer del nodo actual
        */
      public void setPadre(Nodo padre){
            this.padre= padre;
      }
      /**
        * <b>Establecer nodo Derecho.</b>
        * @param derecho  Nodo Derecho a establecer del nodo actual
        */
      public void setDerecho(Nodo derecho){
            this.derecho= derecho;
      }
      /**
        * <b>Establecer nodo Izquierdo.</b>
        * @param izquierdo  Nodo Izquierdo a establecer del nodo actual
        */
      public void setIzquierdo(Nodo izquierdo){
            this.izquierdo= izquierdo;
      }
      
      /**<b>Obtener la imagen del nodo.</b>
       * @return Retorna una cadena de texto del con la ruta de su respectiva imagen
       */
      public String getImagen(){
          return this.rutaImagen;
      }
      
      /**<b>Obtener cadena de texto.</b>
       * <p>Método sobreescirto del Método "toString()" de Java.</p>
       * @return Retorna una cadena de texto del nodo actual
       */
      @Override
      public String toString(){
            return texto;
      }
}