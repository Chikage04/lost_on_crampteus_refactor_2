package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Hitbox;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Terrain;

// il faudra que itemAuSol extend la classe Acteur qui soit rename en Entite
public class ItemAuSol {
    private final Item item;
    private final DoubleProperty posX = new SimpleDoubleProperty();
    private final DoubleProperty posY = new SimpleDoubleProperty();
    private final double width = 22;
    private final double height = 22;
    private double velocityY = 0;


    public ItemAuSol(Item item, double posX, double posY) {
        this.item = item;
        this.posX.set(posX);
        this.posY.set(posY);
    }

    //temporaire
    public void updateGraviteItem() {
        double nextY = getPosY() + velocityY;

        Monde.getInstance();
        velocityY += Monde.GRAVITY;

        Hitbox testY = new Hitbox(getPosX(), nextY, getWidth(), getHeight());
        if (!Terrain.getInstance().checkCollision(testY)) {
            setPosY(nextY);
        } else {
            if (velocityY > 0) {
                velocityY = 0;
            }
        }
    }

    public double getHeight() {
        return height;
    }
    public double getWidth() {
        return width;
    }
    public Item getItem() { return item; }
    public double getPosX() { return posX.get(); }
    public double getPosY() { return posY.get(); }
    public void setPosY(double y) { posY.set(y); }
    public DoubleProperty posXProperty() { return posX; }
    public DoubleProperty posYProperty() { return posY; }


}