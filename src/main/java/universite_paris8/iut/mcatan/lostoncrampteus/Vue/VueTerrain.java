package universite_paris8.iut.mcatan.lostoncrampteus.Vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Map.Terrain;
import universite_paris8.iut.mcatan.lostoncrampteus.Utils.ResourceManager;

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

                if (tile == 1 || tile == 2 || tile == 15 || tile == 16 || tile == 29 || tile == 30 || tile == 31 || tile == 32 || tile == 33) {
                    tuilesSolideVue.add(imageView);
                }
            }
        }
    }

    private ArrayList<Image> getAllTileImages() {
        ArrayList<Image> images = new ArrayList<>();

        // Utiliser le ResourceManager pour charger toutes les images de tuiles
        ResourceManager rm = ResourceManager.getInstance();

        images.add(rm.getImage("sky1.png"));                              // 0
        images.add(rm.getImage("grass.png"));                             // 1
        images.add(rm.getImage("dirt.png"));                              // 2
        images.add(rm.getImage("sky2.png"));                              // 3
        images.add(rm.getImage("sky3.png"));                              // 4
        images.add(rm.getImage("sky4.png"));                              // 5
        images.add(rm.getImage("sky5.png"));                              // 6
        images.add(rm.getImage("sky6.png"));                              // 7
        images.add(rm.getImage("sky7.png"));                              // 8
        images.add(rm.getImage("sky8.png"));                              // 9
        images.add(rm.getImage("sky9.png"));                              // 10
        images.add(rm.getImage("sky10.png"));                             // 11
        images.add(rm.getImage("sky11.png"));                             // 12
        images.add(rm.getImage("sky12.png"));                             // 13
        images.add(rm.getImage("sky13.png"));                             // 14
        images.add(rm.getImage("Arbre/arbre1.png"));                      // 15
        images.add(rm.getImage("Arbre/arbre2.png"));                      // 16
        images.add(rm.getImage("Arbre/arbre3.png"));                      // 17
        images.add(rm.getImage("Arbre/arbre4.png"));                      // 18
        images.add(rm.getImage("Arbre/arbre5.png"));                      // 19
        images.add(rm.getImage("Arbre/arbre6.png"));                      // 20
        images.add(rm.getImage("Arbre/arbre7.png"));                      // 21
        images.add(rm.getImage("Arbre/arbre8.png"));                      // 22
        images.add(rm.getImage("Arbre/arbre9.png"));                      // 23
        images.add(rm.getImage("Arbre/arbre10.png"));                     // 24
        images.add(rm.getImage("Arbre/arbre11.png"));                     // 25
        images.add(rm.getImage("Arbre/arbre12.png"));                     // 26
        images.add(rm.getImage("Arbre/arbre13.png"));                     // 27
        images.add(rm.getImage("Arbre/arbre14.png"));                     // 28
        images.add(rm.getImage("Items/Ressources/aluminium.png"));        // 29
        images.add(rm.getImage("Items/Ressources/cramptenium.png"));      // 30
        images.add(rm.getImage("Items/Ressources/cuivre.png"));           // 31
        images.add(rm.getImage("Items/Ressources/fer.png"));              // 32
        images.add(rm.getImage("Items/Ressources/pierre.png"));           // 33

        return images;
    }

    public void updateTile(int x, int y){
        int tile = terrain.getMap()[y][x];
        ArrayList<Image> images = getAllTileImages();
        Image sprite = images.get(tile);
        tileViews[y][x].setImage(sprite);

        if (terrain.estTuilleSolide(tile)) {
            if (!tuilesSolideVue.contains(tileViews[y][x])) {
                tuilesSolideVue.add(tileViews[y][x]);
            }
        } else {
            tuilesSolideVue.remove(tileViews[y][x]);
        }
    }

    public Terrain getTerrain() {
        return terrain;
    }
}