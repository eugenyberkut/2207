package logic;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yevhen on 22.07.2015.
 * Пример серверной программы
 */
public class Main {
    List<ClientQueryHandler> handlers = new ArrayList<>();

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            while (true) {
                Socket socket = serverSocket.accept();
                ClientQueryHandler clientQueryHandler = new ClientQueryHandler(socket, this);
                handlers.add(clientQueryHandler);
                clientQueryHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void send(String line) {
        for (ClientQueryHandler handler : handlers) {
            handler.send(line);
        }
    }
}
