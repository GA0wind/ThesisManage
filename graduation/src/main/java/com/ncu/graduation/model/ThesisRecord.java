package com.ncu.graduation.model;

import java.util.Date;

public class ThesisRecord {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column thesis_record.id
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column thesis_record.pno
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    private String pno;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column thesis_record.file_path
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    private String filePath;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column thesis_record.is_pass
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    private Byte isPass;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column thesis_record.trial_grade
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    private Byte trialGrade;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column thesis_record.trial_comment
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    private String trialComment;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column thesis_record.gmt_create
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    private Date gmtCreate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column thesis_record.id
     *
     * @return the value of thesis_record.id
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column thesis_record.id
     *
     * @param id the value for thesis_record.id
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column thesis_record.pno
     *
     * @return the value of thesis_record.pno
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    public String getPno() {
        return pno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column thesis_record.pno
     *
     * @param pno the value for thesis_record.pno
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    public void setPno(String pno) {
        this.pno = pno == null ? null : pno.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column thesis_record.file_path
     *
     * @return the value of thesis_record.file_path
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column thesis_record.file_path
     *
     * @param filePath the value for thesis_record.file_path
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column thesis_record.is_pass
     *
     * @return the value of thesis_record.is_pass
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    public Byte getIsPass() {
        return isPass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column thesis_record.is_pass
     *
     * @param isPass the value for thesis_record.is_pass
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    public void setIsPass(Byte isPass) {
        this.isPass = isPass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column thesis_record.trial_grade
     *
     * @return the value of thesis_record.trial_grade
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    public Byte getTrialGrade() {
        return trialGrade;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column thesis_record.trial_grade
     *
     * @param trialGrade the value for thesis_record.trial_grade
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    public void setTrialGrade(Byte trialGrade) {
        this.trialGrade = trialGrade;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column thesis_record.trial_comment
     *
     * @return the value of thesis_record.trial_comment
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    public String getTrialComment() {
        return trialComment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column thesis_record.trial_comment
     *
     * @param trialComment the value for thesis_record.trial_comment
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    public void setTrialComment(String trialComment) {
        this.trialComment = trialComment == null ? null : trialComment.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column thesis_record.gmt_create
     *
     * @return the value of thesis_record.gmt_create
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column thesis_record.gmt_create
     *
     * @param gmtCreate the value for thesis_record.gmt_create
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}