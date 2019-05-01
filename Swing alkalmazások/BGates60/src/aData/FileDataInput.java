package aData;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author KissJGabi
 */
public class FileDataInput implements DataInput {

    private String inputSource;
    private final String CODE_PAGE = "UTF-8";
    private final String REGEX = ";";

    public FileDataInput(String inputSource) {
        this.inputSource = inputSource;
    }

    @Override
    public List<Object> inputList() throws Exception {
        List<Object> list = new ArrayList<>();
        try (InputStream ins = this.getClass().getResourceAsStream(inputSource);
                Scanner sc = new Scanner(ins, CODE_PAGE)) {
            String line, datas[];
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                datas = line.split(REGEX);
                list.add(datas);
            }
        }
        return list;
    }
}
