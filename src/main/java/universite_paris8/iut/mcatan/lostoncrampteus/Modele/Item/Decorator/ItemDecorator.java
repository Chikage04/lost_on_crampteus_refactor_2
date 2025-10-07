package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Decorator;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Item;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;

public abstract class ItemDecorator extends Item {
    protected Item wrappedItem;

    public ItemDecorator(Item item) {
        super(item.getNom(), item.getStackLimit(), item.getTailleStack());
        this.wrappedItem = item;
    }

    @Override
    public void attaquer(int tileX, int tileY) {
        wrappedItem.attaquer(tileX, tileY);
    }

    @Override
    public void utiliser(Monde monde, int tileX, int tileY) {
        wrappedItem.utiliser(monde, tileX, tileY);
    }

    @Override
    public String getNom() {
        return wrappedItem.getNom();
    }
}
