package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;

import java.util.ArrayList;

public class PickaxeStrategy implements AttackStrategy {
    @Override
    public ArrayList<int[]> attack(Arme arme, int tileX, int tileY) {
        Monde.getInstance().getJoueur().casserTile(Monde.getInstance().getTerrain(), tileX, tileY);
        ArrayList<int[]> affected = new ArrayList<>();
        affected.add(new int[]{tileX, tileY});
        return affected;
    }
}
