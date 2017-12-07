/*
 * Proyecto creado por Luis Fernando Hernández Morales.
 * Ingeniería en Desarrollo de Software.
 * Materia:
 * Fecha de creacion:
 * Universidad Politécnica de Chiapas.
 * Contacto: 163189@ids.upchiapas.edu.mx   ||  961 100 3141
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fer_i
 */
public class FXMLGUINuevoNodoController implements Initializable {

      /**
       * Initializes the controller class.
       */
      @FXML
      private TextField nombreNuevo;
      @FXML
      private ChoiceBox stringCaracteristcas;
      @FXML
      private TextField caracteristica;
      @FXML
      private TextField rutaDelArchivo;
      @FXML
      private Button abrirVentana;
      @FXML
      private Button guardar;
      @FXML
      private ImageView imagenAnimal;

      private final ExploradorImagenes explorador = new ExploradorImagenes();
      private ObservableList<String> arrayDeCaracteristicas;
      private String rutaImagen;
      private Arbol arbol;
      private final CopiarArchivos copiador = new CopiarArchivos();
      private String[] formatoImagen;
      private Archivo<Arbol> archivo;

      @Override
      public void initialize(URL url, ResourceBundle rb) {

            arrayDeCaracteristicas = FXCollections.observableArrayList();
            arrayDeCaracteristicas.add("No tiene");
            arrayDeCaracteristicas.add("No es");
            arrayDeCaracteristicas.add("No suele");
            arrayDeCaracteristicas.add("No huele");
            arrayDeCaracteristicas.add("No se ve como");
            stringCaracteristcas.setItems(arrayDeCaracteristicas);

            //Deshabilitar botones y campos para validar que todo esté lleno
            stringCaracteristcas.setDisable(true);
            caracteristica.setDisable(true);
            rutaDelArchivo.setDisable(true);
            abrirVentana.setDisable(true);
            guardar.setDisable(true);

            nombreNuevo.textProperty().addListener((observable, oldvalue, newvalue) -> {
                  if (!nombreNuevo.getText().isEmpty()) {
                        stringCaracteristcas.setDisable(false);
                        caracteristica.setDisable(false);
                  } else {
                        stringCaracteristcas.setDisable(true);
                        caracteristica.setDisable(true);
                  }
            });
            caracteristica.textProperty().addListener((observable, oldValue, newValue) -> {
                  if (!caracteristica.getText().isEmpty()) {
                        abrirVentana.setDisable(false);
                  } else {
                        abrirVentana.setDisable(true);
                  }
            });

            rutaDelArchivo.textProperty().addListener((observable, oldvalue, newvalue) -> {
                  if (!rutaDelArchivo.getText().isEmpty()) {
                        guardar.setDisable(false);
                  } else {
                        guardar.setDisable(true);
                  }
            });
      }

      public void setArbol(Arbol a) {
            this.arbol = a;
      }

      @FXML
      private void abrirExplorador(ActionEvent e) {
            rutaImagen = explorador.startExplorer();
            rutaDelArchivo.setText(rutaImagen);
            imagenAnimal.setImage(new Image("file:" + rutaImagen));
      }

      @FXML
      private void save(ActionEvent e) throws FileNotFoundException, IOException {

            if (arbol == null) {
                  System.out.println("Arbol nulo :c");
            } else {
                  System.out.println("Arbol no nulo :v");
                  
                  String str = (String) stringCaracteristcas.getSelectionModel().getSelectedItem() + " " + caracteristica.getText();
                  formatoImagen = rutaImagen.split("\\.");
                  copiador.copiarArchivo(rutaImagen, System.getProperty("user.dir") + "\\Images\\" + nombreNuevo.getText() + "." + formatoImagen[formatoImagen.length - 1]);
                  
                  System.out.println("Temporal recorrido antes de añadir pregunta = " + arbol.getTemporalRecorrido());
                  arbol.añadirPregunta(str);
                  System.out.println("Temporal recorrido despues de añadir pregunta = " + arbol.getTemporalRecorrido());
                  
                  arbol.añadirRespuesta(nombreNuevo.getText(), System.getProperty("user.dir") + "\\Images\\" + nombreNuevo.getText() + "." + formatoImagen[formatoImagen.length - 1]);
                  System.out.println("Temporal recorrido despues de añadir respuesta = " + arbol.getTemporalRecorrido());
                  
                  Stage thisStage = (Stage) guardar.getScene().getWindow();
                  arbol.resetTemporalRecorrido();
                  System.out.println("Temp recorrido despues de resetear: " + arbol.getTemporalRecorrido());
                  archivo.serializar(arbol);
                  arbol= archivo.deserializar();
                  arbol.preOrder();

                  Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                  alert2.setTitle("Adivinador");
                  alert2.setContentText("Se ha guardado el nuevo animal ;D\n\nGracias por jugar!");
                  alert2.showAndWait();
                  thisStage.close();

                  Stage old = (Stage) guardar.getScene().getWindow();
                  Stage nuevo = new Stage();
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLGUIBienvenida.fxml"));
                  Parent root = (Parent) loader.load();
                  System.out.println("Ventana Bienvenida OK");
                  Scene scene = new Scene(root);
                  nuevo.setScene(scene);
                  old.close();
                  nuevo.show();
            }
      }

      public void setArchivo(Archivo archivo) {
            this.archivo = archivo;
      }
}
