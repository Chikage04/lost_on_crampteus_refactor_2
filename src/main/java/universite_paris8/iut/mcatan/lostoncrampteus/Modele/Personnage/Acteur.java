package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Hitbox;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Terrain;

public abstract class Acteur {

    private DoubleProperty pv;
    private int id;
    private DoubleProperty posX;
    private DoubleProperty posY;
    protected double velocityX;
    protected double velocityY;

    private Hitbox hitbox;
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

        this.hitbox = new Hitbox(getPosX(), getPosY(), width, height);

        this.monde = monde;
    }

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

    public DoubleProperty getPosXProperty() {
        return posX;
    }

    public DoubleProperty getPosYProperty() {
        return posY;
    }

    public double getPosX(){
        return this.posX.getValue();
    }
    
    public double getPosY(){
        return this.posY.getValue();
    }

    public void setPosX(double posX) {
        this.posX.set(posX);
    }
    
    public void setPosY(double posY) {
        this.posY.set( posY);
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public Hitbox getHitbox() {
        return new Hitbox(getPosX(), getPosY(), width, height); //mettre a jour la hitbox du jour a chaque fois que la position change
    }
    public double getWidth() {
        return width;
    }

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

    public void perdreVie(double pv){
        if (getPvProperty().getValue() > 0)
            setPv(getPvProperty().getValue()  - pv);
    }

    public void ajouterVie(double pv){
        setPv(getPvProperty().getValue() + pv);
    }

    public void appliquerGravite(){
        double nextY = getPosY() + velocityY;

        velocityY += Monde.GRAVITY;

        Hitbox testY = new Hitbox(getPosX(), nextY, getWidth(), getHeight());
        if (!monde.getTerrain().checkCollision(testY)) {
            setPosY(nextY);
        } else {
            if (velocityY > 0) {
                velocityY = 0;
            }
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }


}