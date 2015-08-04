package com.planyourexchange.rest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thiago on 29/07/15.
 */
public class Course extends BaseModel {

    private Integer weekDuration;

    public Integer getWeekDuration() {
        return weekDuration;
    }

    public void setWeekDuration(Integer weekDuration) {
        this.weekDuration = weekDuration;
    }
}
