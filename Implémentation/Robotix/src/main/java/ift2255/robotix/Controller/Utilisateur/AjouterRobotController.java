package ift2255.robotix.Controller.Utilisateur;

import ift2255.robotix.Modeles.Robot;
import ift2255.robotix.Modeles.GestionRobots;
import ift2255.robotix.View.Utilisateur.RobotMenuView;
import ift2255.robotix.View.Utilisateur.AjouterRobotView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Contrôleur pour l'ajout des robots. Gère les interactions de l'utilisateur
 * avec la vue d'ajout des robots.
 */
public class AjouterRobotController {
    private Stage stage;
    private AjouterRobotView view;
    private GestionRobots gestionRobots;

    /**
     * Constructeur pour initialiser le contrôleur.
     *
     * @param stage La scène principale de l'application.
     * @param view  La vue d'ajout des robots.
     * @param gestionRobots La gestion des robots.
     */
    public AjouterRobotController(Stage stage, AjouterRobotView view, GestionRobots gestionRobots){
        this.stage = stage;
        this.view = view;
        this.gestionRobots = gestionRobots;

        // On ajoute un gestionnaire d'événements pour le bouton d'enregistrement
        this.view.getAjouterButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                ajouterRobot();
            }
        });

        // On ajoute un gestionnaire d'événements pour le bouton de retour
        this.view.getRetourButton().setOnAction(e -> retourAuMenu());
    }

    /**
     * Ajoute un nouveau robot après validation des champs.
     */
    private void ajouterRobot() {
        // Récupération des valeurs des champs
        String nom = view.getNomField().getText();
        String numeroSerie = view.getNumeroSerieField().getText();
        String type = view.getTypeField().getText();
        String position = view.getPositionField().getText();
        String vitesseText = view.getVitesseField().getText();
        String niveauBatterieText = view.getNiveauBatterieField().getText();
        String consommationCPUText = view.getConsommationCPUField().getText();
        String consommationMemoireText = view.getConsommationMemoireField().getText();

        // Vérification que tous les champs sont remplis
        if (nom.isEmpty() || numeroSerie.isEmpty() || type.isEmpty() || position.isEmpty() ||
                vitesseText.isEmpty() || niveauBatterieText.isEmpty() || consommationCPUText.isEmpty() || consommationMemoireText.isEmpty()) {
            showAlert("Erreur", "Tous les champs doivent être remplis.");
            return;
        }

        try {
            // Conversion des valeurs des champs
            double vitesse = Double.parseDouble(vitesseText);
            int niveauBatterie = Integer.parseInt(niveauBatterieText);
            double consommationCPU = Double.parseDouble(consommationCPUText);
            double consommationMemoire = Double.parseDouble(consommationMemoireText);

            // Création du robot et ajout
            Robot robot = new Robot(gestionRobots.getNextId(), numeroSerie, nom, type, position, vitesse, niveauBatterie, consommationCPU, consommationMemoire);
            gestionRobots.ajouterRobot(robot);

            retourAuMenu();
        } catch (NumberFormatException e) {
            // Affiche un message d'erreur en cas de format de nombre incorrect
            showAlert("Erreur de format", "Vérifiez les formats des nombres.");
        }
    }

    /**
     * Affiche une boîte de dialogue d'alerte avec le message spécifié.
     *
     * @param title Le titre de l'alerte.
     * @param message Le message de l'alerte.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Retourne au menu des robots.
     */
    private void retourAuMenu(){
        RobotMenuView robotMenuView = new RobotMenuView();
        RobotMenuController robotMenuController = new RobotMenuController(stage, robotMenuView);
        stage.setScene(new Scene(robotMenuView, 900, 700));
    }
}
