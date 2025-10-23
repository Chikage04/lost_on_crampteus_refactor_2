package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage;

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
        if (Joueur.getInstance().getHitbox().colision(this.getHitbox()))
            Joueur.getInstance().perdreVie(0.003);
    }
}
