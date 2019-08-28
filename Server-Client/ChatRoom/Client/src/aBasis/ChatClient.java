/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import aControl.Control;
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
public class ChatClient extends Thread {

    private static Control c;

    public static void setControl(Control c) {
        ChatClient.c = c;
    }

    private final String language;
    private final String MB6IP;
    private final int MB6PORT;
    private final String USER;

    private String closeConnect;
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAbortConnect() {
        return closeConnect;
    }

    public String getMB6IP() {
        return MB6IP;
    }

    public int getMB6PORT() {
        return MB6PORT;
    }

    public String getUser() {
        return USER;
    }

    private boolean online = false;

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public ChatClient(String ip, int port, String user, String lang) {
        this.MB6IP = ip;
        this.MB6PORT = port;
        this.USER = user;
        this.language = lang;
        this.message = "";
    }

    public void initClient(BufferedReader in, PrintWriter out) {
        out.println(language);
        System.out.println("locale: " + language);
        out.println(USER);
        System.out.println("locale: " + USER);
    }

    @Override
    public void run() {
        try (Socket socket = new Socket(this.MB6IP, this.MB6PORT);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream(),
                                StandardCharsets.UTF_8));
                PrintWriter out = new PrintWriter(
                        socket.getOutputStream(), true)) {
            initClient(in, out);
            closeConnect = in.readLine();
            JOptionPane.showMessageDialog(null, closeConnect);
            online = true;
            while (online) {
                if (!message.isEmpty()) {
                    System.out.println("> " + message);
                    out.println(message);
                    setMessage("");
                    System.out.println("message sand and reset... " + message);
                }
                if (in.ready()) {
                    String str = in.readLine();
                    c.writeMessage(str);
                    c.lastUser(str.substring(0, str.indexOf(">") - 1));
                }
                c.refreshGraphity();
            }
            System.out.println("> " + closeConnect);
            out.println(closeConnect);
            in.close();
            out.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Thred:> " + this.getName() + " finished,");
        c.clientClosed(this);
    }

    @Override
    public String toString() {
        return getUser();
    }
}
