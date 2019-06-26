/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aData;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author KissJGabi
 */
public class InputFromFile implements InputData {

    private String inputSource;
    private final String CODE_PAGE = "UTF-8";
    private File dataFile;
    private final String REGEX = ";";

    private enum Source {
        FIXED, CHOOSER
    }
    private Source source;

    public InputFromFile(String inputSource) {
        this.inputSource = inputSource;
        source = Source.FIXED;
    }

    public InputFromFile(File dataFile) {
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
