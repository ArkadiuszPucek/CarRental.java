package pl.CampMads.io.file;

import pl.CampMads.exeption.DataExportException;
import pl.CampMads.exeption.DataImportException;
import pl.CampMads.model.CarsStorage;

import java.io.*;

public class SerializableFileManager implements FileManager{
    private static final String FILE_NAME = "Garage.o";

    @Override
    public CarsStorage importData() {
        try (
                FileInputStream fis = new FileInputStream(FILE_NAME);
                ObjectInputStream ois = new ObjectInputStream(fis);
                ) {
            return (CarsStorage)ois.readObject();
        } catch (FileNotFoundException e) {
            throw new DataImportException("Brak pliku " + FILE_NAME);
        } catch (IOException e) {
            throw new DataImportException("Błąd odczytu pliku " + FILE_NAME);
        } catch (ClassNotFoundException e) {
            throw new DataImportException("Niezgodny typ danych w pliku " + FILE_NAME);
        }
    }

    @Override
    public void exportData(CarsStorage carStorage) {
        try (
                FileOutputStream fos = new FileOutputStream(FILE_NAME);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                ){
            oos.writeObject(carStorage);
        } catch (FileNotFoundException e) {
            throw new DataExportException("Brak pliku" + FILE_NAME);
        } catch (IOException e) {
            throw new DataExportException("Błąd zpisu danych do pliku " + FILE_NAME);
        }
    }
}
