package universite_paris8.iut.mcatan.lostoncrampteus.Modele.DesignPatterns;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc.Fer;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Composite.ItemBundle;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Composite.ItemContainer;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Consomable.Pomme;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Item;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;

import static org.junit.jupiter.api.Assertions.*;

class CompositePatternTest {

    private Monde monde;

    @BeforeEach
    void setUp() {
        monde = new Monde();
    }

    @Test
    void testItemBundleCreation() {
        ItemBundle bundle = new ItemBundle("Starter Pack");
        assertNotNull(bundle);
        assertEquals(0, bundle.getNombreItems());
    }

    @Test
    void testItemBundleAddItems() {
        ItemBundle bundle = new ItemBundle("Resource Pack");
        bundle.ajouterItem(new Pomme());
        bundle.ajouterItem(new Fer());
        
        assertEquals(2, bundle.getNombreItems());
    }

    @Test
    void testItemBundleRemoveItems() {
        ItemBundle bundle = new ItemBundle("Test Bundle");
        Item pomme = new Pomme();
        bundle.ajouterItem(pomme);
        bundle.ajouterItem(new Fer());
        
        assertEquals(2, bundle.getNombreItems());
        
        bundle.retirerItem(pomme);
        assertEquals(1, bundle.getNombreItems());
    }

    @Test
    void testItemBundleGetItems() {
        ItemBundle bundle = new ItemBundle("Mixed Pack");
        bundle.ajouterItem(new Pomme());
        bundle.ajouterItem(new Fer());
        
        assertEquals(2, bundle.getItems().size());
    }

    @Test
    void testItemContainerCreation() {
        ItemContainer container = new ItemContainer("Chest", 10);
        assertNotNull(container);
        assertEquals(10, container.getCapacite());
        assertEquals(0, container.getNombreItems());
    }

    @Test
    void testItemContainerCapacity() {
        ItemContainer container = new ItemContainer("Small Chest", 3);
        
        container.ajouterItem(new Pomme());
        container.ajouterItem(new Fer());
        container.ajouterItem(new Pomme());
        
        assertFalse(container.estPlein());
        assertEquals(3, container.getNombreItems());
    }

    @Test
    void testItemContainerFullCapacity() {
        ItemContainer container = new ItemContainer("Tiny Box", 2);
        
        container.ajouterItem(new Pomme());
        container.ajouterItem(new Fer());
        
        assertTrue(container.estPlein());
        
        // Try to add one more (should not be added)
        container.ajouterItem(new Pomme());
        assertEquals(2, container.getNombreItems());
    }

    @Test
    void testItemContainerType() {
        ItemContainer container = new ItemContainer("Backpack", 20);
        assertEquals("container", container.getCompositeType());
    }

    @Test
    void testItemBundleType() {
        ItemBundle bundle = new ItemBundle("Gift Box");
        assertEquals("bundle", bundle.getCompositeType());
    }

    @Test
    void testCompositeNomFormat() {
        ItemBundle bundle = new ItemBundle("Test Pack");
        bundle.ajouterItem(new Pomme());
        bundle.ajouterItem(new Fer());
        
        String nom = bundle.getNom();
        assertTrue(nom.contains("Test Pack"));
        assertTrue(nom.contains("2 items"));
    }
}
