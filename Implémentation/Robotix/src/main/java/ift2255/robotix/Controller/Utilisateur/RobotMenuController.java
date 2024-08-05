package ift2255.robotix.Controller.Utilisateur;

import ift2255.robotix.Controller.Utilisateur.MenuController;
import ift2255.robotix.Modeles.GestionRobots;
import ift2255.robotix.Modeles.Robot;
import ift2255.robotix.Modeles.RegisterUtilisateur;
import ift2255.robotix.Modeles.Utilisateur;
import ift2255.robotix.View.Utilisateur.AjouterRobotView;
import ift2255.robotix.View.Utilisateur.MenuView;
import ift2255.robotix.View.Utilisateur.RobotMenuView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.List;

/**
 * Contrôleur pour gérer les actions dans le menu des robots.
 * Permet d'afficher, modifier et supprimer les robots associés à l'utilisateur courant.
 */
public class RobotMenuController {
    private Stage stage;
    private RobotMenuView view;
    private GestionRobots gestionRobots;
    private ObservableList<Robot> robotsObservableList;
    private Utilisateur utilisateur;

    /**
     * Constructeur pour initialiser le contrôleur du menu des robots.
     *
     * @param stage La scène principale de l'application.
     * @param view La vue du menu des robots.
     */
    public RobotMenuController(Stage stage, RobotMenuView view) {
        this.stage = stage;
        this.view = view;
        this.gestionRobots = new GestionRobots(); // Assumes GestionRobots can be instantiated here.
        this.utilisateur = RegisterUtilisateur.getInstance().getUtilisateur(); // Get the current user

        // initialisation de la listView et de l'observable List
        robotsObservableList = FXCollections.observableArrayList();
        view.getRobotListView().setItems(FXCollections.observableArrayList());

        // configuration des actions des boutons
        initializeButtonActions();

        // afficher les robots pour l'utilisateur courant
        chargerEtAfficherRobots(utilisateur.getEmail());
    }

    /**
     * Initialise les actions des boutons dans la vue du menu des robots.
     */
    private void initializeButtonActions() {
        view.getAddButton().setOnAction(e -> ajouterRobot());
        view.getDeleteButton().setOnAction(e -> supprimerRobot());
        view.getDisplayButtonComplete().setOnAction(e -> afficherEtatRobot());
        view.getDisplayButtonGeneral().setOnAction(e-> afficherEtatRobotGeneral());
        view.getExitMenuButton().setOnAction(e -> quitterMenu());
        view.getBackButton().setOnAction(e -> retournerMenu());
    }

    /**
     * Affiche les robots dans la liste observable.
     *
     * @param robots Liste des robots à afficher.
     */
    public void afficherRobots(List<Robot> robots) {
        ObservableList<String> robotNames = FXCollections.observableArrayList();
        for (Robot robot : robots) {
            robotNames.add(robot.getNom());
        }
        view.getRobotListView().setItems(robotNames);
    }

    /**
     * Supprime le robot sélectionné dans la vue.
     */
    private void supprimerRobot() {
        String selected = view.getRobotListView().getSelectionModel().getSelectedItem();
        if (selected != null) {
            Robot robot = gestionRobots.getRobotByName(selected, utilisateur.getEmail());
            if (robot != null) {
                gestionRobots.supprimerRobot(robot.getId(), robot.getUtilisateurEmail());
                view.getRobotListView().getItems().remove(selected);

                // Afficher une alerte de confirmation
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Suppression");
                alert.setHeaderText(null);
                alert.setContentText("Suppression effectuée du robot: '" + selected + "'!");
                alert.show();
            }
        }
    }

    /**
     * Affiche l'état general du robot sélectionné.
     */
    private void afficherEtatRobot() {
        String selected = view.getRobotListView().getSelectionModel().getSelectedItem();
        if (selected != null) {
            Robot robot = gestionRobots.getRobotByName(selected, utilisateur.getEmail());
            if (robot != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("État du robot");
                alert.setHeaderText("Détails du Robot:");
                alert.setContentText("Numéro de série : " + robot.getNumeroSerie() + "\n" +
                                     "Nom : " + robot.getNom() + "\n" +
                                     "Type : " + robot.getType() + "\n" +
                                     "Position : " + robot.getPosition() + "\n" +
                                     "Vitesse : " + robot.getVitesse() + " m/s\n" +
                                     "Niveau de batterie : " + robot.getNiveauBatterie() + " %\n" +
                                     "Consommation CPU : " + robot.getConsommationCPU() + " %\n" +
                                     "Consommation Mémoire : " + robot.getConsommationMemoire() + " MB\n" +
                                     "Email de l'utilisateur : " + robot.getUtilisateurEmail());
                alert.show();
            }
        }
    }
    /**
     * Affiche l'état general du robot sélectionné.
     */
    private void afficherEtatRobotGeneral() {
        String selected = view.getRobotListView().getSelectionModel().getSelectedItem();
        if (selected != null) {
            Robot robot = gestionRobots.getRobotByName(selected, utilisateur.getEmail());
            if (robot != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("État du robot");
                alert.setHeaderText("Détails du Robot:");
                alert.setContentText(
                                     "Nom : " + robot.getNom() + "\n" +
                                     "Type : " + robot.getType() + "\n" +
                                     
                                     "Niveau de batterie : " + robot.getNiveauBatterie() + " %\n");
                alert.show();
            }
        }
    }

    /**
     * Ajoute un nouveau robot.
     */
    private void ajouterRobot() {
        AjouterRobotView ajouterRobotView = new AjouterRobotView();
        AjouterRobotController ajouterRobotController = new AjouterRobotController(stage, ajouterRobotView, gestionRobots);
        stage.setScene(new Scene(ajouterRobotView, 600, 400));
    }

    /**
     * Quitte le menu.
     */
    private void quitterMenu() {
        stage.close();
    }

    /**
     * Retourne au menu principal.
     */
    private void retournerMenu() {
        MenuView menu = new MenuView();
        MenuController menuController = new MenuController(stage, menu);
        stage.setScene(new Scene(menu, 900, 700));
    }

    /**
     * Charge et affiche les robots pour l'utilisateur donné.
     *
     * @param utilisateurEmail L'email de l'utilisateur dont les robots doivent être chargés.
     */
    private void chargerEtAfficherRobots(String utilisateurEmail) {
        List<Robot> robots = gestionRobots.chargerRobots();
        afficherRobots(robots);
    }
}