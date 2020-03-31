package usermicroservice.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    List<User> getUsers(){
        List<User> users = new ArrayList<User>();
        this.userRepository.findAll().forEach(users::add);
        return users;
    }

    @GetMapping("/user/{id}")
    Optional<User> getOne(@PathVariable long id){
        return userRepository.findById(id);
    }

    @PostMapping("/user")
    User newUser(@RequestBody  User user){
        return userRepository.save(user);
    }

    @PutMapping("/user/{id}")
    User modifyUser(@RequestBody User newUser, @PathVariable long oldUser){
        return this.userRepository.findById(oldUser).map((user)->{
            user.setFullname(newUser.getFullname());
            user.setMail(newUser.getMail());
            user.setPassword(newUser.getPassword());
            user.setUsername(newUser.getUsername());
            return user;
        }).orElseGet(()->{
            newUser.setId(oldUser);
            return userRepository.save(newUser);
        });
    }

    @DeleteMapping("/user/{id}")
    ResponseEntity<Boolean> deleteUserById(@PathVariable long id){
        try{
            userRepository.deleteById(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(false, HttpStatus.NO_CONTENT);
        }
    }
}
