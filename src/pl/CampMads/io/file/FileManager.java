package pl.CampMads.io.file;

import pl.CampMads.model.CarsStorage;

public interface FileManager {
    CarsStorage importData();
    void exportData(CarsStorage carStorage);
}
 