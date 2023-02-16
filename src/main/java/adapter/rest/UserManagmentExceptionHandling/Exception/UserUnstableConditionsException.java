package adapter.rest.UserManagmentExceptionHandling.Exception;

public class UserUnstableConditionsException extends AbstractUserServiceException {
    public UserUnstableConditionsException(String userId) {
        super(userId);
    }
}
