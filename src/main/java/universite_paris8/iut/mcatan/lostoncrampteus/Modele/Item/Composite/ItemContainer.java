package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Composite;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Item;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;

/**
 * Represents a container that can hold multiple items.
 * Example: Chest, backpack, storage box, etc.
 */
public class ItemContainer extends ItemComposite {
    private int capacite;

    public ItemContainer(String nom, int capacite) {
        super(nom, "container");
        this.capacite = capacite;
    }

    @Override
    public void ajouterItem(Item item) {
        if (getNombreItems() < capacite) {
            super.ajouterItem(item);
        }
    }

    public boolean estPlein() {
        return getNombreItems() >= capacite;
    }

    public int getCapacite() {
        return capacite;
    }

    @Override
    public void utiliser(Monde monde, int tileX, int tileY) {
        // When a container is used, it places itself as a block
        // (Future implementation: place container in world)
    }
}
