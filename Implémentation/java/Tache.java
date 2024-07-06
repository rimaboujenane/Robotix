class Tache {

    private Operation[] ops;
    private String name;
    private double battery_cost;

    public Tache(String name, Operation... ops) {

        this.name = name;
        this.ops = ops;
        for (Operation o: ops) {battery_cost += o.getBatteryCost();}
    }
    public double getBatteryCost() {return battery_cost;}
    public double go() {

        System.out.println("Tâche: " + name);
        System.out.println("Battérie: " + battery_cost);
        for (Operation o : ops) {o.go();}

        return battery_cost;
    }
    public String getName() {return name;}
}