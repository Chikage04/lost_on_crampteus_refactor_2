package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Consomable;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Inventaire;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Joueur;

public class Pomme extends ItemConsommable {
    public Pomme() {
        super("pomme", 5, 1);
    }

    @Override
    public void utiliser(int tileX, int tileY) {
        if (!(Joueur.getInstance().getPvProperty().getValue() == 1.0)){
            Joueur.getInstance().ajouterVie(0.015);
            setTailleStack(this.getTailleStack()-1);
        }

        if (this.getTailleStack() == 0){
            Inventaire.getInstance().enleverItem(this);
        }
    }

}
