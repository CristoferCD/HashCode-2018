import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
  public static int Rows, Columns, AmountOfCars, NumberOfRides, Bonus, T;

  public static void main(String[] args) {
    List<Ride> routes = loadRoutes(args[0]);
    int currentT = 0;
    List<Car> cars = new ArrayList<>();

    System.out.println("Cargados " + routes.size());
    for (Ride r : routes) {
      System.out.println("--" + r);
    }
    System.out.println(Rows + " " + Columns + " " + AmountOfCars + " " + NumberOfRides + " " + Bonus + " " + T);

    for (int i = 0; i < AmountOfCars; i++)
      cars.add(new Car(i, new Point(0, 0)));

    for (currentT = 0; currentT < T; currentT++) {
      // Mover todos los coches
      cars.stream().filter((c) -> c.currentRoute != null).forEach(Car::move);

      List<Car> availableCars = cars.stream().filter((c) -> c.currentRoute == null).collect(Collectors.toList());

      for (Car car : availableCars) {
        int finalCurrentT = currentT;
        Optional<Ride> currentRoutes = routes.stream().filter((route) -> {
          try {
            Helpers.realCost(car.currentPosition, route, finalCurrentT);
            return true;
          } catch (Exception e) {
            return false;
          }
        }).sorted((r1, r2) -> {
          try {
            return Helpers.realCost(car.currentPosition, r1, finalCurrentT) < Helpers.realCost(car.currentPosition, r2,
                finalCurrentT) ? -1 : 1;
          } catch (Exception e) {
              if (Helpers.hasBonus(car.currentPosition, r1, finalCurrentT))
                  return -1;
              else if (Helpers.hasBonus(car.currentPosition, r2, finalCurrentT))
                  return 1;
              else {
                  int d1 = Helpers.distance(car.currentPosition, r1.start);
                  int d2 = Helpers.distance(car.currentPosition, r2.start);
                  if (d1 < d2) return -1;
                  else if (d1 > d2) return 1;
                  else return 0;
              }
          }
        }).findFirst();

        if (currentRoutes.isPresent()) {
          Ride selectedRoute = currentRoutes.get();
          car.assignedRoutes.add(selectedRoute.id);
          car.currentRoute = selectedRoute;
          routes.remove(selectedRoute);
        }

        if (routes.isEmpty()) {
          break;
        }
      }
    }
      try {
          PrintWriter writer = new PrintWriter("c_no_hurry.txt", "UTF-8");
          for (Car c : cars) {
              writer.println(c.printFormatted());
          }
          writer.close();
      } catch (FileNotFoundException | UnsupportedEncodingException e) {
          e.printStackTrace();
      }
      return;
  }

  private static List<Ride> loadRoutes(String file) {
    try {
      List<Ride> listaRides = new ArrayList<>();
      List<String> lineas = Files.readAllLines(Paths.get(file));
      String linea[] = lineas.get(0).split(" ");
      Rows = Integer.parseInt(linea[0].trim());
      Columns = Integer.parseInt(linea[1].trim());
      AmountOfCars = Integer.parseInt(linea[2].trim());
      NumberOfRides = Integer.parseInt(linea[3].trim());
      Bonus = Integer.parseInt(linea[4].trim());
      T = Integer.parseInt(linea[5].trim());
      for (int i = 1; i < lineas.size(); i++) {
        linea = lineas.get(i).split(" ");
        Point inicio = new Point(Integer.parseInt(linea[0].trim()), Integer.parseInt(linea[1].trim()));
        Point fin = new Point(Integer.parseInt(linea[2].trim()), Integer.parseInt(linea[3].trim()));
        int eS = Integer.parseInt(linea[4].trim());
        int lF = Integer.parseInt(linea[5].trim());
        Ride r = new Ride(i-1, inicio, fin, eS, lF);
        System.out.println(r);
        listaRides.add(r);
      }
      return listaRides;
    } catch (IOException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }
}
