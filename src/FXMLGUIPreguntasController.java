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
import javafx.scene.control.Alert;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLGUIRespuestas.fxml"));
        Parent root = (Parent) loader.load();
        FXMLGUIRespuestasController controller = loader.<FXMLGUIRespuestasController>getController();
        controller.setNodo(nodo);
        controller.setArbol(arbol);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        /*
        Archivo<Nodo> respaldoDelNodo = new Archivo("Node.nd");
        respaldoDelNodo.crearArchivoVacio();
        respaldoDelNodo.serializar(nodo);
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLGUIRespuestas.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        */
      }
      
      @Override
      public void initialize(URL url, ResourceBundle rb) {
          System.out.println("Inicia");
          firstTime = true;
          disableButtons(true);
          try {
              //Iniciar el arbol
              String nombreArchivoDatos= "Tree.tree";
              String rutaArchivo= System.getProperty("user.dir") + "\\" + nombreArchivoDatos;
              
              System.out.println(rutaArchivo);
              arbol = new Arbol();
              Archivo<Arbol> archivo= new Archivo<>(nombreArchivoDatos);
              
              File file = new File(rutaArchivo);
              if(file.exists()){
                  arbol = archivo.deserializar();
                  arbol.preOrder();
              }else{
                  try {
                      arbol.añadirPregunta("¿Es una animal doméstico?");
                      arbol.añadirRespuesta("Perro","");
                      arbol.añadirRespuesta("Oso","");
                      archivo.crearArchivoVacio();
                      archivo.serializar(arbol);
                  } catch (FileNotFoundException ex) {
                      Logger.getLogger(FXMLGUIPreguntasController.class.getName()).log(Level.SEVERE, null, ex);
                  }
              }
              //juego(arbol.recorrerAdivinador(), arbol);
              arbol.resetTemporalRecorrido();
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
          botonNoSe.setDisable(false);
          if(nodo.getIzquierdo() == null && nodo.getDerecho() == null){
            //Si el nodo es una hoja
            //Y el usuario si pensó en este animal, entonces mostrará
            //Una ventana que diga "Te lo adivine :v"
              respuestaSi();
              System.out.println("Te lo adivine prro");
          }else{
            nodo = arbol.recorrerAdivinador(1);   
            mostrarPregunta();
          }
      }
      
      private void no() throws IOException{
          botonNoSe.setDisable(false);
          if(nodo.getIzquierdo() == null && nodo.getDerecho() == null){
            //Si el nodo es una hoja
            //Y el usuario no pensó en este animal, entonces mostrará
            //Una ventana para que agregue al animal
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("Adivinador");
              alert.setContentText("Oh, estuve muy cerca!\nAgrega el animal que pensaste...");
              alert.showAndWait();
              Stage old = (Stage) botonSi.getScene().getWindow();
              Stage nuevo = new Stage();
              FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLGUINuevoNodo.fxml"));
              Parent root = (Parent) loader.load();
              FXMLGUINuevoNodoController controller = loader.<FXMLGUINuevoNodoController>getController();
              controller.setArbol(arbol);
              System.out.println("ok");
              Scene scene = new Scene(root);
              nuevo.setScene(scene);
              old.close();
              nuevo.show();
          }else{
            nodo = arbol.recorrerAdivinador(-1);   
            mostrarPregunta();
          }
      }
      
      private void dunno(){
          nodo = arbol.recorrerAdivinador(0);
          botonNoSe.setDisable(true);
          mostrarPregunta();
      }
      
      
      private void mostrarPregunta(){
          disableButtons(false);
          if(firstTime){
            labelPregunta.setText(nodo.getTexto());
            firstTime = false;
          }else{
              if(getGeneroXD(nodo.getTexto())){
                labelPregunta.setText("Estás pensando en un " + nodo.getTexto() + "?");
              }else{
                labelPregunta.setText("Estás pensando en una " + nodo.getTexto() + "?");  
              }
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
      
      private boolean getGeneroXD(String str){
          boolean macho = true;
          char last = 'z';
          for(int i = 0; i < str.length(); i++){
              last = str.charAt(i);
          }
          
          if(last == 'a'){
              macho = false;
          }
          return macho;
      }
}