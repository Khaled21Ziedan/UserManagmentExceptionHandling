package adapter.rest.UserManagmentExceptionHandling.Service;

import adapter.rest.UserManagmentExceptionHandling.Model.User;
import org.springframework.stereotype.Service;
import adapter.rest.UserManagmentExceptionHandling.Repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findById(String userid){
        return userRepository.getById(userid);
    }
    public User register(User user){return userRepository.save(user);
    }
    public void update(User user){userRepository.update(user); }
    public User remove (String userId) {return userRepository.remove(userId);}
    public void modify(User user){userRepository.modify(user);}

}
