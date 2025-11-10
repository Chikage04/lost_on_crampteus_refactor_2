package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Hitbox;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Map.Terrain;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Physics.PhysicalEntity;

/**
 * Classe abstraite représentant un personnage dans le jeu
 * Implémente PhysicalEntity pour la gestion centralisée de la physique
 */
public abstract class Acteur implements PhysicalEntity {
    private DoubleProperty pv;
    private int id;
    private DoubleProperty posX;
    private DoubleProperty posY;
    protected double velocityX;
    protected double velocityY;
    private double width;
    private double height;
    protected Monde monde;

    public Acteur(double pv, Monde monde) {
        this.pv = new SimpleDoubleProperty(1);
        this.id = Terrain.compteur++;
        this.posX = new SimpleDoubleProperty(0);
        this.posY = new SimpleDoubleProperty(0);
        this.velocityX = 0;
        this.velocityY = 0;
        this.width = 30;
        this.height = 30;
        this.monde = monde;
    }

    // ==================== MÉTHODES MÉTIER ====================

    public int getId(){
        return this.id;
    }

    public DoubleProperty getPvProperty(){
        return this.pv;
    }

    public void setPv(double pv){
        if (pv >= 0) {
            this.pv.setValue(pv);
        }
        else {
            this.pv.setValue(0);
        }
    }

    public boolean estVivant(){
        return this.pv.getValue() > 0;
    }

    public void perdreVie(double pv){
        if (getPvProperty().getValue() > 0)
            setPv(getPvProperty().getValue()  - pv);
    }

    public void ajouterVie(double pv){
        setPv(getPvProperty().getValue() + pv);
    }

    /**
     * Méthode utilitaire pour vérifier si une action peut être exécutée
     */
    protected boolean peutAgir() {
        return estVivant();
    }

    // ==================== PROPRIÉTÉS PHYSIQUES ====================

    public DoubleProperty getPosXProperty() {
        return posX;
    }

    public DoubleProperty getPosYProperty() {
        return posY;
    }

    @Override
    public double getPosX(){
        return this.posX.getValue();
    }

    @Override
    public double getPosY(){
        return this.posY.getValue();
    }

    public void setPosX(double posX) {
        this.posX.set(posX);
    }

    @Override
    public void setPosY(double posY) {
        this.posY.set(posY);
    }

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    @Override
    public double getVelocityY() {
        return velocityY;
    }

    @Override
    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public Hitbox getHitbox() {
        return new Hitbox(getPosX(), getPosY(), width, height);
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    public Monde getMonde() {
        return monde;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    // ==================== IMPLÉMENTATION PHYSICALENTITY ====================

    @Override
    public boolean isAffectedByGravity() {
        return true; // Tous les Acteurs sont affectés par la gravité
    }

    @Override
    public double getGravityScale() {
        return 1.0; // Gravité normale par défaut
    }


    @Override
    public String toString() {
        return getClass().getSimpleName() + "{id=" + id + ", pv=" + pv.getValue() + "}";
    }
}