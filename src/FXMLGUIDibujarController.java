/*
 *  Creado por: David Pérez Sánchez
 *  Matrícula: 163202
 *  Materia: 
 *  Universidad Politécnica de Chiapas.
 *  Fecha de Creación: /10/2017
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;

/**
 * FXML Controller class
 *
 * @author David Pérez S.
 */
public class FXMLGUIDibujarController implements Initializable {
      
      @FXML
      Canvas canvas;
      
      Archivo<Arbol> archivo = new Archivo<>("Tree.tree");
      Arbol arbol = new Arbol();
      
      /**
       * Initializes the controller class.
       * @param url
       * @param rb
       */
      @Override
      public void initialize(URL url, ResourceBundle rb) {
            
            arbol= archivo.deserializar();
            
            double altoCanvas= 150 + (arbol.obtenerNivelArbol() * 100);
            double anchoCanvas= 150 + (arbol.obtenerNivelArbol() * 200);
            canvas.setWidth(anchoCanvas);
            canvas.setHeight(altoCanvas);
            
            
      }      
      
}
