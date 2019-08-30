/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author b6dmin
 */
public class ZenUser {

    private ZenData userData;
    private String lastMessage;
    private String abortConnectString;
    private String locale;
    private final List<String> history;

    public ZenData getUserData() {
        return userData;
    }

    public void setUserData(ZenData userData) {
        this.userData = userData;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
        addToHistory();
    }

    public String getAbortConnectString() {
        return abortConnectString;
    }

    public void setAbortConnectString(String abortConnectString) {
        this.abortConnectString = abortConnectString;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public List<String> getHistory() {
        return history;
    }

    public ZenUser() {
        history = new ArrayList<>();
    }

    public void addToHistory() {
        history.add(lastMessage);
    }

    @Override
    public String toString() {
        return userData.getZenUserName() + ", (" + locale + ')';
    }
}
