package ift2255.robotix.View.Utilisateur;

import ift2255.robotix.Modeles.Activite;
import ift2255.robotix.Modeles.GestionActivites;
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

/**
 * La classe ActiviteMenuView représente une vue dans une application JavaFX
 * pour afficher et gérer les activités d'un utilisateur.
 */
public class ActiviteMenuView extends VBox {
    private Button backButton;
    private GestionActivites activites;

    /**
     * Constructeur par défaut qui initialise la vue en appelant afficherVue().
     */
    public ActiviteMenuView() {
        afficherVue();
    }

    /**
     * Retourne le bouton de retour.
     *
     * @return le bouton de retour.
     */
    public Button getBackButton() {
        return backButton;
    }

    /**
     * Affiche la vue des activités en effaçant les enfants actuels et en ajoutant
     * les composants nécessaires.
     */
    public void afficherVue() {
        this.getChildren().clear(); // Effacer tous les enfants actuels de ActiviteMenuView

        GestionActivites listActivites = new GestionActivites();

        // Création d'un VBox pour contenir les éléments de la vue
        VBox contentBox = new VBox(10);
        contentBox.setStyle("-fx-padding: 20; -fx-background-color: #0D1B2A; -fx-text-fill: white;");

        // Création et stylisation du label de titre
        Label titleLabel = new Label("Mes Activités");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 40px; -fx-font-weight: bold;");
        contentBox.getChildren().add(titleLabel);

        // Affichage des activités auxquelles l'utilisateur est inscrit
        afficherActivite(listActivites.getMesActivites(), contentBox, "Se désinscrire", "#E94343");

        // Création et stylisation du label pour s'inscrire à une nouvelle activité
        Label inscrireLabel = new Label("S'inscrire à une activité");
        inscrireLabel.setStyle("-fx-text-fill: white; -fx-font-size: 40px; -fx-font-weight: bold;");
        contentBox.getChildren().add(inscrireLabel);

        // Affichage des activités auxquelles l'utilisateur n'est pas encore inscrit
        afficherActivite(listActivites.getActivitesNonInscrites(), contentBox, "S'inscrire", "#0466C8");

        // Création et stylisation du bouton de retour
        backButton = new Button("Retour");
        backButton.setStyle("-fx-background-color: #1B263B; -fx-text-fill: white;");
        contentBox.getChildren().add(backButton);

        // Ajout d'un ScrollPane pour permettre le défilement des activités
        ScrollPane scrollPane = new ScrollPane(contentBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: #0D1B2A;");

        // Ajout du ScrollPane à la vue principale
        this.getChildren().add(scrollPane);
        this.setStyle("-fx-background-color: #0D1B2A; -fx-text-fill: white;");
    }

    /**
     * Affiche les activités dans la contentBox avec un bouton associé à chaque activité.
     *
     * @param listActivites la liste des activités à afficher.
     * @param contentBox le conteneur VBox où les activités seront affichées.
     * @param boutonTexte le texte à afficher sur le bouton pour chaque activité.
     * @param boutonCouleur la couleur de fond du bouton.
     */
    private void afficherActivite(List<Activite> listActivites, VBox contentBox, String boutonTexte, String boutonCouleur) {
        for (Activite activite : listActivites) {
            // Création et stylisation des labels pour le nom et les dates de l'activité
            Label nomActiviteLabel = new Label(activite.getNom());
            nomActiviteLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20px;");

            Label dateDebutLabel = new Label("Début: " + activite.getDateDebut().toString());
            dateDebutLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");

            Label dateFinLabel = new Label("Fin: " + activite.getDateFin().toString());
            dateFinLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");

            // Création d'un VBox pour contenir les labels de dates
            VBox dateVBox = new VBox(5, dateDebutLabel, dateFinLabel);
            dateVBox.setAlignment(Pos.CENTER);

            // Création et stylisation du bouton pour l'activité
            Button button = new Button(boutonTexte);
            button.setStyle("-fx-background-color: " + boutonCouleur + "; -fx-text-fill: white;");
            button.setUserData(activite);

            // Création d'un HBox pour contenir les éléments de l'activité
            HBox hBox = new HBox(100, nomActiviteLabel, dateVBox, button);
            hBox.setAlignment(Pos.CENTER);
            hBox.setStyle("-fx-background-color: #1B263B; -fx-padding: 10px;");
            hBox.setPrefWidth(700);
            hBox.setMaxWidth(700);

            // Création d'un séparateur pour séparer visuellement les activités
            Region separator = new Region();
            separator.setStyle("-fx-border-color: black; -fx-border-width: 0 0 0 1;"); // Bordure noire sur la gauche
            separator.setPrefHeight(10); // Même hauteur que les HBox

            // Ajout du séparateur et du HBox à un VBox
            VBox vbox = new VBox(separator, hBox);
            vbox.setAlignment(Pos.CENTER);

            // Ajout de l'activité à la contentBox
            contentBox.getChildren().add(vbox);
        }
    }

    /**
     * Définit l'action du bouton avec un texte spécifique dans la vue.
     *
     * @param buttonText le texte du bouton auquel l'action doit être associée.
     * @param handler le gestionnaire d'événements à associer au bouton.
     */
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
