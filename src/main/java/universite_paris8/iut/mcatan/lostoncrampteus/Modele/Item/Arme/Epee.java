package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Strategy.AttaqueMeleeStrategy;

/**
 * Épée - Arme de mêlée.
 * Utilise AttaqueMeleeStrategy pour son comportement d'attaque.
 */
public class Epee extends Arme {

    private Monde monde;
    private int range;

    public Epee(Monde monde){
        super("épée", 25, 100);
        this.range = 64;
        this.monde = monde;

        // Configuration de la stratégie d'attaque au corps à corps
        setStrategieAttaque(new AttaqueMeleeStrategy(getDegats(), range));
    }

    public Epee(int range, Monde monde) {
        super("épée", 10, 100);
        this.range = range;
        this.monde = monde;

        setStrategieAttaque(new AttaqueMeleeStrategy(getDegats(), range));
    }

    @Override
    public String toString() {
        return "épée";
    }
}