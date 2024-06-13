class NoStockException extends Exception {

    public NoStockException() {

        System.out.println("No parts in inventory.");
    }
}