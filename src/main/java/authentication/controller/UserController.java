package authentication.controller;

import authentication.model.Person;
import authentication.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/sign-up")
    public String signUp(@RequestBody Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        userService.save(person);
        return "users/sign-up";
    }

    @GetMapping("/all")
    public List<Person> findAll() {
        return userService.findAll();
    }

}
