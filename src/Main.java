import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Ride> routes = loadRoutes(args[1]);


    }

    public static List<Ride> loadRoutes(String file) {
        try {
            List<String> lineas = Files.readAllLines(Paths.get(file));
            // Parsear
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
