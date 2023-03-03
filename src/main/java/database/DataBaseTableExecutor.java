package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public final class DataBaseTableExecutor {
    private final DataBaseConnection connection;
    private final DataBaseStatement statement;
    private final String tableName;
    private final List<String> names;

    public DataBaseTableExecutor(String tableName, List<String> names) throws SQLException {
        this.tableName = tableName;
        this.names = Collections.unmodifiableList(names);
        connection = new DataBaseConnection();
        statement = new DataBaseStatement();
        connection.connect();
        statement.createStatement(connection);
    }

    public void addColumn(List<String> names, List<String> values) throws SQLException {
        String request = String.format("INSERT INTO %s (%s) VALUES (%s);", tableName,
                String.join(", ", names), String.join(", ", values));
        System.out.println(request);
        statement.executeQueryWithoutResult(request);
    }

    public void addColumn(List<String> values) throws SQLException {
        addColumn(names, values);
    }

    public void deleteAllColumns() throws SQLException {
        statement.executeQueryWithoutResult("DELETE FROM " + tableName);
    }

    public ResultSet getAllColumns() throws SQLException {
        return statement.executeQuery("SELECT * FROM " + tableName);
    }
}
