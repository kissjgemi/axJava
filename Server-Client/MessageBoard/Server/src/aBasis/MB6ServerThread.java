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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b6dmin
 */
public class MB6ServerThread extends Thread {

    private static Control c;

    public static void setControl(Control c) {
        MB6ServerThread.c = c;
    }

    public boolean isIsRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    private boolean isRunning;

    private final Socket socket;
    private final MB6User user;

    MB6ServerThread(Socket socket) {
        this.socket = socket;
        this.isRunning = false;
        this.user = new MB6User();
        System.out.println("MB6ServerThread()");
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

            user.setLocale(in.readLine());
            Locale locale = new Locale(user.getLocale());
            user.setUserName(in.readLine());

            LocalDateTime today = LocalDateTime.now();
            DateTimeFormatter dtf
                    = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
            dtf = dtf.withLocale(locale);

            String nowString = today.format(dtf);
            out.println(nowString);
            user.setAbortConnectString(nowString);
            System.out.println("abortThread> " + nowString);

            c.addUser(user);
            while (isRunning) {
                user.setLastMessage(in.readLine());
                if (user.getLastMessage().equals(user.getAbortConnectString())) {
                    isRunning = false;
                } else {
                    c.addMessage(user);
                }
            }
            in.close();
            out.close();
            socket.close();
            user.setLastMessage("kilépett");
            c.addMessage(user);
            c.threadFinished(user);
        } catch (IOException ex) {
            Logger.getLogger(MB6ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
