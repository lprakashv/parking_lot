package io.github.lprakashv.parkinglot.processor.result;

public class SimpleStatementResult implements Result {

  private final String statement;

  public SimpleStatementResult(String statement) {
    this.statement = statement;
  }

  @Override
  public String toString() {
    return statement;
  }
}
