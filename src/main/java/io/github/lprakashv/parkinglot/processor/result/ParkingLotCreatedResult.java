package io.github.lprakashv.parkinglot.processor.result;

import static java.lang.System.out;

public class ParkingLotCreatedResult implements Result {

    private final long capacity;

    public ParkingLotCreatedResult(long capacity) {
        this.capacity = capacity;
    }

    @Override
    public void print() {
        out.println("Created a parking lot with " + capacity + " slots");
    }
}
