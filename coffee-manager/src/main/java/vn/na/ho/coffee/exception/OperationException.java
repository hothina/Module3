package vn.na.ho.coffee.exception;

import java.sql.SQLException;

public class OperationException extends RuntimeException {
    public OperationException(String message) {
        super(message);
    }

    public OperationException(SQLException e) {
        super(e);
    }
}
