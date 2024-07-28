package ift2255.robotix.Modeles;

/**
 * La classe {@code RegisterFournisseur} est un singleton qui gère une instance de l'objet {@code Fournisseur}.
 * Elle fournit des méthodes pour obtenir et définir l'objet {@code Fournisseur}.
 */
public class RegisterFournisseur extends RegisterUser{

    /**
     * L'instance unique de la classe {@code RegisterFournisseur}.
     */
    private static RegisterFournisseur instance = null;

    /**
     * L'objet {@code Fournisseur} à stocker.
     */
    private Fournisseur fournisseur;

    /**
     * Constructeur privé pour empêcher l'instanciation depuis l'extérieur de la classe.
     */
    private RegisterFournisseur() {
        // Code d'initialisation
    }

    /**
     * Retourne l'instance unique de la classe {@code RegisterFournisseur}.
     * Si l'instance n'existe pas encore, elle est créée.
     *
     * @return l'instance unique de la classe {@code RegisterFournisseur}
     */
    public static RegisterFournisseur getInstance() {
        if (instance == null) {
            instance = new RegisterFournisseur();
        }
        return instance;
    }

    /**
     * Retourne l'objet {@code Fournisseur}.
     *
     * @return l'objet {@code Fournisseur}
     */
    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    /**
     * Définit l'objet {@code Fournisseur}.
     *
     * @param fournisseur l'objet {@code Fournisseur} à définir
     */
    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }
}