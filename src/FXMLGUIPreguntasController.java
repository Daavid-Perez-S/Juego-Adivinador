/*
 *  Creado por: David Pérez Sánchez & Luis Fernando Hernández Morales
 *  Matrícula: 163202, 163189
 *  Materia: 
 *  Universidad Politécnica de Chiapas.
 *  Fecha de Creación: /10/2017
 */

import java.io.File;
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
      @FXML
      private Button botonContinuar;
      
      private Arbol arbol = new Arbol();
      private Nodo nodo = new Nodo();
      private static Stage stagee;
      private Archivo<Arbol> archivo;
      private Hilo hilo;
      private String labelTiempo2;
      
      @Override
      public void initialize(URL url, ResourceBundle rb) {
            
          System.out.println("Inicia OK");
          //Iniciar el arbol
          String nombreArchivoDatos= "Tree.tree";
          String rutaArchivo= System.getProperty("user.dir") + "\\" + nombreArchivoDatos;
          System.out.println(rutaArchivo);
          archivo= new Archivo<>(nombreArchivoDatos);
          File file = new File(rutaArchivo);
          if(file.exists()){
                arbol = archivo.deserializar();
                System.out.println("Existeee, esto hay: ");
                arbol.preOrder();
          }else{
                      arbol.añadirPregunta("¿Es una animal doméstico?");
                      arbol.añadirRespuesta("Perro", System.getProperty("user.dir") + "\\Images\\" + "Perro.jpg");
                      arbol.añadirRespuesta("Oso", System.getProperty("user.dir") + "\\Images\\" + "Oso.jpg");
                      archivo.crearArchivoVacio();
          }
          hilo = new Hilo("Hilo Tiempo",labelTiempo);
          hilo.start();
          nodo = arbol.recorrerAdivinador();
          mostrarPregunta();                  
      }

      private void si() throws IOException{
            
          nodo= arbol.recorrerAdivinador(1);
          if(nodo.getIzquierdo() == null && nodo.getDerecho() == null){
            //Si el nodo es una hoja
            //Y el usuario si pensó en este animal, entonces se mostrará en una nueva ventana el animal adivinado
            puntosSuspensivos();
          }else{
            nodo = arbol.recorrerAdivinador(1);
            mostrarPregunta();
          }
      }
      
      private void no() throws IOException{
            
          nodo= arbol.recorrerAdivinador(-1);
          if(nodo.getIzquierdo() == null && nodo.getDerecho() == null){
            //Si el nodo es una hoja
            //Y el usuario no pensó en este animal, entonces mostrará
            //Una ventana para que agregue al animal
              puntosSuspensivos();
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
          labelPregunta.setText(nodo.getTexto());
      }
      
       private void mostrarRespuesta() throws IOException {
        Stage este = (Stage) botonSi.getScene().getWindow();
            
            este.setOnCloseRequest(event -> {
                  hilo.interrupt();
            });
        FXMLGUIBienvenidaController.closeStage();
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLGUIRespuestas.fxml"));
        Parent root = (Parent) loader.load();
        FXMLGUIRespuestasController controller = loader.<FXMLGUIRespuestasController>getController();
        controller.setNodo(nodo);
        controller.setArbol(arbol);
        controller.setArchivo(archivo);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        setStage(stage);
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
      
      private void puntosSuspensivos(){
        if(getGeneroXD(nodo.getTexto())){
                labelPregunta.setText("Estás pensando en un ... ?");
              }else{
                labelPregunta.setText("Estás pensando en una ... ?");  
              }
            visibleButtons(false);
            botonContinuar.setVisible(true);     
      }
      
      private void disableButtons(boolean b){
          botonSi.setDisable(b);
          botonNo.setDisable(b);
          botonNoSe.setDisable(b);
      }
      private void visibleButtons(boolean b){
          botonSi.setVisible(b);
          botonNo.setVisible(b);
          botonNoSe.setVisible(b);
      }
      
      @FXML
      private void responderSi_PasaNodo(ActionEvent event) throws IOException {
        si();
      }
      
      @FXML
      private void responderNo_PasaNodo(ActionEvent event) throws IOException{
        no();
      }
      
      @FXML
      private void responderNoSe_PasaNodo(ActionEvent event) {
        dunno();
      }
      @FXML
      private void responderContinuar_PasaNodo(ActionEvent event) {
            try {
                  mostrarRespuesta();
            } catch (IOException ex) {
                  Logger.getLogger(FXMLGUIPreguntasController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
      
      public static void closeStage(){
            stagee.close();
      }
      public void setStage(Stage stage){
            this.stagee= stage;
      }
}