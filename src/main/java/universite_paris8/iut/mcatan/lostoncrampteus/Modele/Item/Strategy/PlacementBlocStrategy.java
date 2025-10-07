package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Strategy;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;

public class PlacementBlocStrategy implements ItemUsageStrategy {
    private String nomBloc;
    private int tileId;

    public PlacementBlocStrategy(String nomBloc, int tileId) {
        this.nomBloc = nomBloc;
        this.tileId = tileId;
    }

    @Override
    public void utiliser(Monde monde, int tileX, int tileY) {
        monde.getJoueur().placerTile(monde.getTerrain(), tileX, tileY, tileId);
    }
}
