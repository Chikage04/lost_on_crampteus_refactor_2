package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Consomable;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;

public class PotionPv extends ItemConsommable{
    public PotionPv() {
        super("potionPv", 5, 1);
    }


    @Override
    public void utiliser(Monde monde, int tileX, int tileY) {
        if (!(monde.getJoueur().getPvProperty().getValue() == 1.0)){
            monde.getJoueur().ajouterVie(0.2);
            setTailleStack(this.getTailleStack()-1);
        }

        if (this.getTailleStack() == 0){
            monde.getJoueur().getInventaire().enleverItem(this);
        }
    }

}
