/*
 *  Creado por: David Pérez Sánchez
 *  Matrícula: 163202
 *  Materia: 
 *  Universidad Politécnica de Chiapas.
 *  Fecha de Creación: /10/2017
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
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
            Parent root = FXMLLoader.load(getClass().getResource("FXMLGUIBienvenida.fxml"));
            Scene scene = new Scene(root);
            String tema;
            URL url = getClass().getResource("Adivinador.css");
            tema = url.toExternalForm();
            scene.getStylesheets().add(tema);
            stage.setScene(scene);
            stage.show();
      }

      /**
       * @param args the command line arguments
       * @throws java.io.FileNotFoundException Excepción creada cuando el archivo no existe o no se puede leer.
       */
      public static void main(String[] args) throws FileNotFoundException, IOException {
            launch(args);
      }
}