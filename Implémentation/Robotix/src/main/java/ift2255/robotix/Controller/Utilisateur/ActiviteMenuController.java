package ift2255.robotix.Controller.Utilisateur;

import ift2255.robotix.Modeles.Activite;
import ift2255.robotix.Modeles.GestionActivites;
import ift2255.robotix.Modeles.Utilisateur;
import ift2255.robotix.View.Utilisateur.ActiviteMenuView;
import ift2255.robotix.View.Utilisateur.MenuView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ActiviteMenuController {
    private ActiviteMenuView activiteMenuView;
    private Stage stage;
    private Utilisateur utilisateur;

    public ActiviteMenuController(Stage stage,ActiviteMenuView activiteMenuView,Utilisateur utilisateur) {
        this.activiteMenuView = activiteMenuView;
        this.stage = stage;
        this.utilisateur = utilisateur;


        this.activiteMenuView.getBackButton().setOnAction(e -> goBackMenu());

        // Définir l'action pour "Se désinscrire"
        this.activiteMenuView.setButtonAction("S'inscrire", event -> handleInscription((Activite) ((Button) event.getSource()).getUserData()));
        this.activiteMenuView.setButtonAction("Se désinscrire", event -> handleDesinscription((Activite) ((Button) event.getSource()).getUserData()));

    }
    public void handleDesinscription(Activite activite){
        GestionActivites activites = new GestionActivites(utilisateur);
        activites.desinscription(activite);
        activiteMenuView.afficherVue(utilisateur);

        this.activiteMenuView.getBackButton().setOnAction(e -> goBackMenu());
        this.activiteMenuView.setButtonAction("S'inscrire", event -> handleInscription((Activite) ((Button) event.getSource()).getUserData()));
        this.activiteMenuView.setButtonAction("Se désinscrire", event -> handleDesinscription((Activite) ((Button) event.getSource()).getUserData()));
    }
    public void handleInscription(Activite activite){
        GestionActivites activites = new GestionActivites(utilisateur);
        activites.inscription(activite, utilisateur);
        activiteMenuView.afficherVue(utilisateur);

        this.activiteMenuView.getBackButton().setOnAction(e -> goBackMenu());
        this.activiteMenuView.setButtonAction("S'inscrire", event -> handleInscription((Activite) ((Button) event.getSource()).getUserData()));
        this.activiteMenuView.setButtonAction("Se désinscrire", event -> handleDesinscription((Activite) ((Button) event.getSource()).getUserData()));
    }

    public void goBackMenu() {
        MenuView menuView = new MenuView(utilisateur);
        MenuController menuController = new MenuController(stage, menuView, utilisateur);
        stage.setScene(new Scene(menuView, 900, 700));
    }
}
