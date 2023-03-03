package beans;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import figures.Circle;
import figures.Figure;
import figures.Rectangle;
import figures.Triangle;

import java.io.Serializable;

@Named
@ApplicationScoped
public class Graph implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Figure[] quarters = new Figure[] {new Triangle(-1, 1), new Rectangle(1, -1), new Circle(3, 0.5)};

    public Graph() {
    }

    public boolean checkHit(double x, double y, double r) {
        for (Figure figure : quarters) {
            if (figure.checkHit(x, y, r)) {
                return true;
            }
        }
        return false;
    }

}
