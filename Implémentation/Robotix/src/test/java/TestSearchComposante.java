import ift2255.robotix.Controller.Utilisateur.SearchComposanteController;
import ift2255.robotix.Modeles.Composante;
import ift2255.robotix.Modeles.Fournisseur;
import ift2255.robotix.Modeles.GestionComposantes;
import ift2255.robotix.Modeles.GestionFournisseurs;
import ift2255.robotix.Modeles.RegisterUtilisateur;
import ift2255.robotix.Modeles.Utilisateur;
import ift2255.robotix.Modeles.NotifService;
import ift2255.robotix.View.Utilisateur.SearchComposanteView;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Classe de test pour {@link SearchComposanteController}.
 * Cette classe contient des tests unitaires pour les méthodes du contrôleur de recherche de composantes.
 */
public class TestSearchComposante extends ApplicationTest {

    private SearchComposanteController controller;
    private SearchComposanteView view;
    private Stage stage;
    private GestionComposantes gestionComposantesMock;
    private GestionFournisseurs gestionFournisseursMock;
    private NotifService notifServiceMock;
    private Utilisateur utilisateurMock;

    /**
     * Configure l'environnement de test avec une scène et initialise le contrôleur et la vue.
     *
     * @param stage La scène principale pour ce test.
     */
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        view = new SearchComposanteView();
        controller = new SearchComposanteController(stage, view);
        view.setController(controller);
        controller.init();
        stage.setScene(new Scene(view));
        stage.show();
    }

    /**
     * Prépare le contrôleur et la vue pour chaque cas de test.
     */
    @BeforeEach
    public void setUp() {
        view = new SearchComposanteView();
        stage = new Stage();

        gestionComposantesMock = mock(GestionComposantes.class);
        gestionFournisseursMock = mock(GestionFournisseurs.class);
        notifServiceMock = mock(NotifService.class);
        utilisateurMock = mock(Utilisateur.class);
        when(utilisateurMock.getEmail()).thenReturn("test@example.com");

        RegisterUtilisateur registerUtilisateurMock = mock(RegisterUtilisateur.class);
        when(registerUtilisateurMock.getUtilisateur()).thenReturn(utilisateurMock);
        RegisterUtilisateur.setInstance(registerUtilisateurMock);

        controller = new SearchComposanteController(stage, view);
        view.setController(controller);

        controller.setGestionComposantes(gestionComposantesMock);
        controller.setGestionFournisseurs(gestionFournisseursMock);
        controller.setNotifService(notifServiceMock);

        controller.init();
    }

    /**
     * Vérifie que le constructeur initialise correctement le contrôleur.
     */
    @Test
    public void testConstructor() {
        assertNotNull(controller);
        assertNotNull(controller.getGestionComposantes());
        assertNotNull(controller.getGestionFournisseurs());
        assertNotNull(controller.getNotifService());
        assertNotNull(controller.getUtilisateur());
    }

    /**
     * Vérifie que la méthode {@link SearchComposanteController#reloadComposantes()} recharge l'inventaire des composantes.
     */
    @Test
    public void testReloadComposantes() {
        List<Composante> composantes = List.of(
                new Composante(1, "CPU", "Type1", "Description1", 150.0, "email@test.com"),
                new Composante(2, "RAM", "Type2", "Description2", 80.0, "email2@test.com")
        );

        when(gestionComposantesMock.getComposantes()).thenReturn(composantes);

        controller.reloadComposantes();

        assertEquals(2, view.getComposantesListView().getItems().size());
        assertTrue(view.getComposantesListView().getItems().contains("CPU"));
        assertTrue(view.getComposantesListView().getItems().contains("RAM"));
    }

    /**
     * Vérifie que la méthode {@link SearchComposanteController#reloadComposantes(String)} recharge les composantes en fonction du type donné.
     */
    @Test
    public void testReloadComposantesWithType() {
        String type = "CPU";
        List<Composante> composantes = List.of(
                new Composante(1, "CPU", "Type1", "Description1", 150.0, "email@test.com"),
                new Composante(2, "RAM", "Type2", "Description2", 80.0, "email2@test.com")
        );

        when(gestionComposantesMock.getComposantesByType(type)).thenReturn(List.of(composantes.get(0)));

        controller.reloadComposantes(type);

        assertEquals(1, view.getComposantesListView().getItems().size());
        assertTrue(view.getComposantesListView().getItems().contains("CPU"));
        assertFalse(view.getComposantesListView().getItems().contains("RAM"));
    }

    /**
     * Vérifie que la méthode {@link SearchComposanteController#reloadComposantes(Fournisseur)} recharge les composantes pour le fournisseur donné.
     */
    @Test
    public void testReloadComposantesWithFournisseur() {
        Fournisseur fournisseur = new Fournisseur("nom", "prenom", " password", "email@test.com", "5145145145", "ift2255");
        List<Composante> composantes = List.of(
                new Composante(1, "CPU", "Type1", "Description1", 150.0, "email@test.com"),
                new Composante(2, "RAM", "Type2", "Description2", 80.0, "email2@test.com")
        );

        when(gestionComposantesMock.getComposantesByFournisseur(fournisseur.getEmail())).thenReturn(List.of(composantes.get(0)));

        controller.reloadComposantes(fournisseur);

        assertEquals(1, view.getComposantesListView().getItems().size());
        assertTrue(view.getComposantesListView().getItems().contains("CPU"));
        assertFalse(view.getComposantesListView().getItems().contains("RAM"));
    }

    /**
     * Vérifie que la méthode {@link SearchComposanteController#acheterComposante(Fournisseur, Composante)} supprime la composante de l'inventaire et envoie une notification.
     */
    @Test
    public void testAcheterComposante() {
        Fournisseur fournisseur = new Fournisseur("nom", "prenom", " password", "email@test.com", "5145145145", "ift2255");
        Composante composante = new Composante(1, "CPU", "Type1", "Description1", 150.0, "email@test.com");

        doNothing().when(gestionComposantesMock).supprimerComposante(composante.getId());
        doNothing().when(notifServiceMock).sendNotification(anyString(), anyString(), anyString());

        controller.acheterComposante(fournisseur, composante);

        verify(gestionComposantesMock, times(1)).supprimerComposante(composante.getId());
        verify(notifServiceMock, times(1)).sendNotification(anyString(), anyString(), anyString());
    }
}
