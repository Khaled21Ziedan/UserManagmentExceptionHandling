package adapter.rest.UserManagmentExceptionHandling.Exception;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@ConfigurationProperties(prefix = "com.user.errors")
@Data
@Component
public class UserServiceExceptionsMessages {
    private Map<String,ErrorsInfo> messages ;
}
