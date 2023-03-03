package database;

import exceptions.DataBaseConnectionException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class DataBaseStatement {
    private static final Logger LOGGER = Logger.getLogger(DataBaseConnection.class.getName());
    private Statement statement;

    public void createStatement(DataBaseConnection connection) throws SQLException {
        statement = connection.createStatement();
        LOGGER.info("statement " + LOGGER.getName() + "was created");
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        if (statement == null) {
            throw new DataBaseConnectionException("Attempt to execute statement without statement");
        }
        return statement.executeQuery(sql);
    }

    public void executeQueryWithoutResult(String sql) throws SQLException {
        if (statement == null) {
            throw new DataBaseConnectionException("Attempt to execute statement without statement");
        }
        statement.execute(sql);
    }
}
