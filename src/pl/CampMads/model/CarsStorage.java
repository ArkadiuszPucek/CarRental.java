package pl.CampMads.model;

import pl.CampMads.exeption.CarAlreadyExistsException;
import pl.CampMads.exeption.ClientAlreadyExistsExeption;

import java.io.Serializable;
import java.util.*;

public class CarsStorage implements Serializable {
    private final Map<String, Car> carArray = new HashMap<>();
    private final Map<String, CarRentalClients> clients = new HashMap<>();

    public Map<String, Car> getCarArray() {
        return carArray;
    }


    public Collection<Car> getSortedCars(Comparator<Car> comparator){
        List<Car> list = new ArrayList<>(carArray.values());
        list.sort(comparator);
        return list;
    }

    public Map<String, CarRentalClients> getClients() {
        return clients;
    }

    public Collection<CarRentalClients> getSortedClients(Comparator<CarRentalClients> comparator){
        ArrayList<CarRentalClients> list = new ArrayList<>(clients.values());
        list.sort(comparator);
        return list;
    }


    public void addCar(Car car){
        if (carArray.containsKey(car.getNrWin())){
            throw new CarAlreadyExistsException(
                    "Samochód o takim nr. WIN już istnieje" + car.getNrWin()
            );

        }
        carArray.put(car.getNrWin(), car);
    }

    public void addClient(CarRentalClients client){
        if(clients.containsKey(client.getPesel())){
            throw new ClientAlreadyExistsExeption(
                    "Klient o takim nr. PESEL już istnieje" + client.getPesel()
            );
        }
        clients.put(client.getPesel(), client);

    }

    public boolean removeCar(Car car){
        if(carArray.containsValue(car)){
            carArray.remove(car.getNrWin());
            return true;
        }else {
            return false;
        }
    }
}
