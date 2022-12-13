package authentication.controller;

import authentication.model.Person;
import authentication.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class.getSimpleName());

    private final ObjectMapper objectMapper;

    @Autowired
    private final UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/sign-up")
    public ResponseEntity<Person>
    signUp(@RequestBody Map<String, String> body) {
        var login = body.get("login");
        var password = body.get("password");
        if (login == null || password == null) {
            throw new NullPointerException("Username and password mustn't be empty");
        }
        if (password.length() > 1 && password.length() < 6) {
            throw new IllegalStateException("Invalid password. Password length must be more than 6 characters.");
        }
        var person = new Person(login, password);
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        Person per = this.userService.save(person);
        return new ResponseEntity<>(per, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<Person> findAll() {
        return userService.findAll();
    }

    @ExceptionHandler(value = { IllegalArgumentException.class })
    public void exceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() { {
            put("message", e.getMessage());
            put("type", e.getClass());
        }}));
        LOGGER.error(e.getLocalizedMessage());
    }

}
