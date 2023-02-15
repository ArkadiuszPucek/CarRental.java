package pl.CampMads.app;

class RentalApp {
    private static final String APP_NAME = "CampMads v1.8.2";

    public static void main(String[] args) {
        System.out.println(APP_NAME);
        RentalControl rentalControl = new RentalControl();
        rentalControl.controlLoop();
    }
}
