package hxy.sparkjava.demo.util;

public class YourCustomException extends Exception {
    String message;
    Integer code;

    public YourCustomException(String message) {
        super(message);
    }
}
