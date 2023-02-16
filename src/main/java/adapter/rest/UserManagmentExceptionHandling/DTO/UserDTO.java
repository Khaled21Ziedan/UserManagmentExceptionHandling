package adapter.rest.UserManagmentExceptionHandling.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
        private String id;
        private String name;
        private int age;
    }

