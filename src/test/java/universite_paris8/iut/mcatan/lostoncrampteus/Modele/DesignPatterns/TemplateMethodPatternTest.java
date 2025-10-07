package universite_paris8.iut.mcatan.lostoncrampteus.Modele.DesignPatterns;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Craft.Craft;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Inventaire;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc.Aluminium;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc.Fer;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Consomable.Pomme;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Item;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Joueur;

import static org.junit.jupiter.api.Assertions.*;

class TemplateMethodPatternTest {

    private Monde monde;
    private Joueur joueur;
    private Inventaire inventaire;
    private Craft craft;

    @BeforeEach
    void setUp() {
        monde = new Monde();
        joueur = monde.getJoueur();
        inventaire = joueur.getInventaire();
        craft = new Craft(monde);
    }

    @Test
    void testCraftWithSufficientIngredients() {
        // Add ingredients for "potionPv"
        for (int i = 0; i < 3; i++) {
            inventaire.ajouterItem(new Pomme());
        }
        
        Item result = craft.craft("potionPv", inventaire);
        assertNotNull(result);
        assertEquals("potionPv", result.getNom());
    }

    @Test
    void testCraftWithInsufficientIngredients() {
        // Add only 2 pommes (need 3 for potionPv)
        for (int i = 0; i < 2; i++) {
            inventaire.ajouterItem(new Pomme());
        }
        
        Item result = craft.craft("potionPv", inventaire);
        assertNull(result);
    }

    @Test
    void testCraftRemovesIngredients() {
        // Add ingredients for "potionPv"
        for (int i = 0; i < 3; i++) {
            inventaire.ajouterItem(new Pomme());
        }
        
        int initialSize = inventaire.getInventaireList().size();
        craft.craft("potionPv", inventaire);
        
        // After crafting, pommes should be removed
        assertTrue(inventaire.getInventaireList().size() < initialSize);
    }

    @Test
    void testCraftInvalidRecipe() {
        Item result = craft.craft("invalid_item", inventaire);
        assertNull(result);
    }

    @Test
    void testCraftEpeeWithIngredients() {
        // Add ingredients for "épée"
        for (int i = 0; i < 3; i++) {
            inventaire.ajouterItem(new Fer());
        }
        for (int i = 0; i < 4; i++) {
            inventaire.ajouterItem(new Aluminium());
        }
        
        Item result = craft.craft("épée", inventaire);
        assertNotNull(result);
        assertEquals("épée", result.getNom());
    }
}
