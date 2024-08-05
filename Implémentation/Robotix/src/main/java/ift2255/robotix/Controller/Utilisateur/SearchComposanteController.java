package ift2255.robotix.Controller.Utilisateur;

import ift2255.robotix.Modeles.Composante;
import ift2255.robotix.Modeles.Fournisseur;
import ift2255.robotix.Modeles.GestionFournisseurs;
import ift2255.robotix.Modeles.GestionComposantes;
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

     public SearchComposanteController(Stage stage, SearchComposanteView searchCompMenuView) {

         this.searchCompMenuView = searchCompMenuView;
         this.stage = stage;

         fournisseurs = new GestionFournisseurs();
         composantes = new GestionComposantes();
         reloadComposantes();
     }
     public void init() {
         this.searchCompMenuView.getBackButton().setOnAction(e -> goBackMenu());
         this.searchCompMenuView.getTypeComboBox().addEventHandler(ComboBox.ON_HIDDEN, event -> {
             reloadComposantes(this.searchCompMenuView.getType());
             //this.searchCompMenuView.afficherVue(printComposantes(inv));
         });
         this.searchCompMenuView.getFournComboBox().addEventHandler(ComboBox.ON_HIDDEN, event -> {
             reloadComposantes(this.searchCompMenuView.getFournisseur());
             //this.searchCompMenuView.afficherVue(printComposantes(inv));
         });
     }
    public void reloadComposantes() {

        HashMap<Fournisseur, List<Composante>> i = new HashMap<>();
        for (Fournisseur f : fournisseurs.getFournisseurMap().values()) {
            i.put(f, composantes.chargerComposantes(f.getEmail()));
        }
        this.searchCompMenuView.afficherVue(printComposantes(i));
        init();
    }
    public void reloadComposantes( String c) {

        HashMap<Fournisseur, List<Composante>> i = new HashMap<>();
        for (Fournisseur f : fournisseurs.getFournisseurMap().values()) {
            List<Composante> comps = composantes.chargerComposantes(f.getEmail());
            for (Composante cc : comps) {
                System.out.println(f.getNom() + cc.getType() + c);
                if (cc.getType().equals(c)) {
                    i.put(f, composantes.chargerComposantes(f.getEmail()));
                    break;
                }
            }
        }
        System.out.println(c);
        this.searchCompMenuView.afficherVue(printComposantes(i));
        init();
    }
    public void reloadComposantes( Fournisseur f) {

        HashMap<Fournisseur, List<Composante>> i = new HashMap<>();
        i.put(f, composantes.chargerComposantes(f.getEmail()));
        this.searchCompMenuView.afficherVue(printComposantes(i));
        init();
    }
    public Text printComposantes(HashMap<Fournisseur, List<Composante>> i) {

        String name = "";
        for (Map.Entry<Fournisseur, List<Composante>> entry : i.entrySet()) {
            name += entry.getKey().getNom() + "\n";
            for (Composante c : entry.getValue()) {
                name += "\t" + c.getNom() + " -> Type: " + c.getType() + "\n";
            }
            name += "\n";
        }
        System.out.println(name);
        return new Text(name);
    }

    private void goBackMenu() {
        MenuView menuView = new MenuView();
        MenuController menuController = new MenuController(stage, menuView);
        stage.setScene(new Scene(menuView, 900, 700));
    }
}

