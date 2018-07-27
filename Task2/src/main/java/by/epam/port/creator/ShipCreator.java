package by.epam.port.creator;

import by.epam.port.entity.Ship;
import by.epam.port.exception.AppException;
import by.epam.port.validator.ShipDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Semaphore;

/**
 * This class is for creating {@link Ship} class instances.
 */
public final class ShipCreator {

    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(ShipCreator.class);

    /**
     * Private constructor for this class.
     */
    private ShipCreator() { }

    /**
     * Method creates a new {@link Ship} object if the input data is valid.
     * @param semaphore {@link Semaphore} instance for gaining access
     * by the ship to one of the limited number of docks is the port.
     * @param name is the ship's name.
     * @param nominalVolume is a number of containers that
     *                      a ship can accommodate.
     * @param occupiedVolume is a number of containers on the ship's board.
     * @param loadVolume is a number of containers to be loaded from the store
     *                   to the ship.
     * @param unloadVolume is a number of containers to be unloaded
     *                     to the store.
     * @return new {@link Ship} object.
     * @throws AppException is thrown when the intended for
     * the {@link Ship} object creation data is not valid.
     */
    public static Ship createShip(final Semaphore semaphore,
                                  final String name,
                                  final int nominalVolume,
                                  final int occupiedVolume,
                                  final int unloadVolume,
                                  final int loadVolume)
            throws AppException {

        if (ShipDataValidator.isNominalVolumeProportionValid(
                nominalVolume,
                occupiedVolume,
                loadVolume)) {

            if (ShipDataValidator.isOccupiedUnloadVolumesValid(
                    occupiedVolume, unloadVolume)) {

                final Ship SHIP = new Ship(semaphore, name,
                        nominalVolume, occupiedVolume,
                        unloadVolume, loadVolume);

                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("The SHIP " + SHIP.getName()
                            + " created.");
                }
                return SHIP;

            } else {
                throw new AppException(
                        "Invalid occupiedVolume and unloadVolume proportion. "
                + "The ship " + name + " object can't be created.");
            }

        } else {
            throw new AppException(
                    "Invalid proportion between nominal volume and other"
                            + " volume values. The ship " + name
                            + " object can't be created.");
        }
    }
}
