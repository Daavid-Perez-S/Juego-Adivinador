
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Hilo extends Thread implements Runnable {

    String tiempo;
    private final Thread hilo;
    @FXML
    private Label labelTime;
    int hora, minuto, segundo;

    /**
     * <b>Constructor de la Clase Hilo</b>
     *
     * @param nombreHilo Nombre del Hilo
     * @param labelTime
     */
    public Hilo(String nombreHilo, Label labelTime) {
        hilo = new Thread(this, nombreHilo);
        this.labelTime = labelTime;
        this.hora = 0;
        this.minuto = 0;
        this.segundo = 0;
    }

    /**
     * <b>Iniciar el método <code>Run</code> </b>
     */
    @Override
    public void run() {
        try {
            for (;;) {
                if (segundo != 59) {
                    segundo++;
                } else {
                    if (minuto != 59) {
                        segundo = 0;
                        minuto++;
                    } else {
                        hora++;
                        minuto = 0;
                        segundo = 0;
                    }
                }
                Platform.runLater(new Runnable() {
                    String h = "";
                    String m = "";
                    String s = "";
                    @Override
                    public void run() {
                        if(hora < 10){
                            h = "0" + String.valueOf(hora);
                        }else{
                            h = String.valueOf(hora);
                        }
                        if(minuto < 10){
                            m = "0" + String.valueOf(minuto);
                        }else{
                            m = String.valueOf(minuto);
                        }
                        if(segundo < 10){
                            s = "0" + String.valueOf(segundo);
                        }else{
                            s = String.valueOf(segundo);
                        }
                        labelTime.setText(h + " : " + m + " : " + s);
                    }

                });

                Thread.sleep(999);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        /*
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
            
         */

    }
}
