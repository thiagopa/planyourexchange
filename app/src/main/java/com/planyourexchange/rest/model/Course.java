package com.planyourexchange.rest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thiago on 29/07/15.
 */
public class Course extends BaseModel {

    @SerializedName("week_duration")
    private Integer weekDuration;

    public Integer getWeekDuration() {
        return weekDuration;
    }

    public void setWeekDuration(Integer weekDuration) {
        this.weekDuration = weekDuration;
    }
}
