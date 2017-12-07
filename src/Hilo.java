import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/*
 *  Creado por: David PÃ©rez SÃ¡nchez
 *  MatrÃ­cula: 163202
 *  Materia: 
 *  Universidad PolitÃ©cnica de Chiapas.
 *  Fecha de CreaciÃ³n: /10/2017
 */
/**
 * @author David PÃ©rez S.
 */
public class Hilo extends Thread implements Runnable {

    String tiempo;
    private final Thread hilo;
    @FXML
    private final Label labelTime;
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
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}