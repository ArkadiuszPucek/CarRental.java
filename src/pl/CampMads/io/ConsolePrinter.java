package pl.CampMads.io;
import pl.CampMads.model.Car;
import pl.CampMads.model.CarRentalClients;
import java.util.Collection;
import java.util.Objects;

public class ConsolePrinter {
    public void printCars(Collection<Car> cars) {
        long count = cars.stream()
                .filter(Objects::nonNull)
                .map(Car::toString)
                .peek(this::printLine)
                .count();
        if (count == 0)
            printLine("Brak dostępnych samochodów");
    }

    public void printClients(Collection<CarRentalClients> clients){

        clients.stream()
                .map(CarRentalClients::toString)
                .forEach(this::printLine);
    }

    public void printLine(String text) {
        System.out.println(text);
    }
}
