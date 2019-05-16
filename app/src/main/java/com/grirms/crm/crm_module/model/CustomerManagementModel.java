package com.grirms.crm.crm_module.model;

/**
 * Created by Administrator on 2019\4\26 0026.
 */

public class CustomerManagementModel {
    private String states;
    private String name;
    private String type;
    private String level;
    private boolean showMap;
    private boolean showAttention;

    public boolean isShowAttention() {
        return showAttention;
    }

    public void setShowAttention(boolean showAttention) {
        this.showAttention = showAttention;
    }

    public boolean isShowMap() {
        return showMap;
    }

    public void setShowMap(boolean showMap) {
        this.showMap = showMap;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
