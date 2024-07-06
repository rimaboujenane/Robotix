class Mouvement {

    private Composante owner;
    private static int mouv_index;
    private int mouv_id;
    private String name;
    private String desc;
    private int dist;
    private String unit;
    private char a;
    private double duree;
    private double battery_cost;
    private int cpu_cost;
    private boolean movesBot;
    
    public Mouvement(Composante comp, String name, String desc, int dist, String unit, char a, double duree) {

        mouv_index++;
        mouv_id = mouv_index;
        owner = comp;
        this.name = name;
        this.desc = desc;
        this.dist = dist;
        this.unit = unit;
        this.a = a;
        this.duree = duree;
        this.battery_cost = duree * comp.getBatterie();

        if ((owner.getType() == "roues") || (owner.getType() == "helice")) {movesBot = true;}
    }
    public void go() {

        owner.compInfo();
        System.out.println("Mouvement ID: " + mouv_id);
        System.out.println(name);
        System.out.println(desc);
        System.out.println("Distance: " + dist + unit);
        System.out.println("Axe: " + a);
        System.out.println("Durée: " + duree);
        System.out.println("Battérie requis: " + battery_cost);
    }
    public String getName() {return name;}
    public double getBatteryCost() {return battery_cost;}
    public static void main(String[] args) {

        Composante arm1 = new Bras();
        Mouvement up = new Mouvement(arm1, "Move Upwards", "Moving upwards 5cm", 5, "cm", 'z', 2);
        up.go();
    }
}