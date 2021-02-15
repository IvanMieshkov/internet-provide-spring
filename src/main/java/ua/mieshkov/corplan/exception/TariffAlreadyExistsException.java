package ua.mieshkov.corplan.exception;

/**
 * @author Ivan Mieshkov
 */
public class TariffAlreadyExistsException extends RuntimeException{

    public TariffAlreadyExistsException(String message) {
        super(message);
    }
}
