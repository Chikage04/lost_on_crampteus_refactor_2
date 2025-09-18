package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;

public abstract class Ennemis extends Pnj{
    public Ennemis(int pv, Monde monde) {
        super(pv, monde);
    }

    public abstract void attaquer();
}
