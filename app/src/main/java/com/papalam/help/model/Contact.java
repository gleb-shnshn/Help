package com.papalam.help.model;


public class Contact {
    String name;
    String description;
    String icon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public Contact(String name, String description, String icon) {
        this.name = name;
        this.description = description;
        this.icon = icon;
    }

}
