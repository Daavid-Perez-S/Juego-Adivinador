/*
 * Proyecto creado por Luis Fernando Hernández Morales.
 * Ingeniería en Desarrollo de Software.
 * Materia:
 * Fecha de creacion:
 * Universidad Politécnica de Chiapas.
 * Contacto: 163189@ids.upchiapas.edu.mx   ||  961 100 3141
 */

import java.io.File;
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
import javafx.stage.FileChooser;
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
    
    private ObservableList<String> arrayDeCaracteristicas;
    private String rutaImagen;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Archivo<Nodo> nodo = new Archivo<>("Node.nd");
        Nodo tmp = nodo.deserializar();
        arrayDeCaracteristicas = FXCollections.observableArrayList();
        arrayDeCaracteristicas.add("Tiene ");
        arrayDeCaracteristicas.add("Es ");
        arrayDeCaracteristicas.add("Usa ");
        arrayDeCaracteristicas.add("Suele ");
        
        stringCaracteristcas.setItems(arrayDeCaracteristicas);
        //Deshabilitar botones y campos para validar que todo esté lleno
        stringCaracteristcas.setDisable(true);
        caracteristica.setDisable(true);
        rutaDelArchivo.setDisable(true);
        abrirVentana.setDisable(true);
        guardar.setDisable(true);
        
        if(tmp != null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Guía rápida");
            alert.setHeaderText("Parece que no lo he podido adivinar...\nPregúntale a tus amiguitas  :)");
            alert.setContentText("Agrega el animal que pensaste, recuerda agregar una caracteristica verdadera para un(a) " + tmp.getTexto() + " pero falsa para el animal que pensaste :)");
            alert.showAndWait();   
        }
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
                abrirVentana.setDisable(false);
            }else{
                abrirVentana.setDisable(true);
            }
        });
        
        abrirVentana.setOnAction(event ->{
            //Abrir un file chooser;
            //Asignar ruta imagen según la ruta del file chooser
            //Poner como texto la ruta en el textfield
            FileChooser ventanita = new FileChooser();
            File img;
            ventanita.setTitle("Seleccione una imagen para un(a): " + nombreNuevo.getText());
            img = ventanita.showOpenDialog(new Stage());
            rutaImagen = img.getPath();
            rutaDelArchivo.setText(rutaImagen);
            System.out.println("Ruta = " + rutaImagen);
        });
        
        rutaDelArchivo.textProperty().addListener((observable, oldvalue, newvalue) -> {
            if(!rutaDelArchivo.getText().isEmpty()){
                guardar.setDisable(false);
            }else{
                guardar.setDisable(true);
            }
        });
        
        
        guardar.setOnAction(event -> {
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Adivinador");
            alert2.setContentText("Se ha guardado el nuevo animal ;D\n\nGracias por jugar!");
            alert2.showAndWait();
            Stage thisStage = (Stage) guardar.getScene().getWindow();
            thisStage.close();
        });
    }    
    
}
