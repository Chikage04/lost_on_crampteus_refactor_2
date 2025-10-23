package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Consomable;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Inventaire;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Joueur;

public class PotionPv extends ItemConsommable{
    public PotionPv() {
        super("potionPv", 5, 1);
    }


    @Override
    public void utiliser(int tileX, int tileY) {
        if (!(Joueur.getInstance().getPvProperty().getValue() == 1.0)){
            Joueur.getInstance().ajouterVie(0.2);
            setTailleStack(this.getTailleStack()-1);
        }

        if (this.getTailleStack() == 0){
            Inventaire.getInstance().enleverItem(this);
        }
    }

}
