/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

/**
 *
 * @author b6dmin
 */
public class MB6User {

    private String userName;
    private String lastMessage;
    private String abortConnectString;
    private String locale;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
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

    public MB6User() {
    }

    @Override
    public String toString() {
        return userName + ", (" + locale + ')';
    }

}
