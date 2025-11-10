package universite_paris8.iut.mcatan.lostoncrampteus.Modele;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Gronfleur;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Joueur;
import universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage.Pnj;

import java.util.HashSet;

public class AlgoRechercheTest {

    private Monde genererMondePourTest() {
        Monde monde = Monde.getInstance();
        monde.ajouterPnj(new Gronfleur());

        return monde;
    }

    private boolean pnjToucheJoueur(Pnj pnj, Joueur joueur) {
        return pnj.getHitbox().colision(joueur.getHitbox());
    }

    @Test
    void unPnjAtteintJoueur() {
        Monde monde = genererMondePourTest();

        Joueur joueur = monde.getJoueur();
        Gronfleur gronfleur = (Gronfleur) monde.getPnjs().get(0);

        joueur.setPosX(32);
        gronfleur.setPosX(96);

        for (int i = 0; i < 200; i++) {
            joueur.updatePosition(new HashSet<>());
            gronfleur.seDeplacer();
        }

        assertTrue(pnjToucheJoueur(gronfleur, joueur));
    }

    @Test
    void unPnjTropLoinJoueur(){
        Monde monde = genererMondePourTest();

        Joueur joueur = monde.getJoueur();
        Gronfleur gronfleur = (Gronfleur) monde.getPnjs().get(0);

        joueur.setPosX(100);
        gronfleur.setPosX(301);

        for (int i = 0; i < 200; i++) {
            joueur.updatePosition(new HashSet<>());
            gronfleur.seDeplacer();
        }

        assertFalse(pnjToucheJoueur(gronfleur, joueur));
    }

    @Test
    void pnjAGaucheEtADroite() {
        Monde monde = genererMondePourTest();

        Joueur joueur = monde.getJoueur();
        for (int i = 0; i <= 1; i++) {
            monde.ajouterPnj(new Gronfleur());
        }

        Gronfleur gronfleurG = (Gronfleur) monde.getPnjs().get(0);
        Gronfleur gronfleurD = (Gronfleur) monde.getPnjs().get(1);

        joueur.setPosX(100);
        gronfleurG.setPosX(50);
        gronfleurD.setPosX(150);

        for (int i = 0; i < 200; i++) {
            joueur.updatePosition(new HashSet<>());
            gronfleurG.seDeplacer();
            gronfleurD.seDeplacer();
        }

        assertTrue(pnjToucheJoueur(gronfleurG, joueur));
        assertTrue(pnjToucheJoueur(gronfleurD, joueur));

    }
}
