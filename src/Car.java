import java.util.ArrayList;
import java.util.List;

public class Car {
    int id;
    Point currentPosition;
    List<Integer> assignedRoutes;
    Ride currentRoute = null;
    int accumDistance = 0;

    public Car(int id, Point currentPosition) {
        this.id = id;
        this.currentPosition = currentPosition;
        this.assignedRoutes = new ArrayList<>();
    }

    public void move() {
        accumDistance++;
        if (accumDistance == currentRoute.distance()) {
            currentPosition = currentRoute.end;
            accumDistance = 0;
            currentRoute = null;
        }
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", currentPosition=" + currentPosition +
                ", assignedRoutes=" + assignedRoutes +
                ", currentRoute=" + currentRoute +
                '}';
    }
}
