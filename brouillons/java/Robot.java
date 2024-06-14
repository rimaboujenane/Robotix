class Robot {

    private static int robot_index;
    private int robot_id;
    private String name;
    private CPU cpu;
    private Composante[] comps;
    private Mouvement[] mouv;
    private Operation[] ops;
    private Inventaire inv = new Inventaire();

    public Robot(String name) {

        robot_index++;
        robot_id = robot_index;
        this.name = name;
    }
    public Robot(String name, CPU cpu) {

        robot_index++;
        robot_id = robot_index;
        this.name = name;
        this.cpu = cpu;
    }
    public Robot(String name, CPU cpu, Composante... comps) {

        robot_index++;
        robot_id = robot_index;
        this.name = name;
        this.cpu = cpu;
        this.comps = comps;
    }
    public void addComposante(Composante comp) {

        try {
            inv.decInv(comp.getType());
            Composante[] comps_copy = new Composante[comps.length+1];
            for (int i = 0; i < comps.length; i++) {
    
                comps_copy[i] = comps[i];
            }
            comps_copy[comps_copy.length-1] = comp;
            comps = comps_copy;
            System.out.println("Added " + comp.getType() + ".");
        } catch (NoStockException e) {System.out.println("No parts available.");
        } catch (InvalidPartException e) {System.out.print("Part not available.");
        } catch (NullPointerException e) {
            comps = new Composante[1]; comps[0] = comp;
            System.out.println("Added " + comp.getType() + ".");
        }
    }
    public void programCPU(Mouvement[] mouv, Operation[] ops) {
        cpu.loadMouv(mouv); cpu.loadOps(ops);
    }
    public void programCPU(Mouvement[] mouv) {cpu.loadMouv(mouv);}
    public void programCPU(Operation[] ops) {cpu.loadOps(ops);}
    public String getName() {return name;}
}