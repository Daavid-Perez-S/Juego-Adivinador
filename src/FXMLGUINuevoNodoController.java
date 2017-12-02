/*
 * Proyecto creado por Luis Fernando Hernández Morales.
 * Ingeniería en Desarrollo de Software.
 * Materia:
 * Fecha de creacion:
 * Universidad Politécnica de Chiapas.
 * Contacto: 163189@ids.upchiapas.edu.mx   ||  961 100 3141
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

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
    
    private ObservableList<String> arrayDeCaracteristicas;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        arrayDeCaracteristicas = FXCollections.observableArrayList();
        arrayDeCaracteristicas.add("Tiene ");
        arrayDeCaracteristicas.add("Es ");
        arrayDeCaracteristicas.add("Usa ");
        arrayDeCaracteristicas.add("Suele ");
        arrayDeCaracteristicas.add("Suele ");
        
        stringCaracteristcas.setItems(arrayDeCaracteristicas);
        //Deshabilitar botones y campos para validar que todo esté lleno
        stringCaracteristcas.setDisable(true);
        caracteristica.setDisable(true);
        rutaDelArchivo.setDisable(true);
        abrirVentana.setDisable(true);
        guardar.setDisable(true);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guía rápida");
        alert.setHeaderText("Parece que no lo he podido adivinar...\nPregúntale a tus amiguitas  :)");
        alert.setContentText("Agrega el animal que pensaste, recuerda agregar una caracteristica verdadera para un " + "nodoActual" + " pero falsa para el animal que pensaste :)");
        alert.showAndWait();
        nombreNuevo.textProperty().addListener((observable,oldvalue,newvalue) -> {
            if(!nombreNuevo.getText().isEmpty()){
                stringCaracteristcas.setDisable(false);
                caracteristica.setDisable(false);
            }else{
                stringCaracteristcas.setDisable(true);
                caracteristica.setDisable(true);
            }
        });
        
        caracteristica.textProperty().addListener((observable,oldValue,newValue) -> {
            if(!caracteristica.getText().isEmpty()){
                rutaDelArchivo.setDisable(false);
                abrirVentana.setDisable(false);
            }else{
                rutaDelArchivo.setDisable(true);
                abrirVentana.setDisable(true);
            }
        });
        
        rutaDelArchivo.textProperty().addListener((observable,oldValue,newValue) -> {
            if(!rutaDelArchivo.getText().isEmpty()){
                guardar.setDisable(false);
            }else{
                guardar.setDisable(true);
            }
        });
    }    
    
}
