package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc;

public class Or extends Bloc{
    public Or() {
        super("or", 64, 1,80, new int[]{266});
    }

    @Override
    public int[] getTileId() {
        return this.id;
    }
}