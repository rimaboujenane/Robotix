import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Inventaire {

    private int cpu;
    private int roues;
    private int bras;
    private int helice;
    private int camera;
    private int hautparleur;
    private int micro;
    private int ecran;

    public Inventaire() {

        readInv();
    }
    public void readInv() {

        try {
            File inv = new File("inventaire.txt");
            Scanner s = new Scanner(inv);
            while (s.hasNextLine()) {

                setInv(s.nextLine());
            }
        } catch (FileNotFoundException e) {System.out.print("No inventory file.");}
            
    }
    public void setInv(String line) {

        String comp = line.split(" ")[0];
        int quant = Integer.parseInt(line.split(" ")[1]);

        switch(comp) {

            case "cpu":
                cpu = quant;
                break;
            case "roues":
                roues = quant;
                break;
            case "bras":
                bras = quant;
                break;
            case "helice":
                helice = quant;
                break;
            case "camera":
                camera = quant;
                break;
            case "hautparleur":
                hautparleur = quant;
                break;
            case "micro":
                micro = quant;
                break;
            case "ecran":
                ecran = quant;
                break;
        }
    }
    public void getInv(String part) throws InvalidPartException {

        int ans;

        switch(part) {

            case "cpu":
                ans = cpu;
                break;
            case "roues":
                ans = roues;
                break;
            case "bras":
                ans = bras;
                break;
            case "helice":
                ans = helice;
                break;
            case "camera":
                ans = camera;
                break;
            case "hautparleur":
                ans = hautparleur;
                break;
            case "micro":
                ans = micro;
                break;
            case "ecran":
                ans = ecran;
                break;
            default:
                throw new InvalidPartException();
        }
        System.out.println(ans);
    }
    public void decInv(String part) throws NoStockException, InvalidPartException {

        switch(part) {

            case "cpu":
                if (cpu < 1) {throw new NoStockException();} else {cpu--;}
                break;
            case "roues":
                if (roues < 1) {throw new NoStockException();} else {roues--;}
                break;
            case "bras":
                if (bras < 1) {throw new NoStockException();} else {bras--;}
                break;
            case "helice":
                if (helice < 1) {throw new NoStockException();} else {helice--;}
                break;
            case "camera":
                if (camera < 1) {throw new NoStockException();} else {camera--;}
                break;
            case "hautparleur":
                if (hautparleur < 1) {throw new NoStockException();} else {hautparleur--;}
                break;
            case "micro":
                if (micro < 1) {throw new NoStockException();} else {micro--;}
                break;
            case "ecran":
                if (ecran < 1) {throw new NoStockException();} else {ecran--;}
                break;
            default:
                throw new InvalidPartException();
        }
    }
    public void incInv(String part) throws InvalidPartException {

        switch(part) {

            case "cpu":
                cpu++;
                break;
            case "roues":
                roues++;
                break;
            case "bras":
                bras++;
                break;
            case "helice":
                helice++;
                break;
            case "camera":
                camera++;
                break;
            case "hautparleur":
                hautparleur++;
                break;
            case "micro":
                micro++;
                break;
            case "ecran":
                ecran++;
                break;
            default:
                throw new InvalidPartException();
        }
    }
    public void printInv() {

        System.out.println("CPU: " + cpu);
        System.out.println("Roues: " + roues);
        System.out.println("Bras: " + bras);
        System.out.println("Helices: " + helice);
        System.out.println("Cameras: " + camera);
        System.out.println("Haut-Parleurs: " + hautparleur);
        System.out.println("Micros: " + micro);
        System.out.println("Ecrans: " + ecran);
    }
    public static void main(String[] args) throws InvalidPartException, NoStockException {

        Inventaire i = new Inventaire();
        i.printInv();
        i.getInv("cpu");
        i.decInv("bras");
    }
}