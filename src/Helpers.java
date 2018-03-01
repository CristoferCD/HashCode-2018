public class Helpers {
  static int distance(Point a, Point b) {
    return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
  }

  static int realCost(Point carStart, Ride ride, int currentT) throws Exception {
    // Llega a ride.start
    int cost = distance(carStart, ride.start);

    // T cuando llegas
    cost += currentT;

    // Tiempo de espera8
    if (ride.earliestStart > cost)
      cost += ride.earliestStart - cost;

    cost += ride.distance();

    if (cost > ride.latestFinish)
      throw new Exception();

    return cost;
  }
}
