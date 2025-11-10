package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage;

import javafx.scene.input.KeyCode;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Craft.Craft;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Craft.Recette;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme.Arme;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Armure;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Hitbox;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Inventaire;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc.*;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Factory.BlocFactory;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Item;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.ItemAuSol;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Terrain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static universite_paris8.iut.mcatan.lostoncrampteus.Controller.Constants.GameConstants.TILE_SIZE;

public class Joueur extends Acteur {

    private static Joueur uniqueInstance=null;
    private Item itemEquipe;
    private Armure armureEquipee;
    private Craft systemeDeCraft;
    private ArrayList<Bloc> blocEnCoursCassage;
    private Map<String, Recette> recetteDisponible;

    private Joueur() {
        super(100);
        setHeight(60);
        setWidth(28);
        this.itemEquipe = null;
        this.armureEquipee = null;
        this.systemeDeCraft = new Craft();
        this.blocEnCoursCassage = new ArrayList<>();
        this.recetteDisponible = new HashMap<>();
        this.blocFactory = new BlocFactory();
    }


    public static Joueur getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Joueur();
        }
        return uniqueInstance;
    }


    public void updatePosition(HashSet<KeyCode> activeKeys) {
        if (estVivant()) {
            double nextX = getPosX() + velocityX;

            if (!(activeKeys.contains(KeyCode.D) && activeKeys.contains(KeyCode.Q))){
                if (activeKeys.contains(KeyCode.D)) velocityX = 3;
                if (activeKeys.contains(KeyCode.Q)) velocityX = -3;
            }
            if(activeKeys.contains(KeyCode.SPACE)){
                Hitbox estAuSol = new Hitbox(getPosX(), getPosY() + 2, getWidth(), getHeight());
                if (Terrain.getInstance().checkCollision(estAuSol)) {
                    velocityY = -11;
                }
            }


            Hitbox testX = new Hitbox(nextX, getPosY(), getWidth(), getHeight());
            if (!Terrain.getInstance().checkCollision(testX)) {
                setPosX(nextX);
            } else {
                velocityX = 0;
            }

            Hitbox frottementSol = new Hitbox(getPosX(), getPosY() + 1, getWidth(), getHeight());
            boolean auSol = Terrain.getInstance().checkCollision(frottementSol);
            if (auSol) {
                velocityX *= 0.6;
            } else {
                velocityX *= 0.7;
            }
        }
    }

    public void checkRamassageItems() {
        Hitbox hitboxJoueur = getHitbox();
        ArrayList<ItemAuSol> itemsARamasser = new ArrayList<>();

        for (ItemAuSol item : Monde.getInstance().getItemsAuSol()) {
            Hitbox hitboxItem = new Hitbox(item.getPosX(), item.getPosY(), TILE_SIZE, TILE_SIZE);
            if (hitboxJoueur.colision(hitboxItem)) {
                itemsARamasser.add(item);
            }
        }

        for (ItemAuSol item : itemsARamasser) {
            if (!Inventaire.getInstance().estPlein()) {
                ajoutInventaire(item.getItem());
                Monde.getInstance().enleverItemAuSol(item);
                miseAjourRecettesDisponibles();
            }
            else if (Inventaire.getInstance().estPlein() && item.getItem().peutEtreStacke()) {
                ajoutInventaire(item.getItem());
                Monde.getInstance().enleverItemAuSol(item);
                miseAjourRecettesDisponibles();
            }
        }
    }

    public boolean craftItem(String nomItem) {
        Item resultat = systemeDeCraft.craft(nomItem);
        if (resultat != null) {
            if (!Inventaire.getInstance().estPlein()) {
                Inventaire.getInstance().ajouterItem(resultat);
            }
            else {
                Monde.getInstance().ajouterItemAuSol(resultat, getPosX()-TILE_SIZE, getPosY());//si inventaire est plein on met l'item au sol
            }
            miseAjourRecettesDisponibles();
            return true;
        }
        return false;
    }

    public void miseAjourRecettesDisponibles() {
        recetteDisponible.clear();
        Map<String, Recette> toutesRecettes = systemeDeCraft.getToutesRecettes();

        for (Map.Entry<String, Recette> entry : toutesRecettes.entrySet()) {
            if (entry.getValue().peutEtreCraft()) {
                recetteDisponible.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public Map<String, Recette> getRecetteDisponible() {
        return recetteDisponible;
    }

    public void ajoutInventaire(Item item) {
        Inventaire.getInstance().ajouterItem(item);
        miseAjourRecettesDisponibles();
    }

    public Item getitemEquipe(){
        return this.itemEquipe;
    }

    public Armure getArmureEquipee(){
        return this.armureEquipee;
    }

    public void setArmureEquipee(Armure armure){
        this.armureEquipee = armure;
    }

    public void casserTile(int x, int y) {
        boolean blocExistantTrouve = false;
        int i = blocEnCoursCassage.size() - 1;

        while (i >= 0 && !blocExistantTrouve) { // parcour a l'envers pour eviter les problemes d'index
            Bloc bloc = blocEnCoursCassage.get(i);
            if (bloc.getX() == x && bloc.getY() == y) {
                blocExistantTrouve = true;

                bloc.perdreDurabilite(((Arme) itemEquipe).getDegats());
                System.out.println(bloc.getDurabilite());

                if (bloc.estDetruit()) {
                    Terrain.getInstance().getMap()[y][x] = 0;
                    blocEnCoursCassage.remove(i);
                    Terrain.getInstance().mettreAJourCollisionTuile(x, y);
                    Monde.getInstance().ajouterItemAuSol(bloc,x*TILE_SIZE,y*TILE_SIZE);
                }
            }
            i--;
        }

        if (!blocExistantTrouve) ajouterBlocEnCourCassage(x, y);
    }

    private BlocFactory blocFactory;

    private void ajouterBlocEnCourCassage(int x, int y){
        int tileType = Terrain.getInstance().getMap()[y][x];
        String blocType = getBlocTypeFromTileType(tileType);

        if (blocType != null) {
            Bloc nouveauBloc = (Bloc) blocFactory.createItem(blocType);
            if (nouveauBloc != null) {
                nouveauBloc.setPosition(x, y);
                blocEnCoursCassage.add(nouveauBloc);
            }
        }
    }

    private String getBlocTypeFromTileType(int tileType) {
        return switch (tileType) {
            case 1 -> "grass";
            case 2 -> "dirt";
            case 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28 -> "bois";
            case 29 -> "aluminium";
            case 30 -> "cramptenium";
            case 31 -> "cuivre";
            case 32 -> "fer";
            case 33 -> "pierre";
            case 34 -> "Gintoki";
            default -> null;
        };
    }

    public void placerTile(int x, int y, int tile){
        Terrain.getInstance().getMap()[y][x] = tile;
        Terrain.getInstance().mettreAJourCollisionTuile(x,y);
        itemEquipe.decrementerStack();
        if (itemEquipe.getTailleStack() == 0){
            Inventaire.getInstance().enleverItem(itemEquipe);
        }
    }

    public void setitemEquipe(Item itemSelectionne) {
        this.itemEquipe = itemSelectionne;
    }

    public void attaquer(Acteur cible) {
        if(itemEquipe instanceof Arme)
            cible.perdreVie(((Arme) itemEquipe).getDegats());
    }
}