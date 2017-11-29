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
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * @author David Pérez S.
 */
public class FXMLGUIPreguntasController implements Initializable {
      
      @FXML
      private Label labelTiempo;
      Stage stage;
      
      @FXML
      private void respuestaSi(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLGUIRespuestas.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
      }
      @Override
      public void initialize(URL url, ResourceBundle rb) {
            Hilo hilo = new Hilo("Hilo Tiempo",labelTiempo);
            //hilo.run();
      }      
      
}