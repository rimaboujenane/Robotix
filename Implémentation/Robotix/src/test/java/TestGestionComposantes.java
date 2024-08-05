import ift2255.robotix.Modeles.Composante;
import ift2255.robotix.Modeles.GestionComposantes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Classe de test pour {@link GestionComposantes}.
 * Cette classe contient des tests unitaires pour la méthode {@link GestionComposantes#chargerComposantes(String)}.
 */
public class TestGestionComposantes {

    private GestionComposantes gestionComposantes;
    private Map<String, List<Composante>> composantesParFournisseur;

    /**
     * Configure l'environnement de test avant chaque cas de test.
     * Cette méthode initialise l'instance de GestionComposantes et la remplit avec des composants de test.
     */
    @BeforeEach
    public void setUp() {
        gestionComposantes = new GestionComposantes();
        composantesParFournisseur = gestionComposantes.getComposantesParFournisseur();
        List<Composante> composantes = new ArrayList<>();
        composantes.add(new Composante(00, "Composante1", "test", "test", 0, "fournisseur@example.com"));
        composantes.add(new Composante(00, "Composante2", "test", "test", 0, "fournisseur@example.com"));
        composantesParFournisseur.put("fournisseur@example.com", composantes);
    }

    /**
     * Teste le scénario où un fournisseur existant est fourni.
     * Ce test vérifie si la méthode {@link GestionComposantes#chargerComposantes(String)} retourne correctement
     * la liste des composants associés au fournisseur existant.
     */
    @Test
    void testChargerComposantes_ExistingFournisseur() {
        List<Composante> result = gestionComposantes.chargerComposantes("fournisseur@example.com");
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Composante1", result.get(0).getNom());
        assertEquals("Composante2", result.get(1).getNom());
    }

    /**
     * Teste le scénario où un fournisseur non existant est fourni.
     * Ce test vérifie si la méthode {@link GestionComposantes#chargerComposantes(String)} retourne une liste vide
     * lorsqu'aucun composant n'est associé au fournisseur non existant.
     */
    @Test
    void testChargerComposantes_NonExistingFournisseur() {
        List<Composante> result = gestionComposantes.chargerComposantes("NonExistingFournisseur@example.com");
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
