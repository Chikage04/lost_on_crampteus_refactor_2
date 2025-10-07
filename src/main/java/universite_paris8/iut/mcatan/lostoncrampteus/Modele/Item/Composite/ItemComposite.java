package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Composite;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Item;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite pattern for managing groups of items.
 * This can be used for containers, bundles, or special inventories.
 */
public class ItemComposite extends Item {
    private List<Item> items;
    private String compositeType;

    public ItemComposite(String nom, String compositeType) {
        super(nom, 1, 1);
        this.items = new ArrayList<>();
        this.compositeType = compositeType;
    }

    public void ajouterItem(Item item) {
        items.add(item);
    }

    public void retirerItem(Item item) {
        items.remove(item);
    }

    public List<Item> getItems() {
        return new ArrayList<>(items);
    }

    public int getNombreItems() {
        return items.size();
    }

    @Override
    public void attaquer(int tileX, int tileY) {
        // For composite items, attack with all items
        for (Item item : items) {
            item.attaquer(tileX, tileY);
        }
    }

    @Override
    public void utiliser(Monde monde, int tileX, int tileY) {
        // For composite items, use all items
        for (Item item : items) {
            item.utiliser(monde, tileX, tileY);
        }
    }

    @Override
    public String getNom() {
        return super.getNom() + " (" + items.size() + " items)";
    }

    public String getCompositeType() {
        return compositeType;
    }
}
