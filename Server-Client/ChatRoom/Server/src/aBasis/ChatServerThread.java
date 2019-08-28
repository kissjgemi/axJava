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
public class ChatServerThread extends Thread {

    private static Control c;

    public static void setControl(Control c) {
        ChatServerThread.c = c;
    }

    public boolean isIsRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    private boolean isRunning;

    private final Socket socket;
    private final ChatUser user;

    private String broadCastString;

    public void setBroadCastString(String broadCastString) {
        this.broadCastString = broadCastString;
    }

    ChatServerThread(Socket socket) {
        this.socket = socket;
        this.isRunning = false;
        this.user = new ChatUser();
        broadCastString = "";
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
        c.broadCast(user);
        out.println(user.getAbortConnectString());
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
                if (in.ready()) {
                    user.setLastMessage(in.readLine());
                    if (user.getLastMessage().equals(user.getAbortConnectString())) {
                        isRunning = false;
                    } else {
                        c.listMessage(user);
                        c.broadCast(user);
                    }
                }
                if (!broadCastString.isEmpty()) {
                    out.println(broadCastString);
                    broadCastString = "";
                }
            }
            in.close();
            out.close();
            socket.close();
            user.setLastMessage(CLIENT_HAS_LEFT);
            c.broadCast(user);
            c.listMessage(user);
            c.threadFinished(user);
        } catch (IOException ex) {
            Logger.getLogger(ChatServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
