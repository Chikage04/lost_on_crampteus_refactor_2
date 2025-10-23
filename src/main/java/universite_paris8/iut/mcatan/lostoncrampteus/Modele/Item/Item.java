package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Item {

    private String nom;
    private int stackLimit;
    private IntegerProperty tailleStack;

    public Item(String nom, int stackLimit, int tailleStack){
        this.nom = nom;
        this.stackLimit = stackLimit;
        this.tailleStack = new SimpleIntegerProperty(tailleStack);
    }

    public String getNom(){
        return this.nom;
    }

    public boolean stackPlein(){
        return tailleStack.getValue() == stackLimit;
    }

    public boolean peutEtreStacke(){
        return stackLimit > 1;
    }
    public int getStackLimit(){
        return this.stackLimit;
    }

    public IntegerProperty getTailleStackProperty() {
        return tailleStack;
    }

    public int getTailleStack(){
        return tailleStack.getValue();
    }

    public void augmenterStack(){
        if (this.tailleStack.getValue() < this.stackLimit) {
            this.tailleStack.set(this.tailleStack.getValue() + 1);
        }
    }

    // pour le clic gauche
    public abstract void attaquer(int tileX, int tileY);

    // pour le clic droit
    public abstract void utiliser(int tileX, int tileY);


    public void decrementerStack(){
        setTailleStack(this.tailleStack.getValue() - 1);
    }

    public void setTailleStack(int tailleStack){
        this.tailleStack.set(tailleStack);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}