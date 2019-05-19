import javafx.beans.property.BooleanProperty;

public class Slot {
    BooleanProperty slot;
    BooleanProperty lock;
    BooleanProperty light;

    public boolean isSlot() {
        return slot.get();
    }

    public void setSlot(boolean slot) {
        this.slot.set(slot);
    }

    public boolean isLock() {
        return lock.get();
    }

    public void setLock(boolean lock) {
        this.lock.set(lock);
    }

    public boolean isLight() {
        return light.get();
    }

    public void setLight(boolean light) {
        this.light.set(light);
    }

    BooleanProperty getValue(int i) {
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