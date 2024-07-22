package ift2255.robotix.View.Fournisseur;

import ift2255.robotix.Modeles.Fournisseur;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MenuFournisseurView extends VBox {
    private Button profilMenuButton;
    private Button mesComposantsMenuButton;
    private Button enregistrerComposantsMenuButton;
    private Button logoutMenuButton;
    private Button exitMenuButton;

    public MenuFournisseurView(Fournisseur fournisseur){
        // Titre principale
        Label titleLabel = new Label("Bienvenue " + fournisseur.getPrenom() + " !");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 40px; -fx-font-weight: bold;");

        profilMenuButton = new Button("Profil");
        mesComposantsMenuButton = new Button("Mes composants");
        enregistrerComposantsMenuButton = new Button("Enregistrer Composants");
        logoutMenuButton = new Button("Déconnexion");
        exitMenuButton = new Button("Quitter");

        // Style des buttons
        String buttonStyle = "-fx-background-color: #1B263B; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px;";
        profilMenuButton.setStyle(buttonStyle);
        mesComposantsMenuButton.setStyle(buttonStyle);
        enregistrerComposantsMenuButton.setStyle(buttonStyle);
        logoutMenuButton.setStyle(buttonStyle);
        exitMenuButton.setStyle(buttonStyle);

        // Ajuster la taille des buttons
        profilMenuButton.setPrefSize(300,100);
        mesComposantsMenuButton.setPrefSize(300,100);
        enregistrerComposantsMenuButton.setPrefSize(300,100);
        logoutMenuButton.setPrefSize(300,100);
        exitMenuButton.setPrefSize(300,100);

        HBox row1 = new HBox(30, profilMenuButton, mesComposantsMenuButton);
        row1.setAlignment(Pos.CENTER);
        HBox row2 = new HBox(30, enregistrerComposantsMenuButton);
        row2.setAlignment(Pos.CENTER);
        HBox row3 = new HBox(30, logoutMenuButton, exitMenuButton);
        row3.setAlignment(Pos.CENTER);

        this.setSpacing(30);
        this.setPadding(new Insets(20));

        this.getChildren().addAll( titleLabel, row1, row2, row3 );
        this.setStyle("-fx-background-color: #0D1B2A; -fx-text-fill: white;");
    }

    public Button getProfilMenuButton() {
        return profilMenuButton;
    }
    public Button getMesComposantsMenuButton() {
        return mesComposantsMenuButton;
    }
    public Button getEnregistrerComposantsMenuButton() {
        return enregistrerComposantsMenuButton;
    }
    public Button getLogoutMenuButton() {
        return logoutMenuButton;
    }
    public Button getExitMenuButton() {
        return exitMenuButton;
    }
}
