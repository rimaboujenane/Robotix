class CPU extends Composante {

    private Mouvement[] mouv;
    private Operation[] ops;
    private Tache[] t;
    private static int ram = 1024;
    
    public CPU() {

        super();
        setType("cpu");
    }
    public CPU(Mouvement[] mouv) {

        super();
        setType("cpu");
        this.mouv = mouv;
    }
    public CPU(Mouvement[] mouv, Operation[] ops) {

        super();
        setType("cpu");
        this.mouv = mouv;
        this.ops = ops;
    }
    public void loadMouv(Mouvement... mouv) {

        if (this.mouv.length == 0) {this.mouv = mouv;}
        else {

            Mouvement[] mouv_copy = new Mouvement[this.mouv.length + mouv.length];
            for (int i = 0; i < this.mouv.length; i++) {
    
                mouv_copy[i] = this.mouv[i];
            }
            for (int i = 0; i < mouv.length; i++) {

                mouv_copy[this.mouv.length + i] = mouv[i];
            }
            this.mouv = mouv_copy;
        }
    }
    public void loadOps(Operation... ops) {

        if (this.ops.length == 0) {this.ops = ops;}
        else {

            Operation[] ops_copy = new Operation[this.ops.length + ops.length];
            for (int i = 0; i < this.ops.length; i++) {
    
                ops_copy[i] = this.ops[i];
            }
            for (int i = 0; i < ops.length; i++) {

                ops_copy[this.ops.length + i] = ops[i];
            }
            this.ops = ops_copy;
        }
    }
    public void loadTache(Tache... t) {
        
        if (this.t.length == 0) {this.t = t;}
        else {

            Tache[] t_copy = new Tache[this.t.length + t.length];
            for (int i = 0; i < this.t.length; i++) {
    
                t_copy[i] = this.t[i];
            }
            for (int i = 0; i < t.length; i++) {

                t_copy[this.t.length + i] = t[i];
            }
            this.t = t_copy;
        }
    }
    public static void main(String[] args) {

        //Composante bras = new Bras();
        //Mouvement[] mouv = {new Mouvement(bras, "up", "go up a little")};
        //Operation[] ops = {new Operation("UP", mouv)};

        //CPU compy = new CPU(mouv, ops);

        //compy.loadMouv(new Mouvement(bras, "down", "go down a little"));
    }
}
