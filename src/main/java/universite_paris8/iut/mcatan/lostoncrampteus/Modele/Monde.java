package universite_paris8.iut.mcatan.lostoncrampteus.Modele;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyCode;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme.Epee;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme.Pioche;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc.Aluminium;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc.Cuivre;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc.Fer;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Consomable.Pomme;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Consomable.PotionPv;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Consomable.Sac;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Item;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.ItemAuSol;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Acteur;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Joueur;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Pnj;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static universite_paris8.iut.mcatan.lostoncrampteus.Controller.Constants.GameConstants.TILE_SIZE;

public class Monde {

    private static Monde uniqueInstance=null;
    private Joueur joueur;
    public final static double GRAVITY = 0.9;
    private ArrayList<Pnj> pnjs;
    private ObservableList<ItemAuSol> itemsAuSol;

    private Monde(){
        this.pnjs = new ArrayList<>();
        this.itemsAuSol = FXCollections.observableArrayList();
        initItemAuSol();
    }

    public static Monde getInstance(){
        if(uniqueInstance==null){
            uniqueInstance = new Monde();
        }
        return uniqueInstance;
    }

    public void setJoueur(Joueur joueur){
        this.joueur = joueur;
    }

    private void initItemAuSol(){
        for (int i=0; i < 4; i++) {
            ajouterItemAuSol(new Pomme(), 100, 100);
        }

        // deux en plus pour tester le ramassage
        ajouterItemAuSol(new Pomme(), 22*TILE_SIZE, 19*TILE_SIZE);

        ajouterItemAuSol(new Sac(new ArrayList<>(Arrays.asList(new Pomme(), new Cuivre(), new PotionPv()))), 180, 180);

        for (int i =0; i < 4; i++) {
            ajouterItemAuSol(new Aluminium(), 21*TILE_SIZE, 19*TILE_SIZE);
            ajouterItemAuSol(new Fer(), 20*TILE_SIZE, 19*TILE_SIZE);
        }

        ajouterItemAuSol(new Pioche(), 0 , 0);
        ajouterItemAuSol(new Epee(), 0, 0);
    }

    public void updateMonde(HashSet<KeyCode> activeKeys) {
        appliquerGraviteItem();
        appliquerGraviteActeurs();
        joueur.checkRamassageItems();
        joueur.updatePosition(activeKeys);
        for (Pnj acteur : pnjs) {
            acteur.seDeplacer();
        }
    }

    public void ajouterItemAuSol(Item item, double x, double y) {
        itemsAuSol.add(new ItemAuSol(item, x, y));
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
        return Terrain.getInstance();
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public ArrayList<Pnj> getPnjs() { return pnjs; }

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
