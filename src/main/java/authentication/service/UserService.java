package authentication.service;

import authentication.model.Person;
import authentication.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Collections.emptyList;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final PersonRepository personRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(user.getLogin(), user.getPassword(), emptyList());
    }

    private Person findByUsername(String username) {
        return personRepository.findPersonByLogin(username).orElseThrow(NoSuchElementException::new);
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }
}
