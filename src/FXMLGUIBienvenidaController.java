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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author David Pérez S.
 */
public class FXMLGUIBienvenidaController implements Initializable {

      @FXML
      private ImageView imagenPresentacion;
      
      private static Stage stagee;
      /**
       * Initializes the controller class.
       * @param url
       * @param rb
       */
      @Override
      public void initialize(URL url, ResourceBundle rb) {
            imagenPresentacion.setImage(new Image("file:" + System.getProperty("user.dir") + "\\Images\\" + "Guacamaya.png"));
      }
      @FXML
      public void jugar(ActionEvent e) throws IOException{
            
          Stage old = (Stage) imagenPresentacion.getScene().getWindow();
          Stage nuevo = new Stage();
          FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLGUIPreguntas.fxml"));
          Parent root = (Parent) loader.load();
          System.out.println("Ventana Jugar OK");
          Scene scene = new Scene(root);
          nuevo.setScene(scene);
          setStage(nuevo);
          old.close();
          nuevo.show();
      }
      
      public static void closeStage(){
            stagee.close();
      }
      public void setStage(Stage stage){
            this.stagee= stage;
      }
}