import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class PartDatabase {

    //private boolean[] parts = new boolean[8];
    private String current_fourn;
    private int ind;

    private static String[] fournisseurs;
    private static boolean[][] db;
    protected static String[] composantes = {"CPU", "Roues", "Bras", "Hélice", "Caméra", "Haut-Parleurs", "Micro", "Écran"};
    protected static String[] lower_comp = {"cpu", "roues", "bras", "helice", "camera", "hautparleur", "micro", "ecran"};
    
    public PartDatabase() {

        getDatabase();
    }
    public void getDatabase() {

        String line;
        
        try {
            
            File produits = new File("produits.txt");
            Scanner s = new Scanner(produits);
            
            while (s.hasNextLine()) {
                line = s.nextLine();
                //System.out.println(line);
                if (line.equals("*")) {ind++;}}
            
            fournisseurs = new String[ind];
            db = new boolean[ind][8];
            ind = 0;
            
            s = new Scanner(produits);
            
            while (s.hasNextLine()) {
                line = s.nextLine();
                //System.out.println(line);
                parseData(line);
            }
            s.close();
        } catch (FileNotFoundException e) {System.out.print("Database unavailable");}
    }
    public void parseData(String line) {
        
        switch(line) {

            case "cpu":
                db[ind][0] = true;
                break;
            case "roues":
                db[ind][1] = true;
                break;
            case "bras":
                db[ind][2] = true;
                break;
            case "helice":
                db[ind][3] = true;
                break;
            case "camera":
                db[ind][4] = true;
                break;
            case "hautparleur":
                db[ind][5] = true;
                break;
            case "micro":
                db[ind][6] = true;
                break;
            case "ecran":
                db[ind][7] = true;
                break;
            case "*":
                fournisseurs[ind] = current_fourn;
                ind++;
                break;
            default:
                current_fourn = line;
        }
    }
    public void purchase(String f, String c) {

        try {
            if (hasItem(f, c)) {
                System.out.println("Purchased " + c + " from " + f + ".");
            } else {
                System.out.println(f + " does not supply " + c);
            }
        } catch (Exception e) {e.printStackTrace();}
    }
    public boolean hasItem(String f, String c) throws InvalidPartException, InvalidSupplierException {

        int ind = 0;
        boolean has;
        boolean found = false;
        
        for (int i = 0; i < fournisseurs.length; i++) {

            if (f.equals(fournisseurs[i])) {ind = i; found = true; break;}
        }
        if (!found) {throw new InvalidSupplierException();}
        
        switch(c) {

            case "cpu":
                has = db[ind][0];
                break;
            case "roues":
                has = db[ind][1];
                break;
            case "bras":
                has = db[ind][2];
                break;
            case "helice":
                has = db[ind][3];
                break;
            case "camera":
                has = db[ind][4];
                break;
            case "hautparleur":
                has = db[ind][5];
                break;
            case "micro":
                has = db[ind][6];
                break;
            case "ecran":
                has = db[ind][7];
                break;
            default:
                throw new InvalidPartException();
        }
        return has;
    }
    public boolean hasFournisseur(String f) {

        boolean found = false;
        
        for (int i = 0; i < fournisseurs.length; i++) {

            if (f.equals(fournisseurs[i])) {ind = i; found = true; break;}
        }
        return found;
    }
    public void addFournisseur(String f) {

        try {
            
            FileWriter produits = new FileWriter("produits.txt", true);
            produits.write("\n" + f);
            produits.write("\n*");
            produits.close();
            ind = 0;
            getDatabase();

        } catch (IOException e) {System.out.println("Problem with database.");}

        updateDatabase();
    }
    public void addComposante(String f, String c) {

        try {
            if (hasItem(f, c)) {System.out.println(f + " already supplies " + c);}
            else {
                for (int i = 0; i < fournisseurs.length; i++) {
    
                    if (f.equals(fournisseurs[i])) {ind = i; break;}
                }
                current_fourn = f;
                parseData(c);
                System.out.println("Added " + c);
                updateDatabase();
            }
        } catch (Exception e) {e.printStackTrace();}
    }

    public void updateDatabase() {

        try {

            FileWriter produits = new FileWriter("produits.txt");
            for (int i = 0; i < fournisseurs.length; i++) {

                produits.write(fournisseurs[i] + "\n");
                for (int j = 0; j < 8; j++) {

                    if (db[i][j]) {produits.write(lower_comp[j] + "\n");}
                }
                if (i == fournisseurs.length-1) {produits.write("*");}
                else {produits.write("*\n");}
            }
            produits.close();
        } catch (IOException e) {System.out.println("Problem with database.");}
    }
    public void displayData() {

        for (int i = 0; i < fournisseurs.length; i++) {

            System.out.println(fournisseurs[i]);
            System.out.println("-------------");
            for (int j = 0; j < 8; j++) {if (db[i][j]) {
                System.out.println(composantes[j]);}
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {

        PartDatabase data = new PartDatabase();
        //data.addFournisseur("Momma's 'Ponents");
        //data.addComposante("Momma's 'Ponents", "bras");
        data.displayData();
        //data.purchase("Peacy PC", "cpu");
    }
}