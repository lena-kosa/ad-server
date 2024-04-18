package com.ads.adserver.controller;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

public class AdServerException extends Exception {

    @Getter
    private HttpStatusCode status;

    public AdServerException(String s) {
        super(s);
    }

    public AdServerException(String s, HttpStatusCode status) {
        super(s);
        this.status = status;
    }
}
