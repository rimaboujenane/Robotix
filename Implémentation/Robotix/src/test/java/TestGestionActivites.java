import ift2255.robotix.Modeles.Activite;
import ift2255.robotix.Modeles.GestionActivites;
import ift2255.robotix.Modeles.Utilisateur;
import ift2255.robotix.Modeles.RegisterUtilisateur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour la gestion des activités dans le système.
 * Cette classe vérifie les fonctionnalités de gestion des activités telles que l'inscription,
 * la désinscription, le chargement des activités, et les manipulations d'activités dans les tests unitaires.
 */
public class TestGestionActivites {

    /** Instance de GestionActivites utilisée pour les tests. */
    private GestionActivites gestionActivites;

    /** Chemin vers le fichier CSV utilisé pour les tests. */
    private static final String CSV_FILE = "src/main/resources/data/activites.csv";

    /**
     * Méthode d'initialisation appelée avant chaque test.
     * Initialise un utilisateur de test et crée une instance de GestionActivites.
     *
     * @throws IOException si un problème d'entrée/sortie survient lors de la configuration.
     */
    @BeforeEach
    public void setUp() throws IOException {
        Utilisateur utilisateur = new Utilisateur("Dupont","Jean","dupont123","jdupont@example.com","1234567890");
        RegisterUtilisateur.getInstance().setUtilisateur(utilisateur);
        gestionActivites = new GestionActivites();
    }

    /**
     * Teste l'inscription réussie d'une activité.
     * Vérifie que l'activité est ajoutée à la liste des activités inscrites et retirée de la liste des non inscrites.
     */
    @Test
    public void testInscriptionSuccess() throws IOException {
        Activite activite = new Activite(5, "Maintenance Préventive", LocalDate.of(2024, 8, 9), LocalDate.of(2024, 8, 10), List.of());
        gestionActivites.inscription(activite);

        List<Activite> mesActivites = gestionActivites.getMesActivites();
        List<Activite> activitesNonInscrites = gestionActivites.getActivitesNonInscrites();

        assertTrue(mesActivites.stream().anyMatch(a -> a.getId() == 5));
        assertFalse(activitesNonInscrites.stream().anyMatch(a -> a.getId() == 5));
    }

    /**
     * Teste la désinscription réussie d'une activité.
     * Vérifie que l'activité est retirée de la liste des activités inscrites et ajoutée à la liste des non inscrites.
     */
    @Test
    public void testDesinscriptionSuccess() throws IOException {
        Activite activite = new Activite(4, "Assemblage de Composants", LocalDate.of(2024, 8, 7), LocalDate.of(2024, 8, 8), List.of("jdupont@example.com"));
        gestionActivites.desinscription(activite);

        List<Activite> mesActivites = gestionActivites.getMesActivites();
        List<Activite> activitesNonInscrites = gestionActivites.getActivitesNonInscrites();

        assertFalse(mesActivites.stream().anyMatch(a -> a.getId() == 4));
        assertTrue(activitesNonInscrites.stream().anyMatch(a -> a.getId() == 4));
    }

    /**
     * Teste le cas où une activité est déjà enregistrée pour un utilisateur.
     * Vérifie que l'activité est toujours présente dans la liste des activités inscrites, sans modification.
     */
    @Test
    public void testInscriptionAlreadyRegistered() throws IOException {
        Activite activite = new Activite(1, "Patrouille de Sécurité", LocalDate.of(2024, 8, 1), LocalDate.of(2024, 8, 2), List.of("jdupont@example.com", "admin"));
        gestionActivites.inscription(activite);

        List<Activite> mesActivites = gestionActivites.getMesActivites();
        assertTrue(mesActivites.stream().anyMatch(a -> a.getId() == 1));
    }

    /**
     * Teste le cas où une désinscription est tentée pour une activité non enregistrée.
     * Vérifie que l'activité est toujours absente de la liste des activités inscrites et présente dans celle des non inscrites.
     */
    @Test
    public void testDesinscriptionNotRegistered() throws IOException {
        Activite activite = new Activite(7, "Livraison de Colis", LocalDate.of(2024, 8, 13), LocalDate.of(2024, 8, 14), List.of());
        gestionActivites.desinscription(activite);

        List<Activite> mesActivites = gestionActivites.getMesActivites();
        List<Activite> activitesNonInscrites = gestionActivites.getActivitesNonInscrites();

        assertFalse(mesActivites.stream().anyMatch(a -> a.getId() == 7));
        assertTrue(activitesNonInscrites.stream().anyMatch(a -> a.getId() == 7));
    }

    /**
     * Teste le chargement des activités depuis la source de données.
     * Vérifie que la liste des activités n'est pas nulle, contient le nombre attendu d'activités et que certaines activités spécifiques sont présentes.
     */
    @Test
    public void testChargementActivites() throws IOException {
        List<Activite> activites = gestionActivites.getActivites();

        assertNotNull(activites);
        assertEquals(10, activites.size());
        assertTrue(activites.stream().anyMatch(a -> a.getId() == 1));
        assertTrue(activites.stream().anyMatch(a -> a.getNom().equals("Transport de Matériaux")));
    }
}
