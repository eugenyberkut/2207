package logic;

import java.io.*;
import java.net.Socket;

/**
 * Created by Yevhen on 22.07.2015.
 */
public class ClientQueryHandler extends Thread {
    private Socket socket;

    public ClientQueryHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            writer.println("Hello, World!");
            String name = reader.readLine();
            writer.println("Hello, " + name + "!!!");
            writer.close();
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
