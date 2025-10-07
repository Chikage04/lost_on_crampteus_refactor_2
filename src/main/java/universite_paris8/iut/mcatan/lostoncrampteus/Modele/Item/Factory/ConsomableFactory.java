package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Factory;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Consomable.*;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Item;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;

public class ConsomableFactory implements ItemFactory {
    
    @Override
    public Item createItem(String type, Monde monde) {
        return switch (type.toLowerCase()) {
            case "pomme" -> new Pomme();
            case "potionpv" -> new PotionPv();
            default -> null;
        };
    }
}
