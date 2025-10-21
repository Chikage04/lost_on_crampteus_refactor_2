package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc;

public class Bois extends Bloc{
    public Bois() {
        super("bois", 20, 1, 80, new int[]{15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28});
    }
    @Override
    public int[] getTileId() {
        return this.id;
    }
}

