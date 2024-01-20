package io.github.lprakashv.parkinglot.processor.result;

import static java.lang.System.out;

public class SimpleStatementResult implements Result {

    private final String statement;

    public SimpleStatementResult(String statement) {
        this.statement = statement;
    }

    @Override
    public void print() {
        out.println(statement);
    }
}
