package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme;

import java.util.ArrayList;

public interface AttackStrategy {
    ArrayList<int[]> attack(Arme arme, int tileX, int tileY);
}
