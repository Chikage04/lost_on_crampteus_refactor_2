package universite_paris8.iut.mcatan.lostoncrampteus.Controller;

import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc.Bloc;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Item;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Services.PlayerService;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Services.InventoryService;
import universite_paris8.iut.mcatan.lostoncrampteus.Vue.VueUI.VueCraft;
import universite_paris8.iut.mcatan.lostoncrampteus.Vue.VueUI.VueInventaire;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ObservateurInventaire implements ListChangeListener<Item> {

    private final TilePane tilePane;
    private final VueInventaire vueInventaire;
    private final VueCraft vueCraft;
    private final PlayerService playerService;
    private final InventoryService inventoryService;

    public Map<Item, ImageView> itemVue;

    public ObservateurInventaire(TilePane tilePane,
                                 VueInventaire vueInventaire,
                                 VueCraft vueCraft,
                                 PlayerService playerService,
                                 InventoryService inventoryService) {
        this.tilePane = tilePane;
        this.itemVue = new HashMap<>();
        this.vueInventaire = vueInventaire;
        this.vueCraft = vueCraft;
        this.playerService = playerService;
        this.inventoryService = inventoryService;
    }

    @Override
    public void onChanged(Change<? extends Item> change) {
        while (change.next()) {
            if (change.wasAdded() || change.wasRemoved() || change.wasUpdated()) {
                vueCraft.updateVueItemCraftable();
            }

            if (change.wasAdded()) {
                handleItemsAdded(change.getAddedSubList());
            }

            if (change.wasRemoved()) {
                handleItemsRemoved(change.getRemoved());
            }
        }
    }

    private void handleItemsAdded(java.util.List<? extends Item> items) {
        for (Item item : items) {
            ImageView itemView = creerImageView(item);
            itemVue.put(item, itemView);
            tilePane.getChildren().add(itemView);
            setupMouseHandlers(itemView, item);
        }
    }

    private void handleItemsRemoved(java.util.List<? extends Item> items) {
        for (Item item : items) {
            ImageView vueASuprimer = itemVue.remove(item);
            if (vueASuprimer != null) {
                tilePane.getChildren().remove(vueASuprimer);
            }
        }
    }

    private void setupMouseHandlers(ImageView itemView, Item item) {
        itemView.setOnMouseClicked(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    inventoryService.selectItem(item);
                    vueInventaire.setItemSelectionneVue(item);
                    vueInventaire.mettreEnEvidenceSelection();
                } else if (event.getButton() == MouseButton.SECONDARY) {
                    if (inventoryService.dropItem(item)) {
                        vueInventaire.setItemSelectionneVue(null);
                        vueInventaire.mettreEnEvidenceSelection();
                    }
                }
            }
        });
    }

    private ImageView creerImageView(Item item) {
        ImageView itemVue = new ImageView();
        String imagePath = "/universite_paris8/iut/mcatan/lostoncrampteus/Images/Items/Inventaire/"
                + item.getNom() + "-inventaire.png";
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        itemVue.setImage(image);

        if (item instanceof Bloc) {
            itemVue.setFitHeight(22);
            itemVue.setFitWidth(22);
        } else {
            itemVue.setFitHeight(32);
            itemVue.setFitWidth(32);
        }

        return itemVue;
    }
}

