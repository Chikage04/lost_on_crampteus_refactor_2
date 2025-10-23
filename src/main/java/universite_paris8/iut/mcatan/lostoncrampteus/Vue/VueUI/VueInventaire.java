package universite_paris8.iut.mcatan.lostoncrampteus.Vue.VueUI;

import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.mcatan.lostoncrampteus.Controller.Controleur;
import universite_paris8.iut.mcatan.lostoncrampteus.Controller.ObservateurInventaire;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Inventaire;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Item;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;

public class VueInventaire {

    private Controleur controleur;
    private TilePane inventaire;
    private Monde monde;
    private ObservateurInventaire observateur;
    private Item itemSelectionne;

    public VueInventaire(TilePane inventaire, Monde monde, Controleur controleur) {
        this.controleur = controleur;
        this.inventaire = inventaire;
        this.monde = monde;
        this.itemSelectionne = null;
        this.observateur = new ObservateurInventaire(inventaire, this, controleur.getVueCraft());
        Inventaire.getInstance().getInventaireList().addListener(observateur);
        init();
    }

    public void init() {
        this.inventaire.getChildren().clear();


        this.inventaire.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
    }

    public void mettreEnEvidenceSelection() {
        for (ImageView itemView : observateur.itemVue.values()) {
            itemView.setStyle("");
        }

        if (itemSelectionne != null && observateur.itemVue.containsKey(itemSelectionne)) {
            observateur.itemVue.get(itemSelectionne).setStyle("-fx-effect: dropshadow(three-pass-box, white, 10, 0.5, 0, 0);");
        }
    }

    public Item getItemSelectionneVue() {
        return itemSelectionne;
    }

    public void setItemSelectionneVue(Item itemSelectionne) {
        this.itemSelectionne = itemSelectionne;
    }
}