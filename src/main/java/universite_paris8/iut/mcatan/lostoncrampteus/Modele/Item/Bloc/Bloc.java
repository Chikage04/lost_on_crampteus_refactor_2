package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.ItemAvecDurabilite;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;

public class Bloc extends ItemAvecDurabilite {

    int x;
    int y;

    public Bloc(String nom, int stackLimit, int tailleStack, int durabilite) {
        super(nom, stackLimit, tailleStack, durabilite);
        this.x = 0;
        this.y = 0;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void utiliser(Monde monde, int tileX, int tileY){
        switch (monde.getJoueur().getItemEquipee().getNom()) {
            case "grass":
                monde.getJoueur().placerTile(monde.getTerrain(), tileX, tileY, 1);
                break;
            case "dirt":
                monde.getJoueur().placerTile(monde.getTerrain(), tileX, tileY, 2);
                break;
            case "bois":
                monde.getJoueur().placerTile(monde.getTerrain(), tileX, tileY, 15);
                break;
            case "aluminium":
                monde.getJoueur().placerTile(monde.getTerrain(), tileX, tileY, 29);
                break;
            case "fer":
                monde.getJoueur().placerTile(monde.getTerrain(), tileX, tileY, 32);
                break;
            case "cramptenium":
                monde.getJoueur().placerTile(monde.getTerrain(), tileX, tileY, 30);
                break;
            case "cuivre":
                monde.getJoueur().placerTile(monde.getTerrain(), tileX, tileY, 31);
                break;
            case "pierre":
                monde.getJoueur().placerTile(monde.getTerrain(), tileX, tileY, 33);
                break;
        }
    }

}
