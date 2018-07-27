package by.epam.port.entity;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

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
     * Time required to load or unload a single container in milliseconds.
     */
    private static final int WORK_TIME = 5;
    /**
     * The ship's name.
     */

    private final  String SHIP_NAME;
    /**
     * The number of containers that a ship can accommodate.
     */
    private final int NOMINAL_VOLUME;
    /**
     * {@link Semaphore} instance for gaining access by the ship to one of
     * the limited number of docks is the port.
     */
    private final Semaphore SEMAPHORE;

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

        SEMAPHORE = sem;
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

    @Override
    public final String toString() {
        return SHIP_NAME + ":\n" + "Nominal volume = " + NOMINAL_VOLUME + ";\n"
                + "Occupied volume = " + occupiedVolume + ";\n"
                + "Unload volume = " + unloadVolume + ";\n"
                + "Load volume = " + loadVolume + ".";
    }

    /**
     * Method loads the volume of containers that should be loaded
     * and unload the volume that should be unloaded while there are
     * any containers that should be loaded or unloaded.
     * @return the name of this class instance thread.
     */
    @Override
    public String call() {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(this.getName() + " arrived at the port"
                    + " and awaits access to dock.");
        }

        try {
            SEMAPHORE.acquire();
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(this.getName() + " moored to dock");
            }
            unload();
            load();

        } catch (InterruptedException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error(e.getMessage());
            }

        } finally {

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(this.getName()
                        + " completed the loading / unloading operations"
                        + " and left the dock");
            }
            SEMAPHORE.release();
        }

        return "\n" + this.toString();
    }

    /**
     * Method unloads one container from ship to the store.
     */
    private void unload() {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(this + "\nUNLOADING CONTAINERS\n");
        }

        while (unloadVolume > 0) {

            if (store.getNominalVolume()
                    == store.getOccupiedVolume()) {

                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(this.getName() + ":\n"
                            + "The store is full!\nUNLOADING INTERRUPTED\n");
                }

                return;
            }

            try {
                TimeUnit.MILLISECONDS.sleep(WORK_TIME);

            } catch (InterruptedException e) {
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error(e.getMessage());
                }
            }
            occupiedVolume--;
            unloadVolume--;
            store.load();
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(this.getName() + ":\nUNLOADING COMPLETED\n");
        }
    }

    /**
     * Method loads one container from warehouse to the ship.
     */
    private void load() {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.log(Level.DEBUG, this + "\nLOADING CONTAINERS\n");
        }

        while (loadVolume > 0) {

            if (store.getOccupiedVolume() == 0) {

                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(this.getName() + ":\n"
                            + "The store is empty!\nLOADING INTERRUPTED\n");
                }
                return;
            }

            try {
                TimeUnit.MILLISECONDS.sleep(WORK_TIME);

            } catch (InterruptedException e) {
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error(e.getMessage());
                }
            }
            occupiedVolume++;
            loadVolume--;
            store.unload();
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(this.getName() + ":\nLOADING COMPLETED\n");
        }
    }
}
