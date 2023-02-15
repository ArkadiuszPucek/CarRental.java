package pl.CampMads.exeption;

public class SoSuchFileTypeException extends RuntimeException {
    public SoSuchFileTypeException(String msg) {
        super(msg);
    }
}
