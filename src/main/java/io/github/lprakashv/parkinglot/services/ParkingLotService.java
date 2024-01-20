package io.github.lprakashv.parkinglot.services;

import java.util.List;
import java.util.Optional;

import io.github.lprakashv.parkinglot.exceptions.ParkingLotFullException;

public interface ParkingLotService {

    void createNewParkingLot(long capacity);

    long parkVehicle(String registrationNumber, String color) throws ParkingLotFullException;

    void leaveSlot(long slotId);

    List<String[]> getStatus();

    List<String> getRegistrationsForParkedCarsByColor(String color);

    List<Long> getSlotIdsForParkedCarsByColor(String color);

    Optional<Long> getSlotIdForVehicleNumber(String registrationNumber);

}
