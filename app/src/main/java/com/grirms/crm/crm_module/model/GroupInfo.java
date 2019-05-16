package com.grirms.crm.crm_module.model;

/**
 * Created by Administrator on 2019\4\28 0028.
 */

public class GroupInfo {
    private String groupID;
    private String groupName;
    private boolean childIsHasRemainData;
    private boolean show_more_data;

    public boolean isShow_more_data() {
        return show_more_data;
    }

    public void setShow_more_data(boolean show_more_data) {
        this.show_more_data = show_more_data;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public boolean ischildIsHasRemainData() {
        return childIsHasRemainData;
    }

    public void setchildIsHasRemainData(boolean childIsHasRemainData) {
        this.childIsHasRemainData = childIsHasRemainData;
    }

}
