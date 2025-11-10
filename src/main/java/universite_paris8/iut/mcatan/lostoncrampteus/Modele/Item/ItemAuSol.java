package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Physics.PhysicalEntity;

public class ItemAuSol implements PhysicalEntity {
    private final Item item;
    private final Monde monde;
    private final DoubleProperty posX = new SimpleDoubleProperty();
    private final DoubleProperty posY = new SimpleDoubleProperty();
    private final double width = 22;
    private final double height = 22;
    private double velocityY = 0;

    public ItemAuSol(Item item, double posX, double posY, Monde monde) {
        this.item = item;
        this.monde = monde;
        this.posX.set(posX);
        this.posY.set(posY);
    }

    // Getters existants
    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public Item getItem() {
        return item;
    }

    public double getPosX() {
        return posX.get();
    }

    public double getPosY() {
        return posY.get();
    }

    public void setPosY(double y) {
        posY.set(y);
    }

    public DoubleProperty posXProperty() {
        return posX;
    }

    public DoubleProperty posYProperty() {
        return posY;
    }

    // Implémentation de PhysicalEntity
    @Override
    public double getVelocityY() {
        return velocityY;
    }

    @Override
    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    @Override
    public boolean isAffectedByGravity() {
        return true;
    }

    @Override
    public double getGravityScale() {
        return 1.0;
    }

    // Ajouter setPosX pour compléter l'interface
    public void setPosX(double x) {
        posX.set(x);
    }
}