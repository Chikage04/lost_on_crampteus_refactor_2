package universite_paris8.iut.mcatan.lostoncrampteus.Vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Joueur;

import java.util.HashSet;

public class VueJoueur {

    private ImageView imageView;

    private Image[] framesStatiques;
    private Image[] gaucheFrames;
    private Image[] droiteFrames;

    private int indiceFrame = 0;
    private int compteurFrame = 0;
    private int frameDelay = 5;

    public VueJoueur(Rectangle player){

        Pane parent = (Pane) player.getParent();
        this.imageView = new ImageView();
        this.imageView.setFitWidth(32);
        this.imageView.setFitHeight(64);

        imageView.translateXProperty().bind(Joueur.getInstance().getPosXProperty());
        imageView.translateYProperty().bind(Joueur.getInstance().getPosYProperty());

        parent.getChildren().remove(player);
        parent.getChildren().add(imageView);

        //Chargement d'images
        framesStatiques = new Image[] {
                new Image("file:src/main/resources/universite_paris8/iut/mcatan/lostoncrampteus/Images/joueur/joueur_idle2.png"),
                new Image("file:src/main/resources/universite_paris8/iut/mcatan/lostoncrampteus/Images/joueur/joueur_idle3.png")
        };

        gaucheFrames = new Image[]{
                new Image("file:src/main/resources/universite_paris8/iut/mcatan/lostoncrampteus/Images/joueur/joueur_left1.png"),
                new Image("file:src/main/resources/universite_paris8/iut/mcatan/lostoncrampteus/Images/joueur/joueur_left2.png"),
                new Image("file:src/main/resources/universite_paris8/iut/mcatan/lostoncrampteus/Images/joueur/joueur_left3.png"),
                new Image("file:src/main/resources/universite_paris8/iut/mcatan/lostoncrampteus/Images/joueur/joueur_left4.png")
        };

        droiteFrames = new Image[]{
                new Image("file:src/main/resources/universite_paris8/iut/mcatan/lostoncrampteus/Images/joueur/joueur_right1.png"),
                new Image("file:src/main/resources/universite_paris8/iut/mcatan/lostoncrampteus/Images/joueur/joueur_right2.png"),
                new Image("file:src/main/resources/universite_paris8/iut/mcatan/lostoncrampteus/Images/joueur/joueur_right3.png"),
                new Image("file:src/main/resources/universite_paris8/iut/mcatan/lostoncrampteus/Images/joueur/joueur_right4.png")
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
