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
 * Cette classe étend {@link VBox} et fournit une interface utilisateur pour gérer les robots. Elle inclut des boutons
 * pour ajouter, supprimer, afficher les détails des robots et quitter le menu. Elle contient également une vue sous forme
 * de liste pour afficher les robots existants.
 */
public class RobotMenuView extends VBox {
    private Button backButton;
    private Button addButton;
    private Button deleteButton;
    private Button displayButtonComplete;
    private Button displayButtonGeneral;
    private Button exitMenuButton;
    private ListView<String> robotListView;

    /**
     * Constructeur pour initialiser les composants de la vue du menu des robots.
     * Configure les boutons, la liste des robots, et la mise en page.
     */
    public RobotMenuView() {
        // Titre de la section
        Label label = new Label("Mes robots");
        label.setStyle("-fx-text-fill: white;");

        // Initialisation des boutons
        backButton = new Button("Retour");
        addButton = new Button("Ajouter");
        deleteButton = new Button("Supprimer");
        displayButtonComplete = new Button("Afficher état complet");
        displayButtonGeneral = new Button("Afficher état général");
        exitMenuButton = new Button("Quitter");

        // ListView pour afficher la liste des robots
        robotListView = new ListView<>();
        robotListView.setPrefHeight(200);

        // Mise en page des boutons
        HBox buttonBox = new HBox(10, addButton, deleteButton, displayButtonComplete, displayButtonGeneral, exitMenuButton);
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
     * Retourne le bouton pour revenir en arrière.
     *
     * @return Le bouton pour revenir en arrière.
     */
    public Button getBackButton() {
        return backButton;
    }

    /**
     * Retourne le bouton pour ajouter un robot.
     *
     * @return Le bouton pour ajouter un robot.
     */
    public Button getAddButton() {
        return addButton;
    }

    /**
     * Retourne le bouton pour supprimer un robot.
     *
     * @return Le bouton pour supprimer un robot.
     */
    public Button getDeleteButton() {
        return deleteButton;
    }

    /**
     * Retourne le bouton pour afficher l'état complet d'un robot.
     *
     * @return Le bouton pour afficher l'état complet d'un robot.
     */
    public Button getDisplayButtonComplete() {
        return displayButtonComplete;
    }

    /**
     * Retourne le bouton pour afficher l'état général d'un robot.
     *
     * @return Le bouton pour afficher l'état général d'un robot.
     */
    public Button getDisplayButtonGeneral() {
        return displayButtonGeneral;
    }

    /**
     * Retourne la liste des robots sous forme de {@link ListView}.
     *
     * @return La liste des robots.
     */
    public ListView<String> getRobotListView() {
        return robotListView;
    }

    /**
     * Retourne le bouton pour quitter le menu.
     *
     * @return Le bouton pour quitter le menu.
     */
    public Button getExitMenuButton() {
        return exitMenuButton;
    }
}
