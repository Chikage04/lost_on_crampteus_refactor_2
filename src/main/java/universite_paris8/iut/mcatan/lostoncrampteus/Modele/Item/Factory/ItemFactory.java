package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Factory;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Item;

public interface ItemFactory {
    Item createItem(String type);
}