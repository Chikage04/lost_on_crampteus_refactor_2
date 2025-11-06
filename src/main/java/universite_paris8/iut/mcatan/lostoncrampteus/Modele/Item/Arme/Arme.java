package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.ItemAvecDurabilite;

import java.util.ArrayList;

public  class Arme extends ItemAvecDurabilite {

    private final int degats;
    protected AttackStrategy attackStrategy;


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

    /**
     * Exécute l'attaque et retourne la liste des tuiles affectées (peut être vide).
     */
    public ArrayList<int[]> performAttack(int tileX, int tileY) {
        if (attackStrategy != null) {
            ArrayList<int[]> result = attackStrategy.attack(this, tileX, tileY);
            return result == null ? new ArrayList<>() : result;
        }
        return new ArrayList<>();
    }

    // pour le clic gauche (ancienne API, reste pour compatibilité)
    @Override
    public void attaquer(int tileX, int tileY){
        // conserve le comportement précédent : exécute l'attaque sans retourner les tuiles
        performAttack(tileX, tileY);
    }

}
