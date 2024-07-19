package ift2255.robotix.View.Utilisateur;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class NotifMenuView extends VBox {
    private Button backButton;

    public NotifMenuView() {
        Label label = new Label("Mes Notifications");
        label.setStyle("-fx-text-fill: white;");
        backButton = new Button("Retour");

        this.getChildren().addAll(label, backButton);
        this.setStyle("-fx-background-color: #0D1B2A; -fx-text-fill: white;");
    }

    public Button getBackButton() {
        return backButton;
    }
}
