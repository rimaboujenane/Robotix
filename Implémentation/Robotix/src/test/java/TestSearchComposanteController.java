import ift2255.robotix.Modeles.Composante;
import ift2255.robotix.Modeles.Fournisseur;
import ift2255.robotrix.Modeles.GestionComposantes;
import ift2255.robotix.Modeles.GestionFournisseurs;
import ift2255.robotix.Modeles.Utilisateur;
import ift2255.robotix.View.Utilisateur.SearchComposanteView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestSearchComposante{
    private SearchComposanteView searchCompMenuView;
    private TestGestionFournisseurs fournisseurs;
    private TestGestionComposantes composantes;
    private SearchComposanteController controller;
    private Stage stage;

    // Classes de substitution
    class TestGestionFournisseurs extends GestionFournisseurs {
        private Map<String, Fournisseur> fournisseursMap = new HashMap<>();

        @Override
        public Map<String, Fournisseur> getFournisseurMap() {
            return fournisseursMap;
        }

        @Override
        public Fournisseur getFournisseurByName(String name) {
            return fournisseursMap.values().stream()
                    .filter(f -> f.getNom().equals(name))
                    .findFirst().orElse(null);
        }

        public void addFournisseur(Fournisseur fournisseur) {
            fournisseursMap.put(fournisseur.getEmail(), fournisseur);
        }
    }

    class TestGestionComposantes extends GestionComposantes {
        private Map<String, List<Composante>> composantesMap = new HashMap<>();

        @Override
        public List<Composante> chargerComposantes(String fournisseurEmail) {
            return composantesMap.getOrDefault(fournisseurEmail, new ArrayList<>());
        }

        @Override
        public void supprimerComposante(int id, String fournisseurEmail) {
            List<Composante> composantes = composantesMap.get(fournisseurEmail);
            if (composantes != null) {
                composantes.removeIf(c -> c.getId() == id);
            }
        }

        public void addComposantes(String fournisseurEmail, List<Composante> composantes) {
            composantesMap.put(fournisseurEmail, composantes);
        }
    }

    @BeforeEach
    public void setUp() {
        searchCompMenuView = new SearchComposanteView() {
            @Override
            public void afficherVue(HashMap<Fournisseur, List<Composante>> i) {
                // Override for testing purposes
            }
        };
        fournisseurs = new TestGestionFournisseurs();
        composantes = new TestGestionComposantes();
        stage = new Stage();

        controller = new SearchComposanteController(stage, searchCompMenuView);
        controller.fournisseurs = fournisseurs;
        controller.composantes = composantes;
    }

    @Test
    public void testReloadComposantes() {
        Fournisseur fournisseur = new Fournisseur("Nom", "Prenom", "password", "email@example.com", "123456789","Notch");
        List<Composante> composantesList = List.of(
                new Composante(1, "Composante1", "type1", "compos", 10.1, "email@example.com"),
                new Composante(2, "Composante2", "type2", "comps", 10.19, "email@example.com")
        );

        fournisseurs.addFournisseur(fournisseur);
        composantes.addComposantes(fournisseur.getEmail(), composantesList);

        controller.reloadComposantes();

        // Vous devriez vérifier si la vue a été mise à jour avec les données appropriées
        // Ici nous ne pouvons pas vérifier directement le résultat puisque afficherVue est override
        // Mais vous pouvez ajouter des assertions pour les effets secondaires si nécessaire
    }

    @Test
    public void testReloadComposantesByType() {
        Fournisseur fournisseur = new Fournisseur("Nom", "Prenom", "password", "email@example.com", "123456789","Notch");
        List<Composante> composantesList = List.of(
            new Composante(1, "Composante1", "type1", "compos", 10.1, "email@example.com"),
            new Composante(2, "Composante2", "type2", "comps", 10.19, "email@example.com"),
            new Composante(3, "Composante3", "type3", "coms", 0.15, "email@example.com")
        );

        fournisseurs.addFournisseur(fournisseur);
        composantes.addComposantes(fournisseur.getEmail(), composantesList);

        controller.reloadComposantes("Type1");

        // Vérifiez que seule les composantes du type "Type1" sont chargées
        // Vous devrez adapter ceci en fonction des implémentations de vos méthodes et de vos assertions
    }

    @Test
    public void testReloadComposantesByFournisseur() {
        Fournisseur fournisseur = new Fournisseur("Nom", "Prenom", "password", "email@example.com", "123456789","Notch");
        List<Composante> composantesList = List.of(
            new Composante(1, "Composante1", "type1", "compos", 10.1, "email@example.com"),
            new Composante(2, "Composante2", "type2", "comps", 10.19, "email@example.com")
        );

        fournisseurs.addFournisseur(fournisseur);
        composantes.addComposantes(fournisseur.getEmail(), composantesList);

        controller.reloadComposantes(fournisseur);

        // Vérifiez que les composantes du fournisseur spécifique sont chargées
    }

    @Test
    public void testAcheterComposante() {
        Fournisseur fournisseur = new Fournisseur("Nom", "Prenom", "password", "email@example.com", "123456789","Notch");
        Composante composante = new Composante(2, "Composante2", "type2", "comps", 10.19, "email@example.com");

        fournisseurs.addFournisseur(fournisseur);
        composantes.addComposantes(fournisseur.getEmail(), List.of(composante));

        controller.acheterComposante(fournisseur, composante);

        // Vérifiez que la composante a été supprimée de la liste
        List<Composante> composantesRestantes = composantes.chargerComposantes(fournisseur.getEmail());
        assertTrue(composantesRestantes.isEmpty(), "La composante doit être supprimée.");
    }

   
    
}
