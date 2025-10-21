package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc;

public class Cuivre extends Bloc{
    public Cuivre() {
        super("cuivre", 20, 1, 80, new int[]{31});
    }

    @Override
    public int[] getTileId() {
        return this.id;
    }
}
