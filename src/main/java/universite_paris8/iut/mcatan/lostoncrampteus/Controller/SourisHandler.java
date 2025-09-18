package universite_paris8.iut.mcatan.lostoncrampteus.Controller;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;
import universite_paris8.iut.mcatan.lostoncrampteus.Vue.VueTerrain;

import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SourisHandler {

    private final Monde monde;
    private final VueTerrain vueTerrain;
    private final Rectangle tuileSurvolee;

    private final double TILE_SIZE = 32;
    private final double RANGE_MAX = 128.0;
    private final double RAYON_PROXIMITE = 32.0;


    public SourisHandler(Pane gamePane, Monde monde, VueTerrain vueTerrain) {
        this.monde = monde;
        this.vueTerrain = vueTerrain;

        // Rectangle de survol
        tuileSurvolee = new Rectangle(TILE_SIZE, TILE_SIZE);
        tuileSurvolee.setFill(Color.TRANSPARENT);
        tuileSurvolee.setStrokeWidth(1);
        tuileSurvolee.setVisible(false);
        gamePane.getChildren().add(tuileSurvolee);

        setupMouseEvents(gamePane);
    }

    private void setupMouseEvents(Pane gamePane) {
        gamePane.setOnMouseMoved(event -> gererMouvementSouris(event));
        gamePane.setOnMouseClicked(event -> gererClicSouris(event));
    }

    private void gererMouvementSouris(MouseEvent event) {
        double sourisX = event.getX();
        double sourisY = event.getY();

        int tileX = (int) (sourisX / TILE_SIZE);
        int tileY = (int) (sourisY / TILE_SIZE);

        if (inBounds(tileX, tileY)){
            tuileSurvolee.setVisible(true);
            tuileSurvolee.setX(tileX * TILE_SIZE);
            tuileSurvolee.setY(tileY * TILE_SIZE);

            double dist = distanceToPlayer(tileX, tileY);

            if (dist <= RANGE_MAX) {
                tuileSurvolee.setStroke(Color.GHOSTWHITE);
            }
            else{
                tuileSurvolee.setVisible(false);
            }
        }else {
            tuileSurvolee.setVisible(false);
        }
    }

    private void gererClicSouris(MouseEvent event) {
        int tileX = (int) (event.getX() / TILE_SIZE);
        int tileY = (int) (event.getY() / TILE_SIZE);

        if (estDansLesLimites(tileX, tileY) && monde.getJoueur().getItemEquipee() != null) {
            double distance = distanceToPlayer(tileX, tileY);

            if (distance <= RANGE_MAX) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    gererClicGauche(tileX, tileY);
                } else if (event.getButton() == MouseButton.SECONDARY) {
                    gererClicDroit(tileX, tileY, distance);
                }
            } else {
                System.out.println("Trop loin pour interagir !");
            }
        }
    }

    private void gererClicGauche(int tileX, int tileY) {
        monde.getJoueur().getItemEquipee().attaquer(tileX, tileY);
        vueTerrain.updateTile(tileX, tileY);
    }

    private void gererClicDroit(int tileX, int tileY, double distance) {
        boolean estTropProche = (distance < RAYON_PROXIMITE);
        boolean estCaseOccupee = estTuileSolide(tileX, tileY);

        if (!estTropProche) {
            if (!estCaseOccupee) {
                monde.getJoueur().getItemEquipee().utiliser(monde,tileX, tileY);
                vueTerrain.updateTile(tileX,tileY);
            } else {
                System.out.println("Case déjà occupée !");
            }
        } else {
            System.out.println("Trop proche du joueur !");
        }
    }

    private boolean estDansLesLimites(int x, int y) {
        int[][] map = monde.getTerrain().getMap();
        return x >= 0 && x < map[0].length && y >= 0 && y < map.length;
    }


    private boolean estTuileSolide(int tileX, int tileY) {
        int tileValue = monde.getTerrain().getMap()[tileY][tileX];
        return monde.getTerrain().estTuilleSolide(tileValue);
    }



    private boolean inBounds(int x, int y) {
        int[][] map = monde.getTerrain().getMap();
        return x >= 0 && x < map[0].length && y >= 0 && y < map.length;
    }

    private double distanceToPlayer(int tileX, int tileY) {
        double joueurX = monde.getJoueur().getPosX() + 15;
        double joueurY = monde.getJoueur().getPosY() + 15;

        double tileCenterX = tileX * TILE_SIZE + TILE_SIZE / 2.0;
        double tileCenterY = tileY * TILE_SIZE + TILE_SIZE / 2.0;

        double dx = tileCenterX - joueurX;
        double dy = tileCenterY - joueurY;

        return Math.sqrt(dx * dx + dy * dy);
    }
}