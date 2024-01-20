package io.github.lprakashv.parkinglot.services.impl;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;

import io.github.lprakashv.parkinglot.models.Slot;
import io.github.lprakashv.parkinglot.services.ParkingStrategy;

public class ClosestSlotIdParkingStrategy implements ParkingStrategy<Slot> {

    @Override
    public Optional<Slot> pick(Collection<Slot> slots) {
        return slots.stream().min(Comparator.comparingLong(Slot::getSlotId));
    }
}
