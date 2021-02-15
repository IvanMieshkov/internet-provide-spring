package ua.mieshkov.corplan.exception;
public class TariffNotFoundException extends RuntimeException {

    public TariffNotFoundException(String message) {
        super(message);
    }
}
