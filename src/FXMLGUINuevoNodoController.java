/*
 * Proyecto creado por Luis Fernando Hernández Morales.
 * Ingeniería en Desarrollo de Software.
 * Materia:
 * Fecha de creacion:
 * Universidad Politécnica de Chiapas.
 * Contacto: 163189@ids.upchiapas.edu.mx   ||  961 100 3141
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    private Arbol arbol;
    private Arbol arboltmp;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guía rápida");
        alert.setHeaderText("Parece que no lo he podido adivinar...\nPregúntale a tus amiguitas  :)");
        alert.setContentText("Agrega el animal que pensaste, recuerda agregar una caracteristica que no pueda hacer el animal que pensaste");
        alert.showAndWait();
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
    }    
    
    public void setArbol(Arbol a){
        this.arboltmp = a;
        this.arbol = a;        
    }
    
    private Arbol getArbol(){
        return arboltmp;
    }
    
    @FXML
    private void save(ActionEvent e) throws FileNotFoundException{
        Archivo<Arbol> archivo = new Archivo<>("Tree.tree");
        if (arbol == null) {
            System.out.println("Arbol nulo :c");
        } else {
            System.out.println("Arbol no nulo :v");
            arbol.añadirPregunta(nombreNuevo.getText().toLowerCase());
            String str = (String) stringCaracteristcas.getSelectionModel().getSelectedItem() + " " + caracteristica.getText();
            System.out.println("Nuevo texto = " + str);
            arbol.añadirRespuesta(str, rutaImagen);
            Stage thisStage = (Stage) guardar.getScene().getWindow();
            arbol.resetTemporalRecorrido();
            archivo.serializar(arbol);
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Adivinador");
            alert2.setContentText("Se ha guardado el nuevo animal ;D\n\nGracias por jugar!");
            alert2.showAndWait();
            thisStage.close();
        }
    }
}
