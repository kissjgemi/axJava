/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author b6dmin
 */
public class GDTClient {

    private void start() throws IOException {
        String language = "en";
        String serverAddress
                = JOptionPane.showInputDialog("Adja meg a server ip címét");
        if (serverAddress.equals("") || serverAddress.equals("localhost")) {
            serverAddress = "127.0.0.1";
        }

        int port = 44444;

        try (Socket socket = new Socket(serverAddress, port);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream(),
                                StandardCharsets.UTF_8));
                PrintWriter out = new PrintWriter(
                        socket.getOutputStream(), true)) {
            out.println(language);
            String dateString = in.readLine();
            String xmassString = in.readLine();
            String schoolString = in.readLine();

            JOptionPane.showMessageDialog(null, dateString + "\n"
                    + xmassString + "\n" + schoolString);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            new GDTClient().start();
        } catch (IOException ex) {
            Logger.getLogger(GDTClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
