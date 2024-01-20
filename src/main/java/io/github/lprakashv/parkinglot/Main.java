package io.github.lprakashv.parkinglot;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import io.github.lprakashv.parkinglot.dao.ParkingLotDao;
import io.github.lprakashv.parkinglot.dao.impl.InMemParkingLot;
import io.github.lprakashv.parkinglot.models.Slot;
import io.github.lprakashv.parkinglot.processor.ParkingLotCommandProcessor;
import io.github.lprakashv.parkinglot.services.ParkingLotService;
import io.github.lprakashv.parkinglot.services.ParkingStrategy;
import io.github.lprakashv.parkinglot.services.impl.ClosestSlotIdParkingStrategy;
import io.github.lprakashv.parkinglot.services.impl.ParkingSlotServiceImpl;

public class Main {

    public static void main(String... args) {

        final ParkingStrategy<Slot> strategy = new ClosestSlotIdParkingStrategy();

        final ParkingLotDao dao = InMemParkingLot.getInstance();
        final ParkingLotService service = new ParkingSlotServiceImpl(strategy, dao);
        final ParkingLotCommandProcessor processor = new ParkingLotCommandProcessor(service);

        if (args == null || args.length == 0) {
            try (Scanner sc = new Scanner(System.in)) {
                String commandLine = sc.nextLine();

                while (!commandLine.trim().equals("exit")) {
                    processor.processCommandLine(commandLine).print();
                    commandLine = sc.nextLine();
                }
            }
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
                String line = br.readLine();
                while (line != null) {

                    processor.processCommandLine(line).print();

                    line = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
