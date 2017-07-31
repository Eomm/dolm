package com.github.eomm.tools.dolm.bean;

import java.io.Serializable;

/**
 * Created by Manuel Spigolon on 02/07/2017.
 */
public class Converted<I, O> implements Serializable {

    private boolean converted;

    private I from;

    private O to;

    public Converted() {
    }

    public Converted(I from, O to) {
        this.from = from;
        this.to = to;
    }

    public boolean isConverted() {
        return converted;
    }

    public void setConverted(boolean converted) {
        this.converted = converted;
    }

    public I getFrom() {
        return from;
    }

    public void setFrom(I from) {
        this.from = from;
    }

    public O getTo() {
        return to;
    }

    public void setTo(O to) {
        this.to = to;
    }
}