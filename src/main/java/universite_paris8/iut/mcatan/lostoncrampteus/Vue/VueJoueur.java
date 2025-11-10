package universite_paris8.iut.mcatan.lostoncrampteus.Vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;
import universite_paris8.iut.mcatan.lostoncrampteus.Utils.ResourceManager;

import java.util.HashSet;

public class VueJoueur {

    private Monde monde;
    private ImageView imageView;

    private Image[] framesStatiques;
    private Image[] gaucheFrames;
    private Image[] droiteFrames;

    private int indiceFrame = 0;
    private int compteurFrame = 0;
    private int frameDelay = 5;

    public VueJoueur(Monde monde, Rectangle player){
        this.monde = monde;

        Pane parent = (Pane) player.getParent();
        this.imageView = new ImageView();
        this.imageView.setFitWidth(32);
        this.imageView.setFitHeight(64);

        imageView.translateXProperty().bind(monde.getJoueur().getPosXProperty());
        imageView.translateYProperty().bind(monde.getJoueur().getPosYProperty());

        parent.getChildren().remove(player);
        parent.getChildren().add(imageView);

        // Charger les images du joueur avec le ResourceManager
        ResourceManager rm = ResourceManager.getInstance();

        framesStatiques = new Image[] {
                rm.getImage("joueur/joueur_idle2.png"),
                rm.getImage("joueur/joueur_idle3.png")
        };

        gaucheFrames = new Image[]{
                rm.getImage("joueur/joueur_left1.png"),
                rm.getImage("joueur/joueur_left2.png"),
                rm.getImage("joueur/joueur_left3.png"),
                rm.getImage("joueur/joueur_left4.png")
        };

        droiteFrames = new Image[]{
                rm.getImage("joueur/joueur_right1.png"),
                rm.getImage("joueur/joueur_right2.png"),
                rm.getImage("joueur/joueur_right3.png"),
                rm.getImage("joueur/joueur_right4.png")
        };

        imageView.setImage(framesStatiques[0]);
    }

    public void updateAnimation(HashSet<KeyCode> activeKeys){
        Image[] framesEnCours;

        if (activeKeys.contains(KeyCode.D)){
            framesEnCours = droiteFrames;
        } else if (activeKeys.contains(KeyCode.Q)) {
            framesEnCours = gaucheFrames;
        }
        else {
            framesEnCours = framesStatiques;
        }

        compteurFrame ++;
        if (compteurFrame >= frameDelay){
            compteurFrame = 0;
            indiceFrame = (indiceFrame + 1) % framesEnCours.length;
            imageView.setImage(framesEnCours[indiceFrame]);
        }
    }

    public ImageView getImageView(){
        return imageView;
    }
}