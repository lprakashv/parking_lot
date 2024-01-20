package io.github.lprakashv.parkinglot.processor.result;

import static java.lang.System.out;

import java.util.List;

public class StatusResult implements Result {

    private final List<String[]> status;

    public StatusResult(List<String[]> status) {
        this.status = status;
    }

    @Override
    public void print() {
        out.println("Slot No.    Registration No    Colour");

        status.stream()
                // .map(s -> String.join(" ", s))
                .forEach(s -> out.println(s[0] + "           " + s[1] + "      " + s[2]));
    }
}
