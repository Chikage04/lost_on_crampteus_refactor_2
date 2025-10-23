package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc;

public class Grass extends Bloc {
    public Grass() {
        super("grass", 64, 1, 80, new int[]{1});
    }

    @Override
    public int[] getTileId() {
        return this.id;
    }
}