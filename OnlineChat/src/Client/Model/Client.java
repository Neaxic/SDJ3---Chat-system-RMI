package Client.Model;

import Client.Model.ClientSocketHandler;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable {
    private ClientSocketHandler handler;

    public Client() {
    }

    public void messageReceived(String message) {
        System.out.println(message);
    }

    @Override
    public void run() {
        try (
                Socket socket = new Socket("127.0.0.1", 2222);
        ) {
            this.handler = new ClientSocketHandler(this, socket);
            Thread handlerThread = new Thread(handler);
            handlerThread.setDaemon(true);
            handlerThread.start();
            //Message message = (Message) in.readObject();
            //System.out.println(message.getText());
            while (true) {
                String messageFromClient = new Scanner(System.in).nextLine();
                handler.sendMessage(messageFromClient);
                if (messageFromClient.equals("exit")){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ClientSocketHandler getHandler() {
        return handler;
    }
}
