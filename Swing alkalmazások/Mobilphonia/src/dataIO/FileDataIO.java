package dataIO;

import basis.Gadget;
import basis.Human;
import basis.Mobilphone;
import basis.Smartphone;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author KissJGabi
 */
public class FileDataIO implements DataIO {

    private String humanSource;
    private String gadgetSource;
    private final String CODE_PAGE = "UTF-8";
    private final String REGEX = ";";

    public FileDataIO(String humanSource, String gadgetSource) {
        this.humanSource = humanSource;
        this.gadgetSource = gadgetSource;
    }

    @Override
    public List<Human> humanList() throws Exception {
        List<Human> humans = new ArrayList<>();
        try (InputStream ins = this.getClass().getResourceAsStream(humanSource);
                Scanner sc = new Scanner(ins, CODE_PAGE)) {
            String line, datas[];
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                datas = line.split(REGEX);
                humans.add(new Human(datas[0], datas[1]));
            }
        }
        return humans;
    }

    @Override
    public List<Gadget> gadgetList() throws Exception {
        List<Gadget> gadgets = new ArrayList<>();
        try (InputStream ins = this.getClass().getResourceAsStream(gadgetSource);
                Scanner sc = new Scanner(ins, CODE_PAGE)) {
            String line, datas[];
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                datas = line.split(REGEX);
                if (datas.length == 2) {
                    gadgets.add(new Smartphone(datas[0], datas[1]));
                } else {
                    gadgets.add(new Mobilphone(datas[0]));
                }
            }
        }
        return gadgets;
    }
}
