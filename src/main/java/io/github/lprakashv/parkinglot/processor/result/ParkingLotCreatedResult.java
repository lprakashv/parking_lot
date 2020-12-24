package io.github.lprakashv.parkinglot.processor.result;

public class ParkingLotCreatedResult implements Result {

  private final long capacity;

  public ParkingLotCreatedResult(long capacity) {
    this.capacity = capacity;
  }

  @Override
  public String toString() {
    return "Created a parking lot with " + capacity + " slots";
  }
}
