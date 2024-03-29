package fr.canal.canal.app.error;

import fr.canal.canal.domain.error.InvalidOperandException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(InvalidOperandException.class)
    public ResponseEntity<String> badRequest(final InvalidOperandException exception) {
        return ResponseEntity
                .badRequest()
                .body(exception.getMessage());
    }

}
