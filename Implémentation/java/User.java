import java.util.Scanner;

class User {

    private static PartDatabase db = new PartDatabase();
    private static int user_index;
    private int user_id;
    private String[] interets = new String[100];
    private String username;
    private Mouvement[] mouv;
    private Operation[] ops;
    private Tache[] t;
    protected boolean fournisseur;
    
    public User(String name) {

        user_index++;
        user_id = user_index;
        username = name;
        setFournisseur();
        
        userInfo();
    }
    public void setInterets() {

        System.out.println("Entrer au moins 10 interets. Quand vous avez fini, entrer un '*'.");

        Scanner s = new Scanner(System.in);
        String interet;
        int i = 0;

        while (i < 10) {

            interet = s.nextLine(); //System.out.println(interet);
            if (interet.equals("*")) {
                if (i > 9) {break;}
                else {System.out.println("Il faut au moins 10 interets.");}
            }
            else {i++; interets[i] = interet;}
        }
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
    public void userInfo() {

        System.out.println("Réussite de création de compte.");
        System.out.println(
            
            "Username: " + username +
            "\nID: " + user_id +
            "\nFournisseur: " + fournisseur
        );
    }
    public String getUser() {return username;}
    public PartDatabase getDatabase() {return db;}
    public Mouvement[] getMouv() {return mouv;}
    public Operation[] getOps() {return ops;}
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