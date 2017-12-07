
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 *  Creado por: David Pérez Sánchez
 *  Matrícula: 163202
 *  Materia: Estructura de Datos
 *  Universidad Politécnica de Chiapas.
 *  Fecha de Creación: 06/12/2017
 */

/**
 * @author David Pérez S.
 */
public class CopiarArchivos {

        static final Logger LOGGER = Logger.getAnonymousLogger();

        public void copiarArchivo(String origenArchivo, String destinoArchivo) {
            try {
                Path origenPath = Paths.get(origenArchivo);
                Path destinoPath = Paths.get(destinoArchivo);
                //sobreescribir el fichero de destino si existe y lo copia
                Files.copy(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("\t[ Copiado exitoso ]");
            } catch (FileNotFoundException ex) {
                LOGGER.log(Level.SEVERE, ex.getMessage());
                System.out.println("\t[ No se pudo copiar el archivo ]");
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, ex.getMessage());
                System.out.println("\t[ No se pudo copiar el archivo ]");
            }
        }  
}