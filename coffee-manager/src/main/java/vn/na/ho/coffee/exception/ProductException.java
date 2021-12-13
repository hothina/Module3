package vn.na.ho.coffee.exception;

public class ProductException extends RuntimeException{

    public ProductException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Product :" + super.getMessage();
    }
}
