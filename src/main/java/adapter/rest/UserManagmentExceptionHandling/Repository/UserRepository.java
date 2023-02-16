package adapter.rest.UserManagmentExceptionHandling.Repository;

import adapter.rest.UserManagmentExceptionHandling.Model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> getById(String userId);
     void save(User user);
     void  update(User user);
     void remove(String userId);
     void modify(User user);
}
