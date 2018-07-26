package by.epam.port.entity;

import by.epam.port.exception.OutOfStoreVolumeException;
import by.epam.port.exception.StoreIsEmptyException;
import by.epam.port.exception.LoadingException;
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
    private static final int WORK_TIME = 10;
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
     * @throws LoadingException is thrown when during loading
     * the nominal volume of the store is exceeded or the store is empty.
     * @return the name of this class instance thread.
     */
    @Override
    public String call() throws LoadingException {

        try {
            LOGGER.log(Level.DEBUG, this.getName() + " approached the port.");

            semaphore.acquire();

            LOGGER.log(Level.DEBUG, this.getName() + " moored to dock.");

            while (unloadVolume > 0) {
                LOGGER.log(Level.DEBUG, this.toString()
                        + "\nPROCEEDED TO UNLOAD \n");
                System.out.println("Store NV: " + store.getNominalVolume());
                System.out.println("Store OV: " + store.getOccupiedVolume());
                if (!(store.getNominalVolume() < store.getOccupiedVolume())) {
                    unload();
                    TimeUnit.MILLISECONDS.sleep(WORK_TIME);
                } else {
                    throw new OutOfStoreVolumeException(
                            "The nominal volume of the store is exceed!");
                }
            }



            while (loadVolume > 0) {
                LOGGER.log(Level.DEBUG, this.toString()
                        + "\nPROCEEDED TO LOAD \n");
                System.out.println("Store NV: " + store.getNominalVolume());
                System.out.println("Store OV: " + store.getOccupiedVolume());
                if (!(store.getOccupiedVolume() == 0)) {
                    load();
                    TimeUnit.MILLISECONDS.sleep(WORK_TIME);
                } else {
                    throw new StoreIsEmptyException(
                            "Loading from the store is impossible!"
                                    + " The store is empty.");
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
        store.load();
    }

    /**
     * Method loads one container from warehouse to the ship.
     */
    private void load() {
        occupiedVolume++;
        loadVolume--;
        store.unload();
    }
}
