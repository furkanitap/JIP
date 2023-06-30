package tr.com.obss.jip2022.jip_backend.exceptions.notFoundExceptions;

public class BookNotFoundException extends NotFoundException {

    public BookNotFoundException() {
    }

    public BookNotFoundException(String message) {
        super(message);
    }

    public BookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
