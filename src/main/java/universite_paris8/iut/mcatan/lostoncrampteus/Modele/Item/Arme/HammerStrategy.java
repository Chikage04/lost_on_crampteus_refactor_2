package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Joueur;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Terrain;

import static universite_paris8.iut.mcatan.lostoncrampteus.Controller.Constants.GameConstants.TILE_SIZE;

import java.util.ArrayList;

public class HammerStrategy implements AttackStrategy {

    @Override
    public ArrayList<int[]> attack(Arme arme, int tileX, int tileY) {
        Monde monde = Monde.getInstance();
        Joueur joueur = monde.getJoueur();
        Terrain terrain = monde.getTerrain();

        double joueurCenterX = joueur.getPosX() + 15;
        double joueurCenterY = joueur.getPosY() + 15;

        double tileCenterX = tileX * TILE_SIZE + TILE_SIZE / 2.0;
        double tileCenterY = tileY * TILE_SIZE + TILE_SIZE / 2.0;

        double dx = tileCenterX - joueurCenterX;
        double dy = tileCenterY - joueurCenterY;

        ArrayList<int[]> affected = new ArrayList<>();

        joueur.casserTile(terrain, tileX, tileY);
        affected.add(new int[]{tileX, tileY});

        if (Math.abs(dx) > Math.abs(dy)) {
            if (tileY - 1 >= 0) {
                joueur.casserTile(terrain, tileX, tileY - 1);
                affected.add(new int[]{tileX, tileY - 1});
            }
            if (tileY + 1 < terrain.getMap().length) {
                joueur.casserTile(terrain, tileX, tileY + 1);
                affected.add(new int[]{tileX, tileY + 1});
            }
        } else {
            if (tileX - 1 >= 0) {
                joueur.casserTile(terrain, tileX - 1, tileY);
                affected.add(new int[]{tileX - 1, tileY});
            }
            if (tileX + 1 < terrain.getMap()[0].length) {
                joueur.casserTile(terrain, tileX + 1, tileY);
                affected.add(new int[]{tileX + 1, tileY});
            }
        }

        return affected;
    }
}
