package client;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Yevhen on 22.07.2015.
 */
public class ClientThread extends Thread {
    ClientMain main;

    public ClientThread(ClientMain main) {
        this.main = main;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = main.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                main.getTextArea().append(line+"\n");
                main.repaint();
            }
        } catch (IOException ex) {

        }
    }
}
