package ift2255.robotix.Controller.Utilisateur;

import ift2255.robotix.Modeles.*;
import ift2255.robotix.View.Utilisateur.MenuView;
import ift2255.robotix.View.Utilisateur.SearchComposanteView;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contrôleur pour la vue de recherche des composantes.
 * Permet la gestion et la recherche des composantes disponibles en fonction des critères de filtrage.
 */
public class SearchComposanteController {
    private SearchComposanteView searchCompMenuView;
    private Stage stage;
    private HashMap<Fournisseur, List<Composante>> inv;
    private GestionFournisseurs fournisseurs;
    private GestionComposantes composantes;
    private Utilisateur utilisateur = RegisterUtilisateur.getInstance().getUtilisateur();

    /**
     * Constructeur pour initialiser le contrôleur de recherche de composantes.
     *
     * @param stage La scène principale de l'application.
     * @param searchCompMenuView La vue de recherche de composantes.
     */
    public SearchComposanteController(Stage stage, SearchComposanteView searchCompMenuView) {
        this.searchCompMenuView = searchCompMenuView;
        this.stage = stage;

        fournisseurs = new GestionFournisseurs();
        composantes = new GestionComposantes();
        reloadComposantes();

        // Initialisation du contrôleur dans la vue
        this.searchCompMenuView.setController(this);
    }

    /**
     * Initialise les actions des éléments de la vue de recherche des composantes.
     */
    public void init() {
        this.searchCompMenuView.getBackButton().setOnAction(e -> goBackMenu());
        this.searchCompMenuView.getTypeComboBox().addEventHandler(ComboBox.ON_HIDDEN, event -> {
            reloadComposantes(this.searchCompMenuView.getType());
        });
        this.searchCompMenuView.getFournComboBox().addEventHandler(ComboBox.ON_HIDDEN, event -> {
            reloadComposantes(this.searchCompMenuView.getFournisseur());
        });
    }

    /**
     * Rechargera les composantes pour chaque fournisseur et les affichera dans la vue.
     */
    public void reloadComposantes() {
        HashMap<Fournisseur, List<Composante>> i = new HashMap<>();
        for (Fournisseur f : fournisseurs.getFournisseurMap().values()) {
            i.put(f, composantes.chargerComposantes(f.getEmail()));
        }
        this.searchCompMenuView.afficherVue(i);
        init();
    }

    /**
     * Rechargera les composantes en fonction du type donné et les affichera dans la vue.
     *
     * @param c Le type de composante pour le filtrage.
     */
    public void reloadComposantes(String c) {
        HashMap<Fournisseur, List<Composante>> i = new HashMap<>();
        for (Fournisseur f : fournisseurs.getFournisseurMap().values()) {
            List<Composante> comps = composantes.chargerComposantes(f.getEmail());
            for (Composante cc : comps) {
                if (cc.getType().equals(c)) {
                    i.put(f, composantes.chargerComposantes(f.getEmail()));
                    break;
                }
            }
        }
        this.searchCompMenuView.afficherVue(i);
        init();
    }

    /**
     * Rechargera les composantes pour le fournisseur donné et les affichera dans la vue.
     *
     * @param f Le fournisseur pour lequel afficher les composantes.
     */
    public void reloadComposantes(Fournisseur f) {
        HashMap<Fournisseur, List<Composante>> i = new HashMap<>();
        i.put(f, composantes.chargerComposantes(f.getEmail()));
        this.searchCompMenuView.afficherVue(i);
        init();
    }

    /**
     * Achete une composante et notifie le fournisseur.
     *
     * @param fournisseur Le fournisseur de la composante.
     * @param composante La composante à acheter.
     */
    public void acheterComposante(Fournisseur fournisseur, Composante composante) {
        // Supprimer la composante de l'inventaire
        composantes.supprimerComposante(composante.getId(), fournisseur.getEmail());

        // Envoyer une notification au fournisseur
        NotifService.getInstance().sendNotifFournisseur(
                fournisseur.getEmail(),
                "Une composante " + composante.getNom() + " a été achetée par l'utilisateur." + utilisateur.getEmail()
        );

        // Recharger les composantes après la suppression
        reloadComposantes();
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
