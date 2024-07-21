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

public class MenuView extends VBox {
    private Button notifMenuButton;
    private Button profileMenuButton;
    private Button robotMenuButton;
    private Button activiteMenuButton;
    private Button logoutMenuButton;
    private Button exitMenuButton;


    public MenuView(Utilisateur utilisateur) {
        // Titre principale
        Label titleLabel = new Label("Bienvenue " + utilisateur.getPrenom() + " !");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 40px; -fx-font-weight: bold;");

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

        // Buttons
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

        // Style des buttons
        String buttonStyle = "-fx-background-color: #1B263B; -fx-padding: 10px; -fx-background-radius: 25;";
        notifMenuButton.setStyle(buttonStyle);
        profileMenuButton.setStyle(buttonStyle);
        robotMenuButton.setStyle(buttonStyle);
        activiteMenuButton.setStyle(buttonStyle);
        logoutMenuButton.setStyle(buttonStyle);
        exitMenuButton.setStyle(buttonStyle);

        // Ajuster la taille des buttons
        notifMenuButton.setPrefSize(75, 75);
        profileMenuButton.setPrefSize(75, 75);
        robotMenuButton.setPrefSize(75, 75);
        activiteMenuButton.setPrefSize(75, 75);
        logoutMenuButton.setPrefSize(75, 75);
        exitMenuButton.setPrefSize(75, 75);

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

        HBox row1 = new HBox(100, vbox1, vbox2);
        row1.setAlignment(Pos.CENTER);
        HBox row2 = new HBox(100, vbox3, vbox4);
        row2.setAlignment(Pos.CENTER);
        HBox row3 = new HBox(100, vbox5, vbox6);
        row3.setAlignment(Pos.CENTER);

        VBox appVbox = new VBox(75, row1, row2, row3);

        this.setSpacing(30);
        this.setPadding(new Insets(20));

        this.getChildren().addAll( titleLabel, appVbox );
        this.setStyle("-fx-background-color: #0D1B2A; -fx-text-fill: white;");
    }

    public Button getNotifMenuButton() {
        return notifMenuButton;
    }
    public Button getProfileMenuButton() {
        return profileMenuButton;
    }
    public Button getRobotMenuButton() {
        return robotMenuButton;
    }
    public Button getActiviteMenuButton() {return activiteMenuButton;}
    public Button getLogoutMenuButton() {
        return logoutMenuButton;
    }
    public Button getExitMenuButton() {
        return exitMenuButton;
    }
}
