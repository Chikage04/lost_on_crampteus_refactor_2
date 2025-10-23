package universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme;

import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Hitbox;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Monde;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Joueur;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Pnj;

public class Epee extends Arme {

    private int range;

    public Epee(){
        super("épée", 25, 100);
        this.range = 64;
    }


    @Override
    public String toString() {
        return "épée";
    }

    @Override
    public void attaquer(int tileX, int tileY) {
        Joueur joueur = Monde.getInstance().getJoueur();
        Hitbox hitboxAttaque;

        if (joueur.getVelocityX() >= 0) {
            hitboxAttaque = new Hitbox(joueur.getPosX() + joueur.getWidth(), joueur.getPosY(), range, joueur.getHeight());
        } else {
            hitboxAttaque = new Hitbox(joueur.getPosX() - range, joueur.getPosY(), range, joueur.getHeight());
        }

        for (Pnj pnj : Monde.getInstance().getPnjs()) {
            if (hitboxAttaque.colision(pnj.getHitbox())) {
                //pnj.setPosY(pnj.getPosY() - 64);
                pnj.perdreVie((double) getDegats() / 100);
            }
        }
    }


}
