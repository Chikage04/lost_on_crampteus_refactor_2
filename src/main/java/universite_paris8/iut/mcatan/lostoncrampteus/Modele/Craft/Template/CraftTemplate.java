package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Craft.Template;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Inventaire;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Item;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Craft.Recette;

import java.util.ArrayList;
import java.util.Map;

public abstract class CraftTemplate {
    
    // Template method - defines the skeleton of the crafting algorithm
    public final Item executerCraft(Recette recette, Inventaire inventaire) {
        if (!verifierIngredients(recette, inventaire)) {
            return null;
        }
        
        retirerIngredients(recette, inventaire);
        return creerResultat(recette);
    }
    
    // Step 1: Check if crafting is possible
    protected boolean verifierIngredients(Recette recette, Inventaire inventaire) {
        return recette.peutEtreCraft(inventaire);
    }
    
    // Step 2: Remove ingredients from inventory
    protected void retirerIngredients(Recette recette, Inventaire inventaire) {
        for (Map.Entry<String, Integer> entry : recette.getIngredients().entrySet()) {
            retirerIngredient(inventaire, entry.getKey(), entry.getValue());
        }
    }
    
    // Step 3: Create the result item
    protected Item creerResultat(Recette recette) {
        return recette.getResultat();
    }
    
    // Helper method to remove specific ingredient
    private void retirerIngredient(Inventaire inventaire, String nomItem, int quantite) {
        int resteARetirer = quantite;
        ArrayList<Item> itemsAretirer = new ArrayList<>();

        for (Item item : inventaire.getInventaireList()) {
            if (resteARetirer > 0) {
                if (item.getNom().equals(nomItem)) {
                    int quantiteDansStack = item.getTailleStack();

                    if (quantiteDansStack > resteARetirer) {
                        item.setTailleStack(quantiteDansStack - resteARetirer);
                        resteARetirer = 0;
                    } else {
                        resteARetirer -= quantiteDansStack;
                        itemsAretirer.add(item);
                    }
                }
            }
        }

        for (Item item : itemsAretirer) {
            inventaire.enleverItem(item);
        }
    }
}
