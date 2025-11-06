package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Joueur;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Terrain;

import static universite_paris8.iut.mcatan.lostoncrampteus.Controller.Constants.GameConstants.TILE_SIZE;

public class HammerStrategy implements AttackStrategy {

    @Override
    public void attack(Arme arme, int tileX, int tileY) {
        Monde monde = Monde.getInstance();
        Joueur joueur = monde.getJoueur();
        Terrain terrain = monde.getTerrain();

        double joueurCenterX = joueur.getPosX() + 15;
        double joueurCenterY = joueur.getPosY() + 15;

        double tileCenterX = tileX * TILE_SIZE + TILE_SIZE / 2.0;
        double tileCenterY = tileY * TILE_SIZE + TILE_SIZE / 2.0;

        double dx = tileCenterX - joueurCenterX;
        double dy = tileCenterY - joueurCenterY;

        joueur.casserTile(terrain, tileX, tileY);

        if (Math.abs(dx) > Math.abs(dy)) {
            if (tileY - 1 >= 0) joueur.casserTile(terrain, tileX, tileY - 1);
            if (tileY + 1 < terrain.getMap().length) joueur.casserTile(terrain, tileX, tileY + 1);
        } else {
            if (tileX - 1 >= 0) joueur.casserTile(terrain, tileX - 1, tileY);
            if (tileX + 1 < terrain.getMap()[0].length) joueur.casserTile(terrain, tileX + 1, tileY);
        }
    }
}

