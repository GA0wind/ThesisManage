package com.ncu.graduation.model;

import java.util.Date;

public class Bulletin {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bulletin.id
     *
     * @mbg.generated Mon Mar 16 00:23:10 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bulletin.title
     *
     * @mbg.generated Mon Mar 16 00:23:10 CST 2020
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bulletin.file_path
     *
     * @mbg.generated Mon Mar 16 00:23:10 CST 2020
     */
    private String filePath;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bulletin.creator_no
     *
     * @mbg.generated Mon Mar 16 00:23:10 CST 2020
     */
    private String creatorNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bulletin.gmt_create
     *
     * @mbg.generated Mon Mar 16 00:23:10 CST 2020
     */
    private Date gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bulletin.gmt_modified
     *
     * @mbg.generated Mon Mar 16 00:23:10 CST 2020
     */
    private Date gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bulletin.school_year
     *
     * @mbg.generated Mon Mar 16 00:23:10 CST 2020
     */
    private String schoolYear;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bulletin.description
     *
     * @mbg.generated Mon Mar 16 00:23:10 CST 2020
     */
    private String description;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bulletin.id
     *
     * @return the value of bulletin.id
     *
     * @mbg.generated Mon Mar 16 00:23:10 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bulletin.id
     *
     * @param id the value for bulletin.id
     *
     * @mbg.generated Mon Mar 16 00:23:10 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bulletin.title
     *
     * @return the value of bulletin.title
     *
     * @mbg.generated Mon Mar 16 00:23:10 CST 2020
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bulletin.title
     *
     * @param title the value for bulletin.title
     *
     * @mbg.generated Mon Mar 16 00:23:10 CST 2020
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bulletin.file_path
     *
     * @return the value of bulletin.file_path
     *
     * @mbg.generated Mon Mar 16 00:23:10 CST 2020
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bulletin.file_path
     *
     * @param filePath the value for bulletin.file_path
     *
     * @mbg.generated Mon Mar 16 00:23:10 CST 2020
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bulletin.creator_no
     *
     * @return the value of bulletin.creator_no
     *
     * @mbg.generated Mon Mar 16 00:23:10 CST 2020
     */
    public String getCreatorNo() {
        return creatorNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bulletin.creator_no
     *
     * @param creatorNo the value for bulletin.creator_no
     *
     * @mbg.generated Mon Mar 16 00:23:10 CST 2020
     */
    public void setCreatorNo(String creatorNo) {
        this.creatorNo = creatorNo == null ? null : creatorNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bulletin.gmt_create
     *
     * @return the value of bulletin.gmt_create
     *
     * @mbg.generated Mon Mar 16 00:23:10 CST 2020
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bulletin.gmt_create
     *
     * @param gmtCreate the value for bulletin.gmt_create
     *
     * @mbg.generated Mon Mar 16 00:23:10 CST 2020
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bulletin.gmt_modified
     *
     * @return the value of bulletin.gmt_modified
     *
     * @mbg.generated Mon Mar 16 00:23:10 CST 2020
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bulletin.gmt_modified
     *
     * @param gmtModified the value for bulletin.gmt_modified
     *
     * @mbg.generated Mon Mar 16 00:23:10 CST 2020
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bulletin.school_year
     *
     * @return the value of bulletin.school_year
     *
     * @mbg.generated Mon Mar 16 00:23:10 CST 2020
     */
    public String getSchoolYear() {
        return schoolYear;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bulletin.school_year
     *
     * @param schoolYear the value for bulletin.school_year
     *
     * @mbg.generated Mon Mar 16 00:23:10 CST 2020
     */
    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear == null ? null : schoolYear.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bulletin.description
     *
     * @return the value of bulletin.description
     *
     * @mbg.generated Mon Mar 16 00:23:10 CST 2020
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bulletin.description
     *
     * @param description the value for bulletin.description
     *
     * @mbg.generated Mon Mar 16 00:23:10 CST 2020
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}