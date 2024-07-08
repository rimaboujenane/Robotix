import java.util.Scanner;
import java.util.Random;
import java.io.IOException;


class Main {

    private User user;
    private User[] users = new User[20];
    private Activite[] activ = new Activite[5];
    
    private String[] names = {"David", "John", "Jeff", "Jill", "Thomas", "Augustine", "Hildegard Von Bingen", "St Francis of Assisi", "Mary", "Charlotte", "Sarah", "Don", "Tifa", "Zelda", "Frank", "Logan", "Judas", "Mark", "Albertus Magnus", "Fred"};
    private String[] interests = {"Battle Robots", "Service Robots", "Racing Robots", "Social Robots", "Construction Robots", "Vintage Robots", "Nano Robots", "Drone Robots", "Vehicular Robots", "Repair Robots", "Entertainment Robots", "Robots for Film", "Mech Warriors", "Remote Control Bots", "Autonomous Robots", "Machine Learning Robots"};

    private String[] activites = {"Rock Paper Scissors", "Thumb War", "Arm Wrestle", "Pat-a-Cake", "Cup Stacking"};
    private String[] descrips = {"Paper beats rock, rock beats scissors, scissors beats paper.", "Strongest thumb wins.", "Strongest arm wins.", "Pat-a-cake, pat-a-cake, baker's man; Bake me a cake as fast as you can; Roll it, pat it, and mark it with a B; Put it in the oven for Baby and me.", "That cup-stacking thing that everyone lears and feels so cool that they can do it even though it's the most useless skill of life."};
    private int[] maxes = {50, 2, 2, 2, 50};
    
    private Inventaire inv = new Inventaire();
    
