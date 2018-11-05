package com.linkdev.linkdevlopmenttask.model;

/**
 * Created by ahmed on 11/5/18.
 */

public class SideMenuModel {

    private String title;
    private int icon;
    private boolean selected = false;

    public SideMenuModel(String title, int icon , boolean selected) {
        this.title = title;
        this.icon = icon;
        this.selected = selected;
    }

    public String getTitle() {
        return title;
    }

    public int getIcon() {
        return icon;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
