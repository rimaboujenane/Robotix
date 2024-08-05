package ift2255.robotix.Controller.Utilisateur;

import ift2255.robotix.Modeles.Composante;
import ift2255.robotix.Modeles.Fournisseur;
import ift2255.robotix.Modeles.GestionFournisseurs;
import ift2255.robotix.Modeles.GestionComposantes;
import ift2255.robotix.View.Utilisateur.MenuView;
import ift2255.robotix.View.Utilisateur.SearchFournisseurView;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchFournisseurController {
    private SearchFournisseurView searchFournMenuView;
    private Stage stage;
    private HashMap<Fournisseur, List<Composante>> inv;
    private GestionFournisseurs fournisseurs;
    private GestionComposantes composantes;

    public SearchFournisseurController(Stage stage, SearchFournisseurView searchFournMenuView) {

        this.searchFournMenuView = searchFournMenuView;
        this.stage = stage;

        fournisseurs = new GestionFournisseurs();
        composantes = new GestionComposantes();
        reloadFournisseurs();
    }
    public void init() {
        this.searchFournMenuView.getBackButton().setOnAction(e -> goBackMenu());
        this.searchFournMenuView.getTypeComboBox().addEventHandler(ComboBox.ON_HIDDEN, event -> {
            reloadFournisseurs(this.searchFournMenuView.getType());
            //this.searchCompMenuView.afficherVue(printComposantes(inv));
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
    public void reloadFournisseurs( String c) {

        HashMap<Fournisseur, List<Composante>> i = new HashMap<Fournisseur, List<Composante>>();
        for (Fournisseur f : fournisseurs.getFournisseurMap().values()) {
            List<Composante> comps = composantes.chargerComposantes(f.getEmail());
            for (Composante cc : comps) {
                System.out.println(f.getNom() + cc.getType() + c);
                if (cc.getType().equals(c)) {
                    Composante[] co = new Composante[1];
                    co[0] = cc;
                    List<Composante> l = Arrays.asList(co);
                    i.put(f, l);
                    break;
                }
            }
        }
        System.out.println(c);
        this.searchFournMenuView.afficherVue(i);
        init();
    }

    public Text printFournisseurs(HashMap<Fournisseur, List<Composante>> i) {

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


