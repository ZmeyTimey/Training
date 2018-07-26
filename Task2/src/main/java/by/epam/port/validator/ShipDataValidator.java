package by.epam.port.validator;

/**
 * This class is for checking validness of the input data for creating
 * Ship class object.
 */
public final class ShipDataValidator {

    /**
     * Private constructor for this class.
     */
    private ShipDataValidator() { }

    /**
     * Method checks whether the correct proportion between nominal volume
     * and other quantities is observed.
     * @param nominalVolume is a number of containers that the ship can
     * accommodate.
     * @param occupiedVolume is a number of containers on the ship's board.
     * @param loadVolume is a number of containers to be loaded
     * from the store to the ship.
     * @return boolean value is the correct proportion is observed.
     */
    public static boolean isNominalVolumeProportionValid(
            final int nominalVolume,
            final int occupiedVolume,
            final int loadVolume) {

        return nominalVolume >= occupiedVolume + loadVolume;
    }

    /**
     * Method checks whether the value of the ship's unload volume
     * doesn't exceed the value of it's occupied volume.
     * @param occupiedVolume is a number of containers on the ship's board.
     * @param unloadVolume is a number of containers to be unloaded
     * from the ship to the store.
     * @return boolean value is the correct proportion is observed.
     */
    public static boolean isOccupiedUnloadVolumesValid(
            final int occupiedVolume,
            final int unloadVolume) {

        return occupiedVolume >= unloadVolume;
    }
}
