package ift2255.robotix.Controller.Fournisseur;

import ift2255.robotix.Controller.LoginController;
import ift2255.robotix.Modeles.Fournisseur;
import ift2255.robotix.View.Fournisseur.MenuFournisseurView;
import ift2255.robotix.View.Fournisseur.ProfilFournisseurView;
import ift2255.robotix.View.LoginView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuFournisseurController {
    private MenuFournisseurView view;
    private Stage stage;
    private Fournisseur fournisseur;

    public MenuFournisseurController(Stage stage, MenuFournisseurView view, Fournisseur fournisseur){
        this.view = view;
        this.stage = stage;
        this.fournisseur = fournisseur;

        this.view.getProfilMenuButton().setOnAction(e -> navigateToProfil());
        this.view.getMesComposantsMenuButton().setOnAction(e -> navigateToMesComposants());
        this.view.getEnregistrerComposantsMenuButton().setOnAction(e -> navigateToEnregistrer());
        this.view.getLogoutMenuButton().setOnAction(e -> returnToLoginMenu());
        this.view.getExitMenuButton().setOnAction(e -> System.exit(0));

    }

    private void navigateToProfil(){
        ProfilFournisseurView profilFournisseurView = new ProfilFournisseurView(fournisseur);
        ProfilFournisseurController profilFournisseurController = new ProfilFournisseurController(stage, profilFournisseurView, fournisseur);
        stage.setScene(new Scene(profilFournisseurView, 900, 700));
    }
    private void navigateToMesComposants(){

    }
    private void navigateToEnregistrer(){

    }
    private void returnToLoginMenu(){
        LoginView loginView = new LoginView();
        LoginController loginController = new LoginController(stage, loginView);
        stage.setScene(new Scene(loginView, 900, 700));
    }
}
