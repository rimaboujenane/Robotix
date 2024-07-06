class Operation {

    private String name;
    private Mouvement[] op;
    private double battery_cost;
    //private Robot owner;
    
    public Operation(String name, Mouvement... mouv) {

        this.name = name;
        op = mouv;
        for (Mouvement m : mouv) {battery_cost += m.getBatteryCost();}
        //this.owner = owner;
    }
    public void go() {

        System.out.println("Opération: " + name);
        System.out.println("Battérie requis: " + battery_cost);
        for (Mouvement m : op) {m.go();}
    }
    public String getName() {return name;}
    public double getBatteryCost() {return battery_cost;}
    public static void main(String[] args) {

        //Composante arm1 = new Bras();
        //Mouvement up = new Mouvement(arm1, "Move Upwards", "Moving upwards 5cm");
        //Mouvement down = new Mouvement(arm1, "Move Downwards", "Moving downwards 5cm");
        //Operation updown = new Operation("Up and down", up, down);
        //updown.go();
    }
}