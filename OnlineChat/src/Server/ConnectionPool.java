package Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
    private List<ServerSocketHandler> connections;

    public ConnectionPool() {
        connections = new ArrayList<>();
    }

    public void addHandler(ServerSocketHandler handler) throws IOException, ClassNotFoundException {
        connections.add(handler);
        for(ServerSocketHandler handler1 : connections){
            handler1.sendMessage("New person connected!");
            handler1.sendMessage("#PEOPLE:"+connections.size());
        }
    }
    public void removeHandler(ServerSocketHandler handler) {
        connections.remove(handler);
    }


    public void broadcastMessage(String msg) {
        System.out.println("Broadcasting '" + msg + "' to " + connections.size() + " clients");;
        for (ServerSocketHandler handler : connections) {
            handler.sendMessage(msg);
        }
    }

    public List<ServerSocketHandler> getConnections() {
        return connections;
    }
}
