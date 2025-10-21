package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Factory;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc.*;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Item;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;

public class BlocFactory implements ItemFactory {
    @Override
    public Item createItem(String type) {
        return switch (type.toLowerCase()) {
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
}