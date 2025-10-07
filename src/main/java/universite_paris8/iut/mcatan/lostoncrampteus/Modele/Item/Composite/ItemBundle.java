package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Composite;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Item;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;

/**
 * Represents a bundle of items that can be stored or traded together.
 * Example: Starter pack, resource bundle, etc.
 */
public class ItemBundle extends ItemComposite {

    public ItemBundle(String nom) {
        super(nom, "bundle");
    }

    /**
     * When a bundle is used, it unpacks all items.
     */
    @Override
    public void utiliser(Monde monde, int tileX, int tileY) {
        // Unpack all items to the ground at the specified location
        for (Item item : getItems()) {
            monde.ajouterItemAuSol(item, tileX * 32, tileY * 32);
        }
        // Clear the bundle after unpacking
        getItems().clear();
    }
}
