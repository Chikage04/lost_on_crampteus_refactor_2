package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Factory;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme.*;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Item;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;

public class ArmeFactory implements ItemFactory {
    
    @Override
    public Item createItem(String type, Monde monde) {
        return switch (type.toLowerCase()) {
            case "epee", "épée" -> new Epee(monde);
            case "pioche" -> new Pioche(monde);
            case "hache" -> new Hache(monde);
            case "arc" -> new Arc(monde);
            default -> null;
        };
    }
}
