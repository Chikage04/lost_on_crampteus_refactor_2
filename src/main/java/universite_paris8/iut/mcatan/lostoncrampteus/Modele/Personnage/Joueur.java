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
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Terrain;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc.BlocType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static universite_paris8.iut.mcatan.lostoncrampteus.Controller.Constants.GameConstants.TILE_SIZE;

public class Joueur extends Acteur {

    private static Joueur uniqueInstance=null;
    private Item itemEquipee;
    private Armure armureEquipee;
    private final Inventaire inventaire;
    private final Craft systemeDeCraft;
    private final Map<String, Bloc> blocEnCoursCassage;
    private final Map<String, Recette> recetteDisponible;

    private Joueur() {
        super(100);
        setHeight(60);
        setWidth(28);
        this.itemEquipee = null;
        this.armureEquipee = null;
        this.inventaire = new Inventaire(10, this);
        this.systemeDeCraft = new Craft();
        this.blocEnCoursCassage = new HashMap<>();
        this.recetteDisponible = new HashMap<>();
        this.blocFactory = new BlocFactory();
    }


    public static Joueur getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Joueur();
        }
        return uniqueInstance;
    }


    public void updatePosition(Terrain terrain, HashSet<KeyCode> activeKeys) {
        if (estVivant()) {
            double nextX = getPosX() + velocityX;

            if (!(activeKeys.contains(KeyCode.D) && activeKeys.contains(KeyCode.Q))){
                if (activeKeys.contains(KeyCode.D)) velocityX = 3;
                if (activeKeys.contains(KeyCode.Q)) velocityX = -3;
            }
            if(activeKeys.contains(KeyCode.SPACE)){
                Hitbox estAuSol = new Hitbox(getPosX(), getPosY() + 2, getWidth(), getHeight());
                if (terrain.checkCollision(estAuSol)) {
                    velocityY = -11;
                }
            }


            Hitbox testX = new Hitbox(nextX, getPosY(), getWidth(), getHeight());
            if (!terrain.checkCollision(testX)) {
                setPosX(nextX);
            } else {
                velocityX = 0;
            }

            Hitbox frottementSol = new Hitbox(getPosX(), getPosY() + 1, getWidth(), getHeight());
            boolean auSol = terrain.checkCollision(frottementSol);
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

        for (ItemAuSol item : super.getMonde().getItemsAuSol()) {
            Hitbox hitboxItem = new Hitbox(item.getPosX(), item.getPosY(), TILE_SIZE, TILE_SIZE);
            if (hitboxJoueur.colision(hitboxItem)) {
                itemsARamasser.add(item);
            }
        }

        for (ItemAuSol item : itemsARamasser) {
            if (!inventaire.estPlein()) {
                ajoutInventaire(item.getItem());
                super.getMonde().enleverItemAuSol(item);
                miseAjourRecettesDisponibles();
            }
            else if (inventaire.estPlein() && item.getItem().peutEtreStacke()) {
                ajoutInventaire(item.getItem());
                super.getMonde().enleverItemAuSol(item);
                miseAjourRecettesDisponibles();
            }
        }
    }

    public boolean craftItem(String nomItem) {
        Item resultat = systemeDeCraft.craft(nomItem, inventaire);
        if (resultat != null) {
            if (!inventaire.estPlein()) {
                inventaire.ajouterItem(resultat);
            }
            else {
                super.getMonde().ajouterItemAuSol(resultat, getPosX()-TILE_SIZE, getPosY());//si inventaire est plein on met l'item au sol
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
            if (entry.getValue().peutEtreCraft(inventaire)) {
                recetteDisponible.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public Map<String, Recette> getRecetteDisponible() {
        return recetteDisponible;
    }

    public void ajoutInventaire(Item item) {
        this.inventaire.ajouterItem(item);
        miseAjourRecettesDisponibles();
    }

    public Item getItemEquipee(){
        return this.itemEquipee;
    }

    public Inventaire getInventaire(){
        return this.inventaire;
    }

    public Armure getArmureEquipee(){
        return this.armureEquipee;
    }

    public void setArmureEquipee(Armure armure){
        this.armureEquipee = armure;
    }

    public void casserTile(Terrain terrain, int x, int y) {
        String key = x + ":" + y;
        Bloc bloc = blocEnCoursCassage.get(key);

        if (bloc == null) {
            ajouterBlocEnCourCassage(terrain, x, y);
            bloc = blocEnCoursCassage.get(key);
            if (bloc == null) return;
        }

        int degats = (itemEquipee instanceof Arme) ? ((Arme) itemEquipee).getDegats() : 1;

        bloc.perdreDurabilite(degats);

        if (bloc.estDetruit()) {
            terrain.getMap()[y][x] = 0;
            blocEnCoursCassage.remove(key);
            terrain.mettreAJourCollisionTuile(x, y);
            super.getMonde().ajouterItemAuSol(bloc, x * TILE_SIZE, y * TILE_SIZE);
        }
    }

    private final BlocFactory blocFactory;

    private void ajouterBlocEnCourCassage(Terrain terrain, int x, int y){
        int tileType = terrain.getMap()[y][x];

        // Utiliser BlocType pour obtenir le type de bloc
        BlocType type = BlocType.fromTileType(tileType);
        if (type != null) {
            Item item = blocFactory.createItem(type.getKey());
            if (item instanceof Bloc nouveauBloc) {
                nouveauBloc.setPosition(x, y);
                String key = x + ":" + y;
                blocEnCoursCassage.put(key, nouveauBloc);
            }
        }
    }

    public void placerTile(Terrain terrain, int x, int y, int tile){
        terrain.getMap()[y][x] = tile;
        terrain.mettreAJourCollisionTuile(x,y);
        itemEquipee.decrementerStack();
        if (itemEquipee.getTailleStack() == 0){
            inventaire.enleverItem(itemEquipee);
        }
    }

    public void setItemEquipee(Item itemSelectionne) {
        this.itemEquipee = itemSelectionne;
    }

    public void attaquer(int tileX, int tileY) {
      //  if(itemEquipee instanceof Arme)
          //  cible.perdreVie(((Arme) itemEquipee).getDegats());
        getItemEquipee().attaquer(tileX, tileY);
    }
}
