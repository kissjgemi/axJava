/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

/**
 *
 * @author KissJGabi
 */
public class InetNews extends Messenger {

    private final String URL;

    public String getURL() {
        return URL;
    }

    public InetNews(String name, String date, String url) {
        super(name, date);
        this.URL = url;
    }
}
