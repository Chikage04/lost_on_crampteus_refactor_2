package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Hitbox;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;

public class Hache extends Arme {
    private Monde monde;


    public Hache(Monde monde) {
        super("hache", 10, 100);
        this.monde = monde;
    }

    public Hache(String nom, int degats, int durabilite, int range, Monde monde) {
        super("hache", 10, 100);
        this.monde = monde;
    }

}