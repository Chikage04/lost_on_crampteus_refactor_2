
package universite_paris8.iut.mcatan.lostoncrampteus;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import universite_paris8.iut.mcatan.lostoncrampteus.Controller.Controleur;
import universite_paris8.iut.mcatan.lostoncrampteus.Controller.KeyEventHandler;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Terrain;
import universite_paris8.iut.mcatan.lostoncrampteus.Vue.Camera;

public class Application extends javafx.application.Application {
    private static final double WINDOW_WIDTH = 800;
    private static final double WINDOW_HEIGHT = 600;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Vue.fxml"));
        Pane root = fxmlLoader.load();

        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        Controleur controleur = fxmlLoader.getController();

        Terrain terrain = controleur.getVueTerrain().getTerrain();

        stage.setTitle("Lost On Crampteus");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setWidth(WINDOW_WIDTH);
        stage.setHeight(WINDOW_HEIGHT);
        stage.show();


        Pane gamePane = controleur.getGamePane();


        Camera camera = new Camera(controleur.getMonde(), gamePane, controleur.getNbStackItem(),controleur.getInventaire(),controleur.getVie(), controleur.getCraft(),WINDOW_WIDTH, WINDOW_HEIGHT);

        KeyEventHandler keyEventHandler = new KeyEventHandler(controleur);

        scene.setOnKeyPressed(keyEventHandler);
        scene.setOnKeyReleased(keyEventHandler);

    }

    public static void main(String[] args) {
        launch();
    }
}
