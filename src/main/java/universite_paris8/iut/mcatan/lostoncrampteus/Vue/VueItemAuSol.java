package universite_paris8.iut.mcatan.lostoncrampteus.Vue;

import javafx.scene.layout.Pane;
import universite_paris8.iut.mcatan.lostoncrampteus.Controller.ObservateurItemAuSol;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.ItemAuSol;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;
import java.util.ArrayList;

public class VueItemAuSol {
    private final ArrayList<VueItem> vuesItems;
    private final Pane gamePane;
    private final Monde monde;
    private ObservateurItemAuSol observateur;

    public VueItemAuSol(Pane gamePane, Monde monde) {
        this.gamePane = gamePane;
        this.monde = monde;
        this.vuesItems = new ArrayList<>();
        this.observateur = new ObservateurItemAuSol(this);
        monde.getItemsAuSol().addListener(this.observateur);
        init();
    }

    // afficher les item au sol initialiser dans la class monde
    private void init(){
        for (ItemAuSol item : monde.getItemsAuSol()) {
            ajouterVueItem(item);
        }
    }

    public void supprimerVueItem() {
        vuesItems.removeIf(vue -> {
            if (!monde.getItemsAuSol().contains(vue.getItemAuSol())) {
                gamePane.getChildren().remove(vue.getImageView());
                return true;
            }
            return false;
        });
    }

    public void ajouterVueItem(ItemAuSol item) {
        VueItem vue = new VueItem(item);
        vuesItems.add(vue);
        gamePane.getChildren().add(vue.getImageView());
    }
}