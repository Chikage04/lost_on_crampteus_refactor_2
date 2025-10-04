package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;

public class Pioche extends Arme{

    public Pioche(){
        super("pioche", 25, 100);
    }

    public Pioche(String nom, int degats, int durabilite, int range) {
        super("pioche", 25, 100);
    }

    @Override
    public void attaquer(int tileX, int tileY) {
        Monde.getInstance().getJoueur().casserTile(Monde.getInstance().getTerrain(), tileX, tileY);
    }

}
