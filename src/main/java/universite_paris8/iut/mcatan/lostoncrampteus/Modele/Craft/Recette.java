package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Craft;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Inventaire;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Item;
import java.util.HashMap;
import java.util.Map;

public class Recette {
    private Map<String, Integer> ingredients;
    private Item resultat;

    public Recette(Item resultat) {
        this.ingredients = new HashMap<>();
        this.resultat = resultat;
    }

    public void ajouterIngredient(String nomItem, int quantite) {
        ingredients.put(nomItem, quantite);
    }

    public boolean peutEtreCraft() {
        for (Map.Entry<String, Integer> entry : ingredients.entrySet()) {
            if (Inventaire.getInstance().getQuantite(entry.getKey()) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    public Item getResultat() {
        return resultat;
    }

    public Map<String, Integer> getIngredients() {
        return new HashMap<>(ingredients);
    }
}