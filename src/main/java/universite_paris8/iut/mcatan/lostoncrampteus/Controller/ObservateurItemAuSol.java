package universite_paris8.iut.mcatan.lostoncrampteus.Controller;

import javafx.collections.ListChangeListener;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.ItemAuSol;
import universite_paris8.iut.mcatan.lostoncrampteus.Vue.VueItemAuSol;

public class ObservateurItemAuSol implements ListChangeListener<ItemAuSol> {

    private VueItemAuSol vueItemAuSol;

    public ObservateurItemAuSol(VueItemAuSol vueItemAuSol) {
        this.vueItemAuSol = vueItemAuSol;
    }
    @Override
    public void onChanged(Change<? extends ItemAuSol> change) {
        while (change.next()){
            if (change.wasAdded()) {
                for (ItemAuSol item : change.getAddedSubList()) {
                    vueItemAuSol.ajouterVueItem(item);
                }
            }
            if (change.wasRemoved()) {
                vueItemAuSol.supprimerVueItem();
            }
        }
    }
}
