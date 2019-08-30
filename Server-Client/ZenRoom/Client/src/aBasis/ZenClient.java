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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author b6dmin
 */
public class ZenClient extends Thread {

    private static Control c;

    public static void setControl(Control c) {
        ZenClient.c = c;
    }

    private final String language;
    private final String MB6IP;
    private final int MB6PORT;
    private final String USER;
    private final ImageIcon face;

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

    public ImageIcon getFace() {
        return face;
    }

    private boolean online = false;

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    private ZenData userData;

    public ZenData getUserData() {
        return userData;
    }

    public final void setUserData(ZenData userData) {
        this.userData = userData;
    }

    public ZenClient(String ip, int port,
            String user, ImageIcon icon, String lang) {
        this.MB6IP = ip;
        this.MB6PORT = port;
        this.USER = user;
        this.face = icon;
        this.language = lang;
        this.message = "";
        setUserData(new ZenData(face, user));
    }

    public void initClient(ObjectOutputStream os, ObjectInputStream is)
            throws IOException, ClassNotFoundException {

        os.writeObject(language);
        System.out.println("ZenClient init locale: " + language);

        os.writeObject(userData);
        System.out.println("ZenClient init userData> " + userData.getZenUserName());

        closeConnect = (String) is.readObject();
        JOptionPane.showMessageDialog(null, closeConnect);
    }

    @Override
    public void run() {
        try (Socket socket = new Socket(this.MB6IP, this.MB6PORT);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream(),
                                StandardCharsets.UTF_8));
                ObjectOutputStream os
                = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream is
                = new ObjectInputStream(socket.getInputStream());
                PrintWriter out = new PrintWriter(
                        socket.getOutputStream(), true)) {
            online = true;

            initClient(os, is);

            while (online) {
                if (!message.isEmpty()) {
                    System.out.println("> " + message);
                    os.writeObject(message);
                    System.out.println("ZenClient write> " + message
                            + "\nZenClient message reset... ");
                    setMessage("");
                }
                if (in.ready()) {
                    Object o = is.readObject();
                    if (o instanceof String) {
                        String str = String.valueOf(o);
                        c.writeMessage(str);
                        c.lastUser(str.substring(0, str.indexOf(">") - 1));
                        System.out.println("ZenClient read string> " + str);
                    }
                    if (o instanceof ZenData) {
                        ZenData z = (ZenData) o;
                        c.lastAvatar(z.getZenFace());
                        System.out.println("ZenClient read userData> "
                                + z.getZenUserName());
                    }
                }
                c.refreshGraphity();
            }
            System.out.println("ZenClient > " + closeConnect);
            os.writeObject(closeConnect);
            in.close();
            os.close();
            is.close();
            out.close();
            socket.close();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ZenClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("ZenClient Thread> " + this.getName() + " finished,");
        c.clientClosed(this);
    }

    @Override
    public String toString() {
        return getUser();
    }
}
