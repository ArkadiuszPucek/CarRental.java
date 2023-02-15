package pl.CampMads.exeption;

public class CarAlreadyExistsException extends RuntimeException{
    public CarAlreadyExistsException(String message) {
        super(message);
    }
}
