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

/**
 * Contrôleur pour la vue de recherche des fournisseurs.
 * Permet la gestion et la recherche des fournisseurs disponibles en fonction des critères de filtrage.
 */
public class SearchFournisseurController {
    private SearchFournisseurView searchFournMenuView;
    private Stage stage;
    private GestionFournisseurs fournisseurs;
    private GestionComposantes composantes;
    private Utilisateur utilisateur = RegisterUtilisateur.getInstance().getUtilisateur();

    /**
     * Constructeur pour initialiser le contrôleur de recherche des fournisseurs.
     *
     * @param stage La scène principale de l'application.
     * @param searchFournMenuView La vue de recherche des fournisseurs.
     */
    public SearchFournisseurController(Stage stage, SearchFournisseurView searchFournMenuView) {
        this.searchFournMenuView = searchFournMenuView;
        this.stage = stage;
        fournisseurs = new GestionFournisseurs();
        composantes = new GestionComposantes();
        reloadFournisseurs();

        // Initialisation du contrôleur dans la vue
        this.searchFournMenuView.setController(this);
    }

    /**
     * Initialise les actions des éléments de la vue de recherche des fournisseurs.
     */
    public void init() {
        this.searchFournMenuView.getBackButton().setOnAction(e -> goBackMenu());
        this.searchFournMenuView.getTypeComboBox().addEventHandler(ComboBox.ON_HIDDEN, event -> {
            reloadFournisseurs(this.searchFournMenuView.getType());
        });
    }

    /**
     * Rechargera les fournisseurs et les affichera dans la vue.
     */
    public void reloadFournisseurs() {
        HashMap<Fournisseur, List<Composante>> i = new HashMap<>();
        for (Fournisseur f : fournisseurs.getFournisseurMap().values()) {
            i.put(f, composantes.chargerComposantes(f.getEmail()));
        }
        this.searchFournMenuView.afficherVue(i);
        init();
    }

    /**
     * Rechargera les fournisseurs en fonction du type donné et les affichera dans la vue.
     *
     * @param type Le type de composante pour filtrer les fournisseurs.
     */
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

    /**
     * Gère l'achat d'une composante et notifie le fournisseur.
     *
     * @param composante La composante à acheter.
     * @param fournisseur Le fournisseur de la composante.
     */
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

    /**
     * Retourne au menu principal de l'utilisateur.
     */
    private void goBackMenu() {
        MenuView menuView = new MenuView();
        MenuController menuController = new MenuController(stage, menuView);
        stage.setScene(new Scene(menuView, 900, 700));
    }
}
