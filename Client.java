package Client;
import model.Calculator;
import model.CalculatorImplementation;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ServerNotActiveException;

public class Client {
    public static void main(String[] args) throws InterruptedException, RemoteException, NotBoundException, ServerNotActiveException {

            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            Calculator calculator = (Calculator) registry.lookup("Calculator");

            calculator.setClientId("client1");
            calculator.pushValue(5);
            calculator.pushValue(10);
            calculator.pushValue(15);
            calculator.pushValue(20);
            calculator.pushOperation("min");
            int min = calculator.pop();
            System.out.println("Min value: " + min);
            calculator.pushValue(5);
            calculator.pushValue(10);
            calculator.pushValue(15);
            calculator.pushValue(20);
            calculator.pushOperation("max");
            int max = calculator.pop();
            System.out.println("Max value: " + max);
            calculator.pushValue(5);
            calculator.pushValue(10);
            calculator.pushValue(15);
            calculator.pushValue(20);
            calculator.pushOperation("lcm");
            int lcm = calculator.pop();
            System.out.println("LCM value: " + lcm);
            calculator.pushValue(5);
            calculator.pushValue(10);
            calculator.pushValue(15);
            calculator.pushValue(20);
            calculator.pushOperation("gcd");
            int gcd = calculator.pop();
            System.out.println("GCD value: " + gcd);
            calculator.pushValue(5);
            calculator.pushValue(10);
            calculator.pushValue(15);
            calculator.pushValue(20);
            System.out.println("Is stack empty? " + calculator.isEmpty());

            calculator.pushValue(25);

            int delayedValue = calculator.delayPop(2000); // Delayed pop
            System.out.println("Delayed popped value: " + delayedValue);
            while (true) {
                    Thread.sleep(1000); // Sleep to avoid busy-waiting
            }
        }
}
