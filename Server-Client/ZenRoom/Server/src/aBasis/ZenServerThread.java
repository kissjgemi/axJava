/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import aControl.Control;
import static aGlobal.Global.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b6dmin
 */
public class ZenServerThread extends Thread {

    private static Control c;

    public static void setControl(Control c) {
        ZenServerThread.c = c;
    }

    public boolean isIsRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    private boolean isRunning;

    private final Socket socket;
    private final ZenUser user;

    private String broadCastString;
    private ZenData broadCastData;

    public void setBroadCastString(String broadCastString) {
        this.broadCastString = broadCastString;
    }

    public void setBroadCastData(ZenData broadCastData) {
        this.broadCastData = broadCastData;
    }

    ZenServerThread(Socket socket) {
        this.socket = socket;
        this.isRunning = false;
        this.user = new ZenUser();
        broadCastString = "";
        broadCastData = null;
        System.out.println("ZenServerThread()");
    }

    private void initUser(ObjectOutputStream os, ObjectInputStream is)
            throws IOException, ClassNotFoundException {

        user.setLocale((String) is.readObject());
        System.out.println("ZenServerThread locale> " + user.getLocale());
        Locale locale = new Locale(user.getLocale());

        LocalDateTime today = LocalDateTime.now();
        String DATE_FORMATTER = ": yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter dtf
                = DateTimeFormatter.ofPattern(DATE_FORMATTER, locale);

        String nowString = today.format(dtf);

        user.setUserData((ZenData) is.readObject());
        System.out.println("ZenServerThread userData> "
                + user.getUserData().getZenUserName());

        user.setAbortConnectString(user.getUserData().getZenUserName() + nowString);
        System.out.println("ZenServerThread abortConnectString> " + user.getAbortConnectString());

        c.addUser(user);
        user.setLastMessage(CLIENT_CONNECTED);

        os.writeObject(user.getAbortConnectString());
    }

    @Override
    public void run() {
        System.out.println("ZenServerThread Thread started: ");

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream(),
                        StandardCharsets.UTF_8));
                ObjectOutputStream os
                = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream is
                = new ObjectInputStream(socket.getInputStream());
                PrintWriter out = new PrintWriter(
                        socket.getOutputStream(), true)) {

            isRunning = true;

            initUser(os, is);
            c.broadCast(user);
            c.broadCastAvatar(user);
            c.listMessage(user);

            while (broadCastData == null) {
            }

            while (isRunning) {
                if (broadCastData != null) {
                    os.writeObject(broadCastData);
                    broadCastData = null;
                }
                if (in.ready()) {
                    Object inObject = is.readObject();
                    if (inObject instanceof String) {
                        user.setLastMessage((String) inObject);
                        if (user.getLastMessage().equals(user.getAbortConnectString())) {
                            user.setLastMessage(CLIENT_HAS_LEFT);
                            isRunning = false;
                        }
                        c.broadCast(user);
                        c.broadCastAvatar(user);
                        c.listMessage(user);
                    }
                }
                if (!broadCastString.isEmpty()) {
                    os.writeObject(broadCastString);
                    broadCastString = "";
                }
            }
            in.close();
            os.close();
            is.close();
            out.close();
            socket.close();
            c.threadFinished(user);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ZenServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
