class Mouvement {

    private Composante owner;
    private static int mouv_index;
    private int mouv_id;
    private String name;
    private String desc;
    
    public Mouvement(Composante comp, String name, String desc) {

        mouv_index++;
        mouv_id = mouv_index;
        owner = comp;
        this.name = name;
        this.desc = desc;
    }
    public void go() {

        owner.compInfo();
        System.out.println("Mouvement ID: " + mouv_id);
        System.out.println(name);
        System.out.println(desc);
    }
    public String getName() {return name;}
    public static void main(String[] args) {

        Composante arm1 = new Bras();
        Mouvement up = new Mouvement(arm1, "Move Upwards", "Moving upwards 5cm");
        up.go();
    }
}