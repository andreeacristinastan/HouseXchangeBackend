package webapp.housexchange.exceptions;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class DatabaseException extends RuntimeException{
    public DatabaseException(String message, DataAccessException exception) {
        super(message, exception);
    }
}
