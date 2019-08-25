/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aData;

import static aBasis.Global.*;
import aBasis.LittleThing;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author b6dmin
 */
public class ReadDatas {

    public static List<LittleThing> inputFromFile() {
        List<LittleThing> toReturn = new ArrayList<>();
        Scanner SC = new Scanner(
                ReadDatas.class.getResourceAsStream(DATA_SOURCE), CODE_PAGE);
        Image image;
        String line = "";
        String[] data;
        try {
            while (SC.hasNext()) {
                line = SC.nextLine();
                data = line.split(REGEX);
                if (data.length == 3) {
                    System.out.print("> " + line);
                } else {
                    throw MyException("Hibás adatsor...");
                }
                LittleThing LittleThing = new LittleThing(data[0],
                        Integer.valueOf(data[2]),
                        new ImageIcon(ReadDatas.class.
                                getResource(SPRITES_URL + data[1])).getImage());
                toReturn.add(LittleThing);
            }
        } catch (NumberFormatException e) {
            Logger.getLogger(ReadDatas.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Hibás adatsor" + line);
        } catch (java.lang.Exception ex) {
            Logger.getLogger(ReadDatas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return toReturn;
    }

    private static Exception MyException(String str) {
        return new Exception(str);
    }
}
