import java.util.ArrayList;

class User {

    private static PartDatabase db = new PartDatabase();
    private static int user_index;
    private int user_id;
    private String username;
    private ArrayList<Mouvement> mouvements;
    private ArrayList<Operation> operations;
    protected boolean fournisseur;
    
    public User(String name) {

        user_index++;
        user_id = user_index;
        username = name;
        setFournisseur();
        
        userInfo();
    }

    
    public void userInfo() {

        System.out.println(

            "Username: " + username +
            "\nID: " + user_id +
            "\nFournisseur: " + fournisseur
        );
    }
    public String getUser() {return username;}
    public PartDatabase getDatabase() {return db;}
    public void setFournisseur() {

        fournisseur = false;
    }
    public void acheteComposante(PartDatabase d, String f, String c) {

        d.purchase(f, c);
    }
    public boolean isFournisseur() {return fournisseur;}
    public void addMouv(Mouvement mouvement){
        mouvements.add(mouvement);
    }
    public void addOps(Operation operation){
        operations.add(operation);
    }
    public static void main(String[] args) {

        //User newUser = new User("David");
    }
}
