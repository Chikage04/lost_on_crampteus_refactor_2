package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme;

public class Arc extends Arme {

    public Arc(){
        super("arc", 50, 100);
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
