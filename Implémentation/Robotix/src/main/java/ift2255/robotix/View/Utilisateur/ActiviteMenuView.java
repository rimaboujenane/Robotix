package ift2255.robotix.View.Utilisateur;

import ift2255.robotix.Modeles.Activite;
import ift2255.robotix.Modeles.GestionActivites;
import ift2255.robotix.Modeles.Utilisateur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.List;

public class ActiviteMenuView extends VBox {
    private Button backButton;
    private GestionActivites activites;

    public ActiviteMenuView(Utilisateur utilisateur) {
        afficherVue(utilisateur);
    }
    public Button getBackButton() {
        return backButton;
    }
    public void afficherVue(Utilisateur utilisateur) {
        this.getChildren().clear(); // Effacer tous les enfants actuels de ActiviteMenuView

        GestionActivites listActivites = new GestionActivites(utilisateur);

        VBox contentBox = new VBox(10);
        contentBox.setStyle("-fx-padding: 20; -fx-background-color: #0D1B2A; -fx-text-fill: white;");

        Label titleLabel = new Label("Mes Activités");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 40px; -fx-font-weight: bold;");
        contentBox.getChildren().add(titleLabel);

        afficherActivite(listActivites.getMesActivites(), contentBox, "Se désinscrire", "#E94343");

        Label inscrireLabel = new Label("S'incrire à une activité");
        inscrireLabel.setStyle("-fx-text-fill: white; -fx-font-size: 40px; -fx-font-weight: bold;");
        contentBox.getChildren().add(inscrireLabel);

        afficherActivite(listActivites.getActivitesNonInscrites(), contentBox, "S'inscrire", "#0466C8");

        backButton = new Button("Retour");
        backButton.setStyle("-fx-background-color: #1B263B; -fx-text-fill: white;");
        contentBox.getChildren().add(backButton);

        ScrollPane scrollPane = new ScrollPane(contentBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: #0D1B2A;");

        this.getChildren().add(scrollPane);
        this.setStyle("-fx-background-color: #0D1B2A; -fx-text-fill: white;");
    }

    private void afficherActivite(List<Activite> listActivites, VBox contentBox, String boutonTexte, String boutonCouleur) {
        for (Activite activite : listActivites) {
            Label nomActiviteLabel = new Label(activite.getNom());
            nomActiviteLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20px;");

            Label dateDebutLabel = new Label("Début: " + activite.getDateDebut().toString());
            dateDebutLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");

            Label dateFinLabel = new Label("Fin: " + activite.getDateFin().toString());
            dateFinLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");

            VBox dateVBox = new VBox(5, dateDebutLabel, dateFinLabel);
            dateVBox.setAlignment(Pos.CENTER);

            Button button = new Button(boutonTexte);
            button.setStyle("-fx-background-color: " + boutonCouleur + "; -fx-text-fill: white;");
            button.setUserData(activite);

            HBox hBox = new HBox(100, nomActiviteLabel, dateVBox, button);
            hBox.setAlignment(Pos.CENTER);
            hBox.setStyle("-fx-background-color: #1B263B; -fx-padding: 10px;");
            hBox.setPrefWidth(700);
            hBox.setMaxWidth(700);

            Region separator = new Region();
            separator.setStyle("-fx-border-color: black; -fx-border-width: 0 0 0 1;"); // Bordure noire sur la gauche
            separator.setPrefHeight(10); // Même hauteur que les HBox

            VBox vbox = new VBox(separator, hBox);
            vbox.setAlignment(Pos.CENTER);

            contentBox.getChildren().add(vbox);
        }
    }

    public void setButtonAction(String buttonText, EventHandler<ActionEvent> handler) {
        // Accéder au ScrollPane
        ScrollPane scrollPane = (ScrollPane) this.getChildren().get(0);
        VBox contentBox = (VBox) scrollPane.getContent();

        // Parcourir tous les enfants du VBox
        for (Node node : contentBox.getChildren()) {
            if (node instanceof VBox) {
                VBox vbox = (VBox) node;
                HBox hBox = (HBox) vbox.getChildren().get(1); // Assurez-vous que l'index 1 est correct
                Button button = (Button) hBox.getChildren().get(2); // Assurez-vous que l'index 2 est correct
                if (buttonText.equals(button.getText())) {
                    button.setOnAction(handler);
                }
            }
        }
    }
}
