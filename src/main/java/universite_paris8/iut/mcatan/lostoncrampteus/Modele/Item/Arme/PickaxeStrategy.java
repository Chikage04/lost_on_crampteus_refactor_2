package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;

public class PickaxeStrategy implements AttackStrategy {
    @Override
    public void attack(Arme arme, int tileX, int tileY) {
        Monde.getInstance().getJoueur().casserTile(Monde.getInstance().getTerrain(), tileX, tileY);
    }
}

