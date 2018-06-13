package service.exceptions;

/**
 * DateParadoxException to be thrown when traveller is trying to move back in time
 */
public class DateParadoxException extends RuntimeException {
    public DateParadoxException() {
        super("Cannot travel back in time");
    }
}
