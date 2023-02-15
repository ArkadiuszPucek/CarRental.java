package pl.CampMads.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CarRentalClients extends User{
    private final List<Car> userRentHistory = new ArrayList<>();
    private final List<Car> borrowedCars = new ArrayList<>();

    public CarRentalClients(String firstName, String lastName, String pesel, String drivingLicense) {
        super(firstName, lastName, pesel, drivingLicense);
    }

    @Override
    public String toCsv() {
        return getFirstName() + ";" + getLastName() + ";" + getPesel() + ";" + getDrivingLicense();
    }

    public List<Car> getUserRentHistory() {
        return userRentHistory;
    }

    public List<Car> getBorrowedCars() {
        return borrowedCars;
    }

    public void addUserRentToHistory(Car car){
        userRentHistory.add(car);
    }

    public void borrowCars(Car car){
        borrowedCars.add(car);
    }

    public boolean returnCar(Car car){
        boolean result = false;
        if (borrowedCars.contains(car)){
            borrowedCars.remove(car);
            addUserRentToHistory(car);
            result = true;
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CarRentalClients that = (CarRentalClients) o;
        return Objects.equals(userRentHistory, that.userRentHistory) && Objects.equals(borrowedCars, that.borrowedCars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userRentHistory, borrowedCars);
    }
}
