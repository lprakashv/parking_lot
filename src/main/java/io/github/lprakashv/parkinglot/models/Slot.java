package io.github.lprakashv.parkinglot.models;

import java.util.Optional;

public class Slot {

    private long slotId;
    private Optional<Vehicle> parkedVehicle = Optional.empty();

    public Slot(long slotId) {
        this.slotId = slotId;
    }

    public long getSlotId() {
        return slotId;
    }

    public Optional<Vehicle> getParkedVehicle() {
        return parkedVehicle;
    }

    public void setParkedVehicle(Vehicle parkedVehicle) {
        this.parkedVehicle = Optional.of(parkedVehicle);
    }

    public void freeUpSlot() {
        this.parkedVehicle = Optional.empty();
    }
}
