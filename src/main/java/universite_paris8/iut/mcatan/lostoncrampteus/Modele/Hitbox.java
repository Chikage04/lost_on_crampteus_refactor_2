package universite_paris8.iut.mcatan.lostoncrampteus.Modele;

import static universite_paris8.iut.mcatan.lostoncrampteus.Controller.Constants.GameConstants.TILE_SIZE;

public class Hitbox {

    private double x;
    private double y;
    private double largeur;
    private double hauteur;

    public Hitbox(double x, double y, double largeur, double hauteur) {
        this.x = x;
        this.y = y;
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    public boolean colision(Hitbox autreHitbox) {
        return this.x < autreHitbox.x + autreHitbox.largeur && this.x + this.largeur > autreHitbox.x && this.y < autreHitbox.y + autreHitbox.hauteur && this.y + this.hauteur > autreHitbox.y;
    }

    public boolean couvreTuile(int tuileX, int tuileY){

        double tuilePosX = tuileX * TILE_SIZE;
        double tuilePosY = tuileY * TILE_SIZE;

        return (this.x == tuilePosX && this.y == tuilePosY && this.largeur == TILE_SIZE && this.hauteur == TILE_SIZE);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getLargeur() {
        return largeur;
    }

    public double getHauteur() {
        return hauteur;
    }
}
