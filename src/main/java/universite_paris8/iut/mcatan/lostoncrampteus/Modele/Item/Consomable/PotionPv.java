package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Consomable;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Strategy.UtilisationConsommableStrategy;

public class PotionPv extends ItemConsommable {

    public PotionPv() {
        super("potionPv", 5, 1);

        // Configuration de la stratégie de consommation
        setStrategieUtilisation(new UtilisationConsommableStrategy(0.2));
    }
}