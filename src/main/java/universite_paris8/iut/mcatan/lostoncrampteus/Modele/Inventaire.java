package universite_paris8.iut.mcatan.lostoncrampteus.Modele;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Joueur;

import java.util.ArrayList;

public class Inventaire {

    private ObservableList<Item> inventaire;
    private int tailleMax;
    private static Inventaire uniqueInstance = null;

    private Inventaire(int tailleMax){
        this.inventaire = FXCollections.observableArrayList();
        this.tailleMax = tailleMax;
    }

    public static Inventaire getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Inventaire(10);
        }
        return uniqueInstance;
    }

    public int getQuantite(String nom) {
        int quantite = 0;
        for (Item item : inventaire) {
            if (item.getNom().equals(nom)) {
                quantite += item.getTailleStack();
            }
        }
        return quantite;
    }

    public void enleverItem(Item item){
        if (Joueur.getInstance().getitemEquipe() == item){
            Joueur.getInstance().setitemEquipe(null);
        }
        this.inventaire.remove(item);
        System.out.println(inventaire);
    }

    public ObservableList<Item> getInventaireList(){
        return this.inventaire;
    }

    public void ajouterItem(Item item) {
        boolean itemStacke = false;
        ArrayList<Item> inventaireCopie = new ArrayList<>(inventaire);
        
        for (Item existant : inventaireCopie) {
            if (!itemStacke && existant.getClass() == item.getClass() && existant.getTailleStack() < existant.getStackLimit()) {
                existant.setTailleStack(existant.getTailleStack() + 1);
                changementStack(existant);
                itemStacke = true;
            }
        }
        
        if (!itemStacke && !estPlein()) {
            inventaire.add(item);
        }
    }

    public boolean estPlein() {
        return inventaire.size() == tailleMax;
    }

    public boolean toutesLesStacksPlein() {
        if (!inventaire.isEmpty()){
            for (Item item : inventaire) {
                if (!item.stackPlein()) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    //temp
    public void changementStack(Item item) {
        inventaire.remove(item);
        inventaire.add(item);
    }
}