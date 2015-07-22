package logic;

import java.io.*;
import java.net.*;

/**
 * Created by Yevhen on 22.07.2015.
 * Пример серверной программы
 */
public class Main {
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            while (true) {
                Socket socket = serverSocket.accept();
                new ClientQueryHandler(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
