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
        //register the port 1099
        registry.rebind("Calculator", (Remote) calculatorImplementation);
        //bind the "Calculator" which will be used in clients
        System.out.println("Server started");
        while (true) {
            Thread.sleep(1000);
            //keep the server running so the multiple clients can use the server
        }
    }}