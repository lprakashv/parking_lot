package io.github.lprakashv.parkinglot.processor.result;

import java.util.List;
import java.util.stream.Collectors;

public class StatusResult implements Result {

  private final List<String[]> status;

  public StatusResult(List<String[]> status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "Slot No.\tRegistration No\tColor\n" +
        status.stream()
            .map(s -> String.join("\t\t", s))
            .collect(Collectors.joining("\n"));
  }
}
