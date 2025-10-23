
package universite_paris8.iut.mcatan.lostoncrampteus.Controller;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.layout.Pane;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Joueur;
import universite_paris8.iut.mcatan.lostoncrampteus.Vue.*;
import universite_paris8.iut.mcatan.lostoncrampteus.Vue.VueEnnemis.VueGronfleur;
import universite_paris8.iut.mcatan.lostoncrampteus.Vue.VueUI.VueCraft;
import universite_paris8.iut.mcatan.lostoncrampteus.Vue.VueUI.VueInventaire;
import universite_paris8.iut.mcatan.lostoncrampteus.Vue.VueUI.VueStackItem;
import universite_paris8.iut.mcatan.lostoncrampteus.Vue.VueUI.VueVie;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    @FXML
    SplitMenuButton craft;

    @FXML
    TilePane tilePane;

    @FXML
    TilePane inventaire;

    @FXML
    Label nbStackItem;

    @FXML
    ProgressBar vie;

    @FXML
    private Rectangle playerVue;

    private Monde monde;

    private VueJoueur vueJoueur;

    private VueTerrain vueTerrain;

    private VueInventaire vueInventaire;

    private VueStackItem vueStackItem;

    private VueItemAuSol vueItemAuSol;

    private VueVie vueVie;

    private VueCraft vueCraft;

    //temp
    private VueGronfleur vueGronfleur;

    private ArrayList<ImageView> tuilesSolides = new ArrayList<>();

    private HashSet<KeyCode> activeKeys = new HashSet<>();

    private Timeline animationTimeline;

    @FXML
    private Pane gamePane;

    private SourisHandler sourisHandler;

    private ObservateurInventaire observateur;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        monde = Monde.getInstance();
        monde.setJoueur(Joueur.getInstance());

        vueItemAuSol = new VueItemAuSol(gamePane, monde); // faire attention a l'ordre
        vueJoueur = new VueJoueur(playerVue);
        vueTerrain = new VueTerrain(tilePane, tuilesSolides);
        vueStackItem = new VueStackItem(nbStackItem);
        vueTerrain.chargeTiles();
        vueCraft = new VueCraft(craft);

        vueVie = new VueVie(vie);
        vueInventaire = new VueInventaire(inventaire, monde, this);
        vueGronfleur = new VueGronfleur(gamePane);

        for (int i = 0; i <= 2; i++) {
            vueGronfleur.ajouterGronfleur();
            monde.getPnjs().get(i).setPosX((i*64)+200);
        }

        setupAnimation();
        sourisHandler = new SourisHandler(gamePane, vueTerrain);


    }

    private void setupAnimation() {
        animationTimeline = new Timeline(
                new KeyFrame(Duration.millis(16), e -> update())
        );
        animationTimeline.setCycleCount(Timeline.INDEFINITE);
        animationTimeline.play();
    }

    private void update() {
        monde.updateMonde(activeKeys);
        vueStackItem.update();
        vueJoueur.updateAnimation(activeKeys);

        if (activeKeys.contains(KeyCode.H)){
            monde.getJoueur().perdreVie(0.01);
        }
        if (activeKeys.contains(KeyCode.J)){
            if (monde.getJoueur().getPvProperty().getValue() < 1) {
                monde.getJoueur().ajouterVie(0.01);
            }
        }
        if (monde.getJoueur().getPvProperty().getValue() < 0.35){
            vueVie.getVie().setStyle("-fx-border-color: BLACK; -fx-border-radius: 5; -fx-background-insets: 0; -fx-accent: RED;");
        }
        else{
            vueVie.getVie().setStyle("-fx-border-color: BLACK; -fx-border-radius: 5; -fx-background-insets: 0; -fx-accent: LIMEGREEN;");
        }
        if (monde.getJoueur().getPvProperty().getValue() <= 0){
            vueVie.getVie().setStyle("-fx-border-color: BLACK; -fx-border-radius: 5; -fx-background-insets: 0; -fx-accent: TRANSPARENT;");
        }
    }

    public HashSet<KeyCode> getActiveKeys() {
        return activeKeys;
    }

    public VueTerrain getVueTerrain() {
        return vueTerrain;
    }

    public Label getNbStackItem() {
        return nbStackItem;
    }

    public Pane getGamePane() {
        return gamePane;
    }

    public TilePane getInventaire() {
        return inventaire;
    }

    public ProgressBar getVie() {
        return vie;
    }

    public SplitMenuButton getCraft() {
        return craft;
    }

    public VueCraft getVueCraft() {
        return vueCraft;
    }

}
