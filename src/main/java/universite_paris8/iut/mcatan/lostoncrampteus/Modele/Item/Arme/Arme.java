package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.ItemAvecDurabilite;

public  class Arme extends ItemAvecDurabilite {

    private int degats;


    public Arme(String nom, int degats, int durabilite) {
        super(nom, 1, 1, durabilite);
        this.degats = degats;
    }

    public int getDegats(){
        return this.degats;
    }

}
