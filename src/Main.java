import java.util.Scanner;
import java.io.File;
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
 *  Fecha de Creación: 20/11/2017
 */

/**
 * Clase principal del proyecto.
 * @author David Pérez S.
 */
public class Main {
      /**
       * Método principal del proyecto.
       * @param args Array de líneas de argumentos
       * @throws java.io.FileNotFoundException Excepción creada cuando el archivo no existe o no se puede leer.
       */
    public static void main(String[] args) throws FileNotFoundException, IOException {
          
          int opcionMenu, opcionMenu2;
          String nombreArchivoDatos= "Tree.tree";
          String rutaArchivo= System.getProperty("user.dir") + "\\" + nombreArchivoDatos;
          
          Scanner lecturaTeclado= new Scanner(System.in);
          Arbol arbol= new Arbol();
          Archivo<Arbol> archivo= new Archivo<>(nombreArchivoDatos);
          
          File file = new File(rutaArchivo);
          if(file.exists()){
              arbol = archivo.deserializar();
              System.out.println("Recuperado = " + arbol.toString());
          }else{
              arbol.añadirPregunta("¿Es una animal doméstico?");
              arbol.añadirRespuesta("Perro");
              arbol.añadirRespuesta("Oso");
              archivo.crearArchivoVacio();
              archivo.serializar(arbol);
          }
          
          do{
            System.out.println("\n BIENVENIDO");
            System.out.println(" **************************************************");
            System.out.println(" [ 1 ]  Jugar");
            System.out.println(" [ 2 ] Salir");
            System.out.print("\n Elija>> ");
            opcionMenu= lecturaTeclado.nextInt();
            System.out.println(" **************************************************\n");
            switch(opcionMenu){
                  case 1:{
                        do{
                              juego(arbol.recorrerAdivinador(), arbol);
                              System.out.println("\n Desea volver a jugar ??");
                              System.out.println(" [ 1 ]  Volver a jugar");
                              System.out.println(" [ 2 ] Salir");
                              System.out.print("\n Elija>> ");
                              opcionMenu2= lecturaTeclado.nextInt();
                              arbol.resetTemporalRecorrido();
                        }while(opcionMenu2 != 2);
                        break;
                  }
                  case 2: break;
            }
          }while(opcionMenu <1 || opcionMenu >2);
     archivo.serializar(arbol);
     CopiarArchivo copy = new CopiarArchivo(rutaArchivo, System.getProperty("user.dir") + "\\Datos2.txt");
    }
    /**
     * Iniciar el juego >> Adivinador.
     * <p>Esta función contiene la mayor parte de la ejecucuón del juego principal</p>
     * @param nodo Nodo inicial, o sea, la raíz, para comenzar el programa.
     * @param arbol Árbol en el cual la función va a ir tomando los diferentes nodos de éste.
     */
    public static void juego(Nodo nodo, Arbol arbol){
          
          int opcionMenu;
          String preguntaUsuario, respuestaUsuario;
          Scanner lecturaTeclado= new Scanner(System.in);
          while(nodo.getDerecho() != null && nodo.getIzquierdo() != null){
                do{
                  System.out.println(nodo.getTexto()+ "\n");
                  System.out.println(" [ 1 ]  Sí");
                  System.out.println(" [ 2 ] No");
                  System.out.println(" [ 3 ] No sé");
                  System.out.print("\n Elija>> ");
                  opcionMenu= lecturaTeclado.nextInt();
                  System.out.println(" -------------------------");
                  switch(opcionMenu){
                        case 1:{
                              nodo= arbol.recorrerAdivinador(1);
                              break;
                        }
                        case 2:{
                              nodo= arbol.recorrerAdivinador(-1);
                              break;
                        }
                        case 3:{
                              nodo= arbol.recorrerAdivinador(0);
                              break;
                        }
                  }
                }while(opcionMenu <1 || opcionMenu >3);
          }
          do{
            System.out.println("\n ¿Es esto lo que estabas pensando?");
            System.out.println(" " + nodo.getTexto().toUpperCase()+ "\n");
            System.out.println(" [ 1 ]  Sí");
            System.out.println(" [ 2 ] No");
            System.out.print("\n Elija>> ");
            opcionMenu= lecturaTeclado.nextInt();
            System.out.println(" -------------------------");
            lecturaTeclado.nextLine();
            switch(opcionMenu){
                  case 1:{
                        System.out.println(" Felicidades !!\n Gracias por jugar !!");
                        break;
                  }
                  case 2:{
                        System.out.print(" Ingrese el animal que pensó: ");
                        respuestaUsuario= lecturaTeclado.nextLine();
                        System.out.print(" Ingrese una característica verdadera para un(a) " + nodo.getTexto().toUpperCase()+ ", pero falsa para un " + respuestaUsuario.toUpperCase() + ": ");
                        preguntaUsuario= lecturaTeclado.nextLine();
                        arbol.añadirPregunta("¿" + preguntaUsuario + "?");
                        arbol.añadirRespuesta(respuestaUsuario);
                        break;
                  }
            }
          }while(opcionMenu<1 || opcionMenu >2);
    }
}

class CopiarArchivo {

        static final Logger LOGGER = Logger.getAnonymousLogger();

        public CopiarArchivo(String origenArchivo, String destinoArchivo) {
            try {
                Path origenPath = Paths.get(origenArchivo);
                Path destinoPath = Paths.get(destinoArchivo);
                //sobreescribir el fichero de destino si existe y lo copia
                Files.copy(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Copiado exitoso");
            } catch (FileNotFoundException ex) {
                LOGGER.log(Level.SEVERE, ex.getMessage());
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, ex.getMessage());
            }
        }
    }
/*
Cambiar de STAGE

//Creas el nuevo stage que se mostrará al usuario
Stage nuevo = new Stage();

//Creas una instancia de Stage la cual vincularás al stage en el que te encuentras
Stage atual = new Stage();

//Agarras un ELEMENTO de tu fxml que tengas declarado
//Supongamos que es un botón

actual = (Stage) boton.getScene().getWindow();

//Luego haces todo eso del FXMLLoader

y cuando lo vayas a mostrar...

nuevo.show();
actual.close();
*/