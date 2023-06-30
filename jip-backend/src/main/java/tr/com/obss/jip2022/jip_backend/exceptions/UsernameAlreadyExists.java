package tr.com.obss.jip2022.jip_backend.exceptions;

public class UsernameAlreadyExists extends BaseException {

    public UsernameAlreadyExists() {
    }

    public UsernameAlreadyExists(String message) {
        super(message);
    }

    public UsernameAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }
}
