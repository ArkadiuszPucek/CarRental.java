package pl.CampMads.exeption;

public class ClientAlreadyExistsExeption extends RuntimeException{
    public ClientAlreadyExistsExeption(String message) {
        super(message);
    }
}
