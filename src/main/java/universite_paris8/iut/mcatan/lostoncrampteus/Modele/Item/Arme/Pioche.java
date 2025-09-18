package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;

public class Pioche extends Arme{
    private Monde monde;

    public Pioche(Monde monde){
        super("pioche", 25, 100);
        this.monde =monde;
    }

    public Pioche(String nom, int degats, int durabilite, int range, Monde monde) {
        super("pioche", 25, 100);
        this.monde = monde;
    }

    @Override
    public void attaquer(int tileX, int tileY) {
        monde.getJoueur().casserTile(monde.getTerrain(), tileX, tileY);
    }

}
