package adapter.rest.UserManagmentExceptionHandling.adapter.rest;

import adapter.rest.UserManagmentExceptionHandling.DTO.UserDTO;
import adapter.rest.UserManagmentExceptionHandling.Model.User;
import adapter.rest.UserManagmentExceptionHandling.Exception.UserNotFoundException;
import adapter.rest.UserManagmentExceptionHandling.Service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.function.Function;

@RestController
@RequestMapping("/users")
@RefreshScope
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public User gettingUser(@PathVariable String userId) {
        return userService.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    @PostMapping
    public ResponseEntity<EntityModel<User>> saveUser(@RequestBody UserDTO userDTO) {
        User user = userService.register(toUserModel().apply(userDTO));
        EntityModel<User> userEntityModel = EntityModel.of(user).
                add(WebMvcLinkBuilder
                        .linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
                                .removeUser(user.getId()))
                        .withRel("delete")).
                add(WebMvcLinkBuilder
                        .linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
                                .gettingUser(user.getId()))
                        .withRel("get user"));
        return ResponseEntity.ok(userEntityModel);
    }

    @PutMapping
    public void updateUser(@RequestBody UserDTO userDTO) {
        userService.update(toUserModel().apply(userDTO));
    }

    @DeleteMapping("/{Id}")
    public User removeUser(@PathVariable String Id) {
        return userService.remove(Id);
    }

    @PatchMapping
    public void modifyUser(UserDTO userDTO) {
        userService.modify(toUserModel().apply(userDTO));
    }

    private Function<UserDTO, User> toUserModel() {
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
