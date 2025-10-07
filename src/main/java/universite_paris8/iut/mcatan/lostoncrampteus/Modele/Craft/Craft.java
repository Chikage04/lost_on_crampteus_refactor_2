
package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Craft;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Inventaire;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.*;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme.Epee;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Consomable.PotionPv;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Craft.Template.CraftTemplate;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Craft.Template.CraftStandard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Craft {
    private Map<String, Recette> recettesMap;
    private Monde monde;
    private CraftTemplate craftTemplate;

    public Craft(Monde monde) {
        this.monde = monde;
        this.recettesMap = new HashMap<>();
        this.craftTemplate = new CraftStandard();
        initialiserRecettesDeBase();
    }

    private void initialiserRecettesDeBase() {
        Recette epeeSimple = new Recette(new Epee(monde));
        epeeSimple.ajouterIngredient("fer", 3);
            epeeSimple.ajouterIngredient("aluminium", 4);
        ajouterRecette("épée", epeeSimple);

        Recette potionPv = new Recette(new PotionPv());
        potionPv.ajouterIngredient("pomme", 3);
        ajouterRecette("potionPv", potionPv);
    }

    public void ajouterRecette(String nomItem, Recette recette) {
        recettesMap.put(nomItem, recette);
    }

    public Map<String, Recette> getToutesRecettes() {
        return new HashMap<>(recettesMap);
    }

    public Item craft(String nomItem, Inventaire inventaire) {
        Recette recette = recettesMap.get(nomItem);
        if (recette == null) {
            return null;
        }
        return craftTemplate.executerCraft(recette, inventaire);
    }

    private void retirerIngredients(Inventaire inventaire, String nomItem, int quantite) {
        // This method is now handled by the template, kept for potential future use
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
