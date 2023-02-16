package adapter.rest.UserManagmentExceptionHandling.Repository;

import adapter.rest.UserManagmentExceptionHandling.Exception.UserNotFoundException;
import adapter.rest.UserManagmentExceptionHandling.Exception.UserUnstableConditionsException;
import adapter.rest.UserManagmentExceptionHandling.Model.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class UserInMemoryRepository implements UserRepository {
    Map<String, User> users = new HashMap() {{
        put("1000", new User("1000", "Ahmad", 20));
        put("1001", new User("1001", "Khaled", 21));
        put("1002", new User("1002", "Mosa", 22));
        put("1003", new User("1003", "Alaa", 23));
        put("1004", new User("1004", "Asad", 24));
    }};

    @Override
    public Optional<User> getById(String userId) {
        return Optional.ofNullable(users.get(userId));
    }

    public void save(User user) {
        if (user.getAge() > 30)
            throw new UserUnstableConditionsException(user.getId());
        users.put(user.getId(), user);
    }

    @Override
    public void update(User user) {
        if (users.get(user.getId()) == null)
            throw new UserNotFoundException(user.getId());
        users.put(user.getId(), user);
    }

    @Override
    public void remove(String userId) {
        if (users.get(userId) == null)
            throw new UserNotFoundException(userId);
        users.remove(userId);
    }

    @Override
    public void modify(User user) {
        if (users.get(user.getId()) == null)
            throw new UserNotFoundException(user.getId());
        else if (user.getAge() > 30)
            throw new UserUnstableConditionsException(user.getId());
        users.put(user.getId(), user);
    }
}
