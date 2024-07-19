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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.List;

public class ActiviteMenuView extends VBox {
    private Button backButton;
    private GestionActivites activites;

    public ActiviteMenuView(Utilisateur utilisateur) {
        GestionActivites listActivites = new GestionActivites(utilisateur);

        Label titleLabel = new Label("Mes Activités");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 40px; -fx-font-weight: bold;");
        this.getChildren().add(titleLabel);

        afficherMesActivites(listActivites.getMesActivites());

        Label inscrireLabel = new Label("S'incrire à une activité");
        inscrireLabel.setStyle("-fx-text-fill: white; -fx-font-size: 40px; -fx-font-weight: bold;");

        this.getChildren().add(inscrireLabel);

        afficherActivite(listActivites.getActivitesNonInscrites());

        backButton = new Button("Retour");
        backButton.setStyle("-fx-background-color: #1B263B; -fx-text-fill: white;");

        this.getChildren().add(backButton);
        this.setStyle("-fx-background-color: #0D1B2A; -fx-text-fill: white;");

    }
    public Button getBackButton() {
        return backButton;
    }

    private void afficherActivite(List<Activite> listActivites) {

        for (Activite activite : listActivites) {
            Label nomActiviteLabel = new Label(activite.getNom());
            nomActiviteLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20px;");

            Label dateDebutLabel = new Label("Début: " + activite.getDateDebut().toString());
            dateDebutLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");

            Label dateFinLabel = new Label("Fin: " + activite.getDateFin().toString());
            dateFinLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");

            VBox dateVBox = new VBox(5, dateDebutLabel, dateFinLabel);
            dateVBox.setAlignment(Pos.CENTER);

            Button button = new Button("S'inscrire");
            button.setStyle("-fx-background-color: #0466C8; -fx-text-fill: white;");
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

            this.getChildren().addAll(vbox);
        }
    }

    private void afficherMesActivites(List<Activite> listActivites) {
        for (Activite activite : listActivites) {
            Label nomActiviteLabel = new Label(activite.getNom());
            nomActiviteLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20px;");

            Label dateDebutLabel = new Label("Début: " + activite.getDateDebut().toString());
            dateDebutLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");

            Label dateFinLabel = new Label("Fin: " + activite.getDateFin().toString());
            dateFinLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");

            VBox dateVBox = new VBox(5, dateDebutLabel, dateFinLabel);
            dateVBox.setAlignment(Pos.CENTER);

            Button button = new Button("Se désinscrire");
            button.setStyle("-fx-background-color: #E94343; -fx-text-fill: white;");
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

            this.getChildren().addAll(vbox);
        }
    }

    public void setDesinscriptionButtonAction(EventHandler<ActionEvent> handler) {
        for (Node node : this.getChildren()) {
            if (node instanceof VBox) {
                VBox vbox = (VBox) node;
                HBox hBox = (HBox) vbox.getChildren().get(1); // Index 1 contient HBox avec les labels et le bouton
                Button desinscriptionButton = (Button) hBox.getChildren().get(2); // Index 2 pour le bouton "Se désinscrire"
                if (desinscriptionButton.getText().equals("Se désinscrire")){
                    desinscriptionButton.setOnAction(handler);
                }
            }
        }
    }
    public void setInscriptionButtonAction(EventHandler<ActionEvent> handler) {
        for (Node node : this.getChildren()) {
            if (node instanceof VBox) {
                VBox vbox = (VBox) node;
                HBox hBox = (HBox) vbox.getChildren().get(1); // Index 1 contient HBox avec les labels et le bouton
                Button desinscriptionButton = (Button) hBox.getChildren().get(2); // Index 2 pour le bouton "Se désinscrire"
                if (desinscriptionButton.getText().equals("S'inscrire")){
                    desinscriptionButton.setOnAction(handler);
                }
            }
        }
    }
    public void rafraichirVue(Utilisateur utilisateur) {
        this.getChildren().clear(); // Effacer tous les enfants actuels de ActiviteMenuView

        Label titleLabel = new Label("Mes Activités");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 40px; -fx-font-weight: bold;");
        this.getChildren().add(titleLabel);

        GestionActivites gestionActivites = new GestionActivites(utilisateur);
        if (gestionActivites.getMesActivites() == null ) {
            Label inscriptionLabel = new Label("Inscrit a aucune activite");
            this.getChildren().add(inscriptionLabel);
        }
        else {
            afficherMesActivites(gestionActivites.getMesActivites());
        }


        Label inscrireLabel = new Label("S'inscrire à une activité");
        inscrireLabel.setStyle("-fx-text-fill: white; -fx-font-size: 40px; -fx-font-weight: bold;");
        this.getChildren().add(inscrireLabel);


        afficherActivite(gestionActivites.getActivitesNonInscrites());

        this.getChildren().add(backButton); // Ajouter à nouveau le bouton "Retour"
        this.setStyle("-fx-background-color: #0D1B2A; -fx-text-fill: white;");
    }

}
