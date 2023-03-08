package tn.esprit.spring.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class NotAllowedOperationException extends  RuntimeException {
    public NotAllowedOperationException(final String message) {
        super(message);
    }
}