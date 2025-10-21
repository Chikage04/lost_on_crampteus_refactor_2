package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc;

public class Aluminium extends Bloc{
    public Aluminium() {
        super("aluminium", 20, 1, 200, new int[]{29});
    }

    @Override
    public int[] getTileId() {
        return this.id;
    }
}