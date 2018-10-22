package com.catolica.salesianoapp.ucv.model;

import android.support.annotation.Nullable;

import java.io.Serializable;

/**
 * Created by Meycon Augusto on 11/02/2018.
 */

public class Aula implements Serializable {

    @Nullable
    private final Integer id = null;
    @Nullable
    private final String name = null;
    @Nullable
    private final String prof = null;
    @Nullable
    private final String place = null;
    @Nullable
    private final Integer day = null;
    @Nullable
    private final String startTime = null;
    @Nullable
    private final String endTime = null;

    @Nullable
    public Integer getId() {
        return id;
    }

    @Nullable
    public String getName() {
        return name;
    }

    @Nullable
    public String getProf() {
        return prof;
    }

    @Nullable
    public String getPlace() {
        return place;
    }

    @Nullable
    public Integer getDay() {
        return day;
    }

    @Nullable
    public String getStartTime() {
        return startTime;
    }

    @Nullable
    public String getEndTime() {
        return endTime;
    }

    public final int getLength() {
        return (this.endTime != null?Integer.parseInt(this.endTime.substring(0,2)):0) - (this.startTime != null?Integer.parseInt(this.startTime.substring(0,2)):0);
    }

}
