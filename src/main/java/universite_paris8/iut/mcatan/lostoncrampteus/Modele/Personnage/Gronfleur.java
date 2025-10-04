package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.AlgoRecherche;

public class Gronfleur extends Ennemis {

    public Gronfleur() {
        super(100);
    }

    @Override
    public void seDeplacer() {
        if (estVivant()) {
            AlgoRecherche.DeplacementTerrestre(this, super.getMonde(), 1.5, 200);
            attaquer();
        }
    }

    @Override
    public void attaquer() {
        if (super.getMonde().getJoueur().getHitbox().colision(this.getHitbox()))
            super.getMonde().getJoueur().perdreVie(0.003);
    }
}
