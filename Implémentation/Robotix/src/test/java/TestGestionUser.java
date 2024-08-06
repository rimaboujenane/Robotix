import static org.junit.jupiter.api.Assertions.*;


import ift2255.robotix.Modeles.GestionUtilisateurs;
import ift2255.robotix.Modeles.Utilisateur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Classe de test pour {@link GestionUtilisateurs}.
 * Cette classe contient des tests unitaires pour les méthodes de la classe GestionUtilisateurs.
 */
public class TestGestionUser {

    private GestionUtilisateurs gestionUtilisateurs;
    private Utilisateur utilisateur;

    /**
     * Configure l'environnement de test avant chaque cas de test.
     * Cette méthode initialise l'instance de GestionUtilisateurs et la remplit avec un utilisateur de test.
     */
    @BeforeEach
    public void setUp() {
        utilisateur = new Utilisateur("testNom", "testPrenom", "password123", "test@gmail.com", "123-456-7890");
        gestionUtilisateurs = new GestionUtilisateurs();
        gestionUtilisateurs.getUtilisateursMap().put("test@gmail.com", utilisateur);
    }

    /**
     * Teste le scénario où un utilisateur valide est fourni avec le mot de passe correct.
     * Ce test vérifie si la méthode {@link GestionUtilisateurs#isValidUser(String, String)} identifie correctement
     * un utilisateur valide et s'assure que l'utilisateur courant est correctement défini.
     */
    @Test
    void testValidUser() {
        assertTrue(gestionUtilisateurs.isValidUser("test@gmail.com", "password123"));
        assertEquals("test@gmail.com", gestionUtilisateurs.getUtilisateur().getEmail());
    }

    /**
     * Teste le scénario où un utilisateur valide est fourni avec un mot de passe incorrect.
     * Ce test vérifie si la méthode {@link GestionUtilisateurs#isValidUser(String, String)} retourne false
     * lorsque le mot de passe est incorrect et s'assure qu'aucun utilisateur n'est défini comme utilisateur courant.
     */
    @Test
    void testInvalidUser() {
        assertFalse(gestionUtilisateurs.isValidUser("test@gmail.com", "wrongPassword123"));
        assertNull(gestionUtilisateurs.getUtilisateur());
    }

    /**
     * Teste le scénario où un utilisateur non existant est interrogé.
     * Ce test vérifie si la méthode {@link GestionUtilisateurs#isValidUser(String, String)} retourne false
     * lorsque l'utilisateur n'existe pas dans le système et s'assure qu'aucun utilisateur n'est défini comme utilisateur courant.
     */
    @Test
    public void testNonExistentUser() {
        assertFalse(gestionUtilisateurs.isValidUser("nonexistent@example.com", "password123"));
        assertNull(gestionUtilisateurs.getUtilisateur());
    }
}
