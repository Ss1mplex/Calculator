package Server;

import model.CalculatorImplementation;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) throws RemoteException, InterruptedException {
        CalculatorImplementation calculatorImplementation = new CalculatorImplementation();
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("Calculator", (Remote) calculatorImplementation);
        System.out.println("Server started");
        while (true) {
            Thread.sleep(1000); // Sleep to avoid busy-waiting
        }
    }}