/*
 *  Creado por: David Pérez Sánchez
 *  Matrícula: 163202
 *  Materia: 
 *  Universidad Politécnica de Chiapas.
 *  Fecha de Creación: /10/2017
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author David Pérez S.
 */
public class Main extends Application {
      
      @Override
      public void start(Stage stage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLGUIPreguntas.fxml"));
            
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.show();
      }

      /**
       * @param args the command line arguments
       * @throws java.io.FileNotFoundException Excepción creada cuando el archivo no existe o no se puede leer.
       */
      public static void main(String[] args) throws FileNotFoundException, IOException {
            launch(args);
            
            ////////////////////////
          String nombreArchivoDatos= "Tree.tree";
          String rutaArchivo= System.getProperty("user.dir") + "\\" + nombreArchivoDatos;
          
          Arbol arbol= new Arbol();
          Archivo<Arbol> archivo= new Archivo<>(nombreArchivoDatos);
          
          File file = new File(rutaArchivo);
          if (file.exists()) {
              arbol = archivo.deserializar();
              arbol.preOrder();
          } else {
              arbol.añadirPregunta("¿Es una animal doméstico?");
              arbol.añadirRespuesta("Perro", "");
              arbol.añadirRespuesta("Oso", "");
              archivo.crearArchivoVacio();
              archivo.serializar(arbol);
          }
          //juego(arbol.recorrerAdivinador(), arbol);
          archivo.serializar(arbol);
      }
      
          /**
     * Iniciar el juego >> Adivinador.
     * <p>Esta función contiene la mayor parte de la ejecucuón del juego principal</p>
     * @param nodo Nodo inicial, o sea, la raíz, para comenzar el programa.
     * @param arbol Árbol en el cual la función va a ir tomando los diferentes nodos de éste.
     */
    public static void juego(Nodo nodo, Arbol arbol){
          
          int opcionMenu;
          String preguntaUsuario, respuestaUsuario;
          Scanner lecturaTeclado= new Scanner(System.in);
          while(nodo.getDerecho() != null && nodo.getIzquierdo() != null){
                do{
                  System.out.println(nodo.getTexto()+ "\n");
                  System.out.println(" [ 1 ]  Sí");
                  System.out.println(" [ 2 ] No");
                  System.out.println(" [ 3 ] No sé");
                  System.out.print("\n Elija>> ");
                  opcionMenu= lecturaTeclado.nextInt();
                  System.out.println(" -------------------------");
                  switch(opcionMenu){
                        case 1:{
                              nodo= arbol.recorrerAdivinador(1);
                              break;
                        }
                        case 2:{
                              nodo= arbol.recorrerAdivinador(-1);
                              break;
                        }
                        case 3:{
                              nodo= arbol.recorrerAdivinador(0);
                              break;
                        }
                  }
                }while(opcionMenu <1 || opcionMenu >3);
          }
          do{
            System.out.println("\n ¿Es esto lo que estabas pensando?");
            System.out.println(" " + nodo.getTexto().toUpperCase()+ "\n");
            System.out.println(" [ 1 ]  Sí");
            System.out.println(" [ 2 ] No");
            System.out.print("\n Elija>> ");
            opcionMenu= lecturaTeclado.nextInt();
            System.out.println(" -------------------------");
            lecturaTeclado.nextLine();
            switch(opcionMenu){
                  case 1:{
                        System.out.println(" Felicidades !!\n Gracias por jugar !!");
                        break;
                  }
                  case 2:{
                        System.out.print(" Ingrese el animal que pensó: ");
                        respuestaUsuario= lecturaTeclado.nextLine();
                        System.out.print(" Ingrese una característica verdadera para un(a) " + nodo.getTexto().toUpperCase()+ ", pero falsa para un " + respuestaUsuario.toUpperCase() + ": ");
                        preguntaUsuario= lecturaTeclado.nextLine();
                        arbol.añadirPregunta("¿" + preguntaUsuario + "?");
                        arbol.añadirRespuesta(respuestaUsuario,"");
                        break;
                  }
            }
          }while(opcionMenu<1 || opcionMenu >2);
    }
}