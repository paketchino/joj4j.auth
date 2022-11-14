package authentication.repository;

import authentication.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

    List<Person> findAll();

}
