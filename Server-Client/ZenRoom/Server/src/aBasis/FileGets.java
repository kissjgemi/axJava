/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b6dmin
 */
public class FileGets extends Thread {

    private boolean transfered;

    public boolean isTransfered() {
        return transfered;
    }

    private byte[] byteArray;
    private String fileName;

    public byte[] getArray() {
        return byteArray;
    }

    public String getFileName() {
        return fileName;
    }

    private final Socket socket;

    public FileGets(Socket socket) {
        this.socket = socket;
        transfered = false;
    }

    @Override
    public void run() {
        System.out.println("ZenServerThread Thread started: ");
        try (ObjectOutputStream os
                = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream is
                = new ObjectInputStream(socket.getInputStream())) {
            fileName = (String) is.readObject();
            System.out.println("FileGets() fileName: " + fileName);
            int controlLength = (int) is.readObject();
            is.read(byteArray);
            os.writeInt(byteArray.length);
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
