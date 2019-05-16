package com.grirms.crm.crm_module.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2019\4\29 0029.
 */

public class BusinessModel implements Serializable {
    private String id;
    private String itemName;
    private String customerCompanyName;
    private String estimatedSalesQuota;
    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCustomerCompanyName() {
        return customerCompanyName;
    }

    public void setCustomerCompanyName(String customerCompanyName) {
        this.customerCompanyName = customerCompanyName;
    }

    public String getEstimatedSalesQuota() {
        return estimatedSalesQuota;
    }

    public void setEstimatedSalesQuota(String estimatedSalesQuota) {
        this.estimatedSalesQuota = estimatedSalesQuota;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
