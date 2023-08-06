import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.*;

public class CalculatorImplementation extends UnicastRemoteObject implements Calculator {
    private Stack<Integer> stack = new Stack<Integer>();

    protected CalculatorImplementation() throws RemoteException {
        super();
    }

    @Override
    public void pushValue(int val) throws RemoteException {
        stack.push(val);
    }

    @Override
    public void pushOperation(String operator) throws RemoteException {
        // This method assumes the stack has at least one value
        int result = stack.pop();

        if (operator.equals("min")) {
            while (!stack.isEmpty()) {
                result = Math.min(result, stack.pop());
            }
        } else if (operator.equals("max")) {
            while (!stack.isEmpty()) {
                result = Math.max(result, stack.pop());
            }
        } else if (operator.equals("lcm")) {
            while (!stack.isEmpty()) {
                result = lcm(result, stack.pop());
            }
        } else if (operator.equals("gcd")) {
            while (!stack.isEmpty()) {
                result = gcd(result, stack.pop());
            }
        } else {
            throw new IllegalArgumentException("Unknown operation: " + operator);
        }

        stack.push(result);
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    private int lcm(int a, int b) {
        return a * (b / gcd(a, b));
    }

    @Override
    public int pop() throws RemoteException {
        return stack.pop();
    }

    @Override
    public boolean isEmpty() throws RemoteException {
        return stack.isEmpty();
    }

    @Override
    public int delayPop(int millis) throws RemoteException {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RemoteException("Delay interrupted", e);
        }
        return pop();
    }
}
