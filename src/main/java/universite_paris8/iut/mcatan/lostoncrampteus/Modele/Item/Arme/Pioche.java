package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Joueur;

public class Pioche extends Arme{

    public Pioche(){
        super("pioche", 25, 100);
    }

    @Override
    public void attaquer(int tileX, int tileY) {
        Joueur.getInstance().casserTile(tileX, tileY);
    }

}
