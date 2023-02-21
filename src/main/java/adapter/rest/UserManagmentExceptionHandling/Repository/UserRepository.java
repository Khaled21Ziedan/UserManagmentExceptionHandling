package adapter.rest.UserManagmentExceptionHandling.Repository;

import adapter.rest.UserManagmentExceptionHandling.Model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> getById(String userId);
     User save(User user);
     void  update(User user);
     User remove(String userId);
     void modify(User user);
}
