package ift2255.robotix.Modeles;

public class Robot{
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

    public Robot(){
        // creation de robot sans attributs
    }

    public Robot(int id,String numeroSerie, String nom, String type, String position, double vitesse,int niveauBatterie, double consommationCPU, double consommationMemoire, String utilisateurEmail){
        this.id= id;
        this.numeroSerie= numeroSerie;
        this.nom= nom;
        this.type=type;
        this.position=position;
        this.vitesse= vitesse;
        this.niveauBatterie= niveauBatterie;
        this.consommationCPU= consommationCPU;
        this.consommationMemoire=consommationMemoire;
        this.utilisateurEmail=utilisateurEmail;
    }

    // getters de la classe
    public int getId(){
        return id;
    }
    public String getNumeroSerie(){
        return numeroSerie;
    }
    public String getNom(){
        return nom;
    }
    public String getType(){
        return type;
    }
    public String getPosition(){
        return position;
    }
    public double getVitesse(){
        return vitesse;
    }
    public int getNiveauBatterie(){
        return niveauBatterie;
    }
    public double getConsommationCPU(){
        return consommationCPU;
    }
    public double getConsommationMemoire(){
        return consommationMemoire;
    }
    public String getUtilisateurEmail(){
        return utilisateurEmail;
    }
    // setters de la classe
    public void setId(int id){
        this.id=id;
    }
    public void setNom(String nom){
        this.nom= nom;
    }
    public void setType(String type){
        this.type=type;
    }
    public void setNumeroSerie(String numeroSerie){
        this.numeroSerie= numeroSerie;
    }
    public void setPosition(String position){
        this.position=position;
    }
    public void setVitesse(double vitesse){
        this.vitesse=vitesse;
    }
    public void setNiveauBatterie( int niveauBatterie){
        this.niveauBatterie= niveauBatterie;
    }
    public void setConsommationCPU(double consommationCPU){
        this.consommationCPU= consommationCPU;
    }
    public void setConsommationMemoire(double consommationMemoire){
        this.consommationMemoire= consommationMemoire;
    }

    public void setUtilisateurEmail(String utilisateurEmail){
        this.utilisateurEmail= utilisateurEmail;
    }

    // fonction qui prends un boolean en parametres si le bool == True 
    // affiche un état général sinon on affiche complet.
    public void afficherEtat(Boolean general){
         if(general){
            System.out.println("Nom : " + nom);
            System.out.println("Type : " + type);
            System.out.println("Niveau de batterie : " + niveauBatterie + "%");
         }else{
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


@Override
public String toString(){
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
