package universite_paris8.iut.mcatan.lostoncrampteus.Modele;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HitboxTest {

    @Test
    void testHitboxColision() {

        Hitbox hitbox1 = new Hitbox(0, 0, 10, 10);
        Hitbox hitbox2 = new Hitbox(10, 0, 10, 10);
        assertFalse(hitbox1.colision(hitbox2), "collision à droite");

        Hitbox hitbox3 = new Hitbox(10, 0, 10, 10);
        Hitbox hitbox4 = new Hitbox(0, 0, 10, 10);
        assertFalse(hitbox3.colision(hitbox4), "collision à gauche");

        Hitbox hitbox5 = new Hitbox(0, 10, 10, 10);
        Hitbox hitbox6 = new Hitbox(0, 0, 10, 10);
        assertFalse(hitbox5.colision(hitbox6), "collision en haut");

        Hitbox hitbox7 = new Hitbox(0, 0, 10, 10);
        Hitbox hitbox8 = new Hitbox(0, 10, 10, 10);
        assertFalse(hitbox7.colision(hitbox8), "collision en bas");
    }

    @Test
    void testHitboxSansColision() {
        Hitbox hitbox1 = new Hitbox(0, 0, 10, 10);
        Hitbox hitbox2 = new Hitbox(20, 20, 10, 10);
        assertFalse(hitbox1.colision(hitbox2), "les hitboxes sont séparées et ne doivent pas entrer en collision");

        Hitbox hitbox3 = new Hitbox(0, 0, 10, 10);
        Hitbox hitbox4 = new Hitbox(10.1, 0, 10, 10);
        assertFalse(hitbox3.colision(hitbox4), "Les hitboxes sont côte à côte mais ne doivent pas rentrer en collision");
    }

    @Test
    void testCouvreTuileExacte() {
        Hitbox hitbox = new Hitbox(32, 64, 32, 32);
        assertTrue(hitbox.couvreTuile(1, 2), "La hitbox devrait couvrir exactement la tuile (1,2)");
    }

    @Test
    void testNeCouvrePasTuilePositionXDifferent() {
        Hitbox hitbox = new Hitbox(33, 64, 32, 32);
        assertFalse(hitbox.couvreTuile(1, 2), "La hitbox ne devrait pas couvrir la tuile à cause de la position X");
    }

    @Test
    void testNeCouvrePasTuilePositionYDifferent() {
        Hitbox hitbox = new Hitbox(32, 63, 32, 32);
        assertFalse(hitbox.couvreTuile(1, 2), "La hitbox ne devrait pas couvrir la tuile à cause de la position Y");
    }

    @Test
    void testNeCouvrePasTuileLargeurDifferent() {
        Hitbox hitbox = new Hitbox(32, 64, 31, 32);
        assertFalse(hitbox.couvreTuile(1, 2), "La hitbox ne devrait pas couvrir la tuile à cause de la largeur");
    }

    @Test
    void testNeCouvrePasTuileHauteurDifferent() {
        Hitbox hitbox = new Hitbox(32, 64, 32, 31);
        assertFalse(hitbox.couvreTuile(1, 2), "La hitbox ne devrait pas couvrir la tuile à cause de la hauteur");
    }

    @Test
    void testCouvreTuileOrigine() {
        Hitbox hitbox = new Hitbox(0, 0, 32, 32);
        assertTrue(hitbox.couvreTuile(0, 0), "La hitbox devrait couvrir exactement la tuile d'origine (0,0)");
    }

}