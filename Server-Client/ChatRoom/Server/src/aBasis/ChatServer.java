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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b6dmin
 */
public class ChatServer extends Thread {

    private static Control c;

    public static void setControl(Control c) {
        ChatServer.c = c;
    }

    private final int PORT;
    private volatile ServerSocket serverSocket;
    private static ChatServer instance = null;

    private final List<ChatServerThread> clientThreads = new ArrayList<>();

    private ChatServer(String port) {
        this.PORT = Integer.valueOf(port);
    }

    public static ChatServer getInstance(String port) {
        if (instance == null) {
            instance = new ChatServer(port);
        }
        return instance;
    }

    public void stopServer() {
        try {
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void broadCast(String str) {
        for (ChatServerThread clientThread : clientThreads) {
            clientThread.setBroadCastString(str);
        }
    }

    @Override
    public void run() {
        System.out.println("Server started: " + this.toString());
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (serverSocket != null && !serverSocket.isClosed()) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Kapcsolódás megtörtént");
                ChatServerThread thread = new ChatServerThread(socket);
                clientThreads.add(thread);
                thread.start();
            } catch (SocketException e) {
                System.out.println("Socket: " + e.getMessage());
            } catch (IOException ex) {
                Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Thred:> " + this.getName() + " finished,");
        c.serverFinished(this);
    }

    @Override
    public String toString() {
        return SERVER_IP_TXT3 + ":" + PORT;
    }
}
