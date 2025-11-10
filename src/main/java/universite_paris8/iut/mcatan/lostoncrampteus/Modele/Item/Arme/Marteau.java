package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Terrain;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Joueur;

import static universite_paris8.iut.mcatan.lostoncrampteus.Controller.Constants.GameConstants.TILE_SIZE;

public class Marteau extends Arme {

    public Marteau() {
        super("pomme", 20, 150);
        setAttackStrategy(new HammerStrategy());
    }

    public Marteau(String nom, int degats, int durabilite) {
        super("marteau", degats, durabilite);
        setAttackStrategy(new HammerStrategy());
    }

    @Override
    public void attaquer(int tileX, int tileY) {
        super.attaquer(tileX, tileY);
    }
}
