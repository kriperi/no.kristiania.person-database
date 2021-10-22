package no.kristiania.person;

import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;


public class GuyDaoTest {
    //Denne testen oppretter et tilfeldig objekt med tilfeldig fornavn og etternavn, den lagrer det i databasen
    //Også henter den det opp som et nytt person objekt, men som skal ha de samme verdiene. Også sjekker den at alle
    //feltene i person objektet er satt slik at vi ikke har glemt å initialisere test dataene mine. Også sjekker den
    //at alle feltene på objektet vi skrev ned er like de objektene som vi leste opp igjen.
    @Test
    void shouldRetrieveSavedPerson () throws SQLException {
        GuyDao dao = new GuyDao(createDataSource());

        Person person = randomPerson();
        dao.save(person);
        assertThat(dao.retrieve(person.getId()))
                .hasNoNullFieldsOrProperties()
                .usingRecursiveComparison()
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
        person.setLastName(pickOne("Olsoon", "Berg", "Arnildsen", "Gunnarson", "persson"));
        return person;

    }

    private String pickOne(String... alternatives) {
        return alternatives[new Random().nextInt(alternatives.length)];
    }
}
