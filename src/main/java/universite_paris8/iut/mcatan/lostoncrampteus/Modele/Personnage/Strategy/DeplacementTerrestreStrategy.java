package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Strategy;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.AlgoRecherche;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Pnj;

public class DeplacementTerrestreStrategy implements DeplacementStrategy {
    private double vitesse;
    private int portee;

    public DeplacementTerrestreStrategy(double vitesse, int portee) {
        this.vitesse = vitesse;
        this.portee = portee;
    }

    @Override
    public void deplacer(Pnj pnj, Monde monde) {
        AlgoRecherche.DeplacementTerrestre(pnj, monde, vitesse, portee);
    }
}
