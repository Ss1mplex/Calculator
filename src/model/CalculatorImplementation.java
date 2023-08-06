package model;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.util.*;

public class CalculatorImplementation implements Calculator , Serializable {
    //I decide to use clientID to make each client use different stacks
    public String clientID;
    private Map<String, Stack<String>> client;



    public CalculatorImplementation() throws RemoteException{
        //use map to aim to different clients
        client = new HashMap<>();

    }
    public void setClientId(String clientid){
        clientID=clientid;
    }
    public Stack<String> getClient() throws ServerNotActiveException {
        //get the unique clientID
        return client.computeIfAbsent(clientID, k-> new Stack<>());
    }

    @Override
    public void pushValue(int val) throws ServerNotActiveException {
        Stack<String> stack = getClient();
            stack.push(Integer.toString(val));
    }


    @Override
        public void pushOperation(String operator) throws ServerNotActiveException {
        Stack<String> stack = getClient();
        //because it requires me to push the operator into the stack so the stack is String
        //but in order to calculate,need int,so use arraylist to do that
        //every operator needs to first push the operator and then pop all stack finally push the result in
            if (operator.equals("min") || operator.equals("max") || operator.equals("lcm") || operator.equals("gcd")) {
                stack.push(operator);
                stack.pop();
                List<Integer> poppedValues = new ArrayList<>();
                while (!stack.isEmpty()) {
                    poppedValues.add(Integer.parseInt(stack.pop()));
                }

                if (poppedValues.isEmpty()) {
                    System.out.println("stack is already empty");
                    return;
                }
                int result = 0;

                //calculate decided by operator
                switch (operator) {
                    case "min":
                        result = Collections.min(poppedValues);
                        break;
                    case "max":
                        result = Collections.max(poppedValues);
                        break;
                    case "lcm":
                        result = calculateLCM(poppedValues);
                        break;
                    case "gcd":
                        result = calculateGCD(poppedValues);
                        break;
                }

                stack.push(Integer.toString(result));

            }

        }
    private int calculateLCM(List<Integer> values) {
        //a way to calculateLCM
        int lcm = values.get(0);

        for (int value : values) {
            lcm = calculateLCM(lcm, value);
        }

        return lcm;
    }
    private int calculateGCD(List<Integer> values) {
        //a way to calculateGCD
        int gcd = values.get(0);

        for (int value : values) {
            gcd = calculateGCD(gcd, value);
        }

        return gcd;
    }
    private int calculateLCM(int a, int b) {
        return (a * b) / calculateGCD(a, b);
    }

    private int calculateGCD(int a, int b) {
        if (b == 0) {
            return a;
        }
        return calculateGCD(b, a % b);
    }

    @Override
    public int pop () throws ServerNotActiveException {
        Stack<String> stack = getClient();
                if (!stack.isEmpty()) {
                    return Integer.parseInt(stack.pop());
                }
                return 0;
            }
            @Override
            public boolean isEmpty () throws ServerNotActiveException {
            //check if the stack is empty
                Stack<String> stack = getClient();
                return stack.isEmpty();
            }

            @Override
            public int delayPop (int millis) throws InterruptedException, ServerNotActiveException {
            //here just simply use Thread.sleep() and then pop the value
                Stack<String> stack = getClient();
                Thread.sleep(millis);
                return Integer.parseInt(stack.pop());
            }
        }
