package com.example.lidongxue.sqldbchat.entity;

/**
 * Created by lidongxue on 17-10-9.
 */

public class User {
    String u_id;
    String  username;


    String u_pwd;
    String u_pic;
    String  u_region;
    Integer  u_status;


    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getU_pwd() {
        return u_pwd;
    }

    public void setU_pwd(String u_pwd) {
        this.u_pwd = u_pwd;
    }

    public String getU_pic() {
        return u_pic;
    }

    public void setU_pic(String u_pic) {
        this.u_pic = u_pic;
    }

    public String getU_region() {
        return u_region;
    }

    public void setU_region(String u_region) {
        this.u_region = u_region;
    }

    public Integer getU_status() {
        return u_status;
    }

    public void setU_status(Integer u_status) {
        this.u_status = u_status;
    }
}
