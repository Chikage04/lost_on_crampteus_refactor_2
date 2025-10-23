package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Consomable;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Item;

public  class ItemConsommable extends Item {

    public ItemConsommable(String nom, int stackLimit, int tailleStack) {
        super(nom, stackLimit, tailleStack);
    }

    @Override
    public void attaquer(int tileX, int tileY) {

    }

    @Override
    public void utiliser(int tileX, int tileY){

    }

}
