package universite_paris8.iut.mcatan.lostoncrampteus.Vue.VueUI;

import javafx.scene.control.ProgressBar;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;

public class VueVie {

    private ProgressBar vie;
    private Monde monde;

    public VueVie(Monde monde, ProgressBar vie){
        this.monde = monde;
        this.vie = vie;
        init();
    }

    private void init(){
        this.vie.progressProperty().bind(monde.getJoueur().getPvProperty());

    }

    public ProgressBar getVie() {
        return vie;
    }
}

