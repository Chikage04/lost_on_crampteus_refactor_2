package universite_paris8.iut.mcatan.lostoncrampteus.Vue.VueUI;

import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Craft.Recette;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;

import java.util.Map;

public class VueCraft {
    private SplitMenuButton craft;
    private Monde monde;

    public VueCraft(SplitMenuButton craft, Monde monde) {
        this.craft = craft;
        this.monde = monde;
        init();
    }

    private void init() {
        craft.setText("Craft");
        craft.setFocusTraversable(false);
    }

    public void updateVueItemCraftable() {
        craft.getItems().clear();
        Map<String, Recette> itemCraftable = monde.getJoueur().getRecetteDisponible();

        if (itemCraftable != null && !itemCraftable.isEmpty()) {
            for (Map.Entry<String, Recette> entry : itemCraftable.entrySet()) {
                MenuItem menuItem = new MenuItem(entry.getKey());
                actionCraftItemVue(menuItem, entry.getKey());
                craft.getItems().add(menuItem);
            }
        }

        if(craft.getItems().isEmpty()) {
            MenuItem aucunItem = new MenuItem("Aucun craft disponible");
            aucunItem.setDisable(true);
            craft.getItems().add(aucunItem);
        }
    }

    private void actionCraftItemVue(MenuItem menuItem, String nomItem) {
        menuItem.setOnAction(event -> {
            if (monde.getJoueur().craftItem(nomItem)) {
                updateVueItemCraftable();
            }
        });
    }
}