package io.github.lprakashv.parkinglot.dao.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import io.github.lprakashv.parkinglot.dao.ParkingLotDao;
import io.github.lprakashv.parkinglot.exceptions.ParkingLotRuntimeException;
import io.github.lprakashv.parkinglot.models.Slot;
import io.github.lprakashv.parkinglot.models.Vehicle;

public class InMemParkingLot implements ParkingLotDao {

    private static InMemParkingLot instance = new InMemParkingLot();

    private InMemParkingLot() {

    }

    private Map<Long, Slot> slotsMap = new HashMap<>();
    private Map<Vehicle, Long> slotVehicleReverseMap = new LinkedHashMap<>();
    private long capacity = 0L;

    public static InMemParkingLot getInstance() {
        return instance;
    }

    @Override
    public long getCapacity() {
        return this.capacity;
    }

    @Override
    public void createNewParkingLot(long lotCapacity) {

        slotsMap.putAll(
                LongStream.range(capacity + 1, capacity + lotCapacity + 1)
                        .mapToObj(Slot::new)
                        .collect(Collectors.toMap(
                                Slot::getSlotId,
                                s -> s)));

        this.capacity += lotCapacity;
    }

    @Override
    public Slot getSlotById(long slotId) {
        return slotsMap.get(slotId);
    }

    @Override
    public List<Slot> getAllSlots() {
        return slotsMap.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    @Override
    public synchronized void parkVehicleToSlotId(Vehicle vehicle, long slotId) {
        Slot s = slotsMap.get(slotId);
        if (s.getParkedVehicle().isPresent())
            throw new ParkingLotRuntimeException();

        s.setParkedVehicle(vehicle);
        slotVehicleReverseMap.put(vehicle, slotId);
    }

    @Override
    public synchronized void leaveSlot(Long slotId) {
        slotsMap.get(slotId)
                .getParkedVehicle()
                .ifPresent(pv -> slotVehicleReverseMap.remove(pv));
        slotsMap.get(slotId).freeUpSlot();
    }

    @Override
    public List<Slot> getAllParkedSlots() {
        return slotVehicleReverseMap.entrySet().stream()
                .map(e -> slotsMap.get(e.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> getAllParkedVehicles() {
        return slotVehicleReverseMap.keySet().stream().collect(Collectors.toList());
    }

    public void clear() {
        this.capacity = 0;
        this.slotVehicleReverseMap.clear();
        this.slotsMap.clear();
    }
}
