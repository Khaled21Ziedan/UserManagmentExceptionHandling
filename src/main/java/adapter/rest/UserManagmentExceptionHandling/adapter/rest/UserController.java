package adapter.rest.UserManagmentExceptionHandling.adapter.rest;

import adapter.rest.UserManagmentExceptionHandling.DTO.UserDTO;
import adapter.rest.UserManagmentExceptionHandling.Exception.UserUnstableConditionsException;
import adapter.rest.UserManagmentExceptionHandling.Model.User;
import adapter.rest.UserManagmentExceptionHandling.Exception.UserNotFoundException;
import adapter.rest.UserManagmentExceptionHandling.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.function.Function;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public User gettingUser(@PathVariable String userId){
        return userService.findById(userId).orElseThrow(()-> new UserNotFoundException(userId));
    }
    @PostMapping
    public void saveUser(@RequestBody UserDTO userDTO){
        userService.register(toUserModel().apply(userDTO));
    }
    @PutMapping
    public void updateUser(@RequestBody UserDTO userDTO){
        userService.update(toUserModel().apply(userDTO));
    }
    @DeleteMapping("/{Id}")
    public void removeUser(@PathVariable String Id ){
        userService.remove(Id);
    }
    @PatchMapping
    public void modifyUser(UserDTO userDTO){
        userService.modify(toUserModel().apply(userDTO));
    }
    private Function<UserDTO,User> toUserModel() {
        return userDTO -> User.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .age(userDTO.getAge())
                .build();
    }
    private Function<User, UserDTO> toUserDto() {
        return user -> UserDTO
                .builder()
                .id(user.getId())
                .age(user.getAge())
                .name(user.getName())
                .build();
    }
}
