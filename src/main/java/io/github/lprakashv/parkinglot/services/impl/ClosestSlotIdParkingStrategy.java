package io.github.lprakashv.parkinglot.services.impl;

import io.github.lprakashv.parkinglot.models.Slot;
import io.github.lprakashv.parkinglot.services.ParkingStrategy;
import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;

public class ClosestSlotIdParkingStrategy implements ParkingStrategy<Slot> {

  @Override
  public Optional<Slot> pick(Collection<Slot> slots) {
    return slots.stream().min(Comparator.comparingLong(i -> i.getSlotId()));
  }
}
