package com.grirms.crm.crm_module.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2019\4\29 0029.
 */

public class ContactsModel implements Serializable{
    private String id;
    private String  name;
    private String  identity;
    private String  companyName;
    private String  phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
