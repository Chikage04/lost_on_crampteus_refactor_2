package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.ItemAvecDurabilite;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;

public abstract class Bloc extends ItemAvecDurabilite {

    int x;
    int y;
    int id;

    public Bloc(String nom, int stackLimit, int tailleStack, int durabilite, int id) {
        super(nom, stackLimit, tailleStack, durabilite);
        this.x = 0;
        this.y = 0;
        this.id = id;
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
    public abstract int getTileId();

    @Override
    public void utiliser(Monde monde, int tileX, int tileY){
        monde.getJoueur().placerTile(monde.getTerrain(), tileX, tileY, this.getTileId());
        }
    }


