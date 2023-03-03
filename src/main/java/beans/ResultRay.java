package beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import database.RaysTable;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Named(value="resultRay")
@RequestScoped
public class ResultRay implements Serializable {
    private static final long serialVersionUID = 1L;
    double x;
    double y;
    double r;
    boolean hitResult;
    String currentTime;
    double executionTime;
    @Inject
    private Graph graph;
    @Inject
    private RaysTable table;
    @Inject
    private ResultRayList list;

    public ResultRay() {
    }

    public ResultRay(double x, double y, double r, boolean hitResult, String currentTime, double executionTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.hitResult = hitResult;
        this.currentTime = currentTime;
        this.executionTime = executionTime;
    }

    public void addRay() throws SQLException {
        addToDataBase();
        list.resetResultRays();
    }

    public void addToDataBase() throws SQLException {
        LocalDateTime time1 = LocalDateTime.now();
        table.addColumn(x, y, r, graph.checkHit(x, y, r), LocalDateTime.now(), ((double)LocalDateTime.now().getNano() - time1.getNano()));
    }

    //    public JsonObject convertToJSON() {
//        JsonObject json = new JsonObject();
//        json.addProperty("x", x);
//        json.addProperty("y", y);
//        json.addProperty("r", r);
//        json.addProperty("hitResult", hitResult);
//        json.addProperty("currentTime", currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
//        json.addProperty("executionTime", executionTime);
//        return json;
//    }

//    public List<String> convertToList() {
//        return new ArrayList<String>() {{add(String.valueOf(x)); add(String.valueOf(y)); add(String.valueOf(r)); add(String.valueOf(hitResult)); add(currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"))); add(String.valueOf(executionTime));}};
//    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public boolean isHitResult() {
        return hitResult;
    }

    public void setHitResult(boolean hitResult) {
        this.hitResult = hitResult;
    }

    public String getCurrentTime() {
        return String.join(" ", currentTime.split("T"));
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public double getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(double executionTime) {
        this.executionTime = executionTime;
    }
}
