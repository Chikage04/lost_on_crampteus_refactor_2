package universite_paris8.iut.mcatan.lostoncrampteus.Modele;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Joueur;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Pnj;

import static universite_paris8.iut.mcatan.lostoncrampteus.Controller.Constants.GameConstants.TILE_SIZE;

public class AlgoRecherche {

    public static void DeplacementTerrestre(Pnj pnj, Monde monde, double vitesse, int range) {
        Joueur joueur = monde.getJoueur();
        Terrain terrain = monde.getTerrain();

        // mouvement horizontal
        int direction;
        if (!(joueur.getHitbox().colision(pnj.getHitbox()) || Math.abs(joueur.getPosX() - pnj.getPosX()) > range || Math.abs(joueur.getPosY() - pnj.getPosY()) > 256) ) {
            if (joueur.getPosX() > pnj.getPosX()) {
                direction = 1;
            }
            else {
                direction = -1;
            }
        }
        else {
            direction = 0;
        }

        if (pnj.getPosY() > terrain.getMapHeight()) {
            pnj.setPosY(0);
            pnj.setVelocityY(2);
        }

        // mouvement vertical
        int pnjCoordonneeX = (int) (pnj.getPosX() / TILE_SIZE);
        int pnjCoordonneeY = (int) (pnj.getPosY() / TILE_SIZE);

        int adjacentTile = terrain.getMap()[pnjCoordonneeY][pnjCoordonneeX + direction];
        if (adjacentTile == 1 || adjacentTile == 2) {
            Hitbox estAuSol = new Hitbox(pnj.getPosX(), pnj.getPosY() + 2, pnj.getWidth(), pnj.getHeight());
            if (terrain.checkCollision(estAuSol)) {
                pnj.setVelocityY(-8);
            }
        }

        // maj mouvement
        double nextX = pnj.getPosX() + direction * vitesse;

        Hitbox testX = new Hitbox(nextX, pnj.getPosY(), pnj.getWidth(), pnj.getHeight());
        if (!monde.getTerrain().checkCollision(testX)) {
            pnj.setPosX(nextX);
        }

    }
}
