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
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b6dmin
 */
public class GDTServer {

    private static final int PORT = 44444;
    private static boolean online = true;

    private void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started...");

            while (online) {
                Socket socket = serverSocket.accept();

                MyServerThread myThread = new MyServerThread(socket);
                myThread.start();
            }
        }
    }

    class MyServerThread extends Thread {

        private Socket socket;

        MyServerThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(
                            socket.getOutputStream(), true)) {

                LocalDate today = LocalDate.now();

                LocalDate xmass = LocalDate.of(2019, 12, 24);
                LocalDateTime schoolBegin = LocalDateTime.of(2019, 9, 1, 8, 00);

                String language = in.readLine();
                Locale locale = new Locale(language);

                DateTimeFormatter dtf
                        = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
                dtf = dtf.withLocale(locale);

                String todayString = today.format(dtf);

                out.println(todayString);

                Period toXmass = Period.between(today, xmass);
                String toXmassString = String.format(
                        "Karácsonyig van még %d hónap és %d nap",
                        toXmass.getMonths(), toXmass.getDays());

                out.println(toXmassString);

                LocalDateTime dateTimeNow = LocalDateTime.now();
                LocalDateTime temp = LocalDateTime.from(dateTimeNow);

                long days = temp.until(schoolBegin, ChronoUnit.DAYS);
                temp = temp.plusDays(days);
                long hours = temp.until(schoolBegin, ChronoUnit.HOURS);
                temp = temp.plusHours(hours);
                long minutes = temp.until(schoolBegin, ChronoUnit.MINUTES);

                String schoolBeginString = String.format(
                        "Az iskolaév %d nap, %d óra és %d perc múlva indul",
                        days, hours, minutes);

                out.println(schoolBeginString);
            } catch (IOException ex) {
                Logger.getLogger(GDTServer.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(GDTServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            new GDTServer().start();
        } catch (IOException ex) {
            Logger.getLogger(GDTServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
