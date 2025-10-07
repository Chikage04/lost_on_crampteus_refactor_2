package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Strategy.DeplacementStrategy;

public abstract class Pnj extends Acteur{

    protected DeplacementStrategy deplacementStrategy;

    public Pnj(int pv, Monde monde) {
        super(pv, monde);
        this.monde.ajouterPnj(this);
    }

    public void setDeplacementStrategy(DeplacementStrategy strategy) {
        this.deplacementStrategy = strategy;
    }

    public void seDeplacer() {
        if (deplacementStrategy != null) {
            deplacementStrategy.deplacer(this, monde);
        }
    }
}
