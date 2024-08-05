/**
 * Classe représentant un robot avec ses attributs et méthodes associées.
 */
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
     * Constructeur pour initialiser un robot avec les attributs spécifiés.
     *
     * @param id L'identifiant du robot
     * @param numeroSerie Le numéro de série du robot
     * @param nom Le nom du robot
     * @param type Le type du robot
     * @param position La position actuelle du robot
     * @param vitesse La vitesse du robot
     * @param niveauBatterie Le niveau de batterie du robot en pourcentage
     * @param consommationCPU La consommation CPU du robot en pourcentage
     * @param consommationMemoire La consommation de mémoire du robot en MB
     * @param utilisateurEmail L'email de l'utilisateur associé au robot
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

    // Getters de la classe

    /**
     * @return L'identifiant du robot
     */
    public int getId() {
        return id;
    }

    /**
     * @return Le numéro de série du robot
     */
    public String getNumeroSerie() {
        return numeroSerie;
    }

    /**
     * @return Le nom du robot
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return Le type du robot
     */
    public String getType() {
        return type;
    }

    /**
     * @return La position actuelle du robot
     */
    public String getPosition() {
        return position;
    }

    /**
     * @return La vitesse du robot
     */
    public double getVitesse() {
        return vitesse;
    }

    /**
     * @return Le niveau de batterie du robot en pourcentage
     */
    public int getNiveauBatterie() {
        return niveauBatterie;
    }

    /**
     * @return La consommation CPU du robot en pourcentage
     */
    public double getConsommationCPU() {
        return consommationCPU;
    }

    /**
     * @return La consommation de mémoire du robot en MB
     */
    public double getConsommationMemoire() {
        return consommationMemoire;
    }

    /**
     * @return L'email de l'utilisateur associé au robot
     */
    public String getUtilisateurEmail() {
        return utilisateurEmail;
    }

    // Setters de la classe

    /**
     * @param id L'identifiant du robot à définir
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param nom Le nom du robot à définir
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @param type Le type du robot à définir
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @param numeroSerie Le numéro de série du robot à définir
     */
    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    /**
     * @param position La position actuelle du robot à définir
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * @param vitesse La vitesse du robot à définir
     */
    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }

    /**
     * @param niveauBatterie Le niveau de batterie du robot en pourcentage à définir
     */
    public void setNiveauBatterie(int niveauBatterie) {
        this.niveauBatterie = niveauBatterie;
    }

    /**
     * @param consommationCPU La consommation CPU du robot en pourcentage à définir
     */
    public void setConsommationCPU(double consommationCPU) {
        this.consommationCPU = consommationCPU;
    }

    /**
     * @param consommationMemoire La consommation de mémoire du robot en MB à définir
     */
    public void setConsommationMemoire(double consommationMemoire) {
        this.consommationMemoire = consommationMemoire;
    }

    /**
     * @param utilisateurEmail L'email de l'utilisateur associé au robot à définir
     */
    public void setUtilisateurEmail(String utilisateurEmail) {
        this.utilisateurEmail = utilisateurEmail;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères du robot.
     *
     * @return Une chaîne de caractères représentant le robot
     */
    @Override
    public String toString() {
        return "Robot
