package by.epam.port.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;

/**
 * {@link Ship} class instances describes the functionality of the ships
 * that load containers into the store and unload them from the store.
 */
public class Ship implements Callable<String> {

    /**
     * {@link Logger} class object for making logs.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(Ship.class);
    /**
     * The ship's name.
     */
    private final String SHIP_NAME;
    /**
     * The number of containers that a ship can accommodate.
     */
    private final int NOMINAL_VOLUME;
    /**
     * {@link Semaphore} instance for gaining access by the ship to one of
     * the limited number of docks is the port.
     */
    private Semaphore semaphore;
    /**
     * Number of containers on board.
     */
    private int occupiedVolume;
    /**
     * Number of containers to be unloaded to the store.
     */
    private int unloadVolume;
    /**
     * Number of containers to be loaded from the store to the ship.
     */
    private int loadVolume;
    /**
     * The store in which a ship unloads containers and from which loads them.
     */
    private Store store = Store.getInstance();
    /**
     * The variable indicates whether the ship is
     * in the loading or unloading state.
     */
    private boolean isInUnloadingState;
    /**
     * Constructor for this class.
     * @param sem is {@link Semaphore} class instance.
     * @param name is an input value of the ship's name.
     * @param nomVol is an input value of the ship's nominal volume.
     * @param occVol is an input value of the ship's occupied volume.
     * @param unloadVol is an input value of the ship's unload volume.
     * @param loadVol is an input value of the ship's load volume.
     */
    public Ship(final Semaphore sem,
                final String name,
                final int nomVol,
                final int occVol,
                final int unloadVol,
                final int loadVol) {

        semaphore = sem;
        SHIP_NAME = name;
        NOMINAL_VOLUME = nomVol;
        occupiedVolume = occVol;
        unloadVolume = unloadVol;
        loadVolume = loadVol;
    }

    /**
     * Getter for the ship's name.
     * @return ship's name.
     */
     public String getName() {
        return SHIP_NAME;
    }

    /**
     * Method loads the volume of containers that should be loaded
     * and unload the volume that should be unloaded while there are
     * any containers that should be loaded or unloaded.
     * @return the name of this class instance thread.
     */
    @Override
    public String call() {

        try {
            LOGGER.log(Level.DEBUG, "The ship " + this.getName()
                    + " approached the port.");

            semaphore.acquire();

            LOGGER.log(Level.DEBUG, "The ship " + this.getName()
                    + " moored to dock.");

            isInUnloadingState = true;
            LOGGER.log(Level.DEBUG, "The ship " + this.getName()
                    + " proceeded to unload.");

            while (unloadVolume > 0 && loadVolume > 0) {
                if (isInUnloadingState) {
                    unload();
                } else {
                    load();
                }

                if ((store.getNominalVolume() == store.getOccupiedVolume())
                        || (unloadVolume == 0)) {
                    isInUnloadingState = false;
                    LOGGER.log(Level.DEBUG, "The ship " + this.getName()
                            + " proceeded to load.");
                }
                if ((store.getOccupiedVolume() == 0) || (loadVolume == 0)) {
                    isInUnloadingState = true;
                    LOGGER.log(Level.DEBUG, "The ship " + this.getName()
                            + " proceeded to unload.");
                }
            }

        } catch (InterruptedException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        }

        semaphore.release();
        LOGGER.log(Level.DEBUG, "The ship " + this.getName() + " completed"
                + " the loading/unloading work and left the dock.");

        return (this.getName() + ":\n"
                + "occupied volume = " + occupiedVolume + " containers;\n"
                + "load volume = " + loadVolume + " containers;\n"
                + "unload volume = " + unloadVolume + " containers.");
    }

    /**
     * Method unloads one container from ship to the store.
     */
    private void unload() {
        occupiedVolume--;
        unloadVolume--;
        store.load(1);
    }

    /**
     * Method loads one container from warehouse to the ship.
     */
    private void load() {
        occupiedVolume++;
        loadVolume--;
        store.unload(1);
    }
}
