package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.AlgoRecherche;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;

public class Gronfleur extends Ennemis {

    public Gronfleur(Monde monde) {
        super(100, monde);
    }

    @Override
    public void seDeplacer() {
        if (estVivant()) {
            AlgoRecherche.DeplacementTerrestre(this, monde, 1.5, 200);
            attaquer();
        }
    }

    @Override
    public void attaquer() {
        if (monde.getJoueur().getHitbox().colision(this.getHitbox()))
            monde.getJoueur().perdreVie(0.003);
    }
}
