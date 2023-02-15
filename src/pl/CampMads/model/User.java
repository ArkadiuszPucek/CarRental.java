package pl.CampMads.model;

import java.io.Serializable;
import java.util.Objects;

abstract class User implements Serializable, CsvConvertible {
     private String firstName;
     private String lastName;
     private String pesel;
     private String drivingLicense;

    public User(String firstName, String lastName, String pesel, String drivingLicense) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.drivingLicense = drivingLicense;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

//    public abstract String toCsv();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) &&
                Objects.equals(pesel, user.pesel) && Objects.equals(drivingLicense, user.drivingLicense);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, pesel, drivingLicense);
    }

    @Override
    public String toString() {
        return firstName + " " +
                lastName + " " +
                pesel + " " +
                drivingLicense;
    }


}
