package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Factory;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc.*;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Item;

public class BlocFactory {

    public Item create(String key) {
        if (key == null) return null;
        return switch (key.toLowerCase()) {
            case "grass" -> new Grass();
            case "dirt" -> new Dirt();
            case "gintoki" -> new Gintoki();
            case "bois" -> new Bois();
            case "aluminium" -> new Aluminium();
            case "fer" -> new Fer();
            case "cramptenium" -> new Cramptenium();
            case "cuivre" -> new Cuivre();
            case "pierre" -> new Pierre();
            default -> null;
        };
    }

    public Item createItem(String type) {
        return create(type);
    }
}