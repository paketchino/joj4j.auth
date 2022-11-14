package joj4j.auth.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "persons")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Person {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String login;

    @NonNull
    private String password;
}
