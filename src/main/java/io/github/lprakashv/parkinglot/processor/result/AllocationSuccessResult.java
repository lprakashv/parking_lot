package io.github.lprakashv.parkinglot.processor.result;

public class AllocationSuccessResult implements Result {

  private final long allocatedSlot;

  public AllocationSuccessResult(long allocatedSlot) {
    this.allocatedSlot = allocatedSlot;
  }

  @Override
  public String toString() {
    return "Allocated slot number: " + allocatedSlot;
  }
}
