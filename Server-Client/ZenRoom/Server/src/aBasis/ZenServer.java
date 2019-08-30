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
public class ZenServer extends Thread {

    private static Control c;

    public static void setControl(Control c) {
        ZenServer.c = c;
    }

    private final int PORT;
    private volatile ServerSocket serverSocket;
    private static ZenServer instance = null;

    private final List<ZenServerThread> clientThreads = new ArrayList<>();

    private ZenServer(String port) {
        this.PORT = Integer.valueOf(port);
    }

    public static ZenServer getInstance(String port) {
        if (instance == null) {
            instance = new ZenServer(port);
        }
        return instance;
    }

    public void stopServer() {
        try {
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ZenServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void broadCast(String str) {
        for (ZenServerThread clientThread : clientThreads) {
            clientThread.setBroadCastString(str);
        }
    }

    public void broadCastAvatar(ZenData zenData) {
        for (ZenServerThread clientThread : clientThreads) {
            clientThread.setBroadCastData(zenData);
        }
    }

    @Override
    public void run() {
        System.out.println("Server started: " + this.toString());
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException ex) {
            Logger.getLogger(ZenServer.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (serverSocket != null && !serverSocket.isClosed()) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Kapcsolódás megtörtént");
                FileGets avatar = new FileGets(socket);
                
                ZenServerThread thread = new ZenServerThread(socket);
                clientThreads.add(thread);
                thread.start();
            } catch (SocketException e) {
                System.out.println("Socket: " + e.getMessage());
            } catch (IOException ex) {
                Logger.getLogger(ZenServer.class.getName()).log(Level.SEVERE, null, ex);
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
