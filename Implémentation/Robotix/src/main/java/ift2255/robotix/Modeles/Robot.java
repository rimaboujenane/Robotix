package ift2255.robotix.Modeles;

public class Robot {
    private int id;
    private String numeroSerie;
    private String nom;
    private String type;
    private String position;
    private double vitesse;
    private int niveauBatterie;
    private double consommationCPU;
    private double consommationMemoire;
    private String utilisateurEmail;

    /**
     * Constructeur par défaut pour créer un robot sans attributs.
     */
    public Robot() {
        // Création de robot sans attributs
    }

    /**
     * Constructeur avec paramètres pour initialiser tous les attributs du robot.
     *
     * @param id                 L'identifiant du robot.
     * @param numeroSerie        Le numéro de série du robot.
     * @param nom                Le nom du robot.
     * @param type               Le type du robot.
     * @param position           La position du robot.
     * @param vitesse            La vitesse du robot.
     * @param niveauBatterie     Le niveau de batterie du robot.
     * @param consommationCPU    La consommation CPU du robot.
     * @param consommationMemoire La consommation mémoire du robot.
     * @param utilisateurEmail   L'email de l'utilisateur propriétaire du robot.
     */
    public Robot(int id, String numeroSerie, String nom, String type, String position, double vitesse, int niveauBatterie, double consommationCPU, double consommationMemoire, String utilisateurEmail) {
        this.id = id;
        this.numeroSerie = numeroSerie;
        this.nom = nom;
        this.type = type;
        this.position = position;
        this.vitesse = vitesse;
        this.niveauBatterie = niveauBatterie;
        this.consommationCPU = consommationCPU;
        this.consommationMemoire = consommationMemoire;
        this.utilisateurEmail = utilisateurEmail;
    }

    /**
     * Obtient l'identifiant du robot.
     *
     * @return L'identifiant du robot.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtient le numéro de série du robot.
     *
     * @return Le numéro de série du robot.
     */
    public String getNumeroSerie() {
        return numeroSerie;
    }

    /**
     * Obtient le nom du robot.
     *
     * @return Le nom du robot.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Obtient le type du robot.
     *
     * @return Le type du robot.
     */
    public String getType() {
        return type;
    }

    /**
     * Obtient la position du robot.
     *
     * @return La position du robot.
     */
    public String getPosition() {
        return position;
    }

    /**
     * Obtient la vitesse du robot.
     *
     * @return La vitesse du robot.
     */
    public double getVitesse() {
        return vitesse;
    }

    /**
     * Obtient le niveau de batterie du robot.
     *
     * @return Le niveau de batterie du robot.
     */
    public int getNiveauBatterie() {
        return niveauBatterie;
    }

    /**
     * Obtient la consommation CPU du robot.
     *
     * @return La consommation CPU du robot.
     */
    public double getConsommationCPU() {
        return consommationCPU;
    }

    /**
     * Obtient la consommation mémoire du robot.
     *
     * @return La consommation mémoire du robot.
     */
    public double getConsommationMemoire() {
        return consommationMemoire;
    }

    /**
     * Obtient l'email de l'utilisateur propriétaire du robot.
     *
     * @return L'email de l'utilisateur propriétaire du robot.
     */
    public String getUtilisateurEmail() {
        return utilisateurEmail;
    }

    /**
     * Définit l'identifiant du robot.
     *
     * @param id L'identifiant du robot.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Définit le nom du robot.
     *
     * @param nom Le nom du robot.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Définit le type du robot.
     *
     * @param type Le type du robot.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Définit le numéro de série du robot.
     *
     * @param numeroSerie Le numéro de série du robot.
     */
    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    /**
     * Définit la position du robot.
     *
     * @param position La position du robot.
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Définit la vitesse du robot.
     *
     * @param vitesse La vitesse du robot.
     */
    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }

    /**
     * Définit le niveau de batterie du robot.
     *
     * @param niveauBatterie Le niveau de batterie du robot.
     */
    public void setNiveauBatterie(int niveauBatterie) {
        this.niveauBatterie = niveauBatterie;
    }

    /**
     * Définit la consommation CPU du robot.
     *
     * @param consommationCPU La consommation CPU du robot.
     */
    public void setConsommationCPU(double consommationCPU) {
        this.consommationCPU = consommationCPU;
    }

    /**
     * Définit la consommation mémoire du robot.
     *
     * @param consommationMemoire La consommation mémoire du robot.
     */
    public void setConsommationMemoire(double consommationMemoire) {
        this.consommationMemoire = consommationMemoire;
    }

    /**
     * Définit l'email de l'utilisateur propriétaire du robot.
     *
     * @param utilisateurEmail L'email de l'utilisateur propriétaire du robot.
     */
    public void setUtilisateurEmail(String utilisateurEmail) {
        this.utilisateurEmail = utilisateurEmail;
    }

    /**
     * Affiche l'état du robot.
     *
     * @param general Si vrai, affiche un état général. Sinon, affiche un état complet.
     */
    public void afficherEtat(Boolean general) {
        if (general) {
            System.out.println("Nom : " + nom);
            System.out.println("Type : " + type);
            System.out.println("Niveau de batterie : " + niveauBatterie + "%");
        } else {
            System.out.println("État du Robot :");
            System.out.println("Numéro de série : " + numeroSerie);
            System.out.println("Nom : " + nom);
            System.out.println("Type : " + type);
            System.out.println("Position : " + position);
            System.out.println("Vitesse : " + vitesse + " m/s");
            System.out.println("Niveau de batterie : " + niveauBatterie + "%");
            System.out.println("Consommation CPU : " + consommationCPU + "%");
            System.out.println("Consommation Mémoire : " + consommationMemoire + " MB");
        }
    }

    /**
     * Convertit les informations du robot en chaîne de caractères.
     *
     * @return Une chaîne de caractères représentant les informations du robot.
     */
    @Override
    public String toString() {
        return "Robot {" +
                "Numéro de série='" + numeroSerie + '\'' +
                ", Nom='" + nom + '\'' +
                ", Type='" + type + '\'' +
                ", Position='" + position + '\'' +
                ", Vitesse=" + vitesse + " m/s" +
                ", Niveau de batterie=" + niveauBatterie + "%" +
                ", Consommation CPU=" + consommationCPU + "%" +
                ", Consommation Mémoire=" + consommationMemoire + " MB" +
                '}';
    }
}
