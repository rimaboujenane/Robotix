package ift2255.robotix.View.Fournisseur;

import ift2255.robotix.Modeles.RegisterFournisseur;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MenuFournisseurView extends VBox {
    private Button profilMenuButton;
    private Button mesComposantsMenuButton;
    private Button enregistrerComposantsMenuButton;
    private Button logoutMenuButton;
    private Button exitMenuButton;

    public MenuFournisseurView() {
        // Titre principal
        Label titleLabel = new Label("Bienvenue " + RegisterFournisseur.getInstance().getFournisseur().getPrenom() + " !");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 40px; -fx-font-weight: bold;");

        // Création des images pour les boutons du menu
        ImageView profilImage = new ImageView("file:src/main/resources/images/profil.png");
        profilImage.setFitHeight(75);
        profilImage.setFitWidth(75);

        ImageView mesComposantsImage = new ImageView("file:src/main/resources/images/mescomps.png");
        mesComposantsImage.setFitHeight(75);
        mesComposantsImage.setFitWidth(75);

        ImageView enregistrerComposantsImage = new ImageView("file:src/main/resources/images/enregistrercomp.png");
        enregistrerComposantsImage.setFitHeight(75);
        enregistrerComposantsImage.setFitWidth(75);

        ImageView logoutImage = new ImageView("file:src/main/resources/images/logout.png");
        logoutImage.setFitHeight(75);
        logoutImage.setFitWidth(75);

        ImageView exitImage = new ImageView("file:src/main/resources/images/exit.png");
        exitImage.setFitHeight(75);
        exitImage.setFitWidth(75);

        // Création des boutons du menu avec les images associées
        profilMenuButton = new Button();
        profilMenuButton.setGraphic(profilImage);
        mesComposantsMenuButton = new Button();
        mesComposantsMenuButton.setGraphic(mesComposantsImage);
        enregistrerComposantsMenuButton = new Button();
        enregistrerComposantsMenuButton.setGraphic(enregistrerComposantsImage);
        logoutMenuButton = new Button();
        logoutMenuButton.setGraphic(logoutImage);
        exitMenuButton = new Button();
        exitMenuButton.setGraphic(exitImage);

        // Style des boutons
        String buttonStyle = "-fx-background-color: #1B263B; -fx-padding: 10px; -fx-background-radius: 25;";
        profilMenuButton.setStyle(buttonStyle);
        mesComposantsMenuButton.setStyle(buttonStyle);
        enregistrerComposantsMenuButton.setStyle(buttonStyle);
        logoutMenuButton.setStyle(buttonStyle);
        exitMenuButton.setStyle(buttonStyle);

        // Ajuster la taille des boutons pour qu'ils soient uniformes
        profilMenuButton.setPrefSize(75, 75);
        mesComposantsMenuButton.setPrefSize(75, 75);
        enregistrerComposantsMenuButton.setPrefSize(75, 75);
        logoutMenuButton.setPrefSize(75, 75);
        exitMenuButton.setPrefSize(75, 75);

        // Création des étiquettes pour chaque bouton
        Label profilLabel = new Label("Profil");
        profilLabel.setStyle("-fx-text-fill: white; -fx-alignment: center");
        Label mesComposantsLabel = new Label("Mes Composants");
        mesComposantsLabel.setStyle("-fx-text-fill: white");
        Label enregistrerComposantsLabel = new Label("Enregistrer Composants");
        enregistrerComposantsLabel.setStyle("-fx-text-fill: white");
        Label logoutLabel = new Label("Déconnexion");
        logoutLabel.setStyle("-fx-text-fill: white");
        Label exitLabel = new Label("Quitter");
        exitLabel.setStyle("-fx-text-fill: white");

        // Organisation des boutons et des étiquettes dans des VBox pour chaque section
        VBox vbox1 = new VBox(10, profilMenuButton, profilLabel);
        vbox1.setAlignment(Pos.CENTER);
        VBox vbox2 = new VBox(10, mesComposantsMenuButton, mesComposantsLabel);
        vbox2.setAlignment(Pos.CENTER);
        VBox vbox3 = new VBox(10, enregistrerComposantsMenuButton, enregistrerComposantsLabel);
        vbox3.setAlignment(Pos.CENTER);
        VBox vbox4 = new VBox(10, logoutMenuButton, logoutLabel);
        vbox4.setAlignment(Pos.CENTER);
        VBox vbox5 = new VBox(10, exitMenuButton, exitLabel);
        vbox5.setAlignment(Pos.CENTER);

        // Organisation des sections en lignes
        HBox row1 = new HBox(100, vbox1, vbox2, vbox3);
        row1.setAlignment(Pos.CENTER);
        HBox row2 = new HBox(100, vbox4, vbox5);
        row2.setAlignment(Pos.CENTER);

        // Organisation des lignes dans la vue principale
        VBox appVbox = new VBox(75, row1, row2);

        // Définir les marges et le style de la vue principale
        this.setSpacing(30);
        this.setPadding(new Insets(20));
        this.getChildren().addAll(titleLabel, appVbox);
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
