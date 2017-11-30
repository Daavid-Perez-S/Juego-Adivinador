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
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
      
      Stage stage;
      
      private Arbol arbol;
      private Nodo nodo;
      private boolean firstTime;
      
      
      private void respuestaSi() throws IOException {
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

      private void si(){
          nodo = arbol.recorrerAdivinador(1);
          System.out.println("Nodo = " + nodo.getTexto());
          mostrarPregunta();
      }
      
      private void no(){
          nodo = arbol.recorrerAdivinador(-1);
          mostrarPregunta();
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
          }
          /*if(nodo.getDerecho() != null && nodo.getIzquierdo() != null){
            System.out.println("Entra");
          }*/
      }
      
      private void disableButtons(boolean b){
          botonSi.setDisable(b);
          botonNo.setDisable(b);
          botonNoSe.setDisable(b);
      }
      
      @FXML
      private void responderSi_PasaNodo(ActionEvent event) throws IOException {
          //Validar que sea una hoja
          if(nodo.getDerecho() == null && nodo.getIzquierdo() == null){
            respuestaSi();
              System.out.println("No adivino");
          }else{
            si();   
            System.out.println("Nodo = " + nodo.getTexto());
          }
      }
      
      @FXML
      private void responderNo_PasaNodo(ActionEvent event) {
            no();
            System.out.println("Nodo = " + nodo.getTexto());
      }
      
      @FXML
      private void responderNoSe_PasaNodo(ActionEvent event) {
            dunno();
            System.out.println("Nodo = " + nodo.getTexto());
      }
}