package aControl;

import aBasis.Guard;
import aBasis.Participant;
import aBasis.Prisoner;
import aData.FileInput;
import aData.InputData;
import aSurface.SimulationPanel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.nio.cs.ext.GB18030;

/**
 *
 * @author KissJGabi
 */
public class Control {

    private List<Guard> guardList = new ArrayList<>();
    private List<Prisoner> prisonerList = new ArrayList<>();
    private List<Guard> evils;

    SimulationPanel simulationPanel;

    private final int EVIL_LIMIT = 15;
    private final int AGRESSION_MAX = 5;

    public Control(SimulationPanel simulationPanel) {
        this.simulationPanel = simulationPanel;
    }

    private final String PERSONS_SOURCE = "/aData/szemelyek.txt";

    private void staticDatas() {
        Guard.setLimit(EVIL_LIMIT);
        Participant.setPastDays(0);
    }

    private void inputFromFile() {
        InputData inputData = new FileInput(PERSONS_SOURCE);
        String datas[];
        try {
            for (Object o : inputData.inputList()) {
                datas = (String[]) o;
                if (Math.random() < 0.4) {
                    guardList.add(new Guard(datas[0]));
                } else {
                    prisonerList.add(new Prisoner(datas[0]));
                }
            }
        } catch (Exception e) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void start() {
        staticDatas();
        inputFromFile();
        simulationPanel.guardList(guardList);
        simulationPanel.prisonerList(prisonerList);
    }

    public void restart() {
        guardList = new ArrayList<>();
        prisonerList = new ArrayList<>();
        Participant.setLastSerialNumber(0);
        Participant.setPastDays(0);
        Prisoner.setOrdered(false);
        inputFromFile();
        simulationPanel.guardList(guardList);
        simulationPanel.prisonerList(prisonerList);
        simulationPanel.daysLabel(Participant.getPastDays());
    }

    public void showGuardDiarys(Participant selectedValue) {
        simulationPanel.diaryList(selectedValue.getDiary().getRecords());
    }

    public void showPrisonerDiarys(Participant selectedValue) {
        simulationPanel.diaryList(selectedValue.getDiary().getRecords());
    }

    public void sortPrisoners() {
        Collections.sort(prisonerList);
        Prisoner.setOrdered(true);
        simulationPanel.prisonerList(prisonerList);
    }

    public void newDay() {
        Participant.newDay();
        simulationPanel.daysLabel(Participant.getPastDays());
        Prisoner.setOrdered(false);
        simulationPanel.prisonerList(prisonerList);
        for (int gg = 0; gg < guardList.size(); gg++) {
            int guardIndex = (int) (Math.random() * guardList.size());
            guardList.get(guardIndex)
                    .increseAgression((int) (Math.random() * AGRESSION_MAX));
        }
        for (int pp = 0; pp < prisonerList.size(); pp++) {
            int prisonerIndex = (int) (Math.random() * prisonerList.size());
            if (Math.random() < 0.5) {
                prisonerList.get(prisonerIndex).moreApathetic();
            } else {
                prisonerList.get(prisonerIndex).revolt();
            }
        }
        int sum = 0, max = 0;
        for (Guard guard : guardList) {
            guard.writeDiary();
            sum += guard.getAgression();
            if (max < guard.getAgression()) {
                max = guard.getAgression();
            }
        }
        if (max > 0) {
            evils = new ArrayList<>();
            for (Guard g : guardList) {
                if (max == g.getAgression()) {
                    evils.add(g);
                }
            }
            simulationPanel.evilList(evils);
        }
        simulationPanel.agressionLabel(sum);
        sum = 0;
        for (Prisoner prisoner : prisonerList) {
            prisoner.writeDiary();
            sum += prisoner.getApathy();
        }
        simulationPanel.apathyLabel(sum);
    }
}
