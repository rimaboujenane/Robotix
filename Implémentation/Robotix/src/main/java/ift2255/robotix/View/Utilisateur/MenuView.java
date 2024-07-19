package ift2255.robotix.View.Utilisateur;

import ift2255.robotix.Modeles.Utilisateur;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

        // Buttons
        notifMenuButton = new Button("Notification");
        profileMenuButton = new Button("Profil");
        robotMenuButton = new Button("Robots");
        activiteMenuButton = new Button("Activités");
        logoutMenuButton = new Button("Déconnexion");
        exitMenuButton = new Button("Quitter");

        // Style des buttons
        String buttonStyle = "-fx-background-color: #1B263B; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px;";
        notifMenuButton.setStyle(buttonStyle);
        profileMenuButton.setStyle(buttonStyle);
        robotMenuButton.setStyle(buttonStyle);
        activiteMenuButton.setStyle(buttonStyle);
        logoutMenuButton.setStyle(buttonStyle);
        exitMenuButton.setStyle(buttonStyle);

        // Ajuster la taille des buttons
        notifMenuButton.setPrefSize(300, 100);
        profileMenuButton.setPrefSize(300, 100);
        robotMenuButton.setPrefSize(300, 100);
        activiteMenuButton.setPrefSize(300, 100);
        logoutMenuButton.setPrefSize(300, 100);
        exitMenuButton.setPrefSize(300, 100);


        HBox row1 = new HBox(30, profileMenuButton, notifMenuButton);
        row1.setAlignment(Pos.CENTER);
        HBox row2 = new HBox(30, robotMenuButton, activiteMenuButton);
        row2.setAlignment(Pos.CENTER);
        HBox row3 = new HBox(30, logoutMenuButton, exitMenuButton);
        row3.setAlignment(Pos.CENTER);



        this.setSpacing(30);
        this.setPadding(new Insets(20));

        this.getChildren().addAll( titleLabel, row1, row2, row3 );
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
