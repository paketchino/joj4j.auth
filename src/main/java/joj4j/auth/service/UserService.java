package joj4j.auth.service;

import joj4j.auth.model.Person;
import joj4j.auth.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return personRepository.findAll().get(Integer.parseInt(username));
    }

    public void save(Person person) {
        personRepository.save(person);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }
}
