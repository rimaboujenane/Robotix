package ift2255.robotix.View.Fournisseur;

import ift2255.robotix.Modeles.NotifFournisseur;
import ift2255.robotix.Modeles.NotifService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * Vue représentant les notifications pour un fournisseur.
 * Cette classe étend VBox et affiche les notifications spécifiques
 * à un fournisseur, avec la possibilité de supprimer chaque notification.
 */
public class NotifFournisseurView extends VBox {
    private Button backButton;
    private String fournisseurEmail; // Email du fournisseur pour filtrer les notifications

    /**
     * Constructeur de la classe NotifFournisseurView.
     *
     * @param fournisseurEmail L'email du fournisseur pour filtrer les notifications affichées.
     */
    public NotifFournisseurView(String fournisseurEmail) {
        this.fournisseurEmail = fournisseurEmail;
        afficher();
    }

    /**
     * Affiche les notifications du fournisseur en cours.
     * Réinitialise le contenu de la vue et met en place les composants visuels.
     */
    public void afficher() {
        this.getChildren().clear();
        this.setAlignment(Pos.TOP_CENTER); // Aligner tout le contenu au centre
        this.setSpacing(10); // Espacement entre les éléments

        Label titleLabel = new Label("Mes Notifications");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 40px; -fx-font-weight: bold;");

        ScrollPane scrollPane = new ScrollPane(afficherNotif());
        scrollPane.setStyle("-fx-background: #0D1B2A; -fx-background-color: #0D1B2A;");

        backButton = new Button("Retour");
        backButton.setStyle("-fx-background-color: #1B263B; -fx-text-fill: white;");

        this.getChildren().addAll(titleLabel, scrollPane, backButton);
        this.setStyle("-fx-background-color: #0D1B2A; -fx-text-fill: white;");
    }

    /**
     * Retourne le bouton de retour à la vue du menu.
     *
     * @return Le bouton de retour.
     */
    public Button getBackButton() {
        return backButton;
    }

    /**
     * Crée et retourne une VBox contenant les notifications du fournisseur filtrées
     * par l'email du fournisseur. Les notifications sont affichées avec un bouton de suppression.
     *
     * @return Une VBox contenant les notifications à afficher.
     */
    public VBox afficherNotif() {
        List<NotifFournisseur> notificationsFournisseur = NotifService.getInstance().getNotifFournisseur();
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER); // Aligner les notifications au centre

        if (notificationsFournisseur.isEmpty()) {
            Label notifText = new Label("Aucune notification");
            notifText.setStyle("-fx-text-fill: white; -fx-font-size: 15px;");
            vbox.getChildren().add(notifText);
        } else {
            for (NotifFournisseur notification : notificationsFournisseur) {
                if (notification.getEmailFournisseur().equals(fournisseurEmail)) { // Filtrer par email du fournisseur
                    HBox hBox = new HBox();
                    hBox.setAlignment(Pos.CENTER);
                    Label notifText = new Label(notification.getMessage());
                    notifText.setStyle("-fx-text-fill: white; -fx-font-size: 15px;");

                    ImageView xButtonImage = new ImageView("file:src/main/resources/images/xButton.png");
                    xButtonImage.setFitHeight(20);
                    xButtonImage.setFitWidth(20);

                    Button notifButton = new Button();
                    notifButton.setGraphic(xButtonImage);
                    notifButton.setStyle("-fx-background-color: #1B263B");
                    notifButton.setAlignment(Pos.CENTER_RIGHT);
                    notifButton.setUserData(notification);

                    hBox.getChildren().addAll(notifText, notifButton);
                    hBox.setAlignment(Pos.CENTER);
                    hBox.setStyle("-fx-background-color: #1B263B; -fx-padding: 15px; -fx-border-radius: 5px; -fx-background-radius: 5px;");
                    hBox.setPrefWidth(700);
                    hBox.setMaxWidth(700);

                    Region separator = new Region();
                    separator.setStyle("-fx-border-color: black; -fx-border-width: 0 0 0 1;");
                    separator.setPrefHeight(10);

                    vbox.getChildren().addAll(hBox, separator);
                }
            }
        }
        return vbox;
    }

    /**
     * Définit le gestionnaire d'événements pour les boutons de suppression des notifications.
     *
     * @param handler Le gestionnaire d'événements à appliquer aux boutons de suppression.
     */
    public void setButtonAction(EventHandler<ActionEvent> handler) {
        ScrollPane scrollPane = (ScrollPane) this.getChildren().get(1);
        VBox contentBox = (VBox) scrollPane.getContent();

        for (Node node : contentBox.getChildren()) {
            if (node instanceof HBox) {
                HBox hBox = (HBox) node;
                if (hBox.getChildren().size() > 1 && hBox.getChildren().get(1) instanceof Button) {
                    Button button = (Button) hBox.getChildren().get(1);
                    button.setOnAction(handler);
                }
            }
        }
    }
}
