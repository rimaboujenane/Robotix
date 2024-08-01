package ift2255.robotix.Modeles;

public class Composante {
    private int id;
    private String nom;
    private String type;
    private String description;
    private int prix;
    private String fournisseurEmail;

    public Composante(int id, String nom, String type, String description, int prix, String fournisseurEmail) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.description = description;
        this.prix = prix;
        this.fournisseurEmail = fournisseurEmail;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getFournisseurEmail() {
        return fournisseurEmail;
    }

    public void setFournisseurEmail(String fournisseurEmail) {
        this.fournisseurEmail = fournisseurEmail;
    }

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