package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Strategy;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;

public interface ItemUsageStrategy {
    void utiliser(Monde monde, int tileX, int tileY);
}
