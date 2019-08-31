/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aData;

import static aGlobal.Global.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b6dmin
 */
public class ReadList {

    public static List<String> inputFromFile() {
        List<String> toReturn = new ArrayList<>();
        Scanner SC = new Scanner(
                ReadTargets.class.getResourceAsStream(LIST_SOURCE), CODE_PAGE);
        String line;
        try {
            while (SC.hasNext()) {
                line = SC.nextLine();
                if (line.isEmpty()) {
                    throw MyException("Hibás adatsor...");
                } else {
                    toReturn.add(line);
                    System.out.println("> " + line);
                }
            }
        } catch (java.lang.Exception ex) {
            Logger.getLogger(ReadTargets.class.getName()).log(Level.SEVERE, null, ex);
        }
        return toReturn;
    }

    private static Exception MyException(String str) {
        return new Exception(str);
    }
}
