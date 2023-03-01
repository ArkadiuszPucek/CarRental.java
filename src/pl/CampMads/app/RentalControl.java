package pl.CampMads.app;

import pl.CampMads.exeption.*;
import pl.CampMads.io.ConsolePrinter;
import pl.CampMads.io.DataReader;
import pl.CampMads.io.file.FileManager;
import pl.CampMads.io.file.FileManagerBuilder;
import pl.CampMads.model.Car;
import pl.CampMads.model.CarRentalClients;
import pl.CampMads.model.CarsStorage;
import pl.CampMads.model.comparator.ProductionDateComparator;
import java.util.InputMismatchException;


class RentalControl {
    private final ConsolePrinter printer;
    private final DataReader dataReader;
    private final FileManager fileManager;
    private final CarsStorage carStorage;

     RentalControl() {
        this.printer = new ConsolePrinter();
        this.dataReader = new DataReader(printer);
        this.fileManager = new FileManagerBuilder(printer, dataReader).build();
        try {
            carStorage = fileManager.importData();
            printer.printLine("Zaimportowane dane z pliku");
        } catch (DataImportException | InvalidDataException e){
            printer.printLine(e.getMessage());
            printer.printLine("Zainicjowano nową bazę.");
            carStorage = new CarsStorage();
        }
    }
// Print clients sorted by last name
    private static int printClients(CarRentalClients c1, CarRentalClients c2) {
        return c1.getLastName().compareToIgnoreCase(c2.getLastName());
    }

 // Control loop to handle user input
    public void controlLoop(){
    Option option;
    do {
        printOptions();
        option = getOption();
        switch (option) {
            case ADD_CAR -> addCar();
            case PRINT_CARS -> printCars();
            case DELETE_CAR -> deleteCar();
            case ADD_CLIENT -> addClient();
            case PRINT_CLIENTS -> printClients();
            case EXIT -> exit();
            default -> printer.printLine("Nieznana opcja! Wprowadź ponownie. ");
         }
        }while (option != Option.EXIT);
    }

//    private void searchCar() {
//        printer.printLine("Podaj markę samochodów którą chcesz wyszukać:");
//        String carBrand = dataReader.getString();
//        String notFoundMessage = "Nie udało się znaleźć samochodu marki";
//        carStorage.findCarByBrand(carBrand)
//                .map(Car::toString)
//                .ifPresentOrElse(System.out::println,() -> System.out.println(notFoundMessage));
//    }

    private void printClients() {
        printer.printClients(carStorage.getSortedClients((c1, c2) -> c1.getLastName().compareToIgnoreCase(c2.getLastName())));
    }

    private void addClient() {
        CarRentalClients carRentalClient = dataReader.createCarRentalClient();
        try {
            carStorage.addClient(carRentalClient);
        }catch (ClientAlreadyExistsExeption e){
            printer.printLine(e.getMessage());
        }
    }

    private void printOptions() {
        System.out.println();
        printer.printLine("Wybierz opcję:");
        for (Option option : Option.values()) {
            printer.printLine(option.toString());
        }
    }

    private Option getOption() {
        boolean optionOk = false;
        Option option = null;
        while (!optionOk) {
            try {
                option = Option.createFromInt(dataReader.getInt());
                optionOk = true;
            }catch (NoSuchOptionException e){
                printer.printLine(e.getMessage());
            }catch (InputMismatchException e){
                printer.printLine("Wprowadzono wartość, która nie jest liczbą, podaj ponownie:");
            }
        }
        return option;
    }
    
    
    private void addCar() {
        try {
            Car car = dataReader.CarCreateInPut();
            carStorage.addCar(car);
        } catch (InputMismatchException e){
            printer.printLine("Nie udało się utowrzyć samochodu, niepoprawne dane");
        } catch (ArrayIndexOutOfBoundsException e){
            printer.printLine("Osiągnięto limit pojemności i nie można dodać kolejnego samochodu");
        }
    }
    
    private void deleteCar(){
        try {
            Car car = dataReader.CarCreateInPut();
            if(carStorage.removeCar(car))
                printer.printLine("Usunięto  samochód");
            else
                printer.printLine("Brak wskazanego samochodu");
        }catch (InputMismatchException e){
            printer.printLine("Niepoprawne dane");
        }
    }
    
    private void printCars() {
        printer.printCars(carStorage.getSortedCars(new ProductionDateComparator()));

    }
    
    private void exit() {
        try {
            fileManager.exportData(carStorage);
            printer.printLine("Export danych do pliku zakończony powodzeniem");
        }catch (DataExportException e){
            printer.printLine(e.getMessage());
        }
        dataReader.close();
        printer.printLine("Do zobaczenia!");
    }

    

    private enum Option {
        EXIT(0, "Wyjście z programu"),
        ADD_CAR(1, "Dodanie samochodu"),
        PRINT_CARS(2, "Wyświetlenie dostępnych samochodów"),
        DELETE_CAR(3,"Usuń samochód z bazy danych"),
        ADD_CLIENT(4, "Dodaj klienta"),
        PRINT_CLIENTS(5, "Wyświetl klientów");
//        SEARCH_CAR(6, "Wyszukiwarka samochodów");

        private int value;
        private String description;

        Option(int value, String desc) {
            this.value = value;
            this.description = desc;
        }

        @Override
        public String toString() {
            return value + " - " + description;
        }

        static Option createFromInt(int option) throws NoSuchOptionException {
            try {
                return Option.values()[option];
            } catch(ArrayIndexOutOfBoundsException e) {
                throw new NoSuchOptionException("Brak opcji o id " + option);
            }
        }
    }

}
