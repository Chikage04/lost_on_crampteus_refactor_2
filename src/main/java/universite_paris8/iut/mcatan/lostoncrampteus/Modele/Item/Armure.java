package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;

public class Armure extends ItemAvecDurabilite{

    private int protection;
    private Monde monde;

    public Armure(Monde monde, int protection) {
        super("armure", 1, 1,10);
        this.protection = protection;
        this.monde = monde;
    }

    public int getProtection() {
        return protection;
    }
}
