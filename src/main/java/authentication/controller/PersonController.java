package authentication.controller;

import authentication.model.Person;
import authentication.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/")
    public List<Person> findAll() {
        return this.personService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable int id) {
        var person = this.personService.findById(id);
        return new ResponseEntity<Person>(
                person.orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "User by id not found")), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Person>
    create(@RequestBody Person person) {
        return new ResponseEntity<>(
                this.personService.save(person),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Boolean> update(@RequestBody Person person) {
        var rsl = personService.findByIdIfExists(person.getId());
        if (rsl) {
            this.personService.update(person);
        }
        return new ResponseEntity<>(rsl, rsl ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        boolean rsl = personService.findByIdIfExists(id);
        if (rsl) {
            this.personService.delete(id);
        }
        Person person = new Person();
        person.setId(id);
        return new ResponseEntity<>(rsl, rsl ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}

