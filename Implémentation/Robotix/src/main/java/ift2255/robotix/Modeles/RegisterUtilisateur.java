package ift2255.robotix.Modeles;

/**
 * La classe {@code RegisterUtilisateur} est un singleton qui gère une instance de l'objet {@code Utilisateur}.
 * Elle fournit des méthodes pour obtenir et définir l'objet {@code Utilisateur}.
 */
public class RegisterUtilisateur extends RegisterUser{

    /**
     * L'instance unique de la classe {@code RegisterUtilisateur}.
     */
    private static RegisterUtilisateur instance = null;

    /**
     * L'objet {@code Utilisateur} à stocker.
     */
    private Utilisateur utilisateur;

    /**
     * Constructeur privé pour empêcher l'instanciation depuis l'extérieur de la classe.
     */
    private RegisterUtilisateur() {
        // Code d'initialisation
    }

    /**
     * Retourne l'instance unique de la classe {@code RegisterUtilisateur}.
     * Si l'instance n'existe pas encore, elle est créée.
     *
     * @return l'instance unique de la classe {@code RegisterUtilisateur}
     */
    public static RegisterUtilisateur getInstance() {
        if (instance == null) {
            instance = new RegisterUtilisateur();
        }
        return instance;
    }

    /**
     * Retourne l'objet {@code Utilisateur}.
     *
     * @return l'objet {@code Utilisateur}
     */
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    /**
     * Définit l'objet {@code Utilisateur}.
     *
     * @param utilisateur l'objet {@code Utilisateur} à définir
     */
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
