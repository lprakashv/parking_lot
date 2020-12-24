package io.github.lprakashv.parkinglot;

import io.github.lprakashv.parkinglot.dao.ParkingLotDao;
import io.github.lprakashv.parkinglot.dao.impl.InMemParkingLot;
import io.github.lprakashv.parkinglot.models.Slot;
import io.github.lprakashv.parkinglot.processor.ParkingLotCommandProcessor;
import io.github.lprakashv.parkinglot.services.ParkingLotService;
import io.github.lprakashv.parkinglot.services.ParkingStrategy;
import io.github.lprakashv.parkinglot.services.impl.ClosestSlotIdParkingStrategy;
import io.github.lprakashv.parkinglot.services.impl.ParkingSlotServiceImpl;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

  public static void main(String... args) {
    final ParkingStrategy<Slot> strategy = new ClosestSlotIdParkingStrategy();
    final ParkingLotDao dao = InMemParkingLot.getINSTANCE();
    final ParkingLotService service = new ParkingSlotServiceImpl(strategy, dao);
    final ParkingLotCommandProcessor processor = new ParkingLotCommandProcessor(service);

    if (args == null || args.length == 0) {
      final Scanner sc = new Scanner(System.in);
      String commandLine = sc.nextLine();

      while (!commandLine.trim().equals("exit")) {
        System.out.println(
            processor.processCommandLine(commandLine).toString()
        );
        commandLine = sc.nextLine();
      }
    } else {
      BufferedReader br;
      try {
        br = new BufferedReader(new FileReader(args[0]));
        String line = br.readLine();
        while (line != null) {

          System.out.println(
              processor.processCommandLine(line.trim()).toString()
          );

          line = br.readLine();
        }
        br.close();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
