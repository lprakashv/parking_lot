package io.github.lprakashv.parkinglot.services;

import java.util.Collection;
import java.util.Optional;

import io.github.lprakashv.parkinglot.models.Slot;

public interface ParkingStrategy<T extends Slot> {

    Optional<T> pick(Collection<T> slots);
}
