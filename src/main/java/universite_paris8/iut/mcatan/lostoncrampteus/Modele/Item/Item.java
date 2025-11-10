// Modele/Item/Item.java
package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Strategy.StrategieAttaque;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Strategy.StrategieUtilisation;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Strategy.AttaqueNulleStrategy;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Strategy.UtilisationNulleStrategy;

/**
 * Classe abstraite représentant un item dans le jeu.
 * Contient maintenant les propriétés physiques de base.
 */
public abstract class Item {
    private String nom;
    private int stackLimit;
    private IntegerProperty tailleStack;

    // Propriétés physiques (pour les items au sol)
    protected double posX = 0;
    protected double posY = 0;
    protected double velocityY = 0;
    protected double width = 22;  // Taille par défaut pour les items au sol
    protected double height = 22;

    // Pattern Strategy
    protected StrategieAttaque strategieAttaque;
    protected StrategieUtilisation strategieUtilisation;

    public Item(String nom, int stackLimit, int tailleStack){
        this.nom = nom;
        this.stackLimit = stackLimit;
        this.tailleStack = new SimpleIntegerProperty(tailleStack);

        // Stratégies par défaut
        this.strategieAttaque = new AttaqueNulleStrategy();
        this.strategieUtilisation = new UtilisationNulleStrategy();
    }

    // ==================== MÉTHODES PHYSIQUES ====================

    /**
     * Vérifie si cet item est affecté par la gravité quand il est au sol
     */
    public boolean isAffectedByGravity() {
        return false; // Par défaut, les items dans l'inventaire ne sont pas affectés
    }

    /**
     * Échelle de gravité (1.0 = normale)
     */
    public double getGravityScale() {
        return 1.0;
    }

    /**
     * Met à jour la physique de l'item (pour les items au sol)
     */
    public void updatePhysics(Monde monde) {
        // À implémenter dans les sous-classes si nécessaire
    }

    // Getters pour les propriétés physiques
    public double getPosX() { return posX; }
    public double getPosY() { return posY; }
    public double getVelocityY() { return velocityY; }
    public double getWidth() { return width; }
    public double getHeight() { return height; }

    // Setters pour les propriétés physiques
    public void setPosX(double posX) { this.posX = posX; }
    public void setPosY(double posY) { this.posY = posY; }
    public void setVelocityY(double velocityY) { this.velocityY = velocityY; }
    public void setWidth(double width) { this.width = width; }
    public void setHeight(double height) { this.height = height; }

    // ==================== MÉTHODES EXISTANTES ====================

    public void setStrategieAttaque(StrategieAttaque strategie) {
        this.strategieAttaque = strategie;
    }

    public void setStrategieUtilisation(StrategieUtilisation strategie) {
        this.strategieUtilisation = strategie;
    }

    public void attaquer(Monde monde, int tileX, int tileY) {
        strategieAttaque.attaquer(monde, tileX, tileY);
    }

    public void utiliser(Monde monde, int tileX, int tileY) {
        strategieUtilisation.utiliser(monde, tileX, tileY);
    }

    public String getNom(){
        return this.nom;
    }

    public boolean stackPlein(){
        return tailleStack.getValue() == stackLimit;
    }

    public boolean peutEtreStacke(){
        return stackLimit > 1;
    }

    public int getStackLimit(){
        return this.stackLimit;
    }

    public IntegerProperty getTailleStackProperty() {
        return tailleStack;
    }

    public int getTailleStack(){
        return tailleStack.getValue();
    }

    public void augmenterStack(){
        if (this.tailleStack.getValue() < this.stackLimit) {
            this.tailleStack.set(this.tailleStack.getValue() + 1);
        }
    }

    public void decrementerStack(){
        setTailleStack(this.tailleStack.getValue() - 1);
    }

    public void setTailleStack(int tailleStack){
        this.tailleStack.set(tailleStack);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}