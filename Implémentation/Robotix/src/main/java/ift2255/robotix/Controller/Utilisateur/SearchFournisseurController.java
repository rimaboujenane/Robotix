package ift2255.robotix.Controller.Utilisateur;

import ift2255.robotix.Modeles.*;
import ift2255.robotix.View.Utilisateur.MenuView;
import ift2255.robotix.View.Utilisateur.SearchFournisseurView;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;

public class SearchFournisseurController {
    private SearchFournisseurView searchFournMenuView;
    private Stage stage;
    private GestionFournisseurs fournisseurs;
    private GestionComposantes composantes;
    private Utilisateur utilisateur = RegisterUtilisateur.getInstance().getUtilisateur();

    public SearchFournisseurController(Stage stage, SearchFournisseurView searchFournMenuView) {
        this.searchFournMenuView = searchFournMenuView;
        this.stage = stage;
        fournisseurs = new GestionFournisseurs();
        composantes = new GestionComposantes();
        reloadFournisseurs();

        // Initialisation du contrôleur dans la vue
        this.searchFournMenuView.setController(this);
    }

    public void init() {
        this.searchFournMenuView.getBackButton().setOnAction(e -> goBackMenu());
        this.searchFournMenuView.getTypeComboBox().addEventHandler(ComboBox.ON_HIDDEN, event -> {
            reloadFournisseurs(this.searchFournMenuView.getType());
        });
    }

    public void reloadFournisseurs() {
        HashMap<Fournisseur, List<Composante>> i = new HashMap<>();
        for (Fournisseur f : fournisseurs.getFournisseurMap().values()) {
            i.put(f, composantes.chargerComposantes(f.getEmail()));
        }
        this.searchFournMenuView.afficherVue(i);
        init();
    }

    public void reloadFournisseurs(String type) {
        HashMap<Fournisseur, List<Composante>> i = new HashMap<>();
        for (Fournisseur f : fournisseurs.getFournisseurMap().values()) {
            List<Composante> comps = composantes.chargerComposantes(f.getEmail());
            for (Composante c : comps) {
                if (c.getType().equals(type)) {
                    i.put(f, List.of(c));
                    break;
                }
            }
        }
        this.searchFournMenuView.afficherVue(i);
        init();
    }

    public void handlePurchase(Composante composante, Fournisseur fournisseur) {
        // Supprimer la composante du gestionnaire
        composantes.supprimerComposante(composante.getId(), fournisseur.getEmail());

        // Envoyer une notification au fournisseur
        NotifService.getInstance().sendNotifFournisseur(
                fournisseur.getEmail(),
                "La composante " + composante.getNom() + " a été achetée par l'utilisateur " + utilisateur.getEmail() + "."
        );

        // Rafraîchir la vue
        reloadFournisseurs();
    }

    private void goBackMenu() {
        MenuView menuView = new MenuView();
        MenuController menuController = new MenuController(stage, menuView);
        stage.setScene(new Scene(menuView, 900, 700));
    }
}
