import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorServer {
    public CalculatorServer() {}

    public static void main(String[] args) {
        try {
            CalculatorImplementation obj = new CalculatorImplementation();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Calculator", obj);
            // Calculator stub = (Calculator) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
           
//            Registry registry = LocateRegistry.getRegistry();
            

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

