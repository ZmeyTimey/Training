package by.epam.port.validator;

/**
 * This class is for checking validness of the input data for creating
 * the Store class instance.
 */
public final class StoreDataValidator {

    /**
     * Private constructor for this class.
     */
    private StoreDataValidator() { }

    /**
     * The method for the safety of the application checks isn't
     * the volume of containers in the store exceeds half
     * of the total volume of the store.
     * @param nominalVolume is a number of containers that the store
     *                      can accommodate.
     * @param occupiedVolume number of containers that are in the store.
     * @return boolean value is occupied volume is equal or less than half
     * of the nominal volume.
     */
    public static boolean isOccupiedVolumeValid(final int nominalVolume,
                                                final int occupiedVolume) {

        return nominalVolume >= 2 * occupiedVolume;
    }

    /**
     * Method checks is a number of docks is more than 0.
     * @param docks is a number of docks in the port.
     * @return is a number of docks is more than 0.
     */
    public static boolean isDocksValueIsValid(final int docks) {
        return docks >= 1;
    }
}
