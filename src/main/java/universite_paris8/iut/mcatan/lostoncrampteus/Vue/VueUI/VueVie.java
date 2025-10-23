package universite_paris8.iut.mcatan.lostoncrampteus.Vue.VueUI;

import javafx.scene.control.ProgressBar;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Joueur;

public class VueVie {

    private ProgressBar vie;

    public VueVie(ProgressBar vie){
        this.vie = vie;
        init();
    }

    private void init(){
        this.vie.progressProperty().bind(Joueur.getInstance().getPvProperty());

    }

    public ProgressBar getVie() {
        return vie;
    }
}

