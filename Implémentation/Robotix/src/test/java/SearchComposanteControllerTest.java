import ift2255.robotix.Modeles.*;
import ift2255.robotix.View.Utilisateur.SearchComposanteView;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Classe de test pour la gestion de la recherche de composantes dans le système.
 * Cette classe vérifie les fonctionnalités de recherche, de filtrage et d'achat des composantes dans les tests unitaires.
 */
public class SearchComposanteControllerTest {

    /** Mock de SearchComposanteView utilisé pour les tests. */
    @Mock
    private SearchComposanteView searchCompMenuView;

    /** Mock de Stage utilisé pour les tests. */
    @Mock
    private Stage stage;

    /** Mock de GestionFournisseurs utilisé pour les tests. */
    @Mock
    private GestionFournisseurs fournisseurs;

    /** Mock de GestionComposantes utilisé pour les tests. */
    @Mock
    private GestionComposantes composantes;

    /** Mock de RegisterUtilisateur utilisé pour les tests. */
    @Mock
    private RegisterUtilisateur registerUtilisateur;

    /** Mock de Utilisateur utilisé pour les tests. */
    @Mock
    private Utilisateur utilisateur;

    /** Instance de SearchComposanteController utilisée pour les tests. */
    @InjectMocks
    private SearchComposanteController searchComposanteController;

    /**
     * Méthode d'initialisation des mocks.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(registerUtilisateur.getUtilisateur()).thenReturn(utilisateur);
    }

    /**
     * Test pour vérifier que le constructeur initialise bien la vue et la scène.
     */
    @Test
    public void testConstructorInitializesViewAndStage() {
        SearchComposanteController controller = new SearchComposanteController(stage, searchCompMenuView);
        assertNotNull(controller);
    }

    /**
     * Test pour vérifier que les gestionnaires d'événements sont correctement configurés.
     */
    @Test
    public void testInitSetsUpEventHandlers() {
        Button backButton = mock(Button.class);
        ComboBox<String> typeComboBox = mock(ComboBox.class);
        ComboBox<String> fournComboBox = mock(ComboBox.class);

        when(searchCompMenuView.getBackButton()).thenReturn(backButton);
        when(searchCompMenuView.getTypeComboBox()).thenReturn(typeComboBox);
        when(searchCompMenuView.getFournComboBox()).thenReturn(fournComboBox);

        searchComposanteController.init();

        verify(backButton).setOnAction(any());
        verify(typeComboBox).addEventHandler(any(), any());
        verify(fournComboBox).addEventHandler(any(), any());
    }

    /**
     * Test pour vérifier que les composantes sont affichées correctement lors du rechargement.
     */
    @Test
    public void testReloadComposantesDisplaysComposantes() {
        Fournisseur fournisseur = mock(Fournisseur.class);
        Composante composante = mock(Composante.class);
        List<Composante> composantesList = Arrays.asList(composante);
        Map<String, Fournisseur> fournisseurMap = new HashMap<>();
        fournisseurMap.put("email@example.com", fournisseur);

        when(fournisseur.getEmail()).thenReturn("email@example.com");
        when(fournisseurs.getFournisseurMap()).thenReturn(fournisseurMap);
        when(composantes.chargerComposantes("email@example.com")).thenReturn(composantesList);

        searchComposanteController.reloadComposantes();

        verify(searchCompMenuView).afficherVue(any());
    }

    /**
     * Test pour vérifier qu'une composante est achetée correctement.
     */
    @Test
    public void testAcheterComposante() {
        Fournisseur fournisseur = mock(Fournisseur.class);
        Composante composante = mock(Composante.class);

        when(fournisseur.getEmail()).thenReturn("email@example.com");
        when(composante.getId()).thenReturn(1);
        when(composante.getNom()).thenReturn("Composante 1");
        when(utilisateur.getEmail()).thenReturn("user@example.com");

        searchComposanteController.acheterComposante(fournisseur, composante);

        verify(composantes).supprimerComposante(1, "email@example.com");
        verify(NotifService.getInstance()).sendNotifFournisseur(
                "email@example.com", 
                "Une composante Composante 1 a été achetée par l'utilisateur.user@example.com"
        );
        verify(searchCompMenuView).afficherVue(any());
    }
}
