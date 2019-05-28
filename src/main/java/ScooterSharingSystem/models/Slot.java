package ScooterSharingSystem.models;

import javafx.beans.property.BooleanProperty;

/**
 * This class is about slot control
 * @author Group 87
 */
public class Slot {
    public BooleanProperty slot;
    public BooleanProperty lock;
    public BooleanProperty light;

    /**
     * To check is slot is slot
     * @return slot or not
     */
    public boolean isSlot() {
        return slot.get();
    }

    /**
     * Lock slot or unlock slot
     * @param slot the slot would be operated
     */
    public void setSlot(boolean slot) {
        this.slot.set(slot);
    }

    /**
     * To check is lock is lock or not
     * @return lock or not lock
     */
    public boolean isLock() {
        return lock.get();
    }

    /**
     * To lock or unlock
     * @param lock the lock would be operated
     */
    public void setLock(boolean lock) {
        this.lock.set(lock);
    }

    /**
     * To check is light is light or not
     * @return light or not light
     */
    public boolean isLight() {
        return light.get();
    }

    /**
     * To set light
     * @param light the light would be operated
     */
    public void setLight(boolean light) {
        this.light.set(light);
    }

    /**
     * Get value of slot
     * @param i the ith data
     * @return data we need
     */
    public BooleanProperty getValue(int i) {
        switch (i) {
            case 0:
                return light;
            case 1:
                return lock;
            case 2:
                return slot;
            default:
                return slot;
        }
    }
}