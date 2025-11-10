
package universite_paris8.iut.mcatan.lostoncrampteus;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import universite_paris8.iut.mcatan.lostoncrampteus.Controller.Controleur;
import universite_paris8.iut.mcatan.lostoncrampteus.Controller.KeyEventHandler;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Map.Terrain;
import universite_paris8.iut.mcatan.lostoncrampteus.Vue.Camera;
import universite_paris8.iut.mcatan.lostoncrampteus.Utils.GameConstants;

public class Application extends javafx.application.Application {


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Vue.fxml"));
        Pane root = fxmlLoader.load();

        Scene scene = new Scene(root, GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT);

        Controleur controleur = fxmlLoader.getController();

        Terrain terrain = controleur.getVueTerrain().getTerrain();

        stage.setTitle("Lost On Crampteus");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setWidth(GameConstants.WINDOW_WIDTH);
        stage.setHeight(GameConstants.WINDOW_HEIGHT);
        stage.show();


        Pane gamePane = controleur.getGamePane();


        Camera camera = new Camera(controleur.getMonde(), gamePane, controleur.getNbStackItem(),controleur.getInventaire(),controleur.getVie(), controleur.getCraft(),GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT);

        KeyEventHandler keyEventHandler = new KeyEventHandler(controleur);

        scene.setOnKeyPressed(keyEventHandler);
        scene.setOnKeyReleased(keyEventHandler);

    }

    public static void main(String[] args) {
        launch();
    }
}
