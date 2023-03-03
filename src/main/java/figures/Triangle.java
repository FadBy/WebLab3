package figures;

public class Triangle extends Figure {
    private final double yEdgeC;
    private final double xEdgeC;

    public double getYEdge() {
        return yEdgeC;
    }

    public double getXEdge() {
        return xEdgeC;
    }

    public Triangle(double xEdge, double yEdge) {
        this.yEdgeC = yEdge;
        this.xEdgeC = xEdge;
    }

    @Override
    public boolean checkHit(double x, double y, double r) {
        double xEdge = xEdgeC * r;
        double yEdge = yEdgeC * r;
        if (!(xEdge < 0 && x <= 0 || xEdge > 0 && x >= 0) || !(yEdge < 0 && y <= 0 || yEdge > 0 && y >= 0)
            || y > x + r/*((Math.abs(y) > -Math.abs(xEdge) * Math.abs(yEdge) / (-Math.abs(xEdge)) + Math.abs(yEdge) * Math.abs(x)))*/) {
            return false;
        }
        return true;
    }
}
