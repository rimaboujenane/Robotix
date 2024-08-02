package ift2255.robotix.Modeles;

/**
 * Représente une composante dans le système.
 * Une composante est un élément avec des attributs tels que nom, type, description, prix et l'email du fournisseur.
 */
public class Composante {
    private int id;
    private String nom;
    private String type;
    private String description;
    private double prix;
    private String fournisseurEmail;

    /**
     * Constructeur pour initialiser une composante avec tous les attributs.
     *
     * @param id              L'identifiant unique de la composante.
     * @param nom             Le nom de la composante.
     * @param type            Le type de la composante.
     * @param description     Une description de la composante.
     * @param prix            Le prix de la composante.
     * @param fournisseurEmail L'email du fournisseur de la composante.
     */
    public Composante(int id, String nom, String type, String description, double prix, String fournisseurEmail) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.description = description;
        this.prix = prix;
        this.fournisseurEmail = fournisseurEmail;
    }

    /**
     * Obtient l'identifiant de la composante.
     *
     * @return L'identifiant de la composante.
     */
    public int getId() {
        return id;
    }

    /**
     * Définit l'identifiant de la composante.
     *
     * @param id L'identifiant de la composante.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtient le nom de la composante.
     *
     * @return Le nom de la composante.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de la composante.
     *
     * @param nom Le nom de la composante.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtient le type de la composante.
     *
     * @return Le type de la composante.
     */
    public String getType() {
        return type;
    }

    /**
     * Définit le type de la composante.
     *
     * @param type Le type de la composante.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Obtient la description de la composante.
     *
     * @return La description de la composante.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Définit la description de la composante.
     *
     * @param description La description de la composante.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtient le prix de la composante.
     *
     * @return Le prix de la composante.
     */
    public double getPrix() {
        return prix;
    }

    /**
     * Définit le prix de la composante.
     *
     * @param prix Le prix de la composante.
     */
    public void setPrix(double prix) {
        this.prix = prix;
    }

    /**
     * Obtient l'email du fournisseur de la composante.
     *
     * @return L'email du fournisseur.
     */
    public String getFournisseurEmail() {
        return fournisseurEmail;
    }

    /**
     * Définit l'email du fournisseur de la composante.
     *
     * @param fournisseurEmail L'email du fournisseur.
     */
    public void setFournisseurEmail(String fournisseurEmail) {
        this.fournisseurEmail = fournisseurEmail;
    }

    /**
     * Retourne une chaîne de caractères représentant la composante.
     *
     * @return Une chaîne de caractères contenant les détails de la composante.
     */
    @Override
    public String toString() {
        return "Composante{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                ", fournisseurEmail='" + fournisseurEmail + '\'' +
                '}';
    }
}
