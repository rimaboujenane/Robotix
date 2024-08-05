package ift2255.robotix.View.Utilisateur;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class RobotMenuView extends VBox{
    private Button backButton;

    public RobotMenuView() {
        Label label = new Label("Mes robots");
        label.setStyle("-fx-text-fill: white;");
        backButton = new Button("Retour");

        this.getChildren().addAll(label, backButton);
        this.setStyle("-fx-background-color: #0D1B2A; -fx-text-fill: white;");
    }

    public Button getBackButton() {
        return backButton;
    }
}
