package com.ads.adserver.controller;

public class AdServerException extends Exception {

    public AdServerException(String s) {
        super(s);
    }

    public AdServerException(String s, Exception e) {
        super(s, e);
    }
}
