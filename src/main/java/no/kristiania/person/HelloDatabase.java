package no.kristiania.person;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloDatabase {

    private final DataSource dataSource;

    public HelloDatabase(DataSource dataSource) {

        this.dataSource = dataSource;
    }

    public static void main(String[] args) throws SQLException {

        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/guy_db");
        dataSource.setUser("guy");
        dataSource.setPassword("Ax2rw])rJdJJ.5vK");

        try (Connection connection = dataSource.getConnection()) {
            //Statement for å gi kommandoer til databasen
            try (PreparedStatement statement = connection.prepareStatement("select * from people")) {

                //Resultatset for å få lese ut resultater fra databsen
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        System.out.println(rs.getString("last_name"));
                    }
                }

            }
        }

    }

    public void save(Person person) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("insert into people (first_name) values (?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, (String) person.getFirstName());
                statement.executeUpdate();

                ResultSet rs = statement.getGeneratedKeys();
                rs.next();
                person.setId(rs.getLong("id"));
            }
        }

    }

    public Person retrieve(long id) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("select * from people where id = ?")) {
                statement.setLong(1, id);
                ResultSet rs = statement.executeQuery();

                if(rs.next()) {
                    Person person = new Person();
                    person.setFirstName(rs.getString("first_name"));
                    return person;
                }

            }
        }
        return null;
    }
}
