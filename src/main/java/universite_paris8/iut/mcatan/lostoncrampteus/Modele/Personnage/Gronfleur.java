package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.AlgoRecherche;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Strategy.DeplacementTerrestreStrategy;

public class Gronfleur extends Ennemis {
    private final DeplacementTerrestreStrategy deplacementStrategy;
    public Gronfleur() {
        super(100);
        this.deplacementStrategy = new DeplacementTerrestreStrategy(1.5, 200);
    }

    @Override
    public void seDeplacer() {
        if (estVivant()) {
            deplacementStrategy.deplacer(this);
            attaquer();
        }
    }

    @Override
    public void attaquer() {
        if (super.getMonde().getJoueur().getHitbox().colision(this.getHitbox()))
            super.getMonde().getJoueur().perdreVie(0.003);
    }
}
