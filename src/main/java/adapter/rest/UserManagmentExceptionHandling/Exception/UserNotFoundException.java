package adapter.rest.UserManagmentExceptionHandling.Exception;

public class UserNotFoundException extends AbstractUserServiceException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