    public Main() {

        Random rand = new Random();
        
        for (int i = 0; i < 20; i++) {users[i] = new User(names[i]);
            for (int j = 0; j < rand.nextInt(10, 15); j++) {users[i].addInteret(interests[rand.nextInt(interests.length)]);}
        }
        for (User u: users) {
            for (int j = 0; j < rand.nextInt(12); j++) {
                u.followUser(users[rand.nextInt(users.length)]);
            }
        }
        for (int i = 0; i < 5; i++) {activ[i] = new Activite(activites[i], descrips[i], maxes[i]);}
        
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
        setInterets(user);
        
    }
    public void setFournisseur() {

        Scanner s = new Scanner(System.in);

        System.out.println("Entrer nom d'entreprise.");
        System.out.println();
        s.nextLine();
        String username = s.nextLine();

        user = new Fournisseur(username);
    }
    public void setInterets(User u) {

        System.out.println("Entrer au moins 10 interets. Quand vous avez fini, entrer un '*'.");

        Scanner s = new Scanner(System.in);
        String interet;
        int i = 0;

        while (true) {

            interet = s.nextLine(); //System.out.println(interet);
            if (interet.equals("*")) {
                if (i > 9) {break;}
                else {System.out.println("Il faut au moins 10 interets.");}
            }
            else {i++; user.addInteret(interet);}
        }
    }
    public void menuUtilisateur() {

        Scanner s = new Scanner(System.in);
        Random rand = new Random();
        
        System.out.println();
        System.out.println("Choisir parmi les options suivantes:");
        System.out.println();

        System.out.println("[1] -> Voir inventaire.");
        System.out.println("[2] -> Voir base de données de fournisseurs et composantes.");
        System.out.println("[3] -> Définir mouvement.");
        System.out.println("[4] -> Définir action.");
        System.out.println("[5] -> Définir tâche.");
        System.out.println("[6] -> Construire un robot.");
        System.out.println("[7] -> Inscrire robot à une activité.");
        System.out.println("[8] -> Acheter composante.");
        System.out.println("[9] -> Consulter utilisateurs.");
        System.out.println("[10] -> Voir notifications.");
        System.out.println("[11] -> Gérer actions.");
        System.out.println("[12] -> Gérer tâches.");
        System.out.println("[13] -> Quitter.");

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
                listActivites();
                break;
            case 8:
                buyComposante();
                break;
            case 9:
                listUsers();
                break;
            case 10:
                for (int i = 0; i < rand.nextInt(3, 8); i++) {users[rand.nextInt(20)].followUser(user);}
                //user.getNotifs();
                menuUtilisateur();
                break;
            case 11:
                listOps();
                break;
            case 12:
                listTaches();
                break;
            case 13:
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

        System.out.println();
        System.out.println("Veuillez entrer le nom de la composante pour laquelle vous voulez créer un mouvement.");
        String c = s.nextLine();
        if (parseData(c) == null) {System.out.println("Composante invalide.");}
        else {
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
        }
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
        System.out.println("Veuillez nommer l'action.");
        String n = s.nextLine();
        System.out.println();

        int i = 0;
        System.out.println("Veuillez entrer les mouvements de l'action en ordre, puis entrer '*' pour terminer.");
        while (!done) {
            String c = s.nextLine();
            if (c.equals("*")) {done = true; break;}
            int ind = Integer.parseInt(c);
            try {
                System.out.println(m[ind].getName() + " ajouté.");
                liste[i] = m[ind]; i++;
            } catch (Exception e) {
                System.out.println("Entrée invalide.");
                break;
            }
        }
            
        System.out.println();

        if (done) {
            Operation o = new Operation(n, liste);
            System.out.println("Action [" + o.getName() + "] créée.");
            user.addOps(o);
        }
        menuUtilisateur();
        
    }
    public void defTache() {

        Scanner s = new Scanner(System.in);
        boolean done = false;
        Operation[] liste = new Operation[100];
        
        System.out.println("Liste d'actions définis:");
        System.out.println();
        Operation[] o = user.getOps();
        for (int i = 0; i < o.length; i++) {
            System.out.println("[" + i + "] -> " + o[i].getName());
        }
        System.out.println("Veuillez nommer la tâche.");
        String n = s.nextLine();
        System.out.println();

        int i = 0;
        System.out.println("Veuillez entrer les actions en ordre, puis entrer '*' pour terminer.");
        while (!done) {
            String c = s.nextLine();
            if (c.equals("*")) {done = true; break;}
            int ind = Integer.parseInt(c);
            try {
                System.out.println(o[ind].getName() + " ajouté.");
                liste[i] = o[ind]; i++;
            } catch (Exception e) {
                System.out.println("Entrée invalide.");
                break;
            }
        }
            
        System.out.println();

        if (done) {
            Tache t = new Tache(n, liste);
            System.out.println("Tâche [" + t.getName() + "] créée.");
            user.addTache(t);
        }
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
            user.addBot(robot);
            menuUtilisateur();
        } catch (InvalidPartException e) {
            System.out.println("Composante invalide.");
        } catch (NoStockException e) {
            System.out.println("La composante n'est pas dans votre inventaire. Achetez les composantes nécéssaires, puis essayez de nouveau.");
            menuUtilisateur();
        }
    }
    public void listUsers() {

        System.out.println("Utilisateurs Robotix:");
        System.out.println();
        
        for (int i = 0; i < users.length; i++) {System.out.println("[" + (i+1) + "] " + users[i].getUser());}
        
        Scanner s = new Scanner(System.in);
        boolean valid = false;

        while (!valid) {
            System.out.println("Entrer le numéro correspondant pour voir un profil.");
            int choice = s.nextInt();
            valid = ((choice <= users.length) & (choice > 0));
            if (!valid) {System.out.println("Entrée invalide.");}
            else {users[choice-1].userProfile(); queryFollow(users[choice-1]);}
        }
        menuUtilisateur();   
    }
    public void queryFollow(User u) {

        System.out.println("[1] -> Suivre " + u.getUser());
        System.out.println("[2] -> Retour");

        Scanner s = new Scanner(System.in);

        while (true) {
            int choice = s.nextInt();
            if (choice == 1) {user.followUser(u); break;}
            else if (choice == 2) {menuUtilisateur(); break;}
            else {System.out.println("Entrée invalide.");}
        }
    }
    public void listOps() {

        System.out.println("Actions définies:");
        System.out.println();
        Operation[] ops = user.getOps();
        for (int i = 0; i < ops.length; i++) {System.out.println("[" + (i+1) + "] " + ops[i].getName());}
        
        Scanner s = new Scanner(System.in);
        boolean valid = false;

        while (!valid) {
            System.out.println("Entrer le numéro correspondant pour voir une action en détail.");
            int choice = s.nextInt();
            valid = ((choice <= ops.length) & (choice > 0));
            if (!valid) {System.out.println("Entrée invalide.");}
            else {ops[choice-1].go(); gererOp(ops[choice-1], choice-1);}
        }
        menuUtilisateur();   
    }
    public void gererOp(Operation o, int i) {

        System.out.println("[1] -> Supprimer " + o.getName());
        System.out.println("[2] -> Modifier " + o.getName());

        Scanner s = new Scanner(System.in);

        while (true) {
            int choice = s.nextInt();
            if (choice == 1) {user.removeOp(i-1); break;}
            else if (choice == 2) {modifyOp(i-1); break;}
            else {System.out.println("Entrée invalide.");}
        }
    }
    public void modifyOp(int i) {

        Scanner s = new Scanner(System.in);

        System.out.println();
        System.out.println("Entrer nouveau nom pour l'action.");
        String nom = s.nextLine();

        System.out.println("Liste de mouvements définis:");
        System.out.println();
        Mouvement[] m = user.getMouv();
        for (int j = 0; j < m.length; j++) {
            System.out.println("[" + (j+1) + "] -> " + m[j].getName());
        }

        System.out.println("Veuillez entrer les mouvements que vous voulez ajouter, puis entrer '*' pour terminer.");
        while (true) {
            String c = s.nextLine();
            if (c.equals("*")) {break;}
            int ind = Integer.parseInt(c)-1;
            try {
                System.out.println(m[ind].getName() + " ajouté.");
                user.addMouv(m[ind]);
            } catch (Exception e) {
                System.out.println("Entrée invalide.");
            }
        }
    }
    public void listTaches() {

        System.out.println("Tâches définies:");
        System.out.println();
        Tache[] ta = user.getTaches();
        for (int i = 0; i < ta.length; i++) {System.out.println("[" + (i+1) + "] " + ta[i].getName());}
        
        Scanner s = new Scanner(System.in);
        boolean valid = false;

        while (!valid) {
            System.out.println("Entrer le numéro correspondant pour voir une tâche en détail.");
            int choice = s.nextInt();
            valid = ((choice <= ta.length) & (choice > 0));
            if (!valid) {System.out.println("Entrée invalide.");}
            else {ta[choice-1].go(); gererTache(ta[choice-1], choice-1);}
        }
        menuUtilisateur();   
    }
    public void gererTache(Tache t, int i) {

        System.out.println("[1] -> Supprimer " + t.getName());
        System.out.println("[2] -> Modifier " + t.getName());

        Scanner s = new Scanner(System.in);

        while (true) {
            int choice = s.nextInt();
            if (choice == 1) {user.removeTache(i-1); break;}
            else if (choice == 2) {modifyTache(i-1); break;}
            else {System.out.println("Entrée invalide.");}
        }
    }
    public void modifyTache(int i) {

        Scanner s = new Scanner(System.in);

        System.out.println();
        System.out.println("Entrer nouveau nom pour la tâche.");
        String nom = s.nextLine();

        System.out.println("Liste d'actions définis:");
        System.out.println();
        Operation[] o = user.getOps();
        for (int j = 0; j < o.length; i++) {
            System.out.println("[" + (j+1) + "] -> " + o[j].getName());
        }

        System.out.println("Veuillez entrer les actions que vous voulez ajouter, puis entrer '*' pour terminer.");
        while (true) {
            String c = s.nextLine();
            if (c.equals("*")) {break;}
            int ind = Integer.parseInt(c)-1;
            try {
                System.out.println(o[ind].getName() + " ajouté.");
                user.addOps(o[ind]);
            } catch (Exception e) {
                System.out.println("Entrée invalide.");
            }
        }
    }
    public void listActivites() {

        Scanner s = new Scanner(System.in);
        boolean done = false;
        Robot[] bots = user.getBots();

        System.out.println("Liste d'activités:");
        for (int i = 0; i < activ.length; i++) {
            System.out.println("[" + (i+1) + "] " + activ[i].getName());
        }
        System.out.println("Veuillez entrer l'activité à laquelle vous voulez assigner.");
        while (!done) {
            try {
                int ind = s.nextInt();
                inscrireRobot(activ[ind-1], bots);
                done = true;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Entrée invalide.");
            }
        }
    }
    
    public void inscrireRobot(Activite a, Robot[] bots) {

        Scanner s = new Scanner(System.in);
        if (bots != null) {
            System.out.println("Liste de vos robots:");
            for (int i = 0; i < bots.length; i++) {
                System.out.println("[" + (i+1) + "] " + bots[i].getName());
            }
            System.out.println();
            System.out.println("Veuillez entrer les robots que vous voulez ajouter, puis entrer '*' pour terminer.");
            while (true) {
                String c = s.nextLine();
                if (c.equals("*")) {break;}
                int ind = Integer.parseInt(c)-1;
                try {
                    System.out.println(bots[ind].getName() + " ajouté.");
                    a.addBot(bots[ind]);
                } catch (Exception e) {
                    System.out.println("Entrée invalide.");
                }
            }
        } else {
            System.out.println("Vous n'avez pas construit des robots. Veuillez en construire pour pouvoir les assigner.");
        }
        menuUtilisateur();
        
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