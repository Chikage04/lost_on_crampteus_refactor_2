package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.AlgoRecherche;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Strategy.DeplacementTerrestreStrategy;

public class Gronfleur extends Ennemis {
    private final DeplacementTerrestreStrategy deplacementStrategy;
    public Gronfleur(Monde monde) {
        super(100, monde);
        this.deplacementStrategy = new DeplacementTerrestreStrategy(1.5, 200);
    }

    @Override
    public void seDeplacer() {
        if (estVivant()) {
            deplacementStrategy.deplacer(this, monde);
            attaquer();
        }
    }

    @Override
    public void attaquer() {
        if (monde.getJoueur().getHitbox().colision(this.getHitbox()))
            monde.getJoueur().perdreVie(0.003);
    }
}
