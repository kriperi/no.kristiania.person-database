package no.kristiania.person;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;


public class GuyDaoTest {
    @Test
    void shouldRetrieveSavedPerson () {
        HelloDatabase dao = new HelloDatabase();

        Person person = randomPerson();
        dao.save(person);
        assertThat(dao.retrieve(person.getId()))
                .isEqualTo(person);
    }

    private Person randomPerson() {
        Person person = new Person();
        person.setFirstName(pickOne("Magnus","Bendik","Jonathan","Are","Jamal"));
        return person;

    }

    private Object pickOne(String... alternatives) {
        return alternatives[new Random().nextInt(alternatives.length)];
    }
}
