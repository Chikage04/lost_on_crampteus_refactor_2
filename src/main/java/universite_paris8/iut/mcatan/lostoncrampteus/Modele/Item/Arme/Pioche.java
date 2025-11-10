package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme;

public class Pioche extends Arme{

    public Pioche(){
        super("pioche", 25, 100);
        setAttackStrategy(new PickaxeStrategy());
    }

    public Pioche(String nom, int degats, int durabilite, int range) {
        super("pioche", 25, 100);
        setAttackStrategy(new PickaxeStrategy());
    }

    @Override
    public void attaquer(int tileX, int tileY) {
        super.attaquer(tileX, tileY);
    }

}
