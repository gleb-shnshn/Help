package com.papalam.help.model;

public class CheckListItem {
    String title;
    String description;
    int type;
    boolean checked = false;

    public final static int TYPE_WATER = 251;
    public final static int TYPE_MEDIC = 255;
    public final static int TYPE_FOOD = 125;

    public CheckListItem(String title, String description, int type) {
        this.title = title;
        this.description = description;
        this.type = type;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
