package universite_paris8.iut.mcatan.lostoncrampteus.Modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyCode;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Craft.Craft;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme.Epee;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme.Pioche;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc.Aluminium;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc.Fer;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Consomable.Pomme;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Item;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.ItemAuSol;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Acteur;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Joueur;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Pnj;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Map.Terrain;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Physics.PhysicalEntity;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Physics.PhysicsEngine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Monde {

    private Terrain terrain;
    private Joueur joueur;
    public final static double GRAVITY = 0.9;
    private ArrayList<Pnj> pnjs;
    private ArrayList<Pnj> pnjsASupprimer;
    private ObservableList<ItemAuSol> itemsAuSol;
    private Craft craft;  // ADDED: Craft system

    public Monde(Terrain terrain, Joueur joueur){
        this.terrain = terrain;
        this.joueur = joueur;
        this.pnjs = new ArrayList<>();
        this.pnjsASupprimer = new ArrayList<>();
        this.itemsAuSol = FXCollections.observableArrayList();
        this.craft = new Craft(this);  // ADDED: Initialize craft system
    }

    public Monde(){
        this.pnjs = new ArrayList<>();
        this.pnjsASupprimer = new ArrayList<>();
        this.terrain = Terrain.builder().construire();
        this.joueur = new Joueur(100, this);
        this.itemsAuSol = FXCollections.observableArrayList();
        this.craft = new Craft(this);  // ADDED: Initialize craft system
        initItemAuSol();
    }

    private void initItemAuSol(){
        for (int i=0; i < 4; i++) {
            ajouterItemAuSol(new Pomme(), 100, 100);
        }

        // deux en plus pour tester le ramassage
        ajouterItemAuSol(new Pomme(), 22*32, 19*32);

        for (int i =0; i < 4; i++) {
            ajouterItemAuSol(new Aluminium(), 21*32, 19*32);
            ajouterItemAuSol(new Fer(), 20*32, 19*32);
        }

        ajouterItemAuSol(new Pioche(this), 0 , 0);
        ajouterItemAuSol(new Epee(this), 0, 0);
    }

    public void updateMonde(HashSet<KeyCode> activeKeys) {
        updateAllPhysics();

        for (Pnj acteur : pnjs) {
            if (acteur.estVivant()) {
                acteur.seDeplacer();
            }
        }

        supprimerPnjsMorts();
    }

    /**
     * Met à jour la physique de TOUTES les entités du monde
     */
    private void updateAllPhysics() {
        List<PhysicalEntity> allPhysicalEntities = new ArrayList<>();
        allPhysicalEntities.add(joueur);
        allPhysicalEntities.addAll(pnjs);

        for (ItemAuSol item : itemsAuSol) {
            allPhysicalEntities.add(item);
        }

        PhysicsEngine.updateAllPhysics(allPhysicalEntities, terrain, Monde.GRAVITY);
    }

    private void supprimerPnjsMorts() {
        pnjsASupprimer.clear();

        for (Pnj pnj : pnjs) {
            if (!pnj.estVivant()) {
                pnjsASupprimer.add(pnj);
            }
        }

        pnjs.removeAll(pnjsASupprimer);

        if (!pnjsASupprimer.isEmpty()) {
            System.out.println(pnjsASupprimer.size() + " PNJ(s) supprimé(s) du monde");
        }
    }

    public void ajouterItemAuSol(Item item, double x, double y) {
        itemsAuSol.add(new ItemAuSol(item, x, y, this));
    }

    public void enleverItemAuSol(ItemAuSol item) {
        itemsAuSol.remove(item);
    }

    public ObservableList<ItemAuSol> getItemsAuSol() {
        return itemsAuSol;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public ArrayList<Pnj> getPnjs() {
        return pnjs;
    }

    public void ajouterPnj(Pnj pnj){
        pnjs.add(pnj);
    }

    // ADDED: Getter for craft system
    public Craft getCraft() {
        return craft;
    }

    /**
     * Méthode utilitaire pour le débogage
     */
    public void afficherStatistiquesPhysiques() {
        System.out.println("=== STATISTIQUES PHYSIQUES ===");
        System.out.println("Joueur: posY=" + joueur.getPosY() + ", velocityY=" + joueur.getVelocityY());
        System.out.println("PNJs: " + pnjs.size());
        System.out.println("Items au sol: " + itemsAuSol.size());

        for (int i = 0; i < Math.min(3, itemsAuSol.size()); i++) {
            ItemAuSol item = itemsAuSol.get(i);
            System.out.println("Item " + i + ": posY=" + item.getPosY() + ", velocityY=" + item.getVelocityY());
        }
    }
}