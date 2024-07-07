class Tache {

    private Operation[] ops;
    private String name;
    private double battery_cost = 0;

    public Tache(String name, Operation... ops) {

        this.name = name;
        this.ops = ops;
        for (Operation o: ops) {if (o != null) {battery_cost += o.getBatteryCost();}}
    }
    public void addOp(Operation op) {

        if (this.ops == null) {this.ops = new Operation[1]; this.ops[0] = op;}
        else {

            Operation[] op_copy = new Operation[this.ops.length + 1];
            for (int i = 0; i < this.ops.length; i++) {
    
                op_copy[i] = this.ops[i];
            }
            op_copy[op_copy.length - 1] = op;
            this.ops = op_copy;
        }
    }
    public double getBatteryCost() {return battery_cost;}
    public double go() {

        System.out.println("Tâche: " + name);
        System.out.println("Battérie: " + battery_cost);
        for (Operation o : ops) {if (o != null) {o.go();}}

        return battery_cost;
    }
    public String getName() {return name;}
    public void setName(String n) {this.name = n;}
}