package pl.coderslab.person;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "persons")
@NoArgsConstructor
@ToString
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_details_id")
    @ToString.Exclude
    private PersonDetails personDetails;

    public Person(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

}
