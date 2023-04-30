package mk.ukim.finki.wp.emt.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class InvalidCountryIdException extends RuntimeException{
    public InvalidCountryIdException(Long id){
        super(String.format("Country with id %d was not found", id));
    }
}
