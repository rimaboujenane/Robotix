package ift2255.robotix.Modeles;

/**
 * Représente un fournisseur dans le système.
 * Hérite des attributs et des méthodes de la classe User.
 */
public class Fournisseur extends User {

    /**
     * Constructeur pour créer un nouveau fournisseur avec les informations spécifiées.
     *
     * @param nom Le nom du fournisseur
     * @param prenom Le prénom du fournisseur
     * @param password Le mot de passe du fournisseur
     * @param email L'email du fournisseur
     * @param telephone Le numéro de téléphone du fournisseur
     */
    public Fournisseur(String nom, String prenom, String password, String email, String telephone) {
        super(nom, prenom, password, email, telephone); // Appel du constructeur de la classe User
    }
}
