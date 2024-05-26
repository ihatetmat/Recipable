package capstone.recipable.global.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    protected ResponseEntity<ErrorResponse> handleApplicationException(ApplicationException exception) {
        log.info("{}: {}", exception.getClass().getSimpleName(), exception.getMessage(), exception);
        return ResponseEntity.status(exception.getError().getStatus()).body(ErrorResponse.of(exception.getError()));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception exception) {
        log.info("{}: {}", exception.getClass().getSimpleName(), exception.getMessage(), exception);
        return ResponseEntity.internalServerError().body(ErrorResponse.create());
    }

}