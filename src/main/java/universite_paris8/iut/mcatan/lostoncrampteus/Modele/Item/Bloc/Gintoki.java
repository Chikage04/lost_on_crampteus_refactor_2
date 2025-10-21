package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc;

public class Gintoki extends Bloc{
    public Gintoki() {
        super("Gintoki", 20, 1, 80, new int[]{34});
    }

    @Override
    public int[] getTileId() {
        return this.id;
    }
}