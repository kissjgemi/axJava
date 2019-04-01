package control;

import basis.Dancer;
import basis.DancingGirl;
import basis.DancingGuy;
import java.io.*;
import java.util.*;

/**
 *
 * @author KissJGabi
 */
public class Main {

    private static final String CHAR_SET = "UTF-8";

    private static RandomAccessFile f;
    private static final String MODE_RW = "rw";
    private final String DATASOURCE = "/datas/balozok.txt";
    private final String DATARESULTS = "results/results.txt";

    private final List<Dancer> DANCERS = new ArrayList<>();

    private final int CONSUMPTION_LIMIT = 10;
    private final double PARTYEND_CHANCE = .1;

    private static final Scanner SC = new Scanner(System.in, ("Cp1250"));

    private void enter2Party() {
        try (InputStream ins = this.getClass().getResourceAsStream(DATASOURCE);
                Scanner sc = new Scanner(ins, CHAR_SET)) {
            String[] datas;
            String line;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                try {
                    if (!line.isEmpty()) {
                        datas = line.split(";");
                        switch (datas[1]) {
                            case "lány":
                                DANCERS.add(new DancingGirl(datas[0],
                                        Integer.parseInt(datas[2])));
                                break;
                            case "fiú":
                                DANCERS.add(new DancingGuy(datas[0],
                                        Integer.parseInt(datas[2])));
                                break;
                            default:
                                throw new Exception();
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Hibás a(z) " + line + " adatsor");
                }
            }
        } catch (Exception ex) {
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
            str = str.replace("ő", String.valueOf((char) 245));
            str = str.replace("Ő", String.valueOf((char) 213));
            str = str.replace("ű", String.valueOf((char) 251));
            str = str.replace("Ű", String.valueOf((char) 219));
            f.writeBytes(str + "\n");
            f.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    private void printLine(String str) {
        System.out.println(str);
        appendFile(DATARESULTS, MODE_RW, str);
    }

    private void members(String str) {
        printLine(str);
        String tempFormat = "%-8s: %3d tánc, %4d Ft fogyasztás";
        for (Dancer dancer : DANCERS) {
            str = String.format(tempFormat,
                    dancer.getName(),
                    dancer.getDanceNumber(),
                    dancer.getMoneySpent());
            if (dancer instanceof DancingGirl) {
                str += " " + ((DancingGirl) dancer).getNumberOfVotes()
                        + " szavazat";
            }
            printLine(str);
        }
        printLine("");
    }

    private void income(String str) {
        printLine(str);
        int sum = 0;
        for (Dancer dancer : DANCERS) {
            sum += dancer.getMoneySpent();
        }
        printLine("\t" + sum + " Ft\n");
    }

    private void consumption(String str) {
        printLine(str);
        for (Dancer dancer : DANCERS) {
            printLine(String.format("%-8s:", dancer.getName())
                    + " " + dancer.getMoneySpent()
                    + " Ft-ot költött");
        }
        printLine("");
    }

    private void votes(String str) {
        printLine(str);
        for (Dancer dancer : DANCERS) {
            if (dancer instanceof DancingGirl) {
                printLine(String.format("%-8s:", dancer.getName())
                        + ((DancingGirl) dancer).getNumberOfVotes()
                        + " szavazatot kapott");
            }
        }
        printLine("");
    }

    private void promQueen(String str) {
        int max = 0;
        for (Dancer dancer : DANCERS) {
            if (dancer instanceof DancingGirl
                    && ((DancingGirl) dancer).getNumberOfVotes() > max) {
                max = ((DancingGirl) dancer).getNumberOfVotes();
            }
        }
        printLine(str + max + " szavazattal:");
        for (Dancer dancer : DANCERS) {
            if (dancer instanceof DancingGirl
                    && ((DancingGirl) dancer).getNumberOfVotes() == max) {
                printLine(dancer.getName());
            }
        }
        printLine("");
    }

    private int randomIndex() {
        return (int) (Math.random() * DANCERS.size());
    }

    private void takeVote(String str) {
        int index = randomIndex();
        DancingGirl girl;
        printLine(str);
        if (DANCERS.get(index) instanceof DancingGirl) {
            girl = (DancingGirl) DANCERS.get(index);
            Dancer dancer = DANCERS.get(randomIndex());
            dancer.vote(girl);
            printLine(dancer.getName() + " szavazott");
            printLine(girl.getName() + " szavazetot kapott");
        } else {
            printLine("Akárki is szavazott");
            printLine("Na-ne, hiszen " + DANCERS.get(index).getName() + " fiú");
        }
    }

    private void takeDance() {
        int index = randomIndex();
        DANCERS.get(index).oneDance();
        printLine(DANCERS.get(index).getName() + " táncol");
    }

    private void buffet() {
        int money = (int) (Math.random() * CONSUMPTION_LIMIT + 1);
        Dancer dancer = DANCERS.get(randomIndex());
        String str = dancer.getName();
        if (dancer.consumption(money)) {
            str += " rendelt ";
            switch (money) {
                case 1:
                    str += "egy ásványvizet";
                    break;
                case 2:
                    str += "egy üdítőt";
                    break;
                case 3:
                    str += "egy szedvicset";
                    break;
                case 4:
                    str += "két üdítőt";
                    break;
                case 5:
                    str += "egy rövidet";
                    break;
                case 6:
                    str += "egy perecet és fröccsöt";
                    break;
                case 7:
                    str += "sósmogyorót és rövedet kisérővel";
                    break;
                case 8:
                    str += "egy koktélt";
                    break;
                default:
                    str += "egy kört a barátainak";
                    break;
            }
        } else {
            str += " szomorú, mert elfogyott a pénze";
        }
        printLine(str);
    }

    private void partySimulation(String str) {
        boolean theEnd = false;
        printLine(str);
        int count = 0;
        do {
            takeVote("Szavazás:");
            for (int ii = 0; ii < randomIndex(); ii++) {
                takeDance();
            }
            for (int ii = 0; ii < randomIndex(); ii++) {
                buffet();
            }
            if (Math.random() < PARTYEND_CHANCE && count > 3) {
                theEnd = true;
            }
            count++;
        } while (!theEnd);
        printLine("\tVége a bálnak\n");
    }

    private void orderByNumberOfDances(String str) {
        OrderBy.setHowTo(OrderBy.DECREASING,
                OrderBy.Principle.NUMBEROFDANCES);
        Collections.sort(DANCERS, new OrderBy());
        members(str);
    }

    private void orderByConsumption(String str) {
        OrderBy.setHowTo(OrderBy.INCREASING,
                OrderBy.Principle.CONSUMPTION);
        Collections.sort(DANCERS, new OrderBy());
        members(str);
    }

    private void orderByNumberOfVotes(String str) {
        OrderBy.setHowTo(OrderBy.DECREASING,
                OrderBy.Principle.NUMBEROFVOTES);
        Collections.sort(DANCERS, new OrderBy());
        members(str);
    }

    private void ladyLookingFor(String str) {
        String name;
        char c;
        do {
            printLine(str);
            name = SC.nextLine();
            appendFile(DATARESULTS, MODE_RW, name);
            DancingGuy guy = new DancingGuy(name, 0);
            if (DANCERS.contains(guy)) {
                printLine(name + " táncra fel'");
            } else {
                printLine(name + " nem kérhető fel...");
            }
            do {
                printLine("\nKeressek még valakit? (i/n)");
                c = SC.nextLine().charAt(0);
                appendFile(DATARESULTS, MODE_RW, String.valueOf(c));
            } while (!"in".contains(String.valueOf(c)));
        } while ("i".equals(String.valueOf(c)));
    }

    private void start() {
        enter2Party();
        deleteFile(DATARESULTS);
        members("\nA bál résztvevői:");
        partySimulation("\tBál szimuláció:");
        income("A sulibuli büfé bevétele:");
        consumption("A bulizók fogyasztása:");
        votes("A lányok szavazatai:");
        promQueen("A legnépszerűbb lány(ok): ");
        orderByNumberOfDances("A táncok száma szerint rendezve:");
        orderByConsumption("A fogyasztás szerint rendezve:");
        orderByNumberOfVotes("A szavazatok száma szerint rendezve:");
        ladyLookingFor("Hölgyem Ön kit választ?");
    }

    public static void main(String[] args) {
        new Main().start();
    }

}
