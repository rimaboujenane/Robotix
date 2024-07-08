class Activite {

    Robot[] robots;
    String name;
    String desc;
    int max_players;
    
    public Activite(String name, String desc, int max) {

        this.name = name;
        this.desc = desc;
        this.robots = new Robot[max];
    }
    public void addBots(Robot... robots) {
        for (int i = 0; i < robots.length; i++) {
            if (i < max_players) {this.robots[i] = robots[i];}
            else {System.out.println("Limite de joueurs. " + robots[i].getName() + " est exclu.");}
        }
    }
    public void addBot(Robot robot) {

        boolean added = false;
        for (Robot r: robots) {if (r == null) {r = robot; added = true;}}
        if (!added) {System.out.println("Limite de jouers. " + robot.getName() + " est exclu.");}
    }
    public void participer() {
        System.out.println("Les robots suivants ont participé à l'activité " + name + ":");
        for (Robot b: robots) {System.out.println("\t" + b.getName());}
    }
    public String getName() {return name;}
}