package parking.model;

/**
 * Marker / capability interface for passes that allow free or reduced parking.
 */
public interface Pass {
    boolean isValid();
    String getPassType();
}
