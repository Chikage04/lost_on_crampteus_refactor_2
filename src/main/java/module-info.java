module universite_paris8.iut.mcatan.lostoncrampteus {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires jdk.xml.dom;
    requires jdk.jfr;
    requires javafx.graphics;
    requires jdk.incubator.vector;

    opens universite_paris8.iut.mcatan.lostoncrampteus to javafx.fxml;
    exports universite_paris8.iut.mcatan.lostoncrampteus;
    exports universite_paris8.iut.mcatan.lostoncrampteus.Modele;
    opens universite_paris8.iut.mcatan.lostoncrampteus.Modele to javafx.fxml;
    exports universite_paris8.iut.mcatan.lostoncrampteus.Controller;
    opens universite_paris8.iut.mcatan.lostoncrampteus.Controller to javafx.fxml;
    exports universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item;
    opens universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item to javafx.fxml;
    exports universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage;
    opens universite_paris8.iut.mcatan.lostoncrampteus.Modele.Personnage to javafx.fxml;
    exports universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc;
    opens universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Bloc to javafx.fxml;
    exports universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Consomable;
    opens universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Consomable to javafx.fxml;
    exports universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme;
    opens universite_paris8.iut.mcatan.lostoncrampteus.Modele.Item.Arme to javafx.fxml;
    exports universite_paris8.iut.mcatan.lostoncrampteus.Modele.Craft;
    opens universite_paris8.iut.mcatan.lostoncrampteus.Modele.Craft to javafx.fxml;
}