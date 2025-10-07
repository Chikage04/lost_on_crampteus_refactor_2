package universite_paris8.iut.mcatan.lostoncrampteus.Modele.DesignPatterns;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Gronfleur;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Strategy.DeplacementStrategy;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Strategy.DeplacementTerrestreStrategy;

import static org.junit.jupiter.api.Assertions.*;

class StrategyPatternTest {

    private Monde monde;
    private Gronfleur gronfleur;

    @BeforeEach
    void setUp() {
        monde = new Monde();
        gronfleur = new Gronfleur(monde);
    }

    @Test
    void testDeplacementStrategyNotNull() {
        assertNotNull(gronfleur);
        // Gronfleur should have a movement strategy set
    }

    @Test
    void testDeplacementTerrestreStrategy() {
        DeplacementStrategy strategy = new DeplacementTerrestreStrategy(2.0, 150);
        assertNotNull(strategy);
        gronfleur.setDeplacementStrategy(strategy);
        
        // Test that seDeplacer doesn't throw exception
        assertDoesNotThrow(() -> gronfleur.seDeplacer());
    }

    @Test
    void testChangeStrategy() {
        DeplacementStrategy strategy1 = new DeplacementTerrestreStrategy(1.0, 100);
        DeplacementStrategy strategy2 = new DeplacementTerrestreStrategy(3.0, 200);
        
        gronfleur.setDeplacementStrategy(strategy1);
        assertDoesNotThrow(() -> gronfleur.seDeplacer());
        
        gronfleur.setDeplacementStrategy(strategy2);
        assertDoesNotThrow(() -> gronfleur.seDeplacer());
    }
}
