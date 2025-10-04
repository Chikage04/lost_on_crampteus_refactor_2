package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage;

public abstract class Pnj extends Acteur{

    public Pnj(int pv) {
        super(pv);
        super.getMonde().ajouterPnj(this);
    }

    public abstract void seDeplacer();
}
