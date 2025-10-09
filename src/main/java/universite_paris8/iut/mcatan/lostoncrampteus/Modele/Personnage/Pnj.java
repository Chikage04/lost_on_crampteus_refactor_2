package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;

public abstract class   Pnj extends Acteur{

    public Pnj(int pv, Monde monde) {
        super(pv, monde);
        this.monde.ajouterPnj(this);
    }

    public abstract void seDeplacer();
}
