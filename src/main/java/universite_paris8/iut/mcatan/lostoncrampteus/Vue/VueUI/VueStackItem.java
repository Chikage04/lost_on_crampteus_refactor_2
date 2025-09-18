package universite_paris8.iut.mcatan.lostoncrampteus.Vue.VueUI;

import javafx.scene.control.Label;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Item;

public class VueStackItem {

    private Label nbStackItem;
    private Monde monde;

    public VueStackItem(Label nbStackItem, Monde monde) {
        this.nbStackItem = nbStackItem;
        this.monde = monde;
        init();
        update();
    }

    private void init() {
        nbStackItem.setText("");
        nbStackItem.setStyle("-fx-font-size: 16px; -fx-text-fill: black;");
    }

    public void update() {
        Item itemEquipe = monde.getJoueur().getItemEquipee();
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