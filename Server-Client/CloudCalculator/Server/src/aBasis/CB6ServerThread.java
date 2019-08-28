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
public class CB6ServerThread extends Thread {

    private static Control c;

    public static void setControl(Control c) {
        CB6ServerThread.c = c;
    }

    public boolean isIsRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    private boolean isRunning;

    private final Socket socket;
    private final CB6User user;

    CB6ServerThread(Socket socket) {
        this.socket = socket;
        this.isRunning = false;
        this.user = new CB6User();
        System.out.println("MB6ServerThread()");
    }

    private void initUser(BufferedReader in, PrintWriter out) throws IOException {
        user.setLocale(in.readLine());
        Locale locale = new Locale(user.getLocale());
        user.setUserName(in.readLine());

        LocalDateTime today = LocalDateTime.now();
        String DATE_FORMATTER = ": yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter dtf
                = DateTimeFormatter.ofPattern(DATE_FORMATTER, locale);

        String nowString = today.format(dtf);
        user.setAbortConnectString(user.getUserName() + nowString);
        System.out.println("abortThread> " + user.getUserName() + nowString);

        c.addUser(user);
        user.setLastMessage(CLIENT_CONNECTED);
        out.println(user.getAbortConnectString());
    }

    private String serverAnswer() {
        String toReturn = "";
        int question;
        try {
            question = Integer.valueOf(user.getLastMessage());
            toReturn += question * question;
        } catch (NumberFormatException nfe) {
            toReturn = "Error: " + nfe.getMessage();
            System.out.println(toReturn);
        }
        return toReturn;
    }

    @Override
    public void run() {
        System.out.println("Thread started: ");
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream(),
                        StandardCharsets.UTF_8));
                PrintWriter out = new PrintWriter(
                        socket.getOutputStream(), true)) {

            isRunning = true;

            initUser(in, out);
            c.listMessage(user);

            while (isRunning) {
                user.setLastMessage(in.readLine());
                if (user.getLastMessage().equals(user.getAbortConnectString())) {
                    isRunning = false;
                } else {
                    c.listMessage(user);
                    user.setLastMessage(serverAnswer());
                    out.println(user.getLastMessage());
                    c.listMessage(user);
                }
            }
            in.close();
            out.close();
            socket.close();
            user.setLastMessage(CLIENT_HAS_LEFT);
            c.listMessage(user);
            c.threadFinished(user);
        } catch (IOException ex) {
            Logger.getLogger(CB6ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
