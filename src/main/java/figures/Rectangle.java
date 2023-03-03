package figures;

public class Rectangle extends Figure {
    private final double xEdgeC;
    private final double yEdgeC;

    public double getXEdge() {
        return xEdgeC;
    }

    public double getYEdge() {
        return yEdgeC;
    }

    public Rectangle(double xEdge, double yEdge) {
        this.xEdgeC = xEdge;
        this.yEdgeC = yEdge;
    }

    @Override
    public boolean checkHit(double x, double y, double r) {
        double xEdge = r * xEdgeC;
        double yEdge = r * yEdgeC;
        if (xEdge > 0 && x < 0 ||
            yEdge > 0 && y < 0 ||
            xEdge < 0 && x > 0 ||
            yEdge < 0 && y > 0 ||
            !(0 <= Math.abs(x) && Math.abs(x) <= Math.abs(xEdge) && 0 <= Math.abs(y) && Math.abs(y) <= Math.abs(yEdge))) {
            return false;
        }
        return true;
    }
}
