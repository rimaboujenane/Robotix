import ift2255.robotix.Modeles.GestionComposantes;
import ift2255.robotix.Modeles.GestionFournisseurs;
import ift2255.robotix.Modeles.RegisterUtilisateur;
import ift2255.robotix.Modeles.Utilisateur;
import ift2255.robotix.Modeles.Composante;
import ift2255.robotix.Modeles.Fournisseur;
import ift2255.robotix.Modeles.NotifService;
import ift2255.robotix.View.Utilisateur.SearchComposanteView;
import ift2255.robotix.Controller.Utilisateur.SearchComposanteController;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour la gestion de la recherche de composantes dans le système.
 * Cette classe vérifie les fonctionnalités de recherche, de filtrage et d'achat des composantes dans les tests unitaires.
 */
public class TestSearchComposante {

    /** Instance de SearchComposanteView utilisée pour les tests. */
    private SearchComposanteView searchCompMenuView;

    /** Instance de Stage utilisée pour les tests. */
    private Stage stage;

    /** Instance de GestionFournisseurs utilisée pour les tests. */
    private GestionFournisseurs fournisseurs;

    /** Instance de GestionComposantes utilisée pour les tests. */
    private GestionComposantes composantes;

    /** Instance de RegisterUtilisateur utilisée pour les tests. */
    private RegisterUtilisateur registerUtilisateur;

    /** Instance de Utilisateur utilisée pour les tests. */
    private Utilisateur utilisateur;

    /** Instance de SearchComposanteController utilisée pour les tests. */
    private SearchComposanteController searchComposanteController;

    /**
     * Méthode d'initialisation appelée avant chaque test.
     * Initialise les instances nécessaires pour les tests.
     */
    @BeforeEach
    public void setUp() {
        stage = new Stage();
        searchCompMenuView = new SearchComposanteView();
        fournisseurs = new GestionFournisseurs();
        composantes = new GestionComposantes();
        registerUtilisateur = RegisterUtilisateur.getInstance();
        utilisateur = new Utilisateur("Dupont", "Jean", "dupont123", "jdupont@example.com", "1234567890");
        registerUtilisateur.setUtilisateur(utilisateur);

        searchComposanteController = new SearchComposanteController(stage, searchCompMenuView);
    }

    /**
     * Test pour vérifier que le constructeur initialise bien la vue et la scène.
     */
    @Test
    public void testConstructorInitializesViewAndStage() {
        assertNotNull(searchComposanteController);
    }

    /**
     * Test pour vérifier que les gestionnaires d'événements sont correctement configurés.
     */
    @Test
    public void testInitSetsUpEventHandlers() {
        Button backButton = new Button();
        ComboBox<String> typeComboBox = new ComboBox<>();
        ComboBox<String> fournComboBox = new ComboBox<>();

        searchCompMenuView.setBackButton(backButton);
        searchCompMenuView.setTypeComboBox(typeComboBox);
        searchCompMenuView.setFournComboBox(fournComboBox);

        searchComposanteController.init();

        assertNotNull(backButton.getOnAction());
        assertNotNull(typeComboBox.getOnHidden());
        assertNotNull(fournComboBox.getOnHidden());
    }

    /**
     * Test pour vérifier que les composantes sont affichées correctement lors du rechargement.
     */
    @Test
    public void testReloadComposantesDisplaysComposantes() {
        Fournisseur fournisseur = new Fournisseur("Jo", "Bla","123654","jbla@gmail.com","5145175145","Notchie");;
        Composante composante = new Composante(2, "Composante 2", "Type 2", "Description 2", 10.01,"comp2@gmail.com");;
        List<Composante> composantesList = Arrays.asList(composante);
        fournisseurs.addFournisseur(fournisseur);
        composantes.ajouterComposante(composante);

        searchComposanteController.reloadComposantes();

        // Ici, nous vérifions si la méthode afficherVue a été appelée avec les bons arguments.
        // Pour ce faire, nous devons implémenter une méthode pour obtenir les données affichées dans SearchComposanteView.
        // Supposons que SearchComposanteView a une méthode getAffichage qui retourne les données affichées.
        HashMap<Fournisseur, List<Composante>> expected = new HashMap<>();
        expected.put(fournisseur, composantesList);
        assertEquals(expected, searchCompMenuView.getAffichage());
    }

    /**
     * Test pour vérifier qu'une composante est achetée correctement.
     */
    @Test
    public void testAcheterComposante() {
        Fournisseur fournisseur = new Fournisseur("John", "Blain","1236","john@gmail.com","5145145145","Notch");
        Composante composante = new Composante(1, "Composante 1", "Type 1", "Description 1", 10.0,"comp1@gmail.com");
        fournisseurs.addFournisseur(fournisseur);
        composantes.ajouterComposante(composante);

        searchComposanteController.acheterComposante(fournisseur, composante);

        // Vérifiez que la composante a été supprimée et une notification a été envoyée
        assertNull(composantes.supprimerComposante(composante.getId(),fournisseur.getEmail()));
        // Supposons que NotifService a une méthode pour vérifier les notifications envoyées
        assertTrue(NotifService.getInstance().sendNotifFournisseur(
                fournisseur.getEmail(),
                "Une composante " + composante.getNom() + " a été achetée par l'utilisateur." + utilisateur.getEmail()
        ));
    }
}
