package com.ncu.graduation.model;

import java.util.Date;

public class ProjectApply {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project_apply.id
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project_apply.pno
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    private String pno;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project_apply.pname
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    private String pname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project_apply.file_path
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    private String filePath;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project_apply.type
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project_apply.tags
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    private String tags;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project_apply.college
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    private String college;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project_apply.creator_no
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    private String creatorNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project_apply.is_pass
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    private Byte isPass;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project_apply.is_select
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    private Byte isSelect;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project_apply.blind_trial_no
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    private String blindTrialNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project_apply.blind_trial_grade
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    private Byte blindTrialGrade;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project_apply.blind_trial_comment
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    private String blindTrialComment;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project_apply.trial_no
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    private String trialNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project_apply.trial_comment
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    private String trialComment;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project_apply.school_year
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    private String schoolYear;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project_apply.gmt_create
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    private Date gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project_apply.gmt_modified
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    private Date gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column project_apply.content
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    private String content;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project_apply.id
     *
     * @return the value of project_apply.id
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project_apply.id
     *
     * @param id the value for project_apply.id
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project_apply.pno
     *
     * @return the value of project_apply.pno
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public String getPno() {
        return pno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project_apply.pno
     *
     * @param pno the value for project_apply.pno
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public void setPno(String pno) {
        this.pno = pno == null ? null : pno.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project_apply.pname
     *
     * @return the value of project_apply.pname
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public String getPname() {
        return pname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project_apply.pname
     *
     * @param pname the value for project_apply.pname
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project_apply.file_path
     *
     * @return the value of project_apply.file_path
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project_apply.file_path
     *
     * @param filePath the value for project_apply.file_path
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project_apply.type
     *
     * @return the value of project_apply.type
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project_apply.type
     *
     * @param type the value for project_apply.type
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project_apply.tags
     *
     * @return the value of project_apply.tags
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public String getTags() {
        return tags;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project_apply.tags
     *
     * @param tags the value for project_apply.tags
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project_apply.college
     *
     * @return the value of project_apply.college
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public String getCollege() {
        return college;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project_apply.college
     *
     * @param college the value for project_apply.college
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public void setCollege(String college) {
        this.college = college == null ? null : college.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project_apply.creator_no
     *
     * @return the value of project_apply.creator_no
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public String getCreatorNo() {
        return creatorNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project_apply.creator_no
     *
     * @param creatorNo the value for project_apply.creator_no
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public void setCreatorNo(String creatorNo) {
        this.creatorNo = creatorNo == null ? null : creatorNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project_apply.is_pass
     *
     * @return the value of project_apply.is_pass
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public Byte getIsPass() {
        return isPass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project_apply.is_pass
     *
     * @param isPass the value for project_apply.is_pass
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public void setIsPass(Byte isPass) {
        this.isPass = isPass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project_apply.is_select
     *
     * @return the value of project_apply.is_select
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public Byte getIsSelect() {
        return isSelect;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project_apply.is_select
     *
     * @param isSelect the value for project_apply.is_select
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public void setIsSelect(Byte isSelect) {
        this.isSelect = isSelect;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project_apply.blind_trial_no
     *
     * @return the value of project_apply.blind_trial_no
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public String getBlindTrialNo() {
        return blindTrialNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project_apply.blind_trial_no
     *
     * @param blindTrialNo the value for project_apply.blind_trial_no
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public void setBlindTrialNo(String blindTrialNo) {
        this.blindTrialNo = blindTrialNo == null ? null : blindTrialNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project_apply.blind_trial_grade
     *
     * @return the value of project_apply.blind_trial_grade
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public Byte getBlindTrialGrade() {
        return blindTrialGrade;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project_apply.blind_trial_grade
     *
     * @param blindTrialGrade the value for project_apply.blind_trial_grade
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public void setBlindTrialGrade(Byte blindTrialGrade) {
        this.blindTrialGrade = blindTrialGrade;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project_apply.blind_trial_comment
     *
     * @return the value of project_apply.blind_trial_comment
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public String getBlindTrialComment() {
        return blindTrialComment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project_apply.blind_trial_comment
     *
     * @param blindTrialComment the value for project_apply.blind_trial_comment
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public void setBlindTrialComment(String blindTrialComment) {
        this.blindTrialComment = blindTrialComment == null ? null : blindTrialComment.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project_apply.trial_no
     *
     * @return the value of project_apply.trial_no
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public String getTrialNo() {
        return trialNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project_apply.trial_no
     *
     * @param trialNo the value for project_apply.trial_no
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public void setTrialNo(String trialNo) {
        this.trialNo = trialNo == null ? null : trialNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project_apply.trial_comment
     *
     * @return the value of project_apply.trial_comment
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public String getTrialComment() {
        return trialComment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project_apply.trial_comment
     *
     * @param trialComment the value for project_apply.trial_comment
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public void setTrialComment(String trialComment) {
        this.trialComment = trialComment == null ? null : trialComment.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project_apply.school_year
     *
     * @return the value of project_apply.school_year
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public String getSchoolYear() {
        return schoolYear;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project_apply.school_year
     *
     * @param schoolYear the value for project_apply.school_year
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear == null ? null : schoolYear.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project_apply.gmt_create
     *
     * @return the value of project_apply.gmt_create
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project_apply.gmt_create
     *
     * @param gmtCreate the value for project_apply.gmt_create
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project_apply.gmt_modified
     *
     * @return the value of project_apply.gmt_modified
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project_apply.gmt_modified
     *
     * @param gmtModified the value for project_apply.gmt_modified
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column project_apply.content
     *
     * @return the value of project_apply.content
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column project_apply.content
     *
     * @param content the value for project_apply.content
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}