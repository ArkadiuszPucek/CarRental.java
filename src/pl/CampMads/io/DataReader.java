package pl.CampMads.io;

import pl.CampMads.model.Car;
import pl.CampMads.model.CarRentalClients;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DataReader {
    private static final String CAR_NICK = "Podaj nick samochodu: ";
    private static final String CARS_BRAND = "Podaj markę samochodu: ";
    private static final String CARS_MODEL = "Podaj model samochodu: ";
    private static final String CARS_PRODUCTION_DAY = "Podaj dzień produkcji: ";
    private static final String CARS_PRODUCTION_MONTH = "Podaj miesiąc produkcji: ";
    private static final String CARS_PRODUCTION_YEAR = "Podaj rok produkcji: ";
    private static final String CARS_DESCRIPTION = "Podaj opis samochodu: ";
    private static final String CARS_ENGINE_CAPACITY = "Podaj pojemność silnika : ";
    private static final String CARS_ENGINE_POWER = "Podaj moc silnika: ";
    private static final String CARS_WIN_NUMBER = "Podaj numer WIN samochodu: ";
    private static final String CARS_BODY_TYPE = "Podaj typ nadwozia: ";

    double engineCapacityInPut = 0.0;
    int enginePowerInPut = 0;
    int productionDayInPut = 0;
    int productionMonthInPut = 0;
    int productionYearInPut = 0;


    private Scanner inPut = new Scanner(System.in);
    private ConsolePrinter printer;


    public DataReader(ConsolePrinter printer) {
        this.printer = printer;
    }

    public void close(){
        inPut.close();
    }

    public Car CarCreateInPut(){
        printer.printLine(CAR_NICK);
        String carNickInPut = inPut.nextLine();

        printer.printLine(CARS_BRAND);
        String carBrandInPut = inPut.nextLine();

        printer.printLine(CARS_MODEL);
        String carModelInPut = inPut.nextLine();

        printer.printLine(CARS_PRODUCTION_DAY);
        productionDayException();

        printer.printLine(CARS_PRODUCTION_MONTH);
        productionMonthException();

        printer.printLine(CARS_PRODUCTION_YEAR);
        productionYearException();
        inPut.nextLine();

        printer.printLine(CARS_DESCRIPTION);
        String descriptionInPut =  inPut.nextLine();

        printer.printLine(CARS_ENGINE_CAPACITY);
        engineCapacityException();

        printer.printLine(CARS_ENGINE_POWER);
        enginePowerException();

        printer.printLine(CARS_WIN_NUMBER);
        String nrWinInPut = inPut.nextLine();

        printer.printLine(CARS_BODY_TYPE);
        String bodyTypeInPut = inPut.nextLine();

        return new Car(carNickInPut, carBrandInPut, carModelInPut, productionDayInPut, productionMonthInPut, productionYearInPut, descriptionInPut, engineCapacityInPut,
                enginePowerInPut, nrWinInPut, bodyTypeInPut);
    }

    public CarRentalClients createCarRentalClient(){
        printer.printLine("Imię");
        String firstNameInPut = inPut.nextLine();
        printer.printLine("Nazwisko");
        String lastNameInPut = inPut.nextLine();
        printer.printLine("Pesel");
        String peselInPut = inPut.nextLine();
        printer.printLine("Nr. prawojazdy");
        String drivingLicenseInPut = inPut.nextLine();
        return new CarRentalClients(firstNameInPut, lastNameInPut, peselInPut, drivingLicenseInPut);
    }

    public int getInt(){
        try {
            return inPut.nextInt();
        } finally {
            inPut.nextLine();
        }
    }

    public String getString(){
        return inPut.nextLine();
    }

    void productionYearException() {
        boolean error = true;
        do {
            try {
                productionYearInPut = inPut.nextInt();
                error = false;
            } catch (InputMismatchException exception) {
                System.err.println("Podana wartość jest nieprawidłowa. Wprowadź liczbę całkowitą!");
                inPut.nextLine();
            }
        } while (error);
    }

    void productionMonthException() {
        boolean error = true;
        do {
            try {
                productionMonthInPut = inPut.nextInt();
                if (productionMonthInPut < 0 || productionMonthInPut > 12){
                error = false;
                }
            } catch (InputMismatchException exception) {
                System.err.println("Podana wartość jest nieprawidłowa. Wprowadź liczbę całkowitą!");
                inPut.nextLine();
            }
        } while (error);
    }

    void productionDayException() {
        boolean error = true;
        do {
            try {
                productionDayInPut = inPut.nextInt();
                error = false;
            } catch (InputMismatchException exception) {
                System.err.println("Podana wartość jest nieprawidłowa. Wprowadź liczbę całkowitą!");
                inPut.nextLine();
            }
        } while (error);
    }

    void engineCapacityException() {
        boolean error = true;
        do {
            try {
                engineCapacityInPut = inPut.nextDouble();
                error = false;
            } catch (InputMismatchException exception) {
                System.err.println("Podana wartość jest nieprawidłowa. Wprowadź liczbę zmiennoprzecinkową!");
                inPut.nextLine();
            }
        } while (error);
    }

    private void enginePowerException() {
        boolean error = true;
        do {
            try {
                enginePowerInPut = inPut.nextInt();
                error = false;
            } catch (InputMismatchException exception) {
                System.err.println("Podana wartość jest nieprawidłowa. Wprowadź liczbę całkowitą!");
                inPut.nextLine();
            }
        } while (error);
        inPut.nextLine();
    }

    public Scanner getInPut() {
        return inPut;
    }

}
