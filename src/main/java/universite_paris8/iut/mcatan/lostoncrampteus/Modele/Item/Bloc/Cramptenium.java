package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc;

public class Cramptenium extends Bloc{
    public Cramptenium() {
        super("cramptenium", 20, 1, 300, new int[]{30});
    }

    @Override
    public int[] getTileId() {
        return this.id;
    }
}