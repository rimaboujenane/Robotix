import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Fournisseur extends User {
    
    public Fournisseur(String name) {

        super(name);
        setName(name);
        registrer(name);
        
    }
    @Override
    public void setFournisseur() {

        fournisseur = true;
    }
    public void setName(String name) {

        String[] fournisseurs = getDatabase().getFournisseurs();
        boolean unique = false;
        
        while (!unique) {
            for (String f : fournisseurs) {
                if (name == f) {
                    System.out.println("\nCe nom existe déjà.");
                    System.out.println("Entrer nom de l'entreprise.");
                    Scanner n = new Scanner(System.in);
                    name = n.nextLine();
                }
                else {unique = true;}
            }
        }
    }
    
    public void registrer(String user) {getDatabase().addFournisseur(user);}
    public void fournir(String... composante) {

        for (String c : composante) {getDatabase().addComposante(getUser(), c);}
    }
    public static void main(String[] args) {

        //User fourn = new Fournisseur("John");
    }
}