/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b6dmin
 */
public class FileSend extends Thread {

    private boolean transfered;

    public boolean isTransfered() {
        return transfered;
    }

    private byte[] byteArray;
    private final String fileName;

    private final Socket socket;

    public FileSend(Socket socket, String filePath, String fileName) {
        this.socket = socket;
        try (RandomAccessFile f = new RandomAccessFile(filePath, "rw")) {
            f.seek(0);
            int fileLength = (int) f.length();
            byteArray = new byte[fileLength];
            int controlLength = f.read(byteArray);
            System.out.println("FileSend() filelength: "
                    + fileLength
                    + " - "
                    + controlLength
                    + " = "
                    + (fileLength - controlLength));
        } catch (IOException ex) {
            Logger.getLogger(FileSend.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.fileName = fileName;
        this.transfered = false;
    }

    @Override
    public void run() {
        System.out.println("ZenServerThread Thread started: ");
        try (ObjectOutputStream os
                = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream is
                = new ObjectInputStream(socket.getInputStream())) {
            os.writeObject(fileName);
            os.writeObject(byteArray.length);
            os.write(byteArray);
            int controlLength = is.readInt();
            if (controlLength != byteArray.length) {
                throw new Exception("hibás átvitel...");
            }
        } catch (IOException ex) {
            Logger.getLogger(FileSend.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        transfered = true;
    }
}
