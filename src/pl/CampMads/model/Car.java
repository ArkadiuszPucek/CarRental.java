package pl.CampMads.model;

import java.io.Serializable;
import java.time.MonthDay;
import java.time.Year;
import java.util.Objects;

public class Car implements Serializable, Comparable<Car>, CsvConvertible {
        private String carNick;
        private String carBrand;
        private String carModel;
        private MonthDay monthDay;
        private Year yearOfProduction;
        private String description;
        private double engineCapacity;
        private int enginePower;
        private String nrWin;
        private String bodyType;

        public Car(String carNick, String carBrand, String carModel, int dayOfProduction, int monthOfProduction,
                   int yearOfProduction, String description, Double engineCapacity, int enginePower, String nrWin, String bodyType) {
            this.carNick = carNick;
            this.carBrand = carBrand;
            this.carModel = carModel;
            this.monthDay = MonthDay.of(monthOfProduction, dayOfProduction);
            this.yearOfProduction = Year.of(yearOfProduction);
            this.description = description;
            this.engineCapacity = engineCapacity;
            this.enginePower = enginePower;
            this.nrWin = nrWin;
            this.bodyType = bodyType;
        }

    public static final String TYPE = "Campervan";

    public String getCarNick() {
        return carNick;
    }

    public void setCarNick(String carNick) {
        this.carNick = carNick;
    }

    public String getCarBrand() {
            return carBrand;
        }

        public void setCarBrand(String carBrand) {
            this.carBrand = carBrand;
        }

        public String getCarModel() {
            return carModel;
        }

        public void setCarModel(String carModel) {
            this.carModel = carModel;
        }

        public MonthDay getMonthDay() {
        return monthDay;
    }

        public void setMonthDay(MonthDay monthDay) {
        this.monthDay = monthDay;
    }

        public Year getYearOfProduction() {
        return yearOfProduction;
    }

        public void setYearOfProduction(Year yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

        public String getDescription() {
                return description;
            }

        public void setDescription(String description) {
            this.description = description;
        }

        public double getEngineCapacity() {
            return engineCapacity;
        }

        public void setEngineCapacity(double engineCapacity) {
            this.engineCapacity = engineCapacity;
        }

        public int getEnginePower() {
            return enginePower;
        }

        public void setEnginePower(int enginePower) {
            this.enginePower = enginePower;
        }

        public String getNrWin() {
            return nrWin;
        }

        public void setNrWin(String nrWin) {
            this.nrWin = nrWin;
        }

        public String getBodyType() {
            return bodyType;
        }

        public void setBodyType(String bodyType) {
            this.bodyType = bodyType;
        }


    public String toCsv() {
        return (TYPE + ";") +
                getCarNick() + ";" +
                getCarBrand() + ";" +
                getCarModel() + ";" +
                monthDay.getDayOfMonth() + ";" +
                monthDay.getMonthValue() + ";" +
                getYearOfProduction() + ";" +
                getDescription() + ";" +
                getEngineCapacity() + ";" +
                getEnginePower() + ";" +
                getNrWin() + ";" +
                getBodyType() + ";";
    }

    @Override
    public String toString() {
        return  "Nazwa campera:" + carNick + ", " +
                "marka: " + carBrand + ", " +
                "model: " + carModel + ", " +
                "data produkcji: " + monthDay.getDayOfMonth() + "." + monthDay.getMonthValue() + "." + yearOfProduction +
                " pojemność silnika: " + engineCapacity + ", " +
                "moc silnika: " + enginePower + "km, " +
                "opis: " + description + ", " +
                "rozmiar busa: " + bodyType + ", " +
                "nr. WIN: " + nrWin;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Double.compare(car.engineCapacity, engineCapacity) == 0 && enginePower == car.enginePower &&
                Objects.equals(carNick, car.carNick) && Objects.equals(carBrand, car.carBrand) &&
                Objects.equals(carModel, car.carModel) && Objects.equals(monthDay, car.monthDay) &&
                Objects.equals(yearOfProduction, car.yearOfProduction) &&
                Objects.equals(description, car.description) && Objects.equals(nrWin, car.nrWin) &&
                Objects.equals(bodyType, car.bodyType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carNick, carBrand, carModel, monthDay, yearOfProduction, description, engineCapacity,
                enginePower, nrWin, bodyType);
    }

    @Override
    public int compareTo(Car o) {
        return carBrand.compareTo(o.carBrand);
    } 
        
}


