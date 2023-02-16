package adapter.rest.UserManagmentExceptionHandling.Exception;

import com.sun.net.httpserver.Headers;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class UserServiceExceptionsHandler {
    private final UserServiceExceptionsMessages userServiceExceptionsMessages;
    private final ErrorsInfo DEFAULT_ERROR_INFO = new ErrorsInfo(-1,400,new HashMap(){{
        put(Language.EN, "General Error");
    }
    });
    public UserServiceExceptionsHandler(UserServiceExceptionsMessages userServiceExceptionsMessages) {
        this.userServiceExceptionsMessages = userServiceExceptionsMessages;
    }

    @ExceptionHandler(AbstractUserServiceException.class)
    public ResponseEntity<Object> handle(AbstractUserServiceException exc) {
        ErrorsInfo errorsInfo = userServiceExceptionsMessages.getMessages().getOrDefault(exc.getClass().getSimpleName(),DEFAULT_ERROR_INFO);
        return new ResponseEntity<Object>(errorsInfo, HttpStatusCode.valueOf(errorsInfo.getStatsCode()));
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handle(UserNotFoundException exc) {
        ErrorsInfo errorsInfo = userServiceExceptionsMessages.getMessages().getOrDefault(exc.getClass().getSimpleName(),DEFAULT_ERROR_INFO);
        return new ResponseEntity<>(errorsInfo, HttpStatusCode.valueOf(errorsInfo.getStatsCode()));
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handle(RuntimeException exc) {
        ErrorsInfo errorsInfo = userServiceExceptionsMessages.getMessages().getOrDefault(exc.getClass().getSimpleName(),DEFAULT_ERROR_INFO);
        return new ResponseEntity<>(errorsInfo+exc.getMessage(), HttpStatusCode.valueOf(errorsInfo.getStatsCode()));
    }
    @ExceptionHandler(UserUnstableConditionsException.class)
    public ResponseEntity<Object> handle(UserUnstableConditionsException exc) {
        ErrorsInfo errorsInfo = userServiceExceptionsMessages.getMessages().getOrDefault(exc.getClass().getSimpleName(),DEFAULT_ERROR_INFO);
        return new ResponseEntity<Object>(errorsInfo, HttpStatusCode.valueOf(errorsInfo.getStatsCode()));
    }
}
