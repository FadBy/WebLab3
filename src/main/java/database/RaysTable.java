package database;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import beans.ResultRay;
import java.util.Arrays;

@Named
@ApplicationScoped
public class RaysTable implements Serializable {
    private static final String name = "result_rays";
    private final DataBaseTableExecutor queryExecutor;
    static final long serialVersionUID = 42L;
    private static final List<String> columnNames = Arrays.asList("x", "y", "r", "hit_result", "\"time\"", "execution_time");

    public RaysTable() throws SQLException {
        this.queryExecutor = new DataBaseTableExecutor(name, columnNames);
    }

    public void addColumn(double x, double y, double r, boolean hitResult, LocalDateTime current_time, double executionTime) throws SQLException {
        LocalDateTime buf = current_time;
        String formatted = buf.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).split("\\.")[0];
        queryExecutor.addColumn(Arrays.asList(
                Double.toString((double)Math.round(x * 100) / 100),
                Double.toString((double)Math.round(y * 100) / 100),
                Double.toString(r),
                Boolean.toString(hitResult),
                "'" + formatted + "'",
                Double.toString(executionTime)
        ));
    }

    public void deleteAllRows() throws SQLException {
        queryExecutor.deleteAllColumns();
    }



//    public void addColumn(ResultRay ray) throws SQLException {
//        queryExecutor.addColumn(List.of(
//                Double.toString(ray.getX()),
//                Double.toString(ray.getY()),
//                Double.toString(ray.getR()),
//                Boolean.toString(ray.getHitResult()),
//                current_time.toString(),
//                Double.toString(executionTime)
//        ));
//    }

    public List<ResultRay> getAllRays() throws SQLException {
        ResultSet resultSet = queryExecutor.getAllColumns();
        List<ResultRay> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(new ResultRay((double)Math.round(resultSet.getDouble(columnNames.get(0)) * 100) / 100,
                    (double)Math.round(resultSet.getDouble(columnNames.get(1)) * 100) / 100,
                    resultSet.getDouble(columnNames.get(2)),
                    resultSet.getBoolean(columnNames.get(3)),
                    resultSet.getString("time"),
                    resultSet.getDouble(columnNames.get(5))));
        }
        return result;
    }


}
