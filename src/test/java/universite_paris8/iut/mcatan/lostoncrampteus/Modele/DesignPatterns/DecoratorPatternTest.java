package universite_paris8.iut.mcatan.lostoncrampteus.Modele.DesignPatterns;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme.Epee;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Decorator.EnchantedItemDecorator;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Item;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;

import static org.junit.jupiter.api.Assertions.*;

class DecoratorPatternTest {

    private Monde monde;
    private Item epee;

    @BeforeEach
    void setUp() {
        monde = new Monde();
        epee = new Epee(monde);
    }

    @Test
    void testEnchantedItemDecorator() {
        EnchantedItemDecorator enchantedEpee = new EnchantedItemDecorator(epee, "Feu", 3);
        
        assertNotNull(enchantedEpee);
        assertTrue(enchantedEpee.getNom().contains("Feu"));
        assertTrue(enchantedEpee.getNom().contains("+3"));
    }

    @Test
    void testDecoratorPreservesBaseName() {
        EnchantedItemDecorator enchantedEpee = new EnchantedItemDecorator(epee, "Glace", 5);
        
        String nom = enchantedEpee.getNom();
        assertTrue(nom.contains("épée"));
        assertTrue(nom.contains("Glace"));
        assertTrue(nom.contains("+5"));
    }

    @Test
    void testMultipleDecorators() {
        // Apply first enchantment
        EnchantedItemDecorator enchanted1 = new EnchantedItemDecorator(epee, "Feu", 2);
        
        // Apply second enchantment on top
        EnchantedItemDecorator enchanted2 = new EnchantedItemDecorator(enchanted1, "Foudre", 1);
        
        assertNotNull(enchanted2);
        String nom = enchanted2.getNom();
        assertTrue(nom.contains("Foudre"));
        assertTrue(nom.contains("+1"));
    }

    @Test
    void testEnchantmentProperties() {
        EnchantedItemDecorator enchanted = new EnchantedItemDecorator(epee, "Poison", 4);
        
        assertEquals("Poison", enchanted.getEnchantmentType());
        assertEquals(4, enchanted.getEnchantmentLevel());
    }

    @Test
    void testDecoratorAttackMethod() {
        EnchantedItemDecorator enchanted = new EnchantedItemDecorator(epee, "Feu", 1);
        
        // Test that attack method doesn't throw exception
        assertDoesNotThrow(() -> enchanted.attaquer(10, 10));
    }

    @Test
    void testDecoratorUtiliserMethod() {
        EnchantedItemDecorator enchanted = new EnchantedItemDecorator(epee, "Glace", 2);
        
        // Test that utiliser method doesn't throw exception
        assertDoesNotThrow(() -> enchanted.utiliser(monde, 10, 10));
    }
}
