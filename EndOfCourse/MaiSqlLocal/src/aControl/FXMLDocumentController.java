/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import static aBasis.Global.*;
import aBasis.MaiLocalHost;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author b6dmin
 */
public class FXMLDocumentController implements Initializable {

    private final MaiLocalHost maiHost = new MaiLocalHost(DATABASE);

    private void writeMessage(String message) {
        lblMessage.setText(message);
    }

    private void setButtonText(String str) {
        button.setText(str);
    }

    private void actionStart(String str) {
        writeMessage(str);
        state = MAI_STATE.CONNECT_ROOT;
        setButtonText(connectRootMessage);
    }

    private void actionRooted() {
        writeMessage(connectRootMessage);
        maiHost.connectAsRoot();
        state = MAI_STATE.DELETE_DATABASE;
        setButtonText(dropDataBaseMessage);
    }

    private void actionDropDatabase() {
        writeMessage(dropDataBaseMessage);
        maiHost.dropDataBase();
        state = MAI_STATE.CREATE_DATABASE;
        setButtonText(createDataBaseMessage);
    }

    private void actionCreateDatabase() {
        writeMessage(createDataBaseMessage);
        if (maiHost.createDataBase() == 1) {
            System.out.println(createDataBaseMessage + maiHost.getMaiDatabase());
        }
        state = MAI_STATE.SELECT_DATABASE;
        setButtonText(openDataBaseMessage);
    }

    private void actionOpenDatabase() {
        writeMessage(openDataBaseMessage);
        maiHost.openDataBase();
        state = MAI_STATE.SHOW_DATABASES;
        setButtonText(showDataBasesQuery);
    }

    private void actionShowDatabases() {
        writeMessage(showDataBasesQuery);
        maiHost.showDataBases();
        state = MAI_STATE.CREATE_TABLES;
        setButtonText(createTablesMessage);
    }

    private void actionCreateTables() {
        writeMessage(createTablesMessage);
        maiHost.fillUpSqlFile(SAMPLE_URL);
        maiHost.showDataBases();
        state = MAI_STATE.SHOW_TABLES;
        setButtonText(showTablesQuery);
    }

    private void actionShowTables() {
        writeMessage(showTablesQuery);
        maiHost.showTables();
        state = MAI_STATE.FILL_SAMPLES;
        setButtonText(fillTablesMessage);
    }

    private void actionFillSamples() {
        writeMessage(fillTablesMessage);
        maiHost.fillUpSqlFile(SAMPLE_FILL_URL);
        maiHost.showTables();
        state = MAI_STATE.FINISH;
        setButtonText(finishPlatformText);
    }

    private void actionFinish() {
        Platform.exit();
    }

    @FXML
    private Label lblMessage;

    @FXML
    private Button button;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        switch (state) {
            case START: {
                actionStart(event.getEventType().getName());
                break;
            }
            case CONNECT_ROOT: {
                actionRooted();
                break;
            }
            case DELETE_DATABASE: {
                actionDropDatabase();
                break;
            }
            case CREATE_DATABASE: {
                actionCreateDatabase();
                break;
            }
            case SELECT_DATABASE: {
                actionOpenDatabase();
                break;
            }
            case SHOW_DATABASES: {
                actionShowDatabases();
                break;
            }
            case CREATE_TABLES: {
                maiHost.closeConnection();
                maiHost.connectAsRoot();
                actionCreateTables();
                break;
            }
            case SHOW_TABLES: {
                actionShowTables();
                break;
            }
            case FILL_SAMPLES: {
                actionFillSamples();
                break;
            }
            case FINISH: {
                actionFinish();
                break;
            }
            default: {
                writeMessage(event.getEventType().getName());
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
