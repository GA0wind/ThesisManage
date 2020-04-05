package com.ncu.graduation.model;

import java.util.Date;

public class ForeignRecord {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column foreign_record.id
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column foreign_record.fno
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    private String fno;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column foreign_record.foreign_file
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    private String foreignFile;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column foreign_record.translation_file
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    private String translationFile;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column foreign_record.trial_grade
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    private Byte trialGrade;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column foreign_record.trial_comment
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    private String trialComment;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column foreign_record.gmt_create
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    private Date gmtCreate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column foreign_record.id
     *
     * @return the value of foreign_record.id
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column foreign_record.id
     *
     * @param id the value for foreign_record.id
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column foreign_record.fno
     *
     * @return the value of foreign_record.fno
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public String getFno() {
        return fno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column foreign_record.fno
     *
     * @param fno the value for foreign_record.fno
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public void setFno(String fno) {
        this.fno = fno == null ? null : fno.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column foreign_record.foreign_file
     *
     * @return the value of foreign_record.foreign_file
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public String getForeignFile() {
        return foreignFile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column foreign_record.foreign_file
     *
     * @param foreignFile the value for foreign_record.foreign_file
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public void setForeignFile(String foreignFile) {
        this.foreignFile = foreignFile == null ? null : foreignFile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column foreign_record.translation_file
     *
     * @return the value of foreign_record.translation_file
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public String getTranslationFile() {
        return translationFile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column foreign_record.translation_file
     *
     * @param translationFile the value for foreign_record.translation_file
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public void setTranslationFile(String translationFile) {
        this.translationFile = translationFile == null ? null : translationFile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column foreign_record.trial_grade
     *
     * @return the value of foreign_record.trial_grade
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public Byte getTrialGrade() {
        return trialGrade;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column foreign_record.trial_grade
     *
     * @param trialGrade the value for foreign_record.trial_grade
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public void setTrialGrade(Byte trialGrade) {
        this.trialGrade = trialGrade;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column foreign_record.trial_comment
     *
     * @return the value of foreign_record.trial_comment
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public String getTrialComment() {
        return trialComment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column foreign_record.trial_comment
     *
     * @param trialComment the value for foreign_record.trial_comment
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public void setTrialComment(String trialComment) {
        this.trialComment = trialComment == null ? null : trialComment.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column foreign_record.gmt_create
     *
     * @return the value of foreign_record.gmt_create
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column foreign_record.gmt_create
     *
     * @param gmtCreate the value for foreign_record.gmt_create
     *
     * @mbg.generated Tue Mar 31 20:48:27 CST 2020
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}