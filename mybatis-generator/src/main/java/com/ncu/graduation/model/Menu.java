package com.ncu.graduation.model;

import java.util.Date;

public class Menu {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.id
     *
     * @mbg.generated Mon Mar 23 15:42:07 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.name
     *
     * @mbg.generated Mon Mar 23 15:42:07 CST 2020
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.url
     *
     * @mbg.generated Mon Mar 23 15:42:07 CST 2020
     */
    private String url;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.super_id
     *
     * @mbg.generated Mon Mar 23 15:42:07 CST 2020
     */
    private Long superId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.role
     *
     * @mbg.generated Mon Mar 23 15:42:07 CST 2020
     */
    private String role;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.gmt_create
     *
     * @mbg.generated Mon Mar 23 15:42:07 CST 2020
     */
    private Date gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu.gmt_modified
     *
     * @mbg.generated Mon Mar 23 15:42:07 CST 2020
     */
    private Date gmtModified;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.id
     *
     * @return the value of menu.id
     *
     * @mbg.generated Mon Mar 23 15:42:07 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.id
     *
     * @param id the value for menu.id
     *
     * @mbg.generated Mon Mar 23 15:42:07 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.name
     *
     * @return the value of menu.name
     *
     * @mbg.generated Mon Mar 23 15:42:07 CST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.name
     *
     * @param name the value for menu.name
     *
     * @mbg.generated Mon Mar 23 15:42:07 CST 2020
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.url
     *
     * @return the value of menu.url
     *
     * @mbg.generated Mon Mar 23 15:42:07 CST 2020
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.url
     *
     * @param url the value for menu.url
     *
     * @mbg.generated Mon Mar 23 15:42:07 CST 2020
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.super_id
     *
     * @return the value of menu.super_id
     *
     * @mbg.generated Mon Mar 23 15:42:07 CST 2020
     */
    public Long getSuperId() {
        return superId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.super_id
     *
     * @param superId the value for menu.super_id
     *
     * @mbg.generated Mon Mar 23 15:42:07 CST 2020
     */
    public void setSuperId(Long superId) {
        this.superId = superId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.role
     *
     * @return the value of menu.role
     *
     * @mbg.generated Mon Mar 23 15:42:07 CST 2020
     */
    public String getRole() {
        return role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.role
     *
     * @param role the value for menu.role
     *
     * @mbg.generated Mon Mar 23 15:42:07 CST 2020
     */
    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.gmt_create
     *
     * @return the value of menu.gmt_create
     *
     * @mbg.generated Mon Mar 23 15:42:07 CST 2020
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.gmt_create
     *
     * @param gmtCreate the value for menu.gmt_create
     *
     * @mbg.generated Mon Mar 23 15:42:07 CST 2020
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu.gmt_modified
     *
     * @return the value of menu.gmt_modified
     *
     * @mbg.generated Mon Mar 23 15:42:07 CST 2020
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu.gmt_modified
     *
     * @param gmtModified the value for menu.gmt_modified
     *
     * @mbg.generated Mon Mar 23 15:42:07 CST 2020
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}