package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage;

public abstract class Ennemis extends Pnj{
    public Ennemis(int pv) {
        super(pv);
    }

    public abstract void attaquer();
}
