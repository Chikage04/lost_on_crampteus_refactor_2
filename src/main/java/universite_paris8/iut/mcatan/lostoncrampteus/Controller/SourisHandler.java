package universite_paris8.iut.mcatan.lostoncrampteus.Controller;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Services.PlayerService;
import universite_paris8.iut.mcatan.lostoncrampteus.Vue.VueTerrain;

import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SourisHandler {

    private final PlayerService playerService;
    private final VueTerrain vueTerrain;
    private final Rectangle tuileSurvolee;

    private static final double TILE_SIZE = 32;

    public SourisHandler(Pane gamePane, Monde monde, VueTerrain vueTerrain, PlayerService playerService) {
        this.playerService = playerService;
        this.vueTerrain = vueTerrain;

        tuileSurvolee = new Rectangle(TILE_SIZE, TILE_SIZE);
        tuileSurvolee.setFill(Color.TRANSPARENT);
        tuileSurvolee.setStrokeWidth(1);
        tuileSurvolee.setVisible(false);
        gamePane.getChildren().add(tuileSurvolee);

        setupMouseEvents(gamePane);
    }

    private void setupMouseEvents(Pane gamePane) {
        gamePane.setOnMouseMoved(this::gererMouvementSouris);
        gamePane.setOnMouseClicked(this::gererClicSouris);
    }

    private void gererMouvementSouris(MouseEvent event) {
        int tileX = (int) (event.getX() / TILE_SIZE);
        int tileY = (int) (event.getY() / TILE_SIZE);

        boolean canInteract = playerService.canInteractWith(tileX, tileY);

        if (canInteract) {
            afficherSurvol(tileX, tileY);
        } else {
            cacherSurvol();
        }
    }

    private void gererClicSouris(MouseEvent event) {
        int tileX = (int) (event.getX() / TILE_SIZE);
        int tileY = (int) (event.getY() / TILE_SIZE);

        boolean success = false;

        if (event.getButton() == MouseButton.PRIMARY) {
            success = playerService.attackAt(tileX, tileY);
        } else if (event.getButton() == MouseButton.SECONDARY) {
            success = playerService.useItemAt(tileX, tileY);
        }

        if (success) {
            vueTerrain.updateTile(tileX, tileY);
        }
    }

    private void afficherSurvol(int tileX, int tileY) {
        tuileSurvolee.setVisible(true);
        tuileSurvolee.setX(tileX * TILE_SIZE);
        tuileSurvolee.setY(tileY * TILE_SIZE);
        tuileSurvolee.setStroke(Color.GHOSTWHITE);
    }

    private void cacherSurvol() {
        tuileSurvolee.setVisible(false);
    }
}