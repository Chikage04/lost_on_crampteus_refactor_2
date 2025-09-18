
package universite_paris8.iut.mcatan.lostoncrampteus.Controller;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc.Bloc;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Item;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;
import universite_paris8.iut.mcatan.lostoncrampteus.Vue.VueUI.VueCraft;
import universite_paris8.iut.mcatan.lostoncrampteus.Vue.VueUI.VueInventaire;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ObservateurInventaire implements ListChangeListener<Item> {

    private TilePane tilePane;
    private VueInventaire vueInventaire;
    private VueCraft vueCraft;
    private Monde monde;
    public Map<Item, ImageView> itemVue;

    public ObservateurInventaire(TilePane tilePane, VueInventaire vueInventaire, VueCraft vueCraft,Monde monde) {
        this.tilePane = tilePane;
        this.monde = monde;
        this.itemVue = new HashMap<>();
        this.vueInventaire = vueInventaire;
        this.vueCraft = vueCraft;
    }

    @Override
    public void onChanged(Change<? extends Item> change) {
        while (change.next()) {
            if (change.wasAdded() || change.wasRemoved() || change.wasUpdated()) {
                vueCraft.updateVueItemCraftable();
            }

            if (change.wasAdded()) {
                for (Item item : change.getAddedSubList()) {
                    ImageView itemView = creeItemView(item);
                    itemVue.put(item, itemView);
                    tilePane.getChildren().add(itemView);

                    selectionItemSouris(itemView, item);
                }
            }

            if (change.wasRemoved()) {
                for (Item item : change.getRemoved()) {
                    ImageView vueAsuprimer = itemVue.remove(item);
                    if (vueAsuprimer != null) {
                        tilePane.getChildren().remove(vueAsuprimer);
                    }
                }
            }
        }
    }

    public void selectionItemSouris(ImageView itemView, Item item){
        itemView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                selectionnerItem(item);
            } else if (event.getButton() == MouseButton.SECONDARY) {
                jeterItemSelectionne();
            }
        });
    }

    public void selectionnerItem(Item item) {
        vueInventaire.setItemSelectionneVue(item);
        monde.getJoueur().setItemEquipee(item);
        vueInventaire.mettreEnEvidenceSelection();
    }

    public void jeterItemSelectionne() {
        if (vueInventaire.getItemSelectionneVue() != null) {
            monde.ajouterItemAuSol(vueInventaire.getItemSelectionneVue(), monde.getJoueur().getPosX() + 32, monde.getJoueur().getPosY());
            monde.getJoueur().getInventaire().enleverItem(vueInventaire.getItemSelectionneVue());

            if (vueInventaire.getItemSelectionneVue() == monde.getJoueur().getItemEquipee()) {
                monde.getJoueur().setItemEquipee(null);
            }
            vueInventaire.setItemSelectionneVue(null);
            vueInventaire.mettreEnEvidenceSelection(); // pour enlever la mise en évidence
        }
    }

    @FXML
    public ImageView creeItemView(Item item) {
        ImageView itemVue = new ImageView();
        System.out.println(item.getNom()); // pour debug et voir le trc dans l'inventaire dans la console
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/Items/Inventaire/" + item.getNom() + "-inventaire.png")));
        itemVue.setImage(image);
        if (item instanceof Bloc){
            itemVue.setFitHeight(22);
            itemVue.setFitWidth(22);
        }else{
            itemVue.setFitHeight(32);
            itemVue.setFitWidth(32);
        }

        return itemVue;
    }
}
