package no.kristiania.person;

import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloDatabase {
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

    public void save(Person person) {
    }

    public Person retrieve(long id) {
        return null;
    }
}
