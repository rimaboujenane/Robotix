class Composante {

    private static int comp_index;
    private int comp_id;
    private String type;
    private double batterie;
    
    public Composante() {

        comp_index++;
        comp_id = comp_index;
    }
    public void setType(String t) {type = t;}
    public void setBatterie(int b) {batterie = b;}

    public String getType() {return type;}
    public double getBatterie() {return batterie;}
    
    public void compInfo() {

        System.out.println("Type: " + type + "; ID: " + comp_id);
    }
}