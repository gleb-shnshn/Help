package com.papalam.help;

import com.google.gson.annotations.SerializedName;
import com.papalam.help.model.PainPoint;

import java.util.ArrayList;

public class PainAreasResponse {
    @SerializedName("regions")
    ArrayList<PainPoint> areas;

    public ArrayList<PainPoint> getAreas() {
        return areas;
    }

    public void setAreas(ArrayList<PainPoint> areas) {
        this.areas = areas;
    }

}
