package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage;

import javafx.scene.input.KeyCode;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Craft.Craft;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Craft.Recette;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme.Arme;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Armure;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Hitbox;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Inventaire;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc.*;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Item;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.ItemAuSol;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Terrain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Joueur extends Acteur {

    private Item itemEquipee;
    private Armure armureEquipee;
    private Inventaire inventaire;
    private Craft systemeDeCraft;
    private ArrayList<Bloc> blocEnCoursCassage;
    private Map<String, Recette> recetteDisponible;

    public Joueur(int pv, Monde monde) {
        super(pv, monde);
        setHeight(60);
        setWidth(28);
        this.itemEquipee = null;
        this.armureEquipee = null;
        this.inventaire = new Inventaire(10, this);
        this.systemeDeCraft = new Craft(monde);
        this.blocEnCoursCassage = new ArrayList<>();
        this.recetteDisponible = new HashMap<>();
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

        for (ItemAuSol item : monde.getItemsAuSol()) {
            Hitbox hitboxItem = new Hitbox(item.getPosX(), item.getPosY(), 32, 32);
            if (hitboxJoueur.colision(hitboxItem)) {
                itemsARamasser.add(item);
            }
        }

        for (ItemAuSol item : itemsARamasser) {
            if (!inventaire.estPlein()) {
                ajoutInventaire(item.getItem());
                monde.enleverItemAuSol(item);
                miseAjourRecettesDisponibles();
            }
            else if (inventaire.estPlein() && item.getItem().peutEtreStacke()) {
                ajoutInventaire(item.getItem());
                monde.enleverItemAuSol(item);
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
                monde.ajouterItemAuSol(resultat, getPosX()-32, getPosY());//si inventaire est plein on met l'item au sol
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
        boolean blocExistantTrouve = false;
        int i = blocEnCoursCassage.size() - 1;

        while (i >= 0 && !blocExistantTrouve) { // parcour a l'envers pour eviter les problemes d'index
            Bloc bloc = blocEnCoursCassage.get(i);
            if (bloc.getX() == x && bloc.getY() == y) {
                blocExistantTrouve = true;

                bloc.perdreDurabilite(((Arme) itemEquipee).getDegats());
                System.out.println(bloc.getDurabilite());

                if (bloc.estDetruit()) {
                    terrain.getMap()[y][x] = 0;
                    blocEnCoursCassage.remove(i);
                    terrain.mettreAJourCollisionTuile(x, y);
                    monde.ajouterItemAuSol(bloc,x*32,y*32);
                }
            }
            i--;
        }

        if (!blocExistantTrouve) ajouterBlocEnCourCassage(terrain, x, y);
    }

    private void ajouterBlocEnCourCassage(Terrain terrain, int x, int y){
        int tileType = terrain.getMap()[y][x];
        Bloc nouveauBloc = null;

        switch (tileType) {
            case 1:
                nouveauBloc = new Grass();break;
            case 2:
                nouveauBloc = new Dirt();break;
            case 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28:
                nouveauBloc = new Bois(); break;
            case 29:
                nouveauBloc = new Aluminium(); break;
            case 30:
                nouveauBloc = new Cramptenium();break;
            case 31:
                nouveauBloc = new Cuivre();break;
            case 32:
                nouveauBloc = new Fer();break;
            case 33:
                nouveauBloc = new Pierre();break;
        }

        if (nouveauBloc != null) {
            nouveauBloc.setPosition(x, y);
            blocEnCoursCassage.add(nouveauBloc);
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

    public void attaquer(Acteur cible) {
        if(itemEquipee instanceof Arme)
            cible.perdreVie(((Arme) itemEquipee).getDegats());
    }
}