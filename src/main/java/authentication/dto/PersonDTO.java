package authentication.dto;

import authentication.model.Person;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;

import java.util.Objects;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonDTO {

    private String login;
    private String password;

    public PersonDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PersonDTO personDTO = (PersonDTO) o;
        return Objects.equals(login, personDTO.login)
                && Objects.equals(password, personDTO.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }
}
