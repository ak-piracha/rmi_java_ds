import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ListRegistry {
    public static void main(String[] args) {
        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            System.out.println("Searching the RMI Registry...");
            String[] boundNames = registry.list();


            for (String name : boundNames) {
                System.out.println(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
