package io.github.lprakashv.parkinglot.dao;

import java.util.List;
import java.util.stream.Collectors;

import io.github.lprakashv.parkinglot.models.Slot;
import io.github.lprakashv.parkinglot.models.Vehicle;

public interface ParkingLotDao {

    long getCapacity();

    void createNewParkingLot(long lotCapacity);

    Slot getSlotById(long slotId);

    List<Slot> getAllSlots();

    void parkVehicleToSlotId(Vehicle vehicle, long slotId);

    void leaveSlot(Long slotId);

    default List<Slot> getAllFreeSlots() {
        return this.getAllSlots().stream()
                .filter(s -> !s.getParkedVehicle().isPresent())
                .collect(Collectors.toList());
    }

    default List<Long> getAllFreeSlotIds() {
        return this.getAllFreeSlots().stream()
                .map(Slot::getSlotId)
                .collect(Collectors.toList());
    }

    default List<Slot> getAllParkedSlots() {
        return this.getAllSlots().stream()
                .filter(s -> s.getParkedVehicle().isPresent())
                .collect(Collectors.toList());
    }

    default List<Vehicle> getAllParkedVehicles() {
        return this.getAllParkedSlots().stream()
                .map(s -> s.getParkedVehicle().get())
                .collect(Collectors.toList());
    }

}
