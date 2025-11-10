package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Inventaire;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Item;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Armure;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme.Arme;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme.Pioche;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc.Bloc;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Map.BlocFactory;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Map.Terrain;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;

/**
 * Classe représentant le joueur - (Data Model seulement)
 * Ne contient que l'état, pas de logique comportementale
 */
public class Joueur extends Acteur {
    private Item itemEquipee;
    private Armure armureEquipee;
    private Inventaire inventaire;

    public Joueur(int pv, Monde monde) {
        super(pv, monde);
        setHeight(60);
        setWidth(28);
        this.inventaire = new Inventaire(10, this);
    }

    // ==================== GETTERS/SETTERS SIMPLES ====================

    public Item getItemEquipee() {
        return itemEquipee;
    }

    public void setItemEquipee(Item itemEquipee) {
        this.itemEquipee = itemEquipee;
    }

    public Armure getArmureEquipee() {
        return armureEquipee;
    }

    public void setArmureEquipee(Armure armureEquipee) {
        this.armureEquipee = armureEquipee;
    }

    public Inventaire getInventaire() {
        return inventaire;
    }

    // Velocity accessors for physics
    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    // ==================== MISSING METHODS - ADDED ====================

    /**
     * Casse une tuile à la position donnée
     * Cette méthode devrait normalement être dans un service, mais garde la compatibilité
     */
    public void casserTile(int tileX, int tileY) {
        if (!estVivant() || itemEquipee == null) {
            return;
        }

        Terrain terrain = monde.getTerrain();
        int[][] map = terrain.getMap();

        // Vérifier si la position est valide
        if (tileY < 0 || tileY >= map.length || tileX < 0 || tileX >= map[0].length) {
            return;
        }

        int tileType = map[tileY][tileX];

        // Vérifier si c'est un bloc minable
        if (!BlocFactory.isMineableBlock(tileType)) {
            return;
        }

        // Créer le bloc et appliquer les dégâts
        Bloc bloc = BlocFactory.createBloc(tileType);
        if (bloc != null) {
            int degats = 0;
            if (itemEquipee instanceof Pioche) {
                degats = ((Pioche) itemEquipee).getDegatsMinage();
            } else if (itemEquipee instanceof Arme) {
                degats = ((Arme) itemEquipee).getDegats();
            }

            bloc.perdreDurabilite(degats);

            if (bloc.estDetruit()) {
                // Détruire le bloc
                map[tileY][tileX] = 0;
                terrain.mettreAJourCollisionTuile(tileX, tileY);

                // Droper l'item
                monde.ajouterItemAuSol(bloc, tileX * 32, tileY * 32);
                System.out.println("Bloc détruit à (" + tileX + ", " + tileY + ")");
            }
        }
    }

    /**
     * Place une tuile à la position donnée
     */
    public void placerTile(int tileX, int tileY, int tileId) {
        if (!estVivant() || itemEquipee == null) {
            return;
        }

        Terrain terrain = monde.getTerrain();
        int[][] map = terrain.getMap();

        // Vérifier si la position est valide
        if (tileY < 0 || tileY >= map.length || tileX < 0 || tileX >= map[0].length) {
            return;
        }

        // Vérifier si la case est vide
        if (map[tileY][tileX] != 0) {
            return;
        }

        // Placer le bloc
        map[tileY][tileX] = tileId;
        terrain.mettreAJourCollisionTuile(tileX, tileY);

        // Décrémenter le stack
        itemEquipee.decrementerStack();
        if (itemEquipee.getTailleStack() == 0) {
            inventaire.enleverItem(itemEquipee);
            setItemEquipee(null);
        }

        System.out.println("Bloc placé à (" + tileX + ", " + tileY + ")");
    }

    /**
     * Craft un item
     */
    public boolean craftItem(String nomItem) {
        if (!estVivant() || nomItem == null) {
            return false;
        }

        Item result = monde.getCraft().craft(nomItem, inventaire);
        if (result != null) {
            inventaire.ajouterItem(result);
            System.out.println("Item crafté: " + nomItem);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Joueur{posX=%.1f, posY=%.1f, pv=%.1f, item=%s}",
                getPosX(), getPosY(), getPvProperty().getValue(),
                getItemEquipee() != null ? getItemEquipee().getNom() : "aucun");
    }
}