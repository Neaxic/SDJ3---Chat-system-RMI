package Server;

import External.MessageServer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServerRun {

        public static void main(String[] args) throws RemoteException, AlreadyBoundException {
            MessageServer server = new RMIServerImpl();
            Registry registry = LocateRegistry.createRegistry(2000);
            registry.bind("Server", server);
            System.out.println("RMI Server Started");
        }
}
