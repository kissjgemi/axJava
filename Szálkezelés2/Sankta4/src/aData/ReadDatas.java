/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aData;

import static aBasis.Global.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author b6dmin
 */
public class ReadDatas {

    public static List<String> inputFromFile() {
        List<String> toReturn = new ArrayList<>();
        Scanner SC = new Scanner(
                ReadDatas.class.getResourceAsStream(DATA_SOURCE), CODE_PAGE);
        String str;
        while (SC.hasNext()) {
            if ((str = SC.nextLine()) != null) {
                if (!str.isEmpty()) {
                    System.out.println("> " + str);
                    toReturn.add(str);
                }
            }
        }
        return toReturn;
    }
}
