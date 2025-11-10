package universite_paris8.iut.mcatan.lostoncrampteus.Controller;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Services.*;
import universite_paris8.iut.mcatan.lostoncrampteus.Vue.VueTerrain;
import universite_paris8.iut.mcatan.lostoncrampteus.Vue.VueUI.VueCraft;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;

/**
 * Simplified main controller
 * Now only coordinates between services and views
 */
public class Controleur implements Initializable {

    private static final boolean DEBUG_MODE = true;

    @FXML private SplitMenuButton craft;
    @FXML private TilePane tilePane;
    @FXML private TilePane inventaire;
    @FXML private Label nbStackItem;
    @FXML private ProgressBar vie;
    @FXML private Rectangle playerVue;
    @FXML private Pane gamePane;

    // Model
    private Monde monde;

    // Services (only 4 now!)
    private PlayerService playerService;
    private InventoryService inventoryService;
    private GameService gameService;

    // View & Controller helpers
    private ViewInitializer viewInitializer;
    private AnimationManager animationManager;
    private HealthBarManager healthBarManager;
    private DebugManager debugManager;
    private SourisHandler sourisHandler;

    private HashSet<KeyCode> activeKeys = new HashSet<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeModel();
        initializeServices();
        initializeManagers();
        initializeViews();
        initializeInputHandlers();
        startAnimation();
    }

    private void initializeModel() {
        monde = new Monde();
    }

    private void initializeServices() {
        playerService = new PlayerService(monde);
        inventoryService = new InventoryService(monde);
        gameService = new GameService(monde, inventoryService);
    }

    private void initializeManagers() {
        healthBarManager = new HealthBarManager(vie, monde.getJoueur());
        debugManager = new DebugManager(monde.getJoueur(), DEBUG_MODE);
    }

    private void initializeViews() {
        viewInitializer = new ViewInitializer(
                monde, gamePane, tilePane, inventaire,
                nbStackItem, vie, craft, playerVue,
                playerService, inventoryService
        );
        viewInitializer.initializeAllViews(this);
    }

    private void initializeInputHandlers() {
        sourisHandler = new SourisHandler(
                gamePane, monde, viewInitializer.getVueTerrain(), playerService
        );
    }

    private void startAnimation() {
        animationManager = new AnimationManager(
                monde,
                viewInitializer.getVueStackItem(),
                viewInitializer.getVueJoueur(),
                healthBarManager,
                debugManager,
                playerService,
                inventoryService,
                activeKeys
        );
        animationManager.start();
    }

    // ==================== INPUT PROCESSING ====================

    public void processInput() {
        handleHorizontalMovement();
        handleJump();
    }

    private void handleHorizontalMovement() {
        boolean movingLeft = activeKeys.contains(KeyCode.Q);
        boolean movingRight = activeKeys.contains(KeyCode.D);

        if (movingLeft == movingRight) {
            playerService.stopMovement();
            return;
        }

        if (movingRight) {
            playerService.moveRight();
        } else {
            playerService.moveLeft();
        }
    }

    private void handleJump() {
        if (activeKeys.contains(KeyCode.SPACE)) {
            playerService.jump();
        }
    }

    // ==================== GETTERS ====================

    public HashSet<KeyCode> getActiveKeys() {
        return activeKeys;
    }

    public VueTerrain getVueTerrain() {
        return viewInitializer.getVueTerrain();
    }

    public Monde getMonde() {
        return monde;
    }

    public PlayerService getPlayerService() {
        return playerService;
    }

    public InventoryService getInventoryService() {
        return inventoryService;
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
        return viewInitializer.getVueCraft();
    }

    public Label getNbStackItem() {
        return nbStackItem;
    }
}