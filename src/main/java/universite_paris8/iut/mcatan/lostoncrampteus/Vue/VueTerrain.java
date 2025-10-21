package universite_paris8.iut.mcatan.lostoncrampteus.Vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Terrain;

import java.util.ArrayList;

public class VueTerrain {

    private TilePane tilePane;
    private Terrain terrain;
    private ArrayList<ImageView> tuilesSolideVue;
    private ImageView[][] tileViews;

    public VueTerrain(TilePane tilePane, Terrain terrain, ArrayList<ImageView> tuilesSolides){
        this.tilePane = tilePane;
        this.terrain = terrain;
        tilePane.setPrefHeight(terrain.getMapWidth());
        tilePane.setPrefWidth(terrain.getMapWidth());
        this.tuilesSolideVue = tuilesSolides;
        this.tileViews = new ImageView[terrain.getMap().length][terrain.getMap()[0].length];
    }

    public void chargeTiles(){
        tilePane.getChildren().clear();
        tuilesSolideVue.clear();

        ArrayList<Image> images = getAllTileImages();

        for (int i = 0; i < terrain.getMap().length; i++) {
            for (int j = 0; j < terrain.getMap()[i].length; j++){
                int tile = terrain.getMap()[i][j];
                Image sprite = images.get(tile);

                ImageView imageView = new ImageView(sprite);
                imageView.setFitHeight(terrain.getTileSize());
                imageView.setFitWidth(terrain.getTileSize());
                tilePane.getChildren().add(imageView);
                imageView.setLayoutX(j * terrain.getTileSize());
                imageView.setLayoutY(i * terrain.getTileSize());

                tileViews[i][j] = imageView;

                if (tile == 1 || tile == 2 || tile == 15 || tile == 16 || tile == 29 || tile == 30 || tile == 31 || tile == 32 || tile == 33 || tile == 34) {
                    tuilesSolideVue.add(imageView);
                }

            }
        }
    }

    private ArrayList<Image> getAllTileImages() {
        ArrayList<Image> images = new ArrayList<>();

        images.add(new Image(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/sky1.png")));             // 0
        images.add(new Image(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/grass.png")));            // 1
        images.add(new Image(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/dirt.png")));             // 2
        images.add(new Image(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/sky2.png")));             // 3
        images.add(new Image(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/sky3.png")));             // 4
        images.add(new Image(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/sky4.png")));             // 5
        images.add(new Image(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/sky5.png")));             // 6
        images.add(new Image(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/sky6.png")));             // 7
        images.add(new Image(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/sky7.png")));             // 8
        images.add(new Image(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/sky8.png")));             // 9
        images.add(new Image(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/sky9.png")));             // 10
        images.add(new Image(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/sky10.png")));            // 11
        images.add(new Image(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/sky11.png")));            // 12
        images.add(new Image(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/sky12.png")));            // 13
        images.add(new Image(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/sky13.png")));            // 14
        images.add(new Image(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/Arbre/arbre1.png")));     // 15
        images.add(new Image(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/Arbre/arbre2.png")));     // 16
        images.add(new Image(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/Arbre/arbre3.png")));     // 17
        images.add(new Image(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/Arbre/arbre4.png")));     // 18
        images.add(new Image(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/Arbre/arbre5.png")));     // 19
        images.add(new Image(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/Arbre/arbre6.png")));     // 20
        images.add(new Image(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/Arbre/arbre7.png")));     // 21
        images.add(new Image(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/Arbre/arbre8.png")));     // 22
        images.add(new Image(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/Arbre/arbre9.png")));     // 23
        images.add(new Image(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/Arbre/arbre10.png")));    // 24
        images.add(new Image(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/Arbre/arbre11.png")));    // 25
        images.add(new Image(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/Arbre/arbre12.png")));    // 26
        images.add(new Image(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/Arbre/arbre13.png")));    // 27
        images.add(new Image(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/Arbre/arbre14.png")));    // 28
        images.add(new Image(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/Items/Ressources/aluminium.png")));    // 29
        images.add(new Image(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/Items/Ressources/cramptenium.png")));    // 30
        images.add(new Image(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/Items/Ressources/cuivre.png")));    // 31
        images.add(new Image(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/Items/Ressources/fer.png")));    // 32
        images.add(new Image(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/Items/Ressources/pierre.png")));    // 33
        images.add(new Image(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/gintoki.png")));    // 34

        return images;
    }



    public void updateTile(int x, int y){
        int tile = terrain.getMap()[y][x];
        Image sprite = getAllTileImages().get(tile);
        tileViews[y][x].setImage(sprite);

        if (terrain.estTuilleSolide(tile)) {
            if (!tuilesSolideVue.contains(tileViews[y][x])) {
                tuilesSolideVue.add(tileViews[y][x]);
            }
        } else {
            tuilesSolideVue.remove(tileViews[y][x]);
        }
    }

}
