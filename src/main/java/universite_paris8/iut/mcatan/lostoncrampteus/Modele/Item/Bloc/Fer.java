package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc;

public class Fer extends Bloc{
    public Fer() {
        super("fer", 20, 1, 130, new int[]{32});
    }

    @Override
    public int[] getTileId() {
        return this.id;
    }
}