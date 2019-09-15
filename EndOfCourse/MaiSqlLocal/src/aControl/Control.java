/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import static aBasis.Global.*;
import aBasis.MaiLocalHost;
import aSurface.Panel01Control;
import aSurface.Panel02Results;
import aSurface.Panel03Console;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author b6dmin
 */
public class Control {

    private final MaiLocalHost maiHost = new MaiLocalHost();

    private final MainFrame MAINFRAME;
    private final Panel01Control CONTROLPANEL;
    private final Panel02Results RESULTSPANEL;
    private final Panel03Console CONSOLEPANEL;

    private List<String> listDatabases;
    private List<String> listTables;
    private List<String> listFields;
    private List<String> listOneField;

    Control(MainFrame mf, Panel01Control pControl,
            Panel02Results pResults, Panel03Console pConsole) {
        this.MAINFRAME = mf;
        this.CONTROLPANEL = pControl;
        this.RESULTSPANEL = pResults;
        this.CONSOLEPANEL = pConsole;
    }

    private void showDialog(String message, String header) {
        JOptionPane.showMessageDialog(MAINFRAME,
                message,
                header,
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void notFound() {
        showDialog(DIALOG_NOTFOUND_TXT, DIALOG_HEADER_TXT);
    }

    public void maiDatabaseInfo() {
        String info = CONTROL_BTN_CONNECTION_INFO.toUpperCase() + "\n\n";
        info += LBL_URL + ": " + URL + "\n";
        info += LBL_USER + ": " + USER + "\n";
        info += LBL_PSWD + ": " + PSWD + "\n";
        info += characterSet + "\n";
        info += collation + "\n";
        showDialog(info, DIALOG_HEADER_TXT);
    }

    private void writeMessage(String message) {
        CONSOLEPANEL.insertTextIntoBody(message);
    }

    private void writeMessageTitle(String message) {
        CONSOLEPANEL.insertTextIntoBody("< " + message + " >");
    }

    private void writeMessage(List<String> list) {
        list.forEach((str) -> {
            writeMessage(str);
        });
    }

    void setup() {
        System.out.println("Control.setup()");
        MAINFRAME.setup();
    }

    public void handleButtonConnect() {
        if (maiHost.isConnected()) {
            writeMessage(maiHost.closeConnection());
        } else {
            writeMessage(maiHost.connectAsRoot());
            listDatabases = maiHost.showDataBases();
            writeMessageTitle(showDataBasesQuery);
            writeMessage(listDatabases);
            CONTROLPANEL.setListDatabasesModel(listDatabases);
            CONTROLPANEL.setcomboDatabasesActivity(true);
            RESULTSPANEL.clearResults();
        }
        CONTROLPANEL.setButtonActivity(maiHost.isConnected());
    }

    public void handleComboDatabases(String item) {
        if (!item.equals(CONTROL_DATABASES_TEXT)) {
            writeMessage(maiHost.openDataBase(item));
            listTables = maiHost.showTables();
            writeMessageTitle(showTablesQuery);
            writeMessage(listTables);
        }
        CONTROLPANEL.initFieldsModel();
        CONTROLPANEL.initPropertiesModel();
        CONTROLPANEL.setListTablesModel(listTables);
        CONTROLPANEL.setcomboTablesActivity(true);
        RESULTSPANEL.clearResults();
    }

    public void handleComboTables(String item) {
        if (!item.equals(CONTROL_TABLES_TEXT)) {
            listFields = maiHost.showFields(item);
            writeMessageTitle(showFieldsQuery + ": " + item);
            writeMessage(listFields);
        }
        CONTROLPANEL.initPropertiesModel();
        CONTROLPANEL.setListFieldsModel(listFields);
        CONTROLPANEL.setcomboFieldsActivity(true);
        RESULTSPANEL.clearResults();
    }

    public void handleComboFields(int id) {
        if (id > 0) {
            String[] field = listFields.get(id - 1).split(REGEX_FIELDS);
            writeMessageTitle(field[0]);
            for (int ii = 1; ii < field.length; ii++) {
                writeMessage(String.format(
                        fieldPropertiesFormat, fieldProperties[ii]) + field[ii]);
            }
            CONTROLPANEL.setListPropertiesModel(field);
            listOneField = maiHost.showOneField(
                    field[0], CONTROLPANEL.getCurrentTable());
            RESULTSPANEL.fillResults(listOneField);
        } else {
            RESULTSPANEL.clearResults();
        }
    }
}
