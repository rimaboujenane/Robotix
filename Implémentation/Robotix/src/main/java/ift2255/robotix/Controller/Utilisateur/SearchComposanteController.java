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

public class SearchComposanteController {
    private SearchComposanteView searchCompMenuView;
    private Stage stage;
    private HashMap<Fournisseur, List<Composante>> inv;
    private GestionFournisseurs fournisseurs;
    private GestionComposantes composantes;
    private Utilisateur utilisateur = RegisterUtilisateur.getInstance().getUtilisateur();

    public SearchComposanteController(Stage stage, SearchComposanteView searchCompMenuView) {
        this.searchCompMenuView = searchCompMenuView;
        this.stage = stage;

        fournisseurs = new GestionFournisseurs();
        composantes = new GestionComposantes();
        reloadComposantes();

        // Initialisation du contrôleur dans la vue
        this.searchCompMenuView.setController(this);
    }

    public void init() {
        this.searchCompMenuView.getBackButton().setOnAction(e -> goBackMenu());
        this.searchCompMenuView.getTypeComboBox().addEventHandler(ComboBox.ON_HIDDEN, event -> {
            reloadComposantes(this.searchCompMenuView.getType());
        });
        this.searchCompMenuView.getFournComboBox().addEventHandler(ComboBox.ON_HIDDEN, event -> {
            reloadComposantes(this.searchCompMenuView.getFournisseur());
        });
    }

    public void reloadComposantes() {
        HashMap<Fournisseur, List<Composante>> i = new HashMap<>();
        for (Fournisseur f : fournisseurs.getFournisseurMap().values()) {
            i.put(f, composantes.chargerComposantes(f.getEmail()));
        }
        this.searchCompMenuView.afficherVue(i);
        init();
    }

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

    public void reloadComposantes(Fournisseur f) {
        HashMap<Fournisseur, List<Composante>> i = new HashMap<>();
        i.put(f, composantes.chargerComposantes(f.getEmail()));
        this.searchCompMenuView.afficherVue(i);
        init();
    }

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


    private void goBackMenu() {
        MenuView menuView = new MenuView();
        MenuController menuController = new MenuController(stage, menuView);
        stage.setScene(new Scene(menuView, 900, 700));
    }
}
