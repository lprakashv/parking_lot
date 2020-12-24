package io.github.lprakashv.parkinglot.services;

import io.github.lprakashv.parkinglot.exceptions.ParkingLotFullException;
import java.util.List;

public interface ParkingLotService {

  void createNewParkingLot(long capacity);

  long parkVehicle(String registrationNumber, String color) throws ParkingLotFullException;

  void leaveSlot(long slotId);

  List<String[]> getStatus();

  List<String> getRegistrationsForParkedCarsByColor(String color);

  List<Long> getSlotIdsForParkedCarsByColor(String color);

}
