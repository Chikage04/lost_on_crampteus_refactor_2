package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Hitbox;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;

public class Arc extends Arme {

    private Monde monde;

    public Arc(Monde monde){
        super("arc", 50, 100);
        this.monde =monde;
    }

    public Arc(int degats, int durabilite) {
        super("arc", degats, durabilite);
    }

    @Override
    public String toString() {
        return "arc";
    }


    @Override
    public void attaquer(int tileX, int tileY) {
        System.out.println("fleche tiree !");
    }

}
