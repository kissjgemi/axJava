package aDataAccess;

import static aBasis.Global.*;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author KissJGabi
 */
public class MyDFAO implements MainDataFileAccessObject {

    private String inputSource;
    private File dataFile;

    private enum Source {
        FIXED, CHOOSER
    }

    private static Source source;

    public MyDFAO(String inputSource) {
        this.inputSource = inputSource;
        source = Source.FIXED;
    }

    public MyDFAO(File dataFile) {
        this.dataFile = dataFile;
        source = Source.CHOOSER;
    }

    @Override
    public List<Object> inputList() throws Exception {
        List<Object> list = new ArrayList<>();
        Scanner sc = null;
        switch (source) {
            case FIXED:
                InputStream ins = this.getClass().getResourceAsStream(inputSource);
                sc = new Scanner(ins, CODE_PAGE);
                break;
            case CHOOSER:
                sc = new Scanner(dataFile, CODE_PAGE);
                break;
            default:
                throw new Exception();
        }
        String line, datas[];
        if (sc != null) {
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                datas = line.split(REGEX);
                list.add(datas);
            }
        }
        return list;
    }
}
