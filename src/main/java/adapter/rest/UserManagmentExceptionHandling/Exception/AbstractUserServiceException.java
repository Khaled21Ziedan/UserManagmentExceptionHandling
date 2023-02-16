package adapter.rest.UserManagmentExceptionHandling.Exception;

public abstract class AbstractUserServiceException extends RuntimeException{
    private final String userId;

    public AbstractUserServiceException(String userId) {
        super(userId);
        this.userId=userId;
    }
}
