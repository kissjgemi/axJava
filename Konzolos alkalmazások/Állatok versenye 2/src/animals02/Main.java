package animals02;

import java.io.*;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author KissJGabi
 */
public class Main {

    private final String CHAR_SET = "UTF-8";
    private final List<Animal> ANIMALS = new ArrayList<>();
    private static RandomAccessFile f;
    private final String DATASOURCE = "/datas/animals.txt";
    private final String DATARESULTS = "results/results2019.txt";
    private final int thisYear = Calendar.getInstance().get(Calendar.YEAR);
    private final int AGELIMIT = 10;
    private final int BEAUTYLIMIT = 11;
    private final int BEHAVIORLIMIT = 11;
    private final int RELATIONSHIPLIMIT = 6;

    private void insertDatas() {
        try (InputStream ins = this.getClass().getResourceAsStream(DATASOURCE);
                Scanner sc = new Scanner(ins, CHAR_SET)) {
            String[] datas;
            String line;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                try {
                    if (!line.isEmpty()) {
                        datas = line.split(";");
                        switch (datas.length) {
                            case 3:
                                ANIMALS.add(new Cat(datas[0],
                                        Integer.parseInt(datas[1]),
                                        Boolean.parseBoolean(datas[2])));
                                break;
                            case 2:
                                ANIMALS.add(new Hund(datas[0],
                                        Integer.parseInt(datas[1])));
                                break;
                            default:
                                throw new Exception();
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Hibás a(z) " + line + " adatsor");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            System.out.println("Hibás fájlmegadás");
            System.exit(-1);
        }
    }

    private static void deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
    }

    private static void appendFile(String fileName, String mode, String str) {
        try {
            f = new RandomAccessFile(fileName, mode);
            f.seek(f.length());//a fájlmutatót a fájl végére mozgatja
            f.writeBytes(str + "\n");
            f.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    private void staticDatas() {
        Animal.setThisYear(thisYear);
        Animal.setAgeLimit(AGELIMIT);
    }

    private void rivals() {
        System.out.println("\nA verseny résztvevői:");
        for (Animal animal : ANIMALS) {
            System.out.println(animal);
        }
    }

    private void hundHostTourney() {
        for (Animal animal : ANIMALS) {
            if (animal instanceof Hund) {
                ((Hund) animal).takeRelationshipPoint(
                        (int) (Math.random() * RELATIONSHIPLIMIT));
            }
        }
    }

    private void tourney() {
        int beauty, behavior;
        for (Animal animal : ANIMALS) {
            beauty = (int) (Math.random() * BEAUTYLIMIT);
            behavior = (int) (Math.random() * BEHAVIORLIMIT);
            animal.takePoints(beauty, behavior);
        }
    }

    private void results(String label) {
        System.out.println(label);
        appendFile(DATARESULTS, "rw", label);
        for (Animal animal : ANIMALS) {
            String str = String.format("%-10s %2d pont", animal.getName(), animal.countPoints());
            System.out.println(str);
            appendFile(DATARESULTS, "rw", str);
        }
    }

    private void bestScores() {
        int max = 0;
        for (Animal animal : ANIMALS) {
            if (animal.countPoints() > max) {
                max = animal.countPoints();
            }
        }
        System.out.printf("\nA legjobb versenyző(k) %d ponttal:\n", max);
        for (Animal animal : ANIMALS) {
            if (animal.countPoints() == max) {
                System.out.println(animal.getName());

            }
        }
    }

    public class orderRivals implements Comparator<Animal> {

        @Override
        public int compare(Animal o1, Animal o2) {
            return o2.countPoints() - o1.countPoints();
        }
    }

    private void orderByScore() {
        ANIMALS.sort(new orderRivals());
    }

    private void start() {
        staticDatas();
        insertDatas();
        rivals();
        hundHostTourney();
        tourney();
        deleteFile(DATARESULTS);
        results("\nA verseny eredménye:");
        bestScores();
        orderByScore();
        results("\nPontszám szerint rendezve:");
    }

    public static void main(String[] args) {
        new Main().start();
    }

}
