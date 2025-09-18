package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;

public class ItemAvecDurabilite extends Item{

    private DoubleProperty durabilite;

    public ItemAvecDurabilite(String nom, int stackLimit, int tailleStack, double durabilite) {
        super(nom, stackLimit, tailleStack);
        this.durabilite = new SimpleDoubleProperty(durabilite);
    }

    public void perdreDurabilite(double degats){
        setDurabilite(getDurabilite() - degats);
    }

    public DoubleProperty getDurabiliteProperty(){
        return durabilite;
    }

    public boolean estDetruit() {
        return durabilite.getValue() <= 0;
    }

    public double getDurabilite() {
        return durabilite.getValue();
    }

    public void setDurabilite(double durabilite) {
        this.durabilite.setValue(durabilite);
    }

    @Override
    public void attaquer(int tileX, int tileY) {

    }

    @Override
    public void utiliser(Monde monde, int tileX, int tileY) {

    }
}
