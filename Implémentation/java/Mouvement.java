class Mouvement {

    private Composante owner;
    private String name;
    private String desc;
    
    public Mouvement(Composante comp, String name, String desc) {

        owner = comp;
        this.name = name;
        this.desc = desc;
    }
    public void go() {

        owner.compInfo();
        System.out.println(name);
        System.out.println(desc);
    }
    public static void main(String[] args) {

        Composante arm1 = new Bras();
        Mouvement up = new Mouvement(arm1, "Move Upwards", "Moving upwards 5cm");
        up.go();
    }
}