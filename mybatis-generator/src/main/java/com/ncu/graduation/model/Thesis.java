package com.ncu.graduation.model;

import java.util.Date;

public class Thesis {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column thesis.id
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column thesis.pno
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    private String pno;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column thesis.file_path
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    private String filePath;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column thesis.is_pass
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    private Byte isPass;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column thesis.trial_grade
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    private Byte trialGrade;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column thesis.trial_comment
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    private String trialComment;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column thesis.blind_trial_no
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    private String blindTrialNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column thesis.blind_trial_grade
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    private Byte blindTrialGrade;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column thesis.blind_trial_comment
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    private String blindTrialComment;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column thesis.school_year
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    private String schoolYear;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column thesis.gmt_create
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    private Date gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column thesis.gmt_modified
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    private Date gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column thesis.modifiable
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    private Byte modifiable;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column thesis.id
     *
     * @return the value of thesis.id
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column thesis.id
     *
     * @param id the value for thesis.id
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column thesis.pno
     *
     * @return the value of thesis.pno
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public String getPno() {
        return pno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column thesis.pno
     *
     * @param pno the value for thesis.pno
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public void setPno(String pno) {
        this.pno = pno == null ? null : pno.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column thesis.file_path
     *
     * @return the value of thesis.file_path
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column thesis.file_path
     *
     * @param filePath the value for thesis.file_path
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column thesis.is_pass
     *
     * @return the value of thesis.is_pass
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public Byte getIsPass() {
        return isPass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column thesis.is_pass
     *
     * @param isPass the value for thesis.is_pass
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public void setIsPass(Byte isPass) {
        this.isPass = isPass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column thesis.trial_grade
     *
     * @return the value of thesis.trial_grade
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public Byte getTrialGrade() {
        return trialGrade;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column thesis.trial_grade
     *
     * @param trialGrade the value for thesis.trial_grade
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public void setTrialGrade(Byte trialGrade) {
        this.trialGrade = trialGrade;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column thesis.trial_comment
     *
     * @return the value of thesis.trial_comment
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public String getTrialComment() {
        return trialComment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column thesis.trial_comment
     *
     * @param trialComment the value for thesis.trial_comment
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public void setTrialComment(String trialComment) {
        this.trialComment = trialComment == null ? null : trialComment.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column thesis.blind_trial_no
     *
     * @return the value of thesis.blind_trial_no
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public String getBlindTrialNo() {
        return blindTrialNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column thesis.blind_trial_no
     *
     * @param blindTrialNo the value for thesis.blind_trial_no
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public void setBlindTrialNo(String blindTrialNo) {
        this.blindTrialNo = blindTrialNo == null ? null : blindTrialNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column thesis.blind_trial_grade
     *
     * @return the value of thesis.blind_trial_grade
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public Byte getBlindTrialGrade() {
        return blindTrialGrade;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column thesis.blind_trial_grade
     *
     * @param blindTrialGrade the value for thesis.blind_trial_grade
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public void setBlindTrialGrade(Byte blindTrialGrade) {
        this.blindTrialGrade = blindTrialGrade;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column thesis.blind_trial_comment
     *
     * @return the value of thesis.blind_trial_comment
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public String getBlindTrialComment() {
        return blindTrialComment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column thesis.blind_trial_comment
     *
     * @param blindTrialComment the value for thesis.blind_trial_comment
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public void setBlindTrialComment(String blindTrialComment) {
        this.blindTrialComment = blindTrialComment == null ? null : blindTrialComment.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column thesis.school_year
     *
     * @return the value of thesis.school_year
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public String getSchoolYear() {
        return schoolYear;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column thesis.school_year
     *
     * @param schoolYear the value for thesis.school_year
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear == null ? null : schoolYear.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column thesis.gmt_create
     *
     * @return the value of thesis.gmt_create
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column thesis.gmt_create
     *
     * @param gmtCreate the value for thesis.gmt_create
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column thesis.gmt_modified
     *
     * @return the value of thesis.gmt_modified
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column thesis.gmt_modified
     *
     * @param gmtModified the value for thesis.gmt_modified
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column thesis.modifiable
     *
     * @return the value of thesis.modifiable
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public Byte getModifiable() {
        return modifiable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column thesis.modifiable
     *
     * @param modifiable the value for thesis.modifiable
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public void setModifiable(Byte modifiable) {
        this.modifiable = modifiable;
    }
}