package beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import database.RaysTable;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@Named
@SessionScoped
public class ResultRayList implements Serializable {
    private static final long serialVersionUID = 1L;
    @Inject
    private RaysTable table;
    private List<ResultRay> resultRays;

    public List<ResultRay> getResultRays() throws SQLException {
        resetResultRays();
        return resultRays;
    }

    public void setResultRays(List<ResultRay> resultRays) {
        this.resultRays = resultRays;
    }

    public void resetResultRays() throws SQLException {
        resultRays = table.getAllRays();
    }

    public void deleteAllRays() throws SQLException {
        table.deleteAllRows();
        resetResultRays();
    }
}
