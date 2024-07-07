class Bras extends Composante {

    public Bras() {

        super();
        setType("bras");
        setBatterie(50);
    }
    public static void main(String[] args) {

        Bras b = new Bras();
        System.out.println(b.getBatterie());
    }
}
