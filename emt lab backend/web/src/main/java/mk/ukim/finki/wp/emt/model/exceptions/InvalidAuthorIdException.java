package mk.ukim.finki.wp.emt.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class InvalidAuthorIdException extends RuntimeException{
    public InvalidAuthorIdException(Long id){
        super(String.format("Author with id %d is not found",id));
    }
}
