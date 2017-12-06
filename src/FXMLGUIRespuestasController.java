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
      
      /**
       * Initializes the controller class.
       * @param url
       * @param rb
       */
      @Override
      public void initialize(URL url, ResourceBundle rb) {
          Archivo<Nodo> recuperarNodo = new Archivo<>("Node.nd");
          nodoRespuesta = recuperarNodo.deserializar();
        if(nodoRespuesta == null){
            labelTituloImagen.setText("Algo salio mal :c");
        }else{
            labelTituloImagen.setText("Pensaste en un(a)" + nodoRespuesta.getTexto());
            imageArea.setImage(new Image(nodoRespuesta.getImagen()));
          }
        
        si.setOnAction(event ->{
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Adivinador");
            alert2.setContentText("He adivinado!\nGracias por jugar!");
            alert2.showAndWait();
        });
        
        no.setOnAction(event -> {
              try {
                  Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                  alert2.setTitle("Adivinador");
                  alert2.setContentText("Vaya, no lo pude adivinar :c\n\n¿Podrías ayudarme a decirme qué animal pensaste?");
                  alert2.showAndWait();
                  Stage stage = new Stage();
                  Parent root = FXMLLoader.load(getClass().getResource("FXMLGUINuevoNodo.fxml"));
                  Scene scene = new Scene(root);
                  stage.setScene(scene);
                  stage.show();
              } catch (IOException ex) {
                  Logger.getLogger(FXMLGUIRespuestasController.class.getName()).log(Level.SEVERE, null, ex);
              }
        });
      }
}