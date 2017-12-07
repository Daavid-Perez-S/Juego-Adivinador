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
      
      /**
       * Initializes the controller class.
       * @param url
       * @param rb
       */
      @Override
      public void initialize(URL url, ResourceBundle rb) {
        
      }
      
      public void setNodo(Nodo n){
          this.nodoRespuesta = n;
          labelTituloImagen.setText(n.getTexto());
          imageArea.setImage(new Image("file:" + n.getImagen()));
      }
      
      public void setArbol(Arbol a){
          this.arbol = a;
      }
      
      @FXML
      public void si(ActionEvent e){
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Adivinador");
          alert.setContentText("Estuvo muy sencillo, gracias por jugar :D");
          alert.showAndWait();
      }
      
      @FXML
      private void no(ActionEvent e) throws IOException{
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Adivinador");
          alert.setContentText("Oh, estuve muy cerca!\nAgrega el animal que pensaste...");
          alert.showAndWait();
          Stage old = (Stage) si.getScene().getWindow();
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
      }
}