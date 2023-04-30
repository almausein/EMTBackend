package mk.ukim.finki.wp.emt.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)

public class NoMoreCopiesException extends RuntimeException{
    public NoMoreCopiesException(Long id){
        super(String.format("There are no more available copies from the selected book with id %d", id));
    }
}
