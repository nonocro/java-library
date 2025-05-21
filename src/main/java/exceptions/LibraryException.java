package exceptions;

/**
 * Custom exception for library-related errors.
 */
public class LibraryException extends Exception {
    // Constructor with a default message
    public LibraryException() {
        super("Calculator error occurred");
    }

    // Constructor with a custom message
    public LibraryException(String message) {
        super(message);
    }

    // Constructor with a custom message and cause
    public LibraryException(String message, Throwable cause) {
        super(message, cause);
    }
}