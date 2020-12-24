package io.github.lprakashv.parkinglot;

import io.github.lprakashv.parkinglot.dao.ParkingLotDao;
import io.github.lprakashv.parkinglot.dao.impl.InMemParkingLot;
import io.github.lprakashv.parkinglot.exceptions.ParkingLotFullException;
import io.github.lprakashv.parkinglot.models.Slot;
import io.github.lprakashv.parkinglot.models.Vehicle;
import io.github.lprakashv.parkinglot.services.ParkingLotService;
import io.github.lprakashv.parkinglot.services.ParkingStrategy;
import io.github.lprakashv.parkinglot.services.impl.ClosestSlotIdParkingStrategy;
import io.github.lprakashv.parkinglot.services.impl.ParkingSlotServiceImpl;
import java.util.Arrays;
import java.util.HashSet;
import org.junit.Assert;
import org.junit.Test;

public class MainTest {

  //private ParkingLotDao dao = new InMemParkingLot();//.getINSTANCE();

  @Test
  public void testCreation() {
    ParkingLotDao dao = new InMemParkingLot();
    dao.createNewParkingLot(10);
    Assert.assertEquals(10, dao.getCapacity());
    Assert.assertEquals(10, dao.getAllSlots().size());
    Assert.assertEquals(dao.getAllFreeSlots().size(), dao.getAllSlots().size());
  }

  @Test
  public void testSecondLot() {
    ParkingLotDao dao = new InMemParkingLot();

    dao.createNewParkingLot(10);
    dao.createNewParkingLot(5);

    Assert.assertEquals(15, dao.getCapacity());
    Assert.assertEquals(15, dao.getAllSlots().size());
    Assert.assertEquals(dao.getAllFreeSlots().size(), dao.getAllSlots().size());
  }

  @Test
  public void testParking() throws ParkingLotFullException {
    ParkingLotDao dao = new InMemParkingLot();
    ParkingLotService service = new ParkingSlotServiceImpl(new ClosestSlotIdParkingStrategy(), dao);

    service.createNewParkingLot(1);
    Assert.assertEquals(dao.getAllFreeSlots().size(), dao.getAllSlots().size());
    service.parkVehicle("KA-01-0101", "White");
    Assert.assertNotEquals(dao.getAllFreeSlots().size(), dao.getAllSlots().size());

    service.createNewParkingLot(1);
    service.parkVehicle("KA-01-0102", "Black");
    Assert.assertEquals(2, dao.getCapacity());
    Assert.assertEquals(0, dao.getAllFreeSlots().size());

    Assert.assertEquals(
        new HashSet<>(dao.getAllParkedVehicles()),
        new HashSet<>(
            Arrays.asList(new Vehicle("KA-01-0102", "Black"),
                new Vehicle("KA-01-0101", "White"))
        ));
  }
}
