package adapter.rest.UserManagmentExceptionHandling.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorsInfo {
    private int code ;
    private int statsCode;
    private Map<Language,String>message;
}
