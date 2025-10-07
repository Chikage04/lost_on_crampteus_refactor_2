package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.ItemAvecDurabilite;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Strategy.ItemUsageStrategy;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Strategy.PlacementBlocStrategy;

public class Bloc extends ItemAvecDurabilite {

    int x;
    int y;
    protected ItemUsageStrategy usageStrategy;

    public Bloc(String nom, int stackLimit, int tailleStack, int durabilite) {
        super(nom, stackLimit, tailleStack, durabilite);
        this.x = 0;
        this.y = 0;
        this.usageStrategy = createUsageStrategy(nom);
    }

    private ItemUsageStrategy createUsageStrategy(String nom) {
        return switch (nom.toLowerCase()) {
            case "grass" -> new PlacementBlocStrategy("grass", 1);
            case "dirt" -> new PlacementBlocStrategy("dirt", 2);
            case "bois" -> new PlacementBlocStrategy("bois", 15);
            case "aluminium" -> new PlacementBlocStrategy("aluminium", 29);
            case "fer" -> new PlacementBlocStrategy("fer", 32);
            case "cramptenium" -> new PlacementBlocStrategy("cramptenium", 30);
            case "cuivre" -> new PlacementBlocStrategy("cuivre", 31);
            case "pierre" -> new PlacementBlocStrategy("pierre", 33);
            default -> new PlacementBlocStrategy(nom, 0);
        };
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

    @Override
    public void utiliser(Monde monde, int tileX, int tileY){
        if (usageStrategy != null) {
            usageStrategy.utiliser(monde, tileX, tileY);
        }
    }

}
