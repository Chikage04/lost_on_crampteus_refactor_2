// Modele/Item/Bloc/Bloc.java
package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.ItemAvecDurabilite;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Strategy.AttaqueNulleStrategy;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Strategy.UtilisationPlacementBlocStrategy;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Map.BlocFactory;

/**
 * Classe de base pour tous les blocs.
 * Utilise UtilisationPlacementBlocStrategy pour être placé dans le monde.
 */
public abstract class Bloc extends ItemAvecDurabilite {
    private int x;
    private int y;
    private final int tileId;

    public Bloc(String nom, int stackLimit, int tailleStack, int durabilite, int tileId) {
        super(nom, stackLimit, tailleStack, durabilite);
        this.tileId = tileId;
        this.x = 0;
        this.y = 0;

        // Les blocs ne peuvent pas attaquer
        setStrategieAttaque(new AttaqueNulleStrategy());

        // Les blocs peuvent être placés
        setStrategieUtilisation(new UtilisationPlacementBlocStrategy(tileId));
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getTileId() {
        return tileId;
    }

    /**
     * Méthode factory pour créer un bloc à partir de son tileId
     */
    public static Bloc createFromTileId(int tileId) {
        return BlocFactory.createBloc(tileId);
    }
}