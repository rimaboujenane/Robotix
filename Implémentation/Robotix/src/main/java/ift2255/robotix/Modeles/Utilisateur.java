package ift2255.robotix.Modeles;

/**
 * Représente un utilisateur spécifique dans le système.
 * Hérite de la classe abstraite {@link User}.
 */
public class Utilisateur extends User {

    /**
     * Constructeur pour initialiser un utilisateur.
     *
     * @param nom Le nom de l'utilisateur
     * @param prenom Le prénom de l'utilisateur
     * @param password Le mot de passe de l'utilisateur
     * @param email L'email de l'utilisateur
     * @param telephone Le numéro de téléphone de l'utilisateur
     */
    public Utilisateur(String nom, String prenom, String password, String email, String telephone) {
        // Appel du constructeur de la classe parent User
        super(nom, prenom, password, email, telephone);
    }
}
