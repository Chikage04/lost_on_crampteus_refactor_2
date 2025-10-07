package universite_paris8.iut.mcatan.lostoncrampteus.Modele.DesignPatterns;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc.Aluminium;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc.Bloc;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc.Fer;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc.Grass;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Factory.ArmeFactory;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Factory.BlocFactory;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Factory.ConsomableFactory;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Item;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;

import static org.junit.jupiter.api.Assertions.*;

class FactoryPatternTest {

    private Monde monde;
    private BlocFactory blocFactory;
    private ArmeFactory armeFactory;
    private ConsomableFactory consomableFactory;

    @BeforeEach
    void setUp() {
        monde = new Monde();
        blocFactory = new BlocFactory();
        armeFactory = new ArmeFactory();
        consomableFactory = new ConsomableFactory();
    }

    @Test
    void testBlocFactoryCreatesGrass() {
        Item grass = blocFactory.createItem("grass", monde);
        assertNotNull(grass);
        assertTrue(grass instanceof Grass);
        assertEquals("grass", grass.getNom());
    }

    @Test
    void testBlocFactoryCreatesFer() {
        Item fer = blocFactory.createItem("fer", monde);
        assertNotNull(fer);
        assertTrue(fer instanceof Fer);
        assertEquals("fer", fer.getNom());
    }

    @Test
    void testBlocFactoryCreatesAluminium() {
        Item aluminium = blocFactory.createItem("aluminium", monde);
        assertNotNull(aluminium);
        assertTrue(aluminium instanceof Aluminium);
        assertEquals("aluminium", aluminium.getNom());
    }

    @Test
    void testBlocFactoryReturnsNullForInvalidType() {
        Item invalid = blocFactory.createItem("invalid_type", monde);
        assertNull(invalid);
    }

    @Test
    void testArmeFactoryCreatesEpee() {
        Item epee = armeFactory.createItem("épée", monde);
        assertNotNull(epee);
        assertEquals("épée", epee.getNom());
    }

    @Test
    void testArmeFactoryCreatesPioche() {
        Item pioche = armeFactory.createItem("pioche", monde);
        assertNotNull(pioche);
        assertEquals("pioche", pioche.getNom());
    }

    @Test
    void testConsomableFactoryCreatesPomme() {
        Item pomme = consomableFactory.createItem("pomme", monde);
        assertNotNull(pomme);
        assertEquals("pomme", pomme.getNom());
    }

    @Test
    void testConsomableFactoryCreatesPotionPv() {
        Item potionPv = consomableFactory.createItem("potionpv", monde);
        assertNotNull(potionPv);
        assertEquals("potionPv", potionPv.getNom());
    }
}
