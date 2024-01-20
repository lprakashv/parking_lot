package io.github.lprakashv.parkinglot.processor.result;

import static java.lang.System.out;

public class AllocationSuccessResult implements Result {

    private final long allocatedSlot;

    public AllocationSuccessResult(long allocatedSlot) {
        this.allocatedSlot = allocatedSlot;
    }

    @Override
    public void print() {
        out.println("Allocated slot number: " + allocatedSlot);
    }
}
