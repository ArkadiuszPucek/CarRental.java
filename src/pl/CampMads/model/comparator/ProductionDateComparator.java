package pl.CampMads.model.comparator;

import pl.CampMads.model.Car;

import java.time.Year;
import java.util.Comparator;

public class ProductionDateComparator implements Comparator<Car> {
    @Override
    public int compare(Car c1, Car c2) {
        if (c1 == null && c2 == null)
            return 0;
        else if (c1 == null)
            return 1;
        else if (c2 == null)
            return -1;
        Year i1 = c1.getYearOfProduction();
        Year i2 = c2.getYearOfProduction();
        return i1.compareTo(i2);
//        return c1.getDateOfProduction().compareTo(c2.getDateOfProduction());
    }
}
