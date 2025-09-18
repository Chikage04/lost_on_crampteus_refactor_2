package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Hitbox;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Joueur;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Pnj;

public class Epee extends Arme {

    private Monde monde;

    private int range;

    public Epee(Monde monde){
        super("épée", 25, 100);
        this.range = 64;
        this.monde =monde;
    }

    public Epee(int range, Monde monde) {
        super("épée", 10, 100);
        this.range = range;
        this.monde = monde;
    }


    @Override
    public String toString() {
        return "épée";
    }

    @Override
    public void attaquer(int tileX, int tileY) {
        Joueur joueur = monde.getJoueur();
        Hitbox hitboxAttaque;

        if (joueur.getVelocityX() >= 0) {
            hitboxAttaque = new Hitbox(joueur.getPosX() + joueur.getWidth(), joueur.getPosY(), range, joueur.getHeight());
        } else {
            hitboxAttaque = new Hitbox(joueur.getPosX() - range, joueur.getPosY(), range, joueur.getHeight());
        }

        for (Pnj pnj : monde.getPnjs()) {
            if (hitboxAttaque.colision(pnj.getHitbox())) {
                //pnj.setPosY(pnj.getPosY() - 64);
                pnj.perdreVie((double) getDegats() / 100);
            }
        }
    }


}
