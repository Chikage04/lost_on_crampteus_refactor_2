package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Consomable;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Inventaire;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Item;

import java.util.ArrayList;

public class Sac extends ItemConsommable {

    ArrayList<Item> extraInventaire;

    public Sac(ArrayList<Item> extraInventaire) {
        super("pomme", 1, 1);
        this.extraInventaire = extraInventaire;
    }

    @Override
    public void utiliser(int tileX, int tileY) {
        for (Item i: extraInventaire) {
            Inventaire.getInstance().ajouterItem(i);
        }
        setTailleStack(this.getTailleStack()-1);

        if (this.getTailleStack() == 0){
            Inventaire.getInstance().enleverItem(this);
        }
    }
}
