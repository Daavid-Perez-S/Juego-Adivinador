/*
 *  Creado por: David Pérez Sánchez & Luis Fernando Hernández Morales
 *  Matrícula: 163202, 163189
 *  Materia: 
 *  Universidad Politécnica de Chiapas.
 *  Fecha de Creación: /10/2017
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
* @author David Pérez S.
 */
public class FXMLGUIPreguntasController implements Initializable {
      
      @FXML
      private Label labelTiempo;
      
      @FXML
      private Label labelPregunta;
      
      @FXML
      private Button botonSi;
      
      @FXML
      private Button botonNo;
      
      @FXML
      private Button botonNoSe;
       
      private Arbol arbol;
      private Nodo nodo;
      private boolean firstTime;
      
      
      private void respuestaSi() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLGUIRespuestas.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
      }
      
      @Override
      public void initialize(URL url, ResourceBundle rb) {
          firstTime = true;
          disableButtons(true);
          try {
              //Iniciar el arbol
              String nombreArchivoDatos= "Tree.tree";
              String rutaArchivo= System.getProperty("user.dir") + "\\" + nombreArchivoDatos;
              
              arbol = new Arbol();
              Archivo<Arbol> archivo= new Archivo<>(nombreArchivoDatos);
              
              File file = new File(rutaArchivo);
              if(file.exists()){
                  arbol = archivo.deserializar();
                  arbol.preOrder();
              }else{
                  try {
                      arbol.añadirPregunta("¿Es una animal doméstico?");
                      arbol.añadirRespuesta("Perro");
                      arbol.añadirRespuesta("Oso");
                      archivo.crearArchivoVacio();
                      archivo.serializar(arbol);
                  } catch (FileNotFoundException ex) {
                      Logger.getLogger(FXMLGUIPreguntasController.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
              //juego(arbol.recorrerAdivinador(), arbol);
              archivo.serializar(arbol);
              
              //Hilo hilo = new Hilo("SSSS",labelTiempo);
              //hilo.start();
              
              ///////////////////////////////////////////////////
              //          Mostrar la primera pregunta         ///
              //////////////////////////////////////////////////
              nodo = arbol.recorrerAdivinador();       
              //Activar los métodos cuando un botón  es pulsado
              mostrarPregunta();
          } catch (FileNotFoundException ex) {
              Logger.getLogger(FXMLGUIPreguntasController.class.getName()).log(Level.SEVERE, null, ex);
          }                  
      }

      private void si() throws IOException{
          if(nodo.getIzquierdo() == null && nodo.getDerecho() == null){
            //Si el nodo es una hoja
            //Y el usuario si pensó en este animal, entonces mostrará
            //Una ventana que diga "Te lo adivine :v"
              System.out.println("Te lo adivine prro");
          }else{
            nodo = arbol.recorrerAdivinador(1);   
            mostrarPregunta();
          }
      }
      
      private void no() throws IOException{
          if(nodo.getIzquierdo() == null && nodo.getDerecho() == null){
            //Si el nodo es una hoja
            //Y el usuario no pensó en este animal, entonces mostrará
            //Una ventana para que agregue al animal
          System.out.println("No lo adivine :c");
          Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLGUINuevoNodo.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
          }else{
            nodo = arbol.recorrerAdivinador(-1);   
            mostrarPregunta();
          }
      }
      
      private void dunno(){
          nodo = arbol.recorrerAdivinador(0);
          mostrarPregunta();
      }
      
      
      private void mostrarPregunta(){
          disableButtons(false);
          if(firstTime){
            labelPregunta.setText(nodo.getTexto());
            firstTime = false;
          }else{
            labelPregunta.setText("Estás pensando en un(a) " + nodo.getTexto() + "?");
            firstTime = true;
          }
      }
      
      private void disableButtons(boolean b){
          botonSi.setDisable(b);
          botonNo.setDisable(b);
          botonNoSe.setDisable(b);
      }
      
      @FXML
      private void responderSi_PasaNodo(ActionEvent event) throws IOException {
        si();
      }
      
      @FXML
      private void responderNo_PasaNodo(ActionEvent event) throws IOException {
        no();
      }
      
      @FXML
      private void responderNoSe_PasaNodo(ActionEvent event) {
        dunno();
      }
}