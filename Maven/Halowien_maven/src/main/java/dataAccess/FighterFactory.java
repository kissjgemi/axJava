/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccess;

import aBasis.Ensign;
import aBasis.Fighter;
import aBasis.Paladin;
import aBasis.PaladinCommander;
import aBasis.PraetorianCommander;

/**
 *
 * @author b6dmin
 */
public class FighterFactory {

    private static FighterFactory instance;

    private FighterFactory() {
    }

    public static FighterFactory getInstance() {
        if (instance == null) {
            instance = new FighterFactory();
        }
        return instance;
    }

    public Fighter getFighter(String name, String rank) {
        switch (rank) {
            case "lovas parancsnok":
                return new PaladinCommander(name);
            case "gyalogos parancsnok":
                return new PraetorianCommander(name);
            case "lovas":
                return new Paladin(name);
            case "gyalogos":
                return new PaladinCommander(name);
            case "zaszlos":
                return new Ensign(name);
            default:
                return new Fighter(name);
        }
    }
}
