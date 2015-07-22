package logic;

import java.io.*;
import java.net.Socket;

/**
 * Created by Yevhen on 22.07.2015.
 * Поток обработки действий клиента
 */
public class ClientQueryHandler extends Thread {
    private Socket socket;
    private Main main;
    private PrintWriter writer;
    private BufferedReader reader;

    public ClientQueryHandler(Socket socket, Main main) {
        this.socket = socket;
        this.main = main;
    }

    @Override
    public void run() {
        try {
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            writer = new PrintWriter(outputStream, true);
            reader = new BufferedReader(new InputStreamReader(inputStream));
            writer.println("Hello, World!");
            String name = reader.readLine();
            writer.println("Hello, " + name + "!!!");
            while (true) {
                String line = reader.readLine();
                main.send(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public synchronized void send(String line) {
        writer.println(">>> " + line);
    }
}
