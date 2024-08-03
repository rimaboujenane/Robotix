package ift2255.robotix.View.Utilisateur;

import ift2255.robotix.Modeles.NotifService;
import ift2255.robotix.Modeles.Notification;
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

public class NotifMenuView extends VBox {
    private Button backButton;

    public NotifMenuView() {
        afficher();
    }
    public void afficher(){
        this.getChildren().clear(); // Effacer tous les enfants actuels de ActiviteMenuView

        Label titleLabel = new Label("Mes Notifications");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 40px; -fx-font-weight: bold;");

        ScrollPane scrollPane = new ScrollPane(afficherNotif());
        scrollPane.setStyle("-fx-background: #0D1B2A; -fx-background-color: #0D1B2A;");



        backButton = new Button("Retour");
        backButton.setStyle("-fx-background-color: #1B263B; -fx-text-fill: white;");

        this.getChildren().addAll(titleLabel,scrollPane, backButton);
        this.setStyle("-fx-background-color: #0D1B2A; -fx-text-fill: white;");
    }

    public Button getBackButton() {
        return backButton;
    }

    public VBox afficherNotif(){
        List<Notification> notifications = NotifService.getInstance().getNotifications();
        VBox vbox = new VBox();

         if(notifications.isEmpty()){
             Label notifText = new Label("Aucune notification");
             notifText.setStyle("-fx-text-fill: white; -fx-font-size: 20px;");
             vbox.getChildren().add(notifText);
         }
         else {
             for (Notification notification : notifications) {
                 HBox hBox = new HBox();

                 Label notifText = new Label(notification.getMessage());
                 notifText.setStyle("-fx-text-fill: white; -fx-font-size: 20px;");

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
                 hBox.setStyle("-fx-background-color: #1B263B; -fx-padding: 10px;  -fx-border-radius: 5px; -fx-background-radius: 5px;");
                 hBox.setPrefWidth(700);
                 hBox.setMaxWidth(700);

                 Region separator = new Region();
                 separator.setStyle("-fx-border-color: black; -fx-border-width: 0 0 0 1;"); // Bordure noire sur la gauche
                 separator.setPrefHeight(10);

                 vbox.getChildren().addAll(hBox, separator);
             }
         }
        return vbox;
    }

    public void setButtonAction(EventHandler<ActionEvent> handler) {
        // Accéder au ScrollPane
        ScrollPane scrollPane = (ScrollPane) this.getChildren().get(1);
        VBox contentBox = (VBox) scrollPane.getContent();

        // Parcourir tous les enfants du VBox
        for (Node node : contentBox.getChildren()) {
            if (node instanceof HBox) {
                HBox hBox = (HBox) node;
                // Supposer que le bouton est le deuxième enfant dans le HBox
                if (hBox.getChildren().size() > 1 && hBox.getChildren().get(1) instanceof Button) {
                    Button button = (Button) hBox.getChildren().get(1);
                    button.setOnAction(handler);
                }
            }
        }
    }
}

