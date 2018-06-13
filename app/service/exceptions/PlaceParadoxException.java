package service.exceptions;

/**
 * PlaceParadoxException to be thrown when traveller is trying to travel to the same wherever traveller is
 */
public class PlaceParadoxException extends RuntimeException {
    public PlaceParadoxException() {
        super("Cannot travel to same place again");
    }
}
