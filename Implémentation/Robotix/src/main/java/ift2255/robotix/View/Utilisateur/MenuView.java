package ift2255.robotix.View.Utilisateur;

import ift2255.robotix.Modeles.Utilisateur;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Vue du menu principal pour l'application Robotix.
 *
 * Cette classe étend `VBox` et fournit une interface utilisateur pour naviguer vers différentes sections de l'application.
 * Elle inclut des boutons pour accéder aux notifications, au profil utilisateur, aux robots, aux activités, ainsi que
 * pour se déconnecter ou quitter l'application.
 *
 * @see javafx.scene.control.Button
 * @see javafx.scene.control.Label
 * @see javafx.scene.image.ImageView
 * @see javafx.scene.layout.VBox
 * @see javafx.scene.layout.HBox
 */
public class MenuView extends VBox {

    private Button notifMenuButton;
    private Button profileMenuButton;
    private Button robotMenuButton;
    private Button activiteMenuButton;
    private Button logoutMenuButton;
    private Button exitMenuButton;

    /**
     * Constructeur de la vue du menu principal.
     * Initialise les éléments de l'interface utilisateur avec les icônes et les étiquettes appropriées.
     *
     * @param utilisateur L'utilisateur dont le prénom sera affiché dans le titre de la vue.
     */
    public MenuView(Utilisateur utilisateur) {
        // Titre principal affichant le prénom de l'utilisateur
        Label titleLabel = new Label("Bienvenue " + utilisateur.getPrenom() + " !");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 40px; -fx-font-weight: bold;");

        // Création des images pour les boutons du menu
        ImageView profilImage = new ImageView("file:src/main/resources/images/profil.png");
        profilImage.setFitHeight(75);
        profilImage.setFitWidth(75);

        ImageView notifImage = new ImageView("file:src/main/resources/images/notif1.png");
        notifImage.setFitHeight(75);
        notifImage.setFitWidth(75);

        ImageView robotImage = new ImageView("file:src/main/resources/images/robot.png");
        robotImage.setFitHeight(75);
        robotImage.setFitWidth(75);

        ImageView activiteImage = new ImageView("file:src/main/resources/images/activite.png");
        activiteImage.setFitHeight(75);
        activiteImage.setFitWidth(75);

        ImageView logoutImage = new ImageView("file:src/main/resources/images/logout.png");
        logoutImage.setFitHeight(75);
        logoutImage.setFitWidth(75);

        ImageView exitImage = new ImageView("file:src/main/resources/images/exit.png");
        exitImage.setFitHeight(75);
        exitImage.setFitWidth(75);

        // Création des boutons du menu avec les images associées
        notifMenuButton = new Button();
        notifMenuButton.setGraphic(notifImage);
        profileMenuButton = new Button();
        profileMenuButton.setGraphic(profilImage);
        robotMenuButton = new Button();
        robotMenuButton.setGraphic(robotImage);
        activiteMenuButton = new Button();
        activiteMenuButton.setGraphic(activiteImage);
        logoutMenuButton = new Button();
        logoutMenuButton.setGraphic(logoutImage);
        exitMenuButton = new Button();
        exitMenuButton.setGraphic(exitImage);

        // Style des boutons
        String buttonStyle = "-fx-background-color: #1B263B; -fx-padding: 10px; -fx-background-radius: 25;";
        notifMenuButton.setStyle(buttonStyle);
        profileMenuButton.setStyle(buttonStyle);
        robotMenuButton.setStyle(buttonStyle);
        activiteMenuButton.setStyle(buttonStyle);
        logoutMenuButton.setStyle(buttonStyle);
        exitMenuButton.setStyle(buttonStyle);

        // Ajuster la taille des boutons pour qu'ils soient uniformes
        notifMenuButton.setPrefSize(75, 75);
        profileMenuButton.setPrefSize(75, 75);
        robotMenuButton.setPrefSize(75, 75);
        activiteMenuButton.setPrefSize(75, 75);
        logoutMenuButton.setPrefSize(75, 75);
        exitMenuButton.setPrefSize(75, 75);

        // Création des étiquettes pour chaque bouton
        Label profilLabel = new Label("Profil");
        profilLabel.setStyle("-fx-text-fill: white; -fx-alignment: center");
        Label notifLabel = new Label("Notification");
        notifLabel.setStyle("-fx-text-fill: white");
        Label robotLabel = new Label("Robots");
        robotLabel.setStyle("-fx-text-fill: white");
        Label activiteLabel = new Label("Activités");
        activiteLabel.setStyle("-fx-text-fill: white");
        Label logoutLabel = new Label("Déconnexion");
        logoutLabel.setStyle("-fx-text-fill: white");
        Label exitLabel = new Label("Quitter");
        exitLabel.setStyle("-fx-text-fill: white");

        // Organisation des boutons et des étiquettes dans des VBox pour chaque section
        VBox vbox1 = new VBox(10, profileMenuButton, profilLabel);
        vbox1.setAlignment(Pos.CENTER);
        VBox vbox2 = new VBox(10, notifMenuButton, notifLabel);
        vbox2.setAlignment(Pos.CENTER);
        VBox vbox3 = new VBox(10, robotMenuButton, robotLabel);
        vbox3.setAlignment(Pos.CENTER);
        VBox vbox4 = new VBox(10, activiteMenuButton, activiteLabel);
        vbox4.setAlignment(Pos.CENTER);
        VBox vbox5 = new VBox(10, logoutMenuButton, logoutLabel);
        vbox5.setAlignment(Pos.CENTER);
        VBox vbox6 = new VBox(10, exitMenuButton, exitLabel);
        vbox6.setAlignment(Pos.CENTER);

        // Organisation des sections en lignes
        HBox row1 = new HBox(100, vbox1, vbox2);
        row1.setAlignment(Pos.CENTER);
        HBox row2 = new HBox(100, vbox3, vbox4);
        row2.setAlignment(Pos.CENTER);
        HBox row3 = new HBox(100, vbox5, vbox6);
        row3.setAlignment(Pos.CENTER);

        // Organisation des lignes dans la vue principale
        VBox appVbox = new VBox(75, row1, row2, row3);

        // Définir les marges et le style de la vue principale
        this.setSpacing(30);
        this.setPadding(new Insets(20));
        this.getChildren().addAll(titleLabel, appVbox);
        this.setStyle("-fx-background-color: #0D1B2A; -fx-text-fill: white;");
    }

    /**
     * Retourne le bouton de notifications.
     *
     * @return Le bouton de notifications.
     */
    public Button getNotifMenuButton() {
        return notifMenuButton;
    }

    /**
     * Retourne le bouton de profil utilisateur.
     *
     * @return Le bouton de profil utilisateur.
     */
    public Button getProfileMenuButton() {
        return profileMenuButton;
    }

    /**
     * Retourne le bouton des robots.
     *
     * @return Le bouton des robots.
     */
    public Button getRobotMenuButton() {
        return robotMenuButton;
    }

    /**
     * Retourne le bouton des activités.
     *
     * @return Le bouton des activités.
     */
    public Button getActiviteMenuButton() {
        return activiteMenuButton;
    }

    /**
     * Retourne le bouton de déconnexion.
     *
     * @return Le bouton de déconnexion.
     */
    public Button getLogoutMenuButton() {
        return logoutMenuButton;
    }

    /**
     * Retourne le bouton de quitter l'application.
     *
     * @return Le bouton de quitter l'application.
     */
    public Button getExitMenuButton() {
        return exitMenuButton;
    }
}

