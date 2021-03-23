package Server;

import  External.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSocketHandler implements Runnable {

    private Socket socket;
    private ConnectionPool pool;
    ObjectOutputStream out;
    ObjectInputStream in;
    private Message currentMessage = new Message("0", "0");

    public ServerSocketHandler(Socket socket, ConnectionPool pool) throws IOException {
        this.socket = socket;
        this.pool = pool;
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        try {
            System.out.println("Client connected from " + socket.getInetAddress().getHostAddress() + " " + socket.getPort());
            //out.writeObject(new Message("Hello from server. Write your name"));
            while (true) {
                String messageFromClient = ((Message) in.readObject()).getWhoText();
                String[] stringInputArray = messageFromClient.split(": ");
                currentMessage.setWhoText(stringInputArray[0], stringInputArray[1]);
                System.out.println(messageFromClient);
                if (currentMessage.getText().equals("!exit")) {
                    pool.removeHandler(this);
                    break;
                }
                if(currentMessage.getText().equals("!chatters")){
                    sendMessage("Currently " +pool.getConnections().size() +" active chatters!: ");
                    for(int i = 0; i < pool.getConnections().size(); i++){
                        sendMessage(pool.getConnections().get(i).currentMessage.getWho() +" ");
                    }
                } else if (currentMessage.getText().contains("#NickNameUpdated!")) {
                    currentMessage.setWhoText(currentMessage.getText().replace("#NickNameUpdated!", ""), "");
                    for(int i = 0; i<pool.getConnections().size(); i++) {
                        pool.broadcastMessage("#NewNickConnected: " + pool.getConnections().get(i).currentMessage.getWho());
                    }
                } else {
                    pool.broadcastMessage(messageFromClient);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Exiting ServerSocketHandler");
    }

    public void sendMessage(String msg) {
        try {
            out.writeObject(new Message(msg));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
