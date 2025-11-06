package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.ItemAvecDurabilite;

public  class Arme extends ItemAvecDurabilite {

    private int degats;
    private AttackStrategy attackStrategy;


    public Arme(String nom, int degats, int durabilite) {
        super(nom, 1, 1, durabilite);
        this.degats = degats;
    }

    public int getDegats(){
        return this.degats;
    }

    public void setAttackStrategy(AttackStrategy strategy) {
        this.attackStrategy = strategy;
    }


    // pour le clic gauche
    @Override
    public void attaquer(int tileX, int tileY){
        if (attackStrategy != null) {
            attackStrategy.attack(this, tileX, tileY);
            return;
        }

    }

}
