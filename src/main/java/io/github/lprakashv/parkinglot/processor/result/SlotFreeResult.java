package io.github.lprakashv.parkinglot.processor.result;

public class SlotFreeResult implements Result {

  private final long freedUpSlot;

  public SlotFreeResult(long freedUpSlot) {
    this.freedUpSlot = freedUpSlot;
  }

  @Override
  public String toString() {
    return "Slot number " + freedUpSlot + " is free";
  }
}
