package tr.com.obss.jip2022.jip_backend.exceptions.notFoundExceptions;

public class RoleNotFoundException extends NotFoundException {

    public RoleNotFoundException() {
    }

    public RoleNotFoundException(String message) {
        super(message);
    }

    public RoleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
