package ift2255.robotix.View.Utilisateur;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Classe représentant la vue du menu des robots pour l'utilisateur.
 */
public class RobotMenuView extends VBox {
    private Button backButton;
    private Button addButton;
    private Button deleteButton;
    private Button displayButton;
    private Button exitMenuButton;
    private ListView<String> robotListView;

    /**
     * Constructeur pour initialiser les composants de la vue du menu des robots.
     */
    public RobotMenuView() {
        // Titre de la section
        Label label = new Label("Mes robots");
        label.setStyle("-fx-text-fill: white;");

        // Initialisation des boutons
        backButton = new Button("Retour");
        addButton = new Button("Ajouter");
        deleteButton = new Button("Supprimer");
        displayButton = new Button("Afficher état");
        exitMenuButton = new Button("Quitter");

        // ListView pour afficher la liste des robots
        robotListView = new ListView<>();
        robotListView.setPrefHeight(200);

        // Mise en page des boutons
        HBox buttonBox = new HBox(10, addButton, deleteButton, displayButton, exitMenuButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10, 0, 10, 0));

        // Ajout des éléments à VBox
        this.getChildren().addAll(label, robotListView, buttonBox, backButton);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
        this.setPadding(new Insets(20));
        this.setStyle("-fx-background-color: #0D1B2A; -fx-text-fill: white;");
    }

    /**
     * @return Le bouton pour revenir en arrière.
     */
    public Button getBackButton() {
        return backButton;
    }

    /**
     * @return Le bouton pour ajouter un robot.
     */
    public Button getAddButton() {
        return addButton;
    }

    /**
     * @return Le bouton pour supprimer un robot.
     */
    public Button getDeleteButton() {
        return deleteButton;
    }

    /**
     * @return Le bouton pour afficher l'état d'un robot.
     */
    public Button getDisplayButton() {
        return displayButton;
    }

    /**
     * @return La liste des robots sous forme de ListView.
     */
    public ListView<String> getRobotListView() {
        return robotListView;
    }

    /**
     * @return Le bouton pour quitter le menu.
     */
    public Button getExitMenuButton() {
        return exitMenuButton;
    }
}
