/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aSource;

import static aBasis.Global.*;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.swing.ImageIcon;

/**
 *
 * @author b6dmin
 */
public class ReadSpriteImages {

    public static List<Image> inputFromFolder() {
        List<Image> toReturn = new ArrayList<>();
        Scanner SC = new Scanner(
                ReadSpriteImages.class.getResourceAsStream(SPRITES_URL), CODE_PAGE);
        String str;
        while (SC.hasNext()) {
            if ((str = SC.nextLine()) != null) {
                if (!str.isEmpty()) {
                    System.out.println("/ " + str);
                    toReturn.add(new ImageIcon(ReadSpriteImages.class.
                            getResource(SPRITES_URL + str)).getImage());
                }
            }
        }
        if (toReturn.isEmpty()) {
            CodeSource src = ReadSpriteImages.class.
                    getProtectionDomain().getCodeSource();
            if (src != null) {
                ZipInputStream zip = null;
                try {
                    URL jar = src.getLocation();
                    zip = new ZipInputStream(jar.openStream());
                    while (true) {
                        ZipEntry e = zip.getNextEntry();
                        if (e == null) {
                            break;
                        }
                        String name = e.getName();
                        if (name.startsWith(SPRITES_PATH)) {
                            System.out.println("> " + name);
                            String sub = name.substring(
                                    name.lastIndexOf(File.separator) + 1);
                            System.out.println(File.separator + ": " + sub);
                            if (sub.length() > 0) {
                                toReturn.add(new ImageIcon(ReadSpriteImages.class.
                                        getResource(SPRITES_URL + sub)).getImage());
                            }
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ReadSpriteImages.class.
                            getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        if (zip != null) {
                            zip.close();
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(ReadSpriteImages.class.
                                getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                try {
                    System.out.println("> " + SPRITE_FOLDER.getCanonicalPath());
                    File[] list = SPRITE_FOLDER.listFiles();
                    if (list == null) {

                    } else {
                        for (File fileEntry : list) {
                            if (fileEntry.isDirectory()) {

                            } else {
                                toReturn.add(new ImageIcon(
                                        ReadSpriteImages.class.getResource(
                                                SPRITES_URL + fileEntry.
                                                        getName())).getImage());
                            }
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ReadSpriteImages.class.
                            getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return toReturn;
    }
}
