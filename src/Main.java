import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Main {
    public static int R, C, F, N, B, T;

    public static void main(String[] args) {
        List<Ride> routes = loadRoutes(args[1]);
        int currentT = 0;
        List<Car> cars = new ArrayList<>();

        List<Ride> listaRides = loadRoutes("a_example.in");
        for(Ride r : listaRides){
            System.out.println("--"+r);
        }
        System.out.println(R +" "+ C +" "+ F +" "+ N +" "+ B +" "+ T);
        for (Car car : cars ) {
            Optional<Ride> currentRoutes = routes.stream().filter((route) -> {
                try {
                    Helpers.realCost(car.currentPosition, route, currentT);
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }).sorted((r1, r2) -> {
                try {
                    return Helpers.realCost(car.currentPosition, r1, currentT) <
                            Helpers.realCost(car.currentPosition, r2, currentT) ? -1 : 1;
                } catch (Exception e) {
                    return 0;
                }
            }).findFirst();

            if (currentRoutes.isPresent()) {
                Ride selectedRoute = currentRoutes.get();
                car.assignedRoutes.add(selectedRoute.id);
                car.enRoute = true;
                //TODO: igual no borra
                routes.remove(selectedRoute);
            }
        }
    }

    private static List<Ride> loadRoutes(String file) {
        try {
            List<Ride> listaRides= new ArrayList<>();
            List<String> lineas = Files.readAllLines(Paths.get(file));
            String linea[]=lineas.get(0).split(" ");
            R=Integer.parseInt(linea[0].trim());
            C=Integer.parseInt(linea[1].trim());
            F=Integer.parseInt(linea[2].trim());
            N=Integer.parseInt(linea[3].trim());
            B=Integer.parseInt(linea[4].trim());
            T=Integer.parseInt(linea[5].trim());
            for(int i=1;i<lineas.size();i++){
                linea=lineas.get(i).split(" ");
                Point inicio = new Point(Integer.parseInt(linea[0].trim()),Integer.parseInt(linea[1].trim()));
                Point fin = new Point(Integer.parseInt(linea[2].trim()),Integer.parseInt(linea[3].trim()));
                int eS = Integer.parseInt(linea[4].trim());
                int lF = Integer.parseInt(linea[5].trim());
                Ride r = new Ride(inicio, fin, eS, lF);
                System.out.println(r);
                listaRides.add(r);
            }
            return listaRides;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return new ArrayList<>();
    }
}

