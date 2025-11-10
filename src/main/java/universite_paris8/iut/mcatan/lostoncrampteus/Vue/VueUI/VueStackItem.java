package universite_paris8.iut.mcatan.lostoncrampteus.Vue.VueUI;

import javafx.scene.control.Label;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Item;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Joueur;

public class VueStackItem {

    private Label nbStackItem;

    public VueStackItem(Label nbStackItem) {
        this.nbStackItem = nbStackItem;
        init();
        update();
    }

    private void init() {
        nbStackItem.setText("");
        nbStackItem.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
    }

    public void update() {
        Item itemEquipe = Joueur.getInstance().getitemEquipe();
        if (itemEquipe != null) {
            itemEquipe.getTailleStackProperty().addListener((observable, oldValue, newValue) -> {
                nbStackItem.setText(newValue.toString());
            });
            nbStackItem.setText(itemEquipe.getTailleStackProperty().getValue().toString());
        } else {
            nbStackItem.setText("");
        }
    }
}