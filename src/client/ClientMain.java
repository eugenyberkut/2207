package client;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

/**
 * Created by Yevhen on 22.07.2015.
 */
public class ClientMain extends JFrame{
    PrintWriter writer;
    BufferedReader reader;
    JTextField textField;
    JTextArea textArea;

    public ClientMain(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            Socket socket = new Socket("localhost", 12345);
            InputStream inputStream = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            OutputStream outputStream = socket.getOutputStream();
            writer = new PrintWriter(outputStream, true);
            textField = new JTextField(40);
            JButton button = new JButton("Send");
            button.addActionListener(e -> {
                writer.println(textField.getText());
                textField.setText("");
            });
            JPanel panel1 = new JPanel();
            panel1.add(textField);
            panel1.add(button);
            getContentPane().add(panel1, BorderLayout.NORTH);
            textArea = new JTextArea(10,40);
            JScrollPane scrollPane = new JScrollPane(textArea);
            getContentPane().add(scrollPane);
            new ClientThread(this).start();
            pack();
        } catch (IOException e) {
            System.exit(0);
        }

    }

    public PrintWriter getWriter() {
        return writer;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public JTextField getTextField() {
        return textField;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientMain("My Client Window").setVisible(true);
            }
        });
    }
}
