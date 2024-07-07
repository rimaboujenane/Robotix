class Operation {

    private String name;
    private Mouvement[] op;
    private double battery_cost = 0;
    //private Robot owner;
    
    public Operation(String name, Mouvement... mouv) {

        this.name = name;
        op = mouv;
        for (Mouvement m : mouv) {if (m != null) {battery_cost += m.getBatteryCost();}}
        //this.owner = owner;
    }
    public void addMouv(Mouvement mouv) {

        if (this.op == null) {this.op = new Mouvement[1]; this.op[0] = mouv;}
        else {

            Mouvement[] op_copy = new Mouvement[this.op.length + 1];
            for (int i = 0; i < this.op.length; i++) {
    
                op_copy[i] = this.op[i];
            }
            op_copy[op_copy.length - 1] = mouv;
            this.op = op_copy;
        }
    }
    public void go() {

        System.out.println("Opération: " + name);
        System.out.println("Battérie requis: " + battery_cost);
        for (Mouvement m : op) {if (m != null) {m.go();}}
    }
    public String getName() {return name;}
    public void setName(String s) {name = s;}
    public double getBatteryCost() {return battery_cost;}
    public static void main(String[] args) {

        Composante arm1 = new Bras();
        Mouvement up = new Mouvement(arm1, "Move Upwards", "Moving upwards 5cm", 5, "cm", 'z', 2);
        Mouvement down = new Mouvement(arm1, "Move Downwards", "Moving downwards 5cm", 5, "cm", 'z', 2);
        Operation updown = new Operation("Up and down", up, down, up, down);
        updown.go();
    }
}