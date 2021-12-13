package vn.na.ho.coffee.exception;

import java.sql.SQLException;

public class AuthorizationException extends RuntimeException {
    public AuthorizationException(String e) {
        super(e);
    }
}
