package io.github.lprakashv.parkinglot.services.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import io.github.lprakashv.parkinglot.dao.ParkingLotDao;
import io.github.lprakashv.parkinglot.exceptions.ParkingLotFullException;
import io.github.lprakashv.parkinglot.models.Slot;
import io.github.lprakashv.parkinglot.models.Vehicle;
import io.github.lprakashv.parkinglot.services.ParkingLotService;
import io.github.lprakashv.parkinglot.services.ParkingStrategy;

public class ParkingSlotServiceImpl implements ParkingLotService {

    private final ParkingStrategy<Slot> parkingStrategy;
    private final ParkingLotDao parkingLotDao;

    public ParkingSlotServiceImpl(
            ParkingStrategy<Slot> parkingStrategy,
            ParkingLotDao parkingLotDao) {
        this.parkingStrategy = parkingStrategy;
        this.parkingLotDao = parkingLotDao;
    }

    @Override
    public void createNewParkingLot(long capacity) {
        parkingLotDao.createNewParkingLot(capacity);
    }

    @Override
    public synchronized long parkVehicle(String registrationNumber, String color)
            throws ParkingLotFullException {
        long pickedSlotId = parkingStrategy
                .pick(parkingLotDao.getAllFreeSlots())
                .orElseThrow(ParkingLotFullException::new)
                .getSlotId();

        parkingLotDao.parkVehicleToSlotId(new Vehicle(registrationNumber, color), pickedSlotId);

        return pickedSlotId;
    }

    @Override
    public void leaveSlot(long slotId) {
        parkingLotDao.leaveSlot(slotId);
    }

    @Override
    public List<String[]> getStatus() {
        return parkingLotDao.getAllParkedSlots().stream()
                .sorted(Comparator.comparingLong(Slot::getSlotId))
                .map(s -> new String[] {
                        String.valueOf(s.getSlotId()),
                        s.getParkedVehicle().get().getRegistrationNumber(),
                        s.getParkedVehicle().get().getColor()
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getRegistrationsForParkedCarsByColor(String color) {
        return parkingLotDao.getAllParkedVehicles().stream()
                .filter(v -> v.getColor().trim().equalsIgnoreCase(color.trim()))
                .map(Vehicle::getRegistrationNumber)
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> getSlotIdsForParkedCarsByColor(String color) {
        return parkingLotDao.getAllParkedSlots().stream()
                .filter(s -> s.getParkedVehicle().get().getColor().trim().equalsIgnoreCase(color.trim()))
                .map(Slot::getSlotId)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Long> getSlotIdForVehicleNumber(String registrationNumber) {
        return parkingLotDao.getAllParkedSlots().stream()
                .filter(s -> s.getParkedVehicle()
                        .filter(v -> v.getRegistrationNumber().equals(registrationNumber)).isPresent())
                .map(Slot::getSlotId)
                .findFirst();
    }

}
