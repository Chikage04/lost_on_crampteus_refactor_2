package universite_paris8.iut.mcatan.lostoncrampteus.Modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyCode;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme.Arc;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme.Epee;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme.Pioche;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc.Aluminium;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc.Dirt;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc.Fer;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc.Grass;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Consomable.Pomme;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Consomable.PotionPv;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Item;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.ItemAuSol;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Acteur;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Joueur;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Pnj;


import java.util.ArrayList;
import java.util.HashSet;

public class Monde {

    private Terrain terrain;
    private Joueur joueur;
    public final static double GRAVITY = 0.9;
    private ArrayList<Pnj> pnjs;
    private ObservableList<ItemAuSol> itemsAuSol;

    public Monde(Terrain terrain, Joueur joueur){
        this.terrain = terrain;
        this.joueur = joueur;
        this.pnjs = new ArrayList<>();
        this.itemsAuSol = FXCollections.observableArrayList();
    }

    public Monde(){
        this.pnjs = new ArrayList<>();
        this.terrain = new Terrain();
        this.joueur = new Joueur(100, this);
        this.itemsAuSol = FXCollections.observableArrayList();
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
        appliquerGraviteItem();
        appliquerGraviteActeurs();
        joueur.checkRamassageItems();
        joueur.updatePosition(terrain, activeKeys);
        for (Pnj acteur : pnjs) {
            acteur.seDeplacer();
        }
    }

    public void ajouterItemAuSol(Item item, double x, double y) {
        itemsAuSol.add(new ItemAuSol(item, x, y,this));
    }

    public void appliquerGraviteItem() {
        for (ItemAuSol item : itemsAuSol) {
            item.updateGraviteItem();
        }
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

    public void appliquerGraviteActeurs() {
        joueur.appliquerGravite();
        for (Acteur acteur : pnjs) {
            acteur.appliquerGravite();
        }
    }
}
