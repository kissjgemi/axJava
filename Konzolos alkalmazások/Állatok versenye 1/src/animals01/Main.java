/*
 * git:
 * https://github.com/kissjgabi/Animals01.git
 * Achs Ágnes - Jáva feladatok
 * https://sites.google.com/view/jatekosjavafeladatok/konzolos-alkalmaz%C3%A1sok/%C3%A1llatok-versenye-1
 */
package animals01;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KjG
 */
public class Main {

    private final List<Animal> ANIMALS = new ArrayList<>();
    private static RandomAccessFile f;
    private final String dataSource = "sources/animals.txt";
    private final String dataResults = "results/results2019.txt";
    private final int thisYear = Calendar.getInstance().get(Calendar.YEAR);
    private final int ageLimit = 10;
    private final int beautyLimit = 11;
    private final int behaviorLimit = 11;

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
        Animal.setAgeLimit(ageLimit);
    }

    private void insertDatas() {
        try {
            Scanner sc = new Scanner(new File(dataSource));
            String[] adatok;
            String sor;
            while (sc.hasNextLine()) {
                sor = sc.nextLine();
                if (!sor.isEmpty()) {
                    adatok = sor.split(";");
                    String name = adatok[0];
                    int birthYear = Integer.parseInt(adatok[1]);
                    ANIMALS.add(new Animal(name, birthYear));
                }
            }
        } catch (FileNotFoundException fne) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, fne);
        }
    }

    private void rivals() {
        System.out.println("\nA verseny résztvevői:");
        for (Animal animal : ANIMALS) {
            System.out.println(animal);
        }
    }

    private void tourney() {
        int beauty, behavior;
        for (Animal animal : ANIMALS) {
            beauty = (int) (Math.random() * beautyLimit);
            behavior = (int) (Math.random() * behaviorLimit);
            animal.countResult(beauty, behavior);
        }
    }

    private void results(String label) {
        System.out.println(label);
        appendFile(dataResults, "rw", label);
        for (Animal animal : ANIMALS) {
            String str = String.format("%-10s %2d pont", animal.getName(), animal.getResult());
            System.out.println(str);
            appendFile(dataResults, "rw", str);
        }
    }

    private void bestScores() {
        int max = 0;
        for (Animal animal : ANIMALS) {
            if (animal.getResult() > max) {
                max = animal.getResult();
            }
        }
        System.out.printf("\nA legjobb versenyző(k) %d ponttal:\n", max);
        for (Animal animal : ANIMALS) {
            if (animal.getResult() == max) {
                System.out.println(animal.getName());
            }
        }
    }

    public class orderRivals implements Comparator<Animal> {

        @Override
        public int compare(Animal o1, Animal o2) {
            return o2.getResult() - o1.getResult();
        }
    }

    private void orderByScore() {
        ANIMALS.sort(new orderRivals());
    }

    private void start() {
        staticDatas();
        insertDatas();
        rivals();
        tourney();
        deleteFile(dataResults);
        results("\nA verseny eredménye:");
        bestScores();
        orderByScore();
        results("\nPontszám szerint rendezve:");
    }

    public static void main(String[] args) {
        new Main().start();
    }

}
