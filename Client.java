package Client;
import model.Calculator;
import model.CalculatorImplementation;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ServerNotActiveException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws InterruptedException, RemoteException, NotBoundException, ServerNotActiveException {
            Scanner scanner = new Scanner(System.in);
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            Calculator calculator = (Calculator) registry.lookup("Calculator");

            System.out.print("Enter client ID: ");
            String clientId = scanner.nextLine();
            calculator.setClientId(clientId);

            while (true) {
                    System.out.println("1. Push Value");
                    System.out.println("2. Push Operation");
                    System.out.println("3. Pop Value");
                    System.out.println("4. Is Empty?");
                    System.out.println("5. Delayed Pop");
                    System.out.println("0. Exit");
                    System.out.print("Enter your choice: ");
                    int choice = scanner.nextInt();

                    switch (choice) {
                            case 1:
                                    System.out.print("Enter value: ");
                                    int value = scanner.nextInt();
                                    calculator.pushValue(value);
                                    break;
                            case 2:
                                    System.out.print("Enter operation (min/max/lcm/gcd): ");
                                    String operation = scanner.next();
                                    calculator.pushOperation(operation);
                                    System.out.println(calculator.pop());
                                    break;
                            case 3:
                                    int poppedValue = calculator.pop();
                                    System.out.println("Popped value: " + poppedValue);
                                    break;
                            case 4:
                                    boolean isEmpty = calculator.isEmpty();
                                    System.out.println("Is stack empty? " + isEmpty);
                                    break;
                            case 5:
                                    System.out.print("Enter delay in milliseconds: ");
                                    int delay = scanner.nextInt();
                                    int delayedValue = calculator.delayPop(delay);
                                    System.out.println("Delayed popped value: " + delayedValue);
                                    break;
                            case 0:
                                    System.out.println("Exiting...");
                                    System.exit(0);
                            default:
                                    System.out.println("Invalid choice.");
                    }
            }
            }
        }

