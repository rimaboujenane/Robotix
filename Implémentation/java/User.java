import java.util.Scanner;

class User {

    private static PartDatabase db = new PartDatabase();
    private static int user_index;
    private int user_id;
    private String[] interets;
    private Notification[] notifs;
    private String username;
    private Mouvement[] mouv;
    private Operation[] ops;
    private Tache[] t;
    private Robot[] bots;
    private User[] following;
    private User[] followers;
    protected boolean fournisseur;
    
    public User(String name) {

        user_index++;
        user_id = user_index;
        username = name;
        setFournisseur();
        
        userInfo();
    }
    public void addMouv(Mouvement mouv) {

        if (this.mouv == null) {this.mouv = new Mouvement[1]; this.mouv[0] = mouv;}
        else {

            Mouvement[] mouv_copy = new Mouvement[this.mouv.length + 1];
            for (int i = 0; i < this.mouv.length; i++) {
    
                mouv_copy[i] = this.mouv[i];
            }
            mouv_copy[mouv_copy.length - 1] = mouv;
            this.mouv = mouv_copy;
        }
    }
    public void addOps(Operation op) {

        if (this.ops == null) {this.ops = new Operation[1]; this.ops[0] = op;}
        else {

            Operation[] ops_copy = new Operation[this.ops.length + 1];
            for (int i = 0; i < this.ops.length; i++) {
    
                ops_copy[i] = this.ops[i];
            }
            ops_copy[ops_copy.length - 1] = op;
            this.ops = ops_copy;
        }
    }
    public void removeOp(int ind) {

        Operation[] o_copy = new Operation[ops.length-1];
        for (int i = 0; i < ind; i++) {o_copy[i] = ops[i];}
        for (int i = ind+1; i < o_copy.length; i++) {o_copy[i] = ops[i];}
        ops = o_copy;
    }
    public void modifyOp(int ind, String n, Mouvement... mouv) {

        ops[ind].setName(n);
        for (Mouvement m: mouv) {ops[ind].addMouv(m);}
    }
    public void addTache(Tache t) {

        if (this.t == null) {this.t = new Tache[1]; this.t[0] = t;}
        else {

            Tache[] t_copy = new Tache[this.t.length + 1];
            for (int i = 0; i < this.t.length; i++) {
    
                t_copy[i] = this.t[i];
            }
            t_copy[t_copy.length - 1] = t;
            this.t = t_copy;
        }
    }
    public void removeTache(int ind) {

        Tache[] t_copy = new Tache[t.length-1];
        for (int i = 0; i < ind; i++) {t_copy[i] = t[i];}
        for (int i = ind+1; i < t_copy.length; i++) {t_copy[i] = t[i];}
        t = t_copy;
    }
    public void modifyTache(int ind, String n, Operation... op) {

        t[ind].setName(n);
        for (Operation o: op) {t[ind].addOp(o);}
    }
    public void addBot(Robot r) {

        if (this.bots == null) {this.bots = new Robot[1]; this.bots[0] = r;}
        else {

            Robot[] r_copy = new Robot[this.bots.length + 1];
            for (int i = 0; i < this.bots.length; i++) {
    
                r_copy[i] = this.bots[i];
            }
            r_copy[r_copy.length - 1] = r;
            this.bots = r_copy;
        }
    }
    public void followUser(User u) {

        if (this.following == null) {this.following = new User[1]; this.following[0] = u;}
        else {

            User[] f_copy = new User[this.following.length + 1];
            for (int i = 0; i < this.following.length; i++) {
    
                f_copy[i] = this.following[i];
            }
            f_copy[f_copy.length - 1] = u;
            this.following = f_copy;
        }
        u.addFollower(this);
        String message = getUser() + " has followed you.";
        u.addNotification(new Notification(message));
    }
    public void addFollower(User u) {

        if (this.followers == null) {this.followers = new User[1]; this.followers[0] = u;}
        else {

            User[] f_copy = new User[this.followers.length + 1];
            for (int i = 0; i < this.followers.length; i++) {
    
                f_copy[i] = this.followers[i];
            }
            f_copy[f_copy.length - 1] = u;
            this.followers = f_copy;
        }
    }
    public void addInteret(String in) {

        if (this.interets == null) {this.interets = new String[1]; this.interets[0] = in;}
        else {

            String[] i_copy = new String[this.interets.length + 1];
            for (int i = 0; i < this.interets.length; i++) {
    
                i_copy[i] = this.interets[i];
            }
            i_copy[i_copy.length - 1] = in;
            this.interets = i_copy;
        }
    }
    public void addNotification(Notification n) {


        if (this.notifs == null) {this.notifs = new Notification[1]; this.notifs[0] = n;}
        else {

            Notification[] n_copy = new Notification[this.notifs.length + 1];
            for (int i = 0; i < this.notifs.length; i++) {
    
                n_copy[i] = this.notifs[i];
            }
            n_copy[n_copy.length - 1] = n;
            this.notifs = n_copy;
        }
    }
    public void userInfo() {

        System.out.println("Réussite de création de compte.");
        System.out.println(
            
            "Username: " + username +
            "\nID: " + user_id +
            "\nFournisseur: " + fournisseur
        );
    }
    public void userProfile() {

        System.out.println(
            
            "Username: " + username +
            "\nFournisseur: " + fournisseur
        );
        System.out.println("Interets:");
        for (String i: interets) {if (i != null) {System.out.println("\t" + i);}}
        System.out.println("Suit:");
        for (User f: following) {System.out.println("\t" + f.getUser());}
        System.out.println("Suiveurs:");
        for (User f: followers) {System.out.println("\t" + f.getUser());}
    }
    public String getUser() {return username;}
    public PartDatabase getDatabase() {return db;}
    public Mouvement[] getMouv() {return mouv;}
    public Operation[] getOps() {return ops;}
    public Tache[] getTaches() {return t;}
    public Robot[] getBots() {return bots;}
    public void getNotifs() {for (Notification n: notifs) {n.announce();}}
    public void setFournisseur() {

        fournisseur = false;
    }
    public void acheteComposante(PartDatabase d, String f, String c) {

        d.purchase(f, c);
    }
    public boolean isFournisseur() {return fournisseur;}
    public static void main(String[] args) {

        //User newUser = new User("David");
    }
}