import java.util.Scanner;
import java.io.IOException;

class Main {

    private User user;
    private Inventaire inv = new Inventaire();
    
    public Main() {

        login();
    }
    public void login() {

        System.out.println("Bienvenu à Robotix!");
        System.out.println("Créer un compte.");
        System.out.println();
        System.out.println("[1] -> Utilisateur");
        System.out.println("[2] -> Fournisseur");

        try {int i = System.in.read(); createUser(i);}
        catch (IOException e) {e.printStackTrace();}
    }
    public void createUser(int in) {

        if (in == 49) {setUser(); menuUtilisateur();}
        else if (in == 50) {setFournisseur(); menuUtilisateur();}
        else {
            System.out.println("Entrée invalide.");
            login();
        }
    }
    public void setUser() {

        Scanner s = new Scanner(System.in);

        System.out.println("Entrer votre nom.");
        System.out.println();
        s.nextLine();
        String username = s.nextLine();

        user = new User(username);
    }
    public void setFournisseur() {

        Scanner s = new Scanner(System.in);

        System.out.println("Entrer nom d'entreprise.");
        System.out.println();
        s.nextLine();
        String username = s.nextLine();

        user = new Fournisseur(username);
    }
    public void menuUtilisateur() {

        System.out.println();
        System.out.println("Choisir parmi les options suivantes:");
        System.out.println();

        System.out.println("[1] -> Voir inventaire.");
        System.out.println("[2] -> Voir base de données de fournisseurs et composantes.");
        System.out.println("[3] -> Définir mouvement.");
        System.out.println("[4] -> Définir opération.");
        System.out.println("[5] -> Construire un robot.");

        try {int i = System.in.read();

            switch(i) {
    
                case 49:
                    inv.printInv();
                    break;
                case 50:
                    user.getDatabase().displayData();
                    break;
                case 51:
                    defMouv();
                    break;
                case 52:
                    defOp();
                    break;
                case 53:
                    defRobot();
                    break;
                default:
                    System.out.println("Entrée invalide.");
                    break;
            }
        } catch (IOException e) {e.printStackTrace();}
    }
    public void defMouv() {

        Scanner s = new Scanner(System.in);
        
        System.out.println("Liste de composantes dans ton inventaire:");
        System.out.println();
        inv.printInv();

        s.nextLine();
        System.out.println();
        System.out.println("Veuillez entrer le nom de la composante pour laquelle vous voulez créer un mouvement.");
        String c = s.nextLine();
        System.out.println();
        System.out.println("Veuillez nommer le mouvement.");
        String n = s.nextLine();
        System.out.println();
        System.out.println("Veuillez ajouter une description pour le mouvement.");
        String d = s.nextLine();
        System.out.println();

        Mouvement m = new Mouvement(parseData(c), n, d);
        //user.addMouv(m);

        menuUtilisateur();

    }
    public void defOp() {

        Scanner s = new Scanner(System.in);
        boolean done = false;
        Mouvement[] liste = new Mouvement[100];
        
        System.out.println("Liste de mouvements définis:");
        System.out.println();
        /*Mouvement[] m = user.getMouv();
        for (int i = 0; i < m.length; i++) {
            System.out.println("[" + i + "] -> " + m[i].getName());
        }*/
        s.nextLine();
        System.out.println("Veuillez nommer l'opération.");
        String n = s.nextLine();
        System.out.println();

        int i = 0;
        System.out.println("Veuillez entrer les mouvements de l'opération en ordre, puis entrer '*' pour terminer.");
        while (!done) {
            String c = s.nextLine();
            if (c.equals("*")) {done = true; break;}
            int ind = Integer.parseInt(c);
            try {
                //System.out.println(m[ind].getName() + " ajouté.");
               // liste[i] = m[ind]; i++;
            } catch (Exception e) {
                System.out.println("Entrée invalide.");
            }
        }
            
        System.out.println();

        Operation o = new Operation(n, liste);
        //System.out.println("Opération [" + o.getName() + "] créée.");
        //user.addOps(o);

        menuUtilisateur();
        
    }
    public void defRobot() {

        Scanner s = new Scanner(System.in);
        boolean done = false;
        Composante[] comps = new Composante[100];
        Robot robot;
        
        System.out.println("Liste de composantes dans ton inventaire:");
        System.out.println();
        inv.printInv();

        s.nextLine();
        System.out.println("Veuillez nommer le robot.");
        String n = s.nextLine();

        try {
            inv.decInv("cpu");
            robot = new Robot(n, new CPU());
            System.out.println();
            System.out.println(n + "crée. Un CPU est inclu par défault.");

            int i = 0;
            System.out.println("Veuillez entrer les composantes que vous voulez inclure, puis entrer '*' pour terminer.");
            while (!done) {
                String c = s.nextLine();
                if (c.equals("*")) {done = true; break;}
                inv.decInv(c);
                robot.addComposante(parseData(c));
            }
        } catch (NoStockException e) {
            System.out.println("Un robot doit inclure un CPU, mais vous n'avez aucun dans ton inventaire.");
            System.out.println(n + "pas créé. Achetez les composantes nécéssaires, puis essayez de nouveau.");
            menuUtilisateur();
        } catch (InvalidPartException e) {
            System.out.println("Composante invalide.");
        }
    }
    public Composante parseData(String line) {

        Composante comp;
        
        switch(line) {

            case "cpu":
                comp = new CPU();
                break;
            case "roues":
                comp = new Roues();
                break;
            case "bras":
                comp = new Bras();
                break;
            case "helice":
                comp = new Helice();
                break;
            case "camera":
                comp = new Camera();
                break;
            case "hautparleur":
                comp = new HautParleur();
                break;
            case "micro":
                comp = new Micro();
                break;
            case "ecran":
                comp = new Ecran();
                break;
            default:
                System.out.println("Entrée invalide.");
                comp = null;
                //defMouv();
                break;
        }
        return comp;
    }
    public static void main(String[] args) {

        Main m = new Main();
    }
}