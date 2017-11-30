import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

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
public class Hilo extends Thread implements Runnable{

      String tiempo;
      private final Thread hilo;
      @FXML
      private Label labelTime;
      /**
       * <b>Constructor de la Clase Hilo</b>
       * @param nombreHilo Nombre del Hilo
       * @param labelTime
       */
      public Hilo(String nombreHilo, Label labelTime){
            hilo= new Thread(this, nombreHilo);
            this.labelTime= labelTime;
      }
      /**
       * <b>Iniciar el método <code>Run</code> </b>
       */
      @Override
      public void run() {
            for(int i=0; i<60; i++){
                  for(int j=0; j<60; j++){
                        tiempo= i + " : " + j;
                        Platform.runLater(new Runnable(){
                                  @Override
                                  public void run() {
                                      labelTime.setText(tiempo);
                                  }
                              });
                        try {
                              Thread.sleep(1000);
                        } catch (InterruptedException ex) {System.err.println("\t[ Error en el Thread.sleep() ]");}
                  }
            }
      }
}