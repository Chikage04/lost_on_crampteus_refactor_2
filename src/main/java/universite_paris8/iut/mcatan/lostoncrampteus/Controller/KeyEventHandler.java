package universite_paris8.iut.mcatan.lostoncrampteus.Controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyEventHandler implements EventHandler <KeyEvent>{

    private Controleur controleur;

    public KeyEventHandler(Controleur controleur){
        this.controleur = controleur;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        KeyCode code = keyEvent.getCode();

        if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
            controleur.getActiveKeys().add(code);
        } else if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED) {
            controleur.getActiveKeys().remove(code);
            if (code == KeyCode.K) {
                System.out.println(controleur.getMonde().getPnjs());
            }
        }
    }
}
