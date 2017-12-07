/*
 *  Creado por: David Pérez Sánchez
 *  Matrícula: 163202
 *  Materia: 
 *  Universidad Politécnica de Chiapas.
 *  Fecha de Creación: /10/2017
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 * @author David Pérez S.
 */
public class FXMLGUIRespuestasController implements Initializable {

      @FXML
      private Label labelTituloImagen;
      @FXML
      private ImageView imageArea;
      @FXML
      private Button si;
      @FXML
      private Button no;
      
      private Nodo nodoRespuesta;
      private Arbol arbol;
      private static Stage stagee;
      private Archivo<Arbol> archivo;
      
      /**
       * Initializes the controller class.
       * @param url
       * @param rb
       */
      @Override
      public void initialize(URL url, ResourceBundle rb) {
            // Vacío xD
      }
      
      public void setNodo(Nodo n){
          this.nodoRespuesta = n;
          labelTituloImagen.setText(nodoRespuesta.getTexto());
          imageArea.setImage(new Image("file:" + nodoRespuesta.getImagen()));
      }
      
      public void setArbol(Arbol a){
          this.arbol = a;
      }
      
      @FXML
      public void si(ActionEvent e) throws IOException{
            
            arbol.resetTemporalRecorrido();
            archivo.serializar(arbol);
            arbol = archivo.deserializar();
            
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Adivinador");
          alert.setContentText("Estuvo muy sencillo, gracias por jugar :D");
          alert.showAndWait();
          
          Stage old = (Stage) si.getScene().getWindow();
          Stage nuevo = new Stage();
          FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLGUIBienvenida.fxml"));
          Parent root = (Parent) loader.load();
          System.out.println("Ventana Jugar OK");
          Scene scene = new Scene(root);
          nuevo.setScene(scene);
          setStage(nuevo);
          old.close();
          nuevo.show();
      }
      
      @FXML
      private void no(ActionEvent e) throws IOException{
            
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Adivinador");
          alert.setContentText("Oh, estuve muy cerca!\nAgrega el animal que pensaste por favor...");
          alert.showAndWait();
          
          Stage old = (Stage) si.getScene().getWindow();
          Stage nuevo = new Stage();
          FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLGUINuevoNodo.fxml"));
          Parent root = (Parent) loader.load();
          FXMLGUINuevoNodoController controller = loader.<FXMLGUINuevoNodoController>getController();
          controller.setArbol(arbol);
          controller.setArchivo(archivo);
          System.out.println("Ventana Nuevo Nodo OK");
          Scene scene = new Scene(root);
          nuevo.setScene(scene);
          old.close();
          nuevo.show();
      }
      
      public static void closeStage(){
            stagee.close();
      }
      public void setStage(Stage stage){
            this.stagee= stage;
      } 
      public void setArchivo(Archivo archivo){
          this.archivo= archivo;
    }
}