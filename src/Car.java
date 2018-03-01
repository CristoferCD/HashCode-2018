import java.util.ArrayList;
import java.util.List;

public class Car {
    int id;
    Point currentPosition;
    Boolean enRoute;
    List<Integer> assignedRoutes;


    public Car(int id, Point currentPosition, Boolean enRoute) {
        this.id = id;
        this.currentPosition = currentPosition;
        this.enRoute = enRoute;
        this.assignedRoutes = new ArrayList<>();
    }
}
