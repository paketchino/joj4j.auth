package authentication.service;

import authentication.model.Person;
import authentication.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Optional<Person> findById(int id) {
        return personRepository.findById(id);
    }

    public Person update(Person person) {
        return personRepository.save(person);
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public void delete(int id) {
        personRepository.deleteById(id);
    }

    public boolean findByIdIfExists(int id) {
        return personRepository.existsById(id);
    }
}
