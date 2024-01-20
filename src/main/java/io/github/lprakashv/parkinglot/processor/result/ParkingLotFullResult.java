package io.github.lprakashv.parkinglot.processor.result;

import static java.lang.System.out;

public class ParkingLotFullResult implements Result {

    @Override
    public void print() {
        out.println("Sorry, parking lot is full");
    }
}
