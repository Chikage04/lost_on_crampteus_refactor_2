package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Strategy.AttaqueMinageStrategy;

/**
 * Pioche - Outil de minage.
 * Utilise AttaqueMinageStrategy pour casser les blocs.
 */
public class Pioche extends Arme {

    private Monde monde;
    private AttaqueMinageStrategy strategieMinage;

    public Pioche(Monde monde){
        super("pioche", 25, 100);
        this.monde = monde;

        // Configuration de la stratégie de minage
        this.strategieMinage = new AttaqueMinageStrategy(getDegats());
        setStrategieAttaque(strategieMinage);
    }

    public Pioche(String nom, int degats, int durabilite, int range, Monde monde) {
        super("pioche", degats, durabilite);
        this.monde = monde;

        this.strategieMinage = new AttaqueMinageStrategy(degats);
        setStrategieAttaque(strategieMinage);
    }

    // Méthode pour accéder aux dégâts depuis Joueur.casserTile()
    public int getDegatsMinage() {
        return strategieMinage.getDegats();
    }

    @Override
    public String toString() {
        return "pioche";
    }
}