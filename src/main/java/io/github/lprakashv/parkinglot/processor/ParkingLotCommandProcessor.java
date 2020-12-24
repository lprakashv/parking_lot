package io.github.lprakashv.parkinglot.processor;

import io.github.lprakashv.parkinglot.exceptions.ParkingLotFullException;
import io.github.lprakashv.parkinglot.processor.result.AllocationSuccessResult;
import io.github.lprakashv.parkinglot.processor.result.ParkingLotCreatedResult;
import io.github.lprakashv.parkinglot.processor.result.ParkingLotFullResult;
import io.github.lprakashv.parkinglot.processor.result.Result;
import io.github.lprakashv.parkinglot.processor.result.SimpleStatementResult;
import io.github.lprakashv.parkinglot.processor.result.SlotFreeResult;
import io.github.lprakashv.parkinglot.processor.result.StatusResult;
import io.github.lprakashv.parkinglot.services.ParkingLotService;
import java.util.stream.Collectors;

public class ParkingLotCommandProcessor {

  private final ParkingLotService parkingLotService;

  public ParkingLotCommandProcessor(ParkingLotService parkingLotService) {
    this.parkingLotService = parkingLotService;
  }

  public Result processCommandLine(String line) {
    String[] fields = line.split("\\s+");
    switch (fields[0]) {
      case "create_parking_lot":
        long capacity = Long.parseLong(fields[1]);
        parkingLotService.createNewParkingLot(capacity);
        return new ParkingLotCreatedResult(capacity);
      case "park":
        try {
          long slotId = parkingLotService.parkVehicle(fields[1], fields[2]);
          return new AllocationSuccessResult(slotId);
        } catch (ParkingLotFullException pfe) {
          return new ParkingLotFullResult();
        }
      case "leave":
        long slotId = Long.parseLong(fields[1]);
        parkingLotService.leaveSlot(slotId);
        return new SlotFreeResult(slotId);
      case "status":
        return new StatusResult(parkingLotService.getStatus());
      case "registration_numbers_for_cars_with_colour":
        return new SimpleStatementResult(
            parkingLotService.getRegistrationsForParkedCarsByColor(fields[1])
                .stream()
                .collect(Collectors.joining(", "))
        );
      default:
        return new SimpleStatementResult("Failed to execute command \"" + line + "\"");
    }
  }
}
