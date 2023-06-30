package tr.com.obss.jip2022.jip_backend.exceptions.notFoundExceptions;


import tr.com.obss.jip2022.jip_backend.exceptions.BaseException;

public class NotFoundException extends BaseException {

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
