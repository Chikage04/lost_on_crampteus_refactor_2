package universite_paris8.iut.mcatan.lostoncrampteus.Vue.VueEnnemis;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Gronfleur;

public class VueGronfleur {

    private Pane gamePane;

    public VueGronfleur(Pane gamePane){
        this.gamePane = gamePane;
    }

    public void ajouterGronfleur(){
        Gronfleur gronfleur = new Gronfleur();
        Rectangle gronfleurVue = new Rectangle(30, 30, Paint.valueOf("purple"));

        gamePane.getChildren().add(gronfleurVue);
        gronfleurVue.xProperty().bind(gronfleur.getPosXProperty());
        gronfleurVue.yProperty().bind(gronfleur.getPosYProperty());
    }
}
