package io.github.lprakashv.parkinglot.services;

import io.github.lprakashv.parkinglot.models.Slot;
import java.util.Collection;
import java.util.Optional;

public interface ParkingStrategy<T extends Slot> {

  Optional<T> pick(Collection<T> slots);
}
