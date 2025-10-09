package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Strategy;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Pnj;

public interface DeplacementStrategy {
    void deplacer(Pnj pnj, Monde monde);
}