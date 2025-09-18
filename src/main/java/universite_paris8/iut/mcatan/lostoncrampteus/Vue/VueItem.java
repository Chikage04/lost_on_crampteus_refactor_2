package universite_paris8.iut.mcatan.lostoncrampteus.Vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.ItemAuSol;

import java.util.Objects;

public class VueItem {

    private final ImageView imageView;
    private final ItemAuSol itemAuSol;

    public VueItem(ItemAuSol itemAuSol) {
        this.itemAuSol = itemAuSol;
        this.imageView = new ImageView();
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/universite_paris8/iut/mcatan/lostoncrampteus/Images/Items/Sol/" + itemAuSol.getItem().getNom() + "-sol.png")));
        imageView.setImage(image);

        imageView.setFitWidth(itemAuSol.getWidth());
        imageView.setFitHeight(itemAuSol.getHeight());

        imageView.xProperty().bind(itemAuSol.posXProperty());
        imageView.yProperty().bind(itemAuSol.posYProperty());
    }

    public ImageView getImageView() {
        return imageView;
    }

    public ItemAuSol getItemAuSol() {
        return itemAuSol;
    }
}
