/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aData;

import static aBasis.Global.*;
import aBasis.User;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b6dmin
 */
public class ReadUsers {

    public static List<User> inputFromFile() {
        List<User> toReturn = new ArrayList<>();
        Scanner SC = new Scanner(
                ReadUsers.class.getResourceAsStream(DATA_SOURCE), CODE_PAGE);
        Image image;
        String in, str;
        int x = 0, y = 0;
        int blue = 0, red = 0, yellow = 0;
        try {
            while (SC.hasNext()) {
                str = SC.nextLine();
                if (!str.isEmpty()) {
                    System.out.print("> " + str);
                } else {
                    throw MyException("Hibás adatsor... nevet kér");
                }
                if ((in = SC.nextLine()) != null) {
                    if (!in.isEmpty()) {
                        System.out.print("; " + in);
                        x = Integer.valueOf(in);
                    } else {
                        throw MyException("Hibás adatsor... x-koordinátát kér");
                    }
                }
                if ((in = SC.nextLine()) != null) {
                    if (!in.isEmpty()) {
                        System.out.print("; " + in);
                        y = Integer.valueOf(in);
                    } else {
                        throw MyException("Hibás adatsor... y-koordinátát kér");
                    }
                }
                int index = (int) (Math.random() * USER_GROUPS.length);
                USER_GROUP_NAME groupname;
                switch (index) {
                    case 0: {
                        groupname = USER_GROUP_NAME.BLUE;
                    }
                    break;
                    case 1: {
                        groupname = USER_GROUP_NAME.RED;
                        break;
                    }
                    case 2: {
                        groupname = USER_GROUP_NAME.YELLOW;
                        break;
                    }
                    default:
                        groupname = USER_GROUP_NAME.BLUE;
                }
                switch (groupname) {
                    case BLUE: {
                        if (blue > 5 && red < 2) {
                            red++;
                            groupname = USER_GROUP_NAME.RED;
                        } else if (blue > 5 && yellow < 2) {
                            yellow++;
                            groupname = USER_GROUP_NAME.YELLOW;
                        } else {
                            blue++;
                        }
                        break;
                    }
                    case RED: {
                        if (red > 5 && blue < 2) {
                            blue++;
                            groupname = USER_GROUP_NAME.BLUE;
                        } else if (red > 5 && yellow < 2) {
                            yellow++;
                            groupname = USER_GROUP_NAME.YELLOW;
                        } else {
                            red++;
                        }
                        break;
                    }
                    case YELLOW: {
                        if (yellow > 5 && red < 2) {
                            red++;
                            groupname = USER_GROUP_NAME.RED;
                        } else if (yellow > 5 && blue < 2) {
                            blue++;
                            groupname = USER_GROUP_NAME.BLUE;
                        } else {
                            yellow++;
                        }
                        break;
                    }
                }
                System.out.println("; " + groupname.toString());
                User user = new User(str, x, y,
                        USER_GROUPS[index], groupname);
                toReturn.add(user);
            }
        } catch (NumberFormatException e) {
            Logger.getLogger(ReadUsers.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Hibás adatsor -> számot kér");
        } catch (java.lang.Exception ex) {
            Logger.getLogger(ReadUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return toReturn;
    }

    private static Exception MyException(String str) {
        return new Exception(str);
    }
}
