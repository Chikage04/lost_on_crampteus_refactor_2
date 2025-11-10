package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Strategy.AttaqueDistanceStrategy;

/**
 * Arc - Arme à distance.
 * Utilise AttaqueDistanceStrategy pour tirer des projectiles.
 */
public class Arc extends Arme {

    private Monde monde;

    public Arc(Monde monde){
        super("arc", 50, 100);
        this.monde = monde;

        // Configuration de la stratégie d'attaque à distance
        setStrategieAttaque(new AttaqueDistanceStrategy(getDegats(), 200));
    }

    public Arc(int degats, int durabilite) {
        super("arc", degats, durabilite);

        setStrategieAttaque(new AttaqueDistanceStrategy(degats, 200));
    }

    @Override
    public String toString() {
        return "arc";
    }
}