public class Helpers {
  static int distance(Point a, Point b) {
    return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
  }

  static int realCost(Point carStart, Ride ride, int currentT) throws Exception {
    // Llega a ride.start
    int cost = distance(carStart, ride.start);

        // T cuando llegas
        int onArrivalT = currentT + cost;

        // Tiempo de espera
        if (ride.earliestStart > onArrivalT)
            cost += ride.earliestStart - onArrivalT;

    cost += ride.distance();

    if (cost > ride.latestFinish)
      throw new Exception();

        return cost;
    }

    static int idleTime (Point actualP, Ride ride, int currentT){
        // Llega a ride.start
        int cost = distance(actualP, ride.start);

        // T cuando llegas
        int onArrivalT = currentT + cost;


        // Tiempo de espera
        if (ride.earliestStart > onArrivalT)
            cost += ride.earliestStart - onArrivalT;

        return cost;
    }


}
