/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import aControl.Control;
import static aGlobal.Global.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b6dmin
 */
public class MB6Server extends Thread {

    private static Control c;

    public static void setControl(Control c) {
        MB6Server.c = c;
    }

    private final int PORT;
    private volatile ServerSocket serverSocket;
    private static MB6Server instance = null;

    private MB6Server(String port) {
        this.PORT = Integer.valueOf(port);
    }

    public static MB6Server getInstance(String port) {
        if (instance == null) {
            instance = new MB6Server(port);
        }
        return instance;
    }

    public void stopServer() {
        try {
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(MB6Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        System.out.println("Server started: " + this.toString());
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException ex) {
            Logger.getLogger(MB6Server.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (serverSocket != null && !serverSocket.isClosed()) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Kapcsolódás megtörtént");
                MB6ServerThread thread = new MB6ServerThread(socket);
                c.firstMessage();
                thread.start();
            } catch (SocketException e) {
                System.out.println("Socket: " + e.getMessage());
            } catch (IOException ex) {
                Logger.getLogger(MB6Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Thred:> " + this.getName() + " finished,");
        c.serverFinished(this);
    }

    @Override
    public String toString() {
        return SERVER_IP_TXT + ":" + PORT;
    }
}
