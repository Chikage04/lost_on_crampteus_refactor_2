package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.ItemAvecDurabilite;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Joueur;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Terrain;

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
    public void utiliser(int tileX, int tileY){
        Joueur.getInstance().placerTile(tileX, tileY, this.getTileId());
        }
    }


