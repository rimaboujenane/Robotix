import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Fournisseur extends User {
    
    public Fournisseur(String name) {

        super(name);
        registrer(name);
        
    }
    @Override
    public void setFournisseur() {

        fournisseur = true;
    }
    public void registrer(String user) {getDatabase().addFournisseur(user);}
    public void fournir(String... composante) {

        for (String c : composante) {getDatabase().addComposante(getUser(), c);}
    }
    public static void main(String[] args) {

        //User fourn = new Fournisseur("John");
    }
}