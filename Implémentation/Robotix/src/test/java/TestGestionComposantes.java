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
    private List<Composante> composantes;

    /**
     * Configure l'environnement de test avant chaque cas de test.
     * Cette méthode initialise l'instance de GestionComposantes et la remplit avec des composants de test.
     */
    @BeforeEach
    public void setUp() {
        gestionComposantes = new GestionComposantes();
        composantesParFournisseur = gestionComposantes.getComposantesParFournisseur();
        composantes = new ArrayList<>();
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

    /**
     * Teste la mise à jour réussie d'un composant avec la méthode {@link GestionComposantes#updateComposante(Composante)}.
     * Vérifie que les modifications apportées au composant sont correctement reflétées dans la liste des composants du fournisseur.
     */
    @Test
    public void testUpdateComposante_Success() {
        Composante composanteModifie = new Composante(00, "Composante1Modifie", "Type1Modifie", "Description1Modifie", 150.0, "fournisseur@example.com");
        gestionComposantes.updateComposante(composanteModifie);

        List<Composante> composantes = gestionComposantes.chargerComposantes("fournisseur@example.com");
        Composante updatedComposante = composantes.stream().filter(c -> c.getId() == 00).findFirst().orElse(null);

        assertNotNull(updatedComposante);
        assertEquals("Composante1Modifie", updatedComposante.getNom());
        assertEquals("Type1Modifie", updatedComposante.getType());
        assertEquals("Description1Modifie", updatedComposante.getDescription());
        assertEquals(150.0, updatedComposante.getPrix());
    }

    /**
     * Teste la mise à jour d'un composant qui n'existe pas avec la méthode {@link GestionComposantes#updateComposante(Composante)}.
     * Vérifie qu'un composant qui n'existe pas dans la liste des composants du fournisseur reste absent après la tentative de mise à jour.
     */
    @Test
    public void testUpdateComposante_NonExistingComposante() {
        Composante composanteModifie = new Composante(3, "Composante3", "Type3", "Description3", 300.0, "fournisseur@example.com");
        gestionComposantes.updateComposante(composanteModifie);

        List<Composante> composantes = gestionComposantes.chargerComposantes("fournisseur@example.com");
        Composante nonUpdatedComposante = composantes.stream().filter(c -> c.getId() == 3).findFirst().orElse(null);

        assertNull(nonUpdatedComposante);
    }
}

