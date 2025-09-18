package universite_paris8.iut.mcatan.lostoncrampteus.Vue;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Joueur;

public class Camera {

    private Monde monde;
    private Pane gamePane;
    private TilePane inventaire;
    private ProgressBar vie;
    private Label stackItem;
    private SplitMenuButton craft;
    private double viewportWidth;
    private double viewportHeight;

    public Camera(Monde monde, Pane gamePane, Label stackItem, TilePane inventaire, ProgressBar vie , SplitMenuButton craft, double viewportWidth, double viewportHeight) {
        this.monde = monde;
        this.gamePane = gamePane;
        this.inventaire = inventaire;
        this.stackItem = stackItem;
        this.vie = vie;
        this.craft = craft;
        this.viewportWidth = viewportWidth;
        this.viewportHeight = viewportHeight;
        init();
    }

    private void init() {
        Joueur joueur = monde.getJoueur();

        ChangeListener<Number> listener = new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Number> obs, Number oldVal, Number newVal) {
                updateCameraPosition(joueur.getPosX(), joueur.getPosY());
            }
        };

        joueur.getPosXProperty().addListener(listener);
        joueur.getPosYProperty().addListener(listener);


        updateCameraPosition(joueur.getPosX(), joueur.getPosY());
    }

    private void updateCameraPosition(double playerX, double playerY) {
        double offsetX = playerX - viewportWidth / 2;
        double offsetY = playerY - viewportHeight / 2;


        double maxOffsetX = monde.getTerrain().getMapWidth() - viewportWidth;
        double maxOffsetY = monde.getTerrain().getMapHeight() - viewportHeight;

        if (offsetX < 0) offsetX = 0;
        else if (offsetX > maxOffsetX) offsetX = maxOffsetX;

        if (offsetY < 0) offsetY = 0;
        else if (offsetY > maxOffsetY) offsetY = maxOffsetY;


        gamePane.setTranslateX(-offsetX);
        gamePane.setTranslateY(-offsetY);
        inventaire.setTranslateX(-gamePane.getTranslateX());
        inventaire.setTranslateY(-gamePane.getTranslateY());
        stackItem.setTranslateX(-gamePane.getTranslateX() + 120);
        stackItem.setTranslateY(-gamePane.getTranslateY() + 20);
        vie.setTranslateX(-gamePane.getTranslateX() + 75);
        vie.setTranslateY(-gamePane.getTranslateY() + 10);
        craft.setTranslateX(-gamePane.getTranslateX() - 80);
        craft.setTranslateY(-gamePane.getTranslateY() + 20);
    }
}