package io.github.lprakashv.parkinglot.processor.result;

import static java.lang.System.out;

public class SlotFreeResult implements Result {

    private final long freedUpSlot;

    public SlotFreeResult(long freedUpSlot) {
        this.freedUpSlot = freedUpSlot;
    }

    @Override
    public void print() {
        out.println("Slot number " + freedUpSlot + " is free");
    }
}
