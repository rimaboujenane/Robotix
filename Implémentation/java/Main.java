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
        else if (in == 50) {setFournisseur(); menuFournisseur();}
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
        user.setInterets();
        
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

        Scanner s = new Scanner(System.in);
        
        System.out.println();
        System.out.println("Choisir parmi les options suivantes:");
        System.out.println();

        System.out.println("[1] -> Voir inventaire.");
        System.out.println("[2] -> Voir base de données de fournisseurs et composantes.");
        System.out.println("[3] -> Définir mouvement.");
        System.out.println("[4] -> Définir opération.");
        System.out.println("[5] -> Définir tâche.");
        System.out.println("[6] -> Construire un robot.");
        System.out.println("[7] -> Acheter composante.");
        System.out.println("[8] -> Quitter.");

        switch(s.nextInt()) {

            case 1:
                inv.printInv();
                menuUtilisateur();
                break;
            case 2:
                user.getDatabase().displayData();
                menuUtilisateur();
                break;
            case 3:
                defMouv();
                break;
            case 4:
                defOp();
                break;
            case 5:
                defTache();
                break;
            case 6:
                defRobot();
                break;
            case 7:
                buyComposante();
                break;
            case 8:
                quit();
                break;
            default:
                System.out.println("Entrée invalide.");
                break;
        }        
    }
    public void menuFournisseur() {

        System.out.println();
        System.out.println("Choisir parmi les options suivantes:");
        System.out.println();

        System.out.println("[1] -> Voir base de données de fournisseurs et composantes.");
        System.out.println("[2] -> Fournir composante.");
        System.out.println("[3] -> Quitter.");

        Scanner s = new Scanner(System.in);
            
        switch(s.nextInt()) {

            case 1:
                user.getDatabase().displayData();
                menuFournisseur();
                break;
            case 2:
                addComposante();
                break;
            case 3:
                quit();
                break;
            default:
                System.out.println("Entrée invalide.1");
                break;
        }
    }
    public void addComposante() {

        Scanner s = new Scanner(System.in);
        s.nextLine();
        
        System.out.println();
        System.out.println("Veuillez entrer le nom de la composante que vous voulez fournir.");
        user.getDatabase().addComposante(user.getUser(), s.nextLine());
        menuFournisseur();
    }
    public void buyComposante() {

        Scanner s = new Scanner(System.in);
        
        System.out.println();
        System.out.println("Veuillez entrer le nom du fournisseur de la composante que vous voulez acheter.");
        String f = s.nextLine();
        System.out.println();
        System.out.println("Veuillez entrer le nom de la composante que vous voulez acheter.");
        String c = s.nextLine();
        try {
            user.acheteComposante(user.getDatabase(), f, c);
            inv.incInv(c);
        } catch (InvalidPartException e) {e.printStackTrace();}
        menuUtilisateur();
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
        System.out.println("Veuillez spécifier la distance du mouvement.");
        int dist = s.nextInt();
        System.out.println(); s.nextLine();
        System.out.println("Veuillez spécifier l'unité pour la distance.");
        String u = s.nextLine();
        System.out.println();
        System.out.println("Veuillez spécifier l'axe (x, y, z) du mouvement.");
        char a = s.next().charAt(0);
        System.out.println(); s.nextLine();
        System.out.println("Veuillez spécifier la duree du mouvement en secondes.");
        double dur = s.nextDouble();
        System.out.println(); s.nextLine();

        Mouvement m = new Mouvement(parseData(c), n, d, dist, u, a, dur);
        user.addMouv(m);

        menuUtilisateur();

    }
    public void defOp() {

        Scanner s = new Scanner(System.in);
        boolean done = false;
        Mouvement[] liste = new Mouvement[100];
        
        System.out.println("Liste de mouvements définis:");
        System.out.println();
        Mouvement[] m = user.getMouv();
        for (int i = 0; i < m.length; i++) {
            System.out.println("[" + i + "] -> " + m[i].getName());
        }
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
                System.out.println(m[ind].getName() + " ajouté.");
                liste[i] = m[ind]; i++;
            } catch (Exception e) {
                System.out.println("Entrée invalide.");
            }
        }
            
        System.out.println();

        Operation o = new Operation(n, liste);
        System.out.println("Opération [" + o.getName() + "] créée.");
        user.addOps(o);

        menuUtilisateur();
        
    }
    public void defTache() {

        Scanner s = new Scanner(System.in);
        boolean done = false;
        Operation[] liste = new Operation[100];
        
        System.out.println("Liste d'opérations définis:");
        System.out.println();
        Operation[] o = user.getOps();
        for (int i = 0; i < o.length; i++) {
            System.out.println("[" + i + "] -> " + o[i].getName());
        }
        s.nextLine();
        System.out.println("Veuillez nommer la tâche.");
        String n = s.nextLine();
        System.out.println();

        int i = 0;
        System.out.println("Veuillez entrer les opérations en ordre, puis entrer '*' pour terminer.");
        while (!done) {
            String c = s.nextLine();
            if (c.equals("*")) {done = true; break;}
            int ind = Integer.parseInt(c);
            try {
                System.out.println(o[ind].getName() + " ajouté.");
                liste[i] = o[ind]; i++;
            } catch (Exception e) {
                System.out.println("Entrée invalide.");
            }
        }
            
        System.out.println();

        Tache t = new Tache(n, liste);
        System.out.println("Tâche [" + t.getName() + "] créée.");
        user.addTache(t);

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

        System.out.println("Veuillez nommer le robot.");
        String n = s.nextLine();

        try {
            inv.decInv("cpu");
            robot = new Robot(n, new CPU());
            System.out.println();
            System.out.println(n + " crée. Un CPU est inclu par défault.");
        } catch (NoStockException e) {
            robot = new Robot(n);
            System.out.println("Un robot doit inclure un CPU, mais vous n'avez aucun dans ton inventaire.");
            System.out.println(n + " pas créé. Achetez les composantes nécéssaires, puis essayez de nouveau.");
            menuUtilisateur();
        } catch (InvalidPartException e) {
            robot = new Robot(n);
            System.out.println("Composante invalide.");
            menuUtilisateur();
        }

        try {
            int i = 0;
            System.out.println("Veuillez entrer les composantes que vous voulez inclure (en miniscules et sans accents), puis entrer '*' pour terminer.");
            while (!done) {
                String c = s.nextLine();
                if (c.equals("*")) {
                    if (i == 0) {System.out.println("Il faut au moins une autre composante. Veuillez choisir parmis celles dans ton inventaire.");}
                    else {done = true; break;}
                } else {
                    i++;
                    inv.decInv(c);
                    robot.addComposante(parseData(c));
                }
            }
            menuUtilisateur();
        } catch (InvalidPartException e) {
            System.out.println("Composante invalide.");
        } catch (NoStockException e) {
            System.out.println("La composante n'est pas dans votre inventaire. Achetez les composantes nécéssaires, puis essayez de nouveau.");
            menuUtilisateur();
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
    public void quit() {System.out.println("Merci d'avoir utilisé Robotix!");}
    public static void main(String[] args) {

        Main m = new Main();
    }
}