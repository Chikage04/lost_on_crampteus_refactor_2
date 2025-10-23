package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item;

public class Armure extends ItemAvecDurabilite{

    private int protection;

    public Armure(int protection) {
        super("armure", 1, 1,10);
        this.protection = protection;
    }

    public int getProtection() {
        return protection;
    }
}
