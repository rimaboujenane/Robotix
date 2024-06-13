class Operation {

    private String name;
    private Mouvement[] op;
    //private Robot owner;
    
    public Operation(String name, Mouvement... mouv) {

        this.name = name;
        op = mouv;
        //this.owner = owner;
    }
    public String getName(){return name;}
    public void go() {

        System.out.println("Performing Operation: " + name);
        for (Mouvement m : op) {m.go();}
    }
    public static void main(String[] args) {

        Composante arm1 = new Bras();
        Mouvement up = new Mouvement(arm1, "Move Upwards", "Moving upwards 5cm");
        Mouvement down = new Mouvement(arm1, "Move Downwards", "Moving downwards 5cm");
        Operation updown = new Operation("Up and down", up, down);
        updown.go();
    }
}
