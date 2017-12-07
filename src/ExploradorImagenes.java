import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/*
 *  Creado por: David Pérez Sánchez
 *  Matrícula: 163202
 *  Materia: 
 *  Universidad Politécnica de Chiapas.
 *  Fecha de Creación: /10/2017
 */

/**
 * @author David Pérez S.
 */
public class ExploradorImagenes {
      
         private String rutaArchivo;
      
    public String startExplorer() {
        
        final FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        rutaArchivo= buscarArchivo(fileChooser);
        return rutaArchivo;
    }
    
    private String buscarArchivo(FileChooser fileChooser) {
          Stage stage = new Stage();
                    File file = fileChooser.showOpenDialog(stage);
                    if (file != null) {
                          rutaArchivo= file.getPath();
                    }
          return rutaArchivo;
    }
    
     private void configureFileChooser(final FileChooser fileChooser) {
            fileChooser.setTitle("Adivinador - Explorador de Imágenes");
            fileChooser.setInitialDirectory(
                new File(System.getProperty("user.dir"))
            );
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("GIF", "*.gif"),
                new FileChooser.ExtensionFilter("TIFF", "*.tiff"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                new FileChooser.ExtensionFilter("ICO", "*.ico"),
                new FileChooser.ExtensionFilter("Todos los archivos", "*.*")
            );
    }
}