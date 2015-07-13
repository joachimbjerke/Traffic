package com.jbjerke.app.traffic.models;

import android.graphics.Point;

import java.util.Date;

/**
 * Created by jbjerke on 03.07.2015.
 */
public class ItemModel {

    //private

    private String header;
    private String description;
    private String icon;
    private String severity;
    private Date validFrom;
    private Date validTo;
    private Service serivce;


    //Getters

    public String getHeader() {
        return header;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }


    public Date getValidFrom() {
        return validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }



    public Service getSerivce() {
        return serivce;
    }

    public String getSeverity() {
        return severity;
    }


    //Setters

    public void setHeader(String header) {
        this.header = header;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }



    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }



    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }



    public void setSerivce(Service serivce) {
        this.serivce = serivce;
    }


    public void setSeverity(String severity) {
        this.severity = severity;
    }


    private class Service {
        private String roadType;
        private int roadNumber;
        private Coord coordinateStart;
        private Coord coordinateEnd;

    }

    private class Coord
    {
        private int latitude;
        private int longitude;
    }

}
