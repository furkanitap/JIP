package tr.com.obss.jip2022.jip_backend.exceptions.notFoundExceptions;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
