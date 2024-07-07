class Notification {

    private String message;
    
    public Notification(String s) {

        message = s;
        announce();
    }
    public void announce() {System.out.println(message);}
}