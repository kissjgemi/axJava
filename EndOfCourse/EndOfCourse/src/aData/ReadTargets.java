/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aData;

import aBasis.TargetObject;
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
public class ReadTargets {

    public static List<TargetObject> inputFromFile() {
        List<TargetObject> toReturn = new ArrayList<>();
        Scanner SC = new Scanner(
                ReadTargets.class.getResourceAsStream(DATA_SOURCE), CODE_PAGE);
        String line = "";
        String[] data;
        try {
            while (SC.hasNext()) {
                line = SC.nextLine();
                data = line.split(REGEX);
                if (data.length == 3) {
                    System.out.println("> " + line);
                } else {
                    throw MyException("Hibás adatsor...");
                }
                TargetObject target = new TargetObject(data[0],
                        Integer.valueOf(data[1]),
                        Integer.valueOf(data[2]));
                toReturn.add(target);
            }
        } catch (NumberFormatException e) {
            Logger.getLogger(ReadTargets.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Hibás adatsor" + line);
        } catch (java.lang.Exception ex) {
            Logger.getLogger(ReadTargets.class.getName()).log(Level.SEVERE, null, ex);
        }
        return toReturn;
    }

    private static Exception MyException(String str) {
        return new Exception(str);
    }
}
