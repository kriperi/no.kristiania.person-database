package no.kristiania.person;

import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;


public class GuyDaoTest {
    @Test
    void shouldRetrieveSavedPerson () throws SQLException {
        HelloDatabase dao = new HelloDatabase(createDataSource());

        Person person = randomPerson();
        dao.save(person);
        assertThat(dao.retrieve(person.getId()))
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(person);
    }

    private DataSource createDataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/guy_db");
        dataSource.setUser("guy");
        dataSource.setPassword("Ax2rw])rJdJJ.5vK");
        return dataSource;

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
