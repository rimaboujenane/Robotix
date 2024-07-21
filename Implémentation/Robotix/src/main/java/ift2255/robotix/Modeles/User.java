package ift2255.robotix.Modeles;

/**
 * Classe abstraite représentant un utilisateur.
 */
public abstract class User {
    private String nom;          // Nom de l'utilisateur
    private String prenom;       // Prénom de l'utilisateur
    private String password;    // Mot de passe de l'utilisateur
    private String email;       // Email de l'utilisateur
    private String telephone;   // Numéro de téléphone de l'utilisateur

    /**
     * Constructeur pour initialiser les informations de l'utilisateur.
     *
     * @param nom Le nom de l'utilisateur
     * @param prenom Le prénom de l'utilisateur
     * @param password Le mot de passe de l'utilisateur
     * @param email L'email de l'utilisateur
     * @param telephone Le numéro de téléphone de l'utilisateur
     */
    public User(String nom, String prenom, String password, String email, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
    }

    /**
     * Retourne le nom de l'utilisateur.
     *
     * @return Le nom de l'utilisateur
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne le prénom de l'utilisateur.
     *
     * @return Le prénom de l'utilisateur
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Retourne le mot de passe de l'utilisateur.
     *
     * @return Le mot de passe de l'utilisateur
     */
    public String getPassword() {
        return password;
    }

    /**
     * Retourne l'email de l'utilisateur.
     *
     * @return L'email de l'utilisateur
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retourne le numéro de téléphone de l'utilisateur.
     *
     * @return Le numéro de téléphone de l'utilisateur
     */
    public String getTelephone() {
        return telephone;
    }
}
