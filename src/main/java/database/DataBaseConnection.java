package database;

import exceptions.DataBaseConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class DataBaseConnection {
    // "jdbc:postgresql://pg:5432/studs?currentSchema=s335110&user=s335110&password=bqp7iRvIQh1dMQoO"
    // "jdbc:postgresql://localhost:5432/postgres?currentSchema=public&user=postgres&password=sul440"
    private static final String URL = "jdbc:postgresql://pg:5432/studs?currentSchema=s335110&user=s335110&password=bqp7iRvIQh1dMQoO";
    private static final Logger LOGGER = Logger.getLogger(DataBaseConnection.class.getName());

    private Connection connection;

    public void connect() throws SQLException {
        connection = DriverManager.getConnection(URL);
        LOGGER.info("connection to " + URL + "was created");
    }

    public Statement createStatement() throws SQLException {
        if (connection == null) {
            throw new DataBaseConnectionException("Attempt to create statement without connection");
        }
        return connection.createStatement();
    }
}
