import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class CalculatorClient {
    private CalculatorClient() {}

    public static void main(String[] args) {
        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Calculator stub = (Calculator) registry.lookup("Calculator");

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Enter 'number', 'operation', 'pop', 'delayPop', or 'exit':");
                String type = scanner.next();
                if (type.equalsIgnoreCase("number")) {
                    System.out.println("Enter a number:");
                    int num = scanner.nextInt();
                    stub.pushValue(num);
                } else if (type.equalsIgnoreCase("operation")) {
                    System.out.println("Enter an operation ('min', 'max', 'gcd', 'lcm'):");
                    String operation = scanner.next();
                    stub.pushOperation(operation);
                } else if (type.equalsIgnoreCase("pop")) {
                    System.out.println("pop: " + stub.pop());
                } else if (type.equalsIgnoreCase("delayPop")) {
                    System.out.println("Enter delay time in milliseconds:");
                    int delay = scanner.nextInt();
                    System.out.println("delayPop: " + stub.delayPop(delay));
                } else if (type.equalsIgnoreCase("exit")) {
                    break;
                } else {
                    System.out.println("Unknown input type. Try again.");
                }
            }
            scanner.close();

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
