package figures;

public class Circle extends Figure {
    private final double rC;
    private final int quarter;

    public double getR() {
        return rC;
    }

    public int getQuarter() {
        return quarter;
    }

    public Circle(int quarter, double rC) {
        if (quarter > 4 || quarter < 1) {
            throw new IllegalArgumentException("Quarter must be in range [1, 4]");
        }
        this.rC = rC;
        this.quarter = quarter;
    }

    @Override
    public boolean checkHit(double x, double y, double r) {
        r = r * rC;
        if (x < 0 && !(quarter == 2 || quarter == 3) ||
            x > 0 && !(quarter == 1 || quarter == 4) ||
            y < 0 && !(quarter == 3 || quarter == 4) ||
            y > 0 && !(quarter == 1 || quarter == 2) ||
            x * x + y * y > r * r) {
            return false;
        }
        return true;
    }
}
