package pl.CampMads.io.file;

import pl.CampMads.exeption.DataExportException;
import pl.CampMads.exeption.DataImportException;
import pl.CampMads.exeption.InvalidDataException;
import pl.CampMads.model.Car;
import pl.CampMads.model.CarRentalClients;
import pl.CampMads.model.CarsStorage;
import pl.CampMads.model.CsvConvertible;

import java.io.*;
import java.util.Collection;
import java.util.Scanner;

public class CsvFileManager implements FileManager{
    private static final String CARS_FILE_NAME = "Garage.csv";
    private static final String CLIENTS_FILE_NAME = "Garage_clients.csv";

    @Override
    public void exportData(CarsStorage carStorage) {
        exportCars(carStorage);
        exportClients(carStorage);
    }
    @Override
    public CarsStorage importData() {
        CarsStorage carsStorage = new CarsStorage();
        importCars(carsStorage);
        importClients(carsStorage);
        return carsStorage;
    }

    private void exportCars(CarsStorage carStorage) {
        Collection<Car> cars = carStorage.getCarArray().values();
        exportToCsv(cars, CARS_FILE_NAME);
    }

    private void exportClients(CarsStorage carStorage) {
        Collection<CarRentalClients> clients = carStorage.getClients().values();
        exportToCsv(clients, CLIENTS_FILE_NAME);
    }

    private<T extends CsvConvertible> void exportToCsv(Collection<T> collection, String fileName) {
        try(FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (T element : collection) {
                bufferedWriter.write(element.toCsv());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new DataExportException("Błąd zapisu danych do pliku" + fileName);
        }
    }

    private Car createObjectFromString(String csvText) {
        String[] split = csvText.split(";");
        String type = split[0];
        if (Car.TYPE.equals(type)){
            return createCar(split);
        }
        throw new InvalidDataException("Nieznany typ samochodu " + type);
    }

    private Car createCar(String[] data) {
        String CarNick = data[1];
        String CarBrand = data[2];
        String CarModel = data[3];
        int DayOfProduction = Integer.valueOf(data[4]);
        int MonthOfProduction = Integer.valueOf(data[5]);
        int YearOfProduction = Integer.valueOf(data[6]);
        String Description = data[7];
        double EngineCapacity = Double.valueOf(data[8]);
        int EnginePower = Integer.valueOf(data[9]);
        String NrWin = data[10];
        String BodyType = data[11];
        return new Car(CarNick, CarBrand, CarModel, DayOfProduction, MonthOfProduction, YearOfProduction, Description,
                EngineCapacity, EnginePower, NrWin, BodyType);
    }

    private void importCars(CarsStorage carsStorage) {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(CARS_FILE_NAME))) {
            bufferedReader.lines()
                    .map(this::createObjectFromString)
                    .forEach(carsStorage::addCar);
        } catch (FileNotFoundException e) {
            throw new DataImportException("Błąd importu pliku" + CARS_FILE_NAME);
        } catch (IOException e) {
            throw new DataImportException("Błąd odczytu pliku" + CARS_FILE_NAME);
        }
    }

    private void importClients(CarsStorage carsStorage) {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(CLIENTS_FILE_NAME))) {
            bufferedReader.lines()
                    .map(this::createUserFromString)
                    .forEach(carsStorage::addClient);
        } catch (FileNotFoundException e) {
            throw new DataImportException("Błąd importu pliku" + CLIENTS_FILE_NAME);
        } catch (IOException e) {
            throw new DataImportException("Błąd odczytu pliku" + CLIENTS_FILE_NAME);
        }
    }

    private CarRentalClients createUserFromString(String csvText) {
        String[] split = csvText.split(";");
        String firstName = split[0];
        String lastName = split[1];
        String pesel = split[2];
        String drivingLicense = split[3];
        return new CarRentalClients(firstName, lastName, pesel, drivingLicense);
    }
}
