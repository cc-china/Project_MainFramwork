package com.grirms.crm.crm_module.model;

/**
 * Created by Administrator on 2019\4\28 0028.
 */

public class ChildrenInfo {
    private String childID;
    private String groupID;
    private String childName;
    private boolean clickInfo;

    public boolean getClickInfo() {
        return clickInfo;
    }

    public void setClickInfo(boolean clickInfo) {
        this.clickInfo = clickInfo;
    }

    public String getChildID() {
        return childID;
    }

    public void setChildID(String childID) {
        this.childID = childID;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }
}
