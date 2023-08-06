package model;

import java.rmi.Remote;
import java.rmi.server.ServerNotActiveException;

public interface Calculator extends Remote {
    void pushValue(int val) throws ServerNotActiveException;
    void pushOperation(String operator) throws ServerNotActiveException;
    int pop() throws ServerNotActiveException;
    boolean isEmpty() throws ServerNotActiveException;
    int delayPop(int millis) throws InterruptedException, ServerNotActiveException;
    void setClientId(String clientid)throws InterruptedException, ServerNotActiveException;
}
