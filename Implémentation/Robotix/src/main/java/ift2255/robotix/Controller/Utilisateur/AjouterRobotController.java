package ift2255.robotix.Controller.Utilisateur;

import ift2255.robotix.Modeles.Robot;
import ift2255.robotix.Modeles.GestionRobots;
import ift2255.robotix.View.Utilisateur.RobotMenuView;
import ift2255.robotix.View.Utilisateur.AjouterRobotView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;


/*
 * Contrôleur pour l'ajout des robots. Gère les intéractions de l'utilsateur
 * avec la vue d'ajouter les robots
 */

public class AjouterRobotController {
    private Stage stage;
    private AjouterRobotView view;
    private GestionRobots gestionRobots;

    /*
     * Constructeur pour initialiser le contrôleur.
     *
     * @param stage Le stage principal de l'application.
     * @param view La vue d'jout des robots.
     * @param gestionRobots La gestion des robots.
     */
    public AjouterRobotController(Stage stage, AjouterRobotView view, GestionRobots gestionRobots){
        this.stage= stage;
        this.view= view;
        this.gestionRobots= gestionRobots;

        // On ajoute un gestionnaire d'event pour le bouton enregistrement
        this.view.getAjouterButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                ajouterRobot();
            }
        });

        // On ajoute un gestionnaire d'evenements pour le bouton de retour
        this.view.getRetourButton().setOnAction(e->retourAuMenu());
    }
    /*
     * Ajout d'un noiveau robot apres validation des champs
     */
    private void ajouterRobot(){
        try{
        String nom= view.getNomField().getText();
        String numeroSerie = view.getNumeroSerieField().getText();
        String type = view.getTypeField().getText();
        String position = view.getPositionField().getText();
        double vitesse = Double.parseDouble(view.getVitesseField().getText());
        int niveauBatterie = Integer.parseInt(view.getNiveauBatterieField().getText());
        double consommationCPU = Double.parseDouble(view.getConsommationCPUField().getText());
        double consommationMemoire = Double.parseDouble(view.getConsommationMemoireField().getText());
        //String utilisateurEmail = view.getUtilisateurEmailField().getText();

         Robot robot = new Robot(gestionRobots.getNextId(), numeroSerie, nom, type, position, vitesse, niveauBatterie, consommationCPU, consommationMemoire);
         gestionRobots.ajouterRobot(robot);

         retourAuMenu();
    }catch(NumberFormatException e){
        // affiche un message d'erreur ou un dialogue pour l'utilisateur
        System.err.println("Erreur de format de nombre: "+e.getMessage());
    }
}

private void retourAuMenu(){
    RobotMenuView robotMenuView= new RobotMenuView();
    RobotMenuController robotMenuController= new RobotMenuController(stage, robotMenuView);
    stage.setScene(new Scene(robotMenuView,900,700));
}
    
}
