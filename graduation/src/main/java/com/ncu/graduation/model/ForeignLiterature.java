package com.ncu.graduation.model;

import java.util.Date;

public class ForeignLiterature {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column foreign_literature.id
     *
     * @mbg.generated Mon Apr 06 15:56:02 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column foreign_literature.fno
     *
     * @mbg.generated Mon Apr 06 15:56:02 CST 2020
     */
    private String fno;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column foreign_literature.pno
     *
     * @mbg.generated Mon Apr 06 15:56:02 CST 2020
     */
    private String pno;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column foreign_literature.is_pass
     *
     * @mbg.generated Mon Apr 06 15:56:02 CST 2020
     */
    private Byte isPass;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column foreign_literature.foreign_file
     *
     * @mbg.generated Mon Apr 06 15:56:02 CST 2020
     */
    private String foreignFile;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column foreign_literature.translation_file
     *
     * @mbg.generated Mon Apr 06 15:56:02 CST 2020
     */
    private String translationFile;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column foreign_literature.trial_grade
     *
     * @mbg.generated Mon Apr 06 15:56:02 CST 2020
     */
    private Byte trialGrade;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column foreign_literature.trial_comment
     *
     * @mbg.generated Mon Apr 06 15:56:02 CST 2020
     */
    private String trialComment;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column foreign_literature.school_year
     *
     * @mbg.generated Mon Apr 06 15:56:02 CST 2020
     */
    private String schoolYear;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column foreign_literature.gmt_create
     *
     * @mbg.generated Mon Apr 06 15:56:02 CST 2020
     */
    private Date gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column foreign_literature.gmt_modified
     *
     * @mbg.generated Mon Apr 06 15:56:02 CST 2020
     */
    private Date gmtModified;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column foreign_literature.id
     *
     * @return the value of foreign_literature.id
     *
     * @mbg.generated Mon Apr 06 15:56:02 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column foreign_literature.id
     *
     * @param id the value for foreign_literature.id
     *
     * @mbg.generated Mon Apr 06 15:56:02 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column foreign_literature.fno
     *
     * @return the value of foreign_literature.fno
     *
     * @mbg.generated Mon Apr 06 15:56:02 CST 2020
     */
    public String getFno() {
        return fno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column foreign_literature.fno
     *
     * @param fno the value for foreign_literature.fno
     *
     * @mbg.generated Mon Apr 06 15:56:02 CST 2020
     */
    public void setFno(String fno) {
        this.fno = fno == null ? null : fno.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column foreign_literature.pno
     *
     * @return the value of foreign_literature.pno
     *
     * @mbg.generated Mon Apr 06 15:56:02 CST 2020
     */
    public String getPno() {
        return pno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column foreign_literature.pno
     *
     * @param pno the value for foreign_literature.pno
     *
     * @mbg.generated Mon Apr 06 15:56:02 CST 2020
     */
    public void setPno(String pno) {
        this.pno = pno == null ? null : pno.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column foreign_literature.is_pass
     *
     * @return the value of foreign_literature.is_pass
     *
     * @mbg.generated Mon Apr 06 15:56:02 CST 2020
     */
    public Byte getIsPass() {
        return isPass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column foreign_literature.is_pass
     *
     * @param isPass the value for foreign_literature.is_pass
     *
     * @mbg.generated Mon Apr 06 15:56:02 CST 2020
     */
    public void setIsPass(Byte isPass) {
        this.isPass = isPass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column foreign_literature.foreign_file
     *
     * @return the value of foreign_literature.foreign_file
     *
     * @mbg.generated Mon Apr 06 15:56:02 CST 2020
     */
    public String getForeignFile() {
        return foreignFile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column foreign_literature.foreign_file
     *
     * @param foreignFile the value for foreign_literature.foreign_file
     *
     * @mbg.generated Mon Apr 06 15:56:02 CST 2020
     */
    public void setForeignFile(String foreignFile) {
        this.foreignFile = foreignFile == null ? null : foreignFile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column foreign_literature.translation_file
     *
     * @return the value of foreign_literature.translation_file
     *
     * @mbg.generated Mon Apr 06 15:56:02 CST 2020
     */
    public String getTranslationFile() {
        return translationFile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column foreign_literature.translation_file
     *
     * @param translationFile the value for foreign_literature.translation_file
     *
     * @mbg.generated Mon Apr 06 15:56:02 CST 2020
     */
    public void setTranslationFile(String translationFile) {
        this.translationFile = translationFile == null ? null : translationFile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column foreign_literature.trial_grade
     *
     * @return the value of foreign_literature.trial_grade
     *
     * @mbg.generated Mon Apr 06 15:56:02 CST 2020
     */
    public Byte getTrialGrade() {
        return trialGrade;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column foreign_literature.trial_grade
     *
     * @param trialGrade the value for foreign_literature.trial_grade
     *
     * @mbg.generated Mon Apr 06 15:56:02 CST 2020
     */
    public void setTrialGrade(Byte trialGrade) {
        this.trialGrade = trialGrade;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column foreign_literature.trial_comment
     *
     * @return the value of foreign_literature.trial_comment
     *
     * @mbg.generated Mon Apr 06 15:56:02 CST 2020
     */
    public String getTrialComment() {
        return trialComment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column foreign_literature.trial_comment
     *
     * @param trialComment the value for foreign_literature.trial_comment
     *
     * @mbg.generated Mon Apr 06 15:56:02 CST 2020
     */
    public void setTrialComment(String trialComment) {
        this.trialComment = trialComment == null ? null : trialComment.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column foreign_literature.school_year
     *
     * @return the value of foreign_literature.school_year
     *
     * @mbg.generated Mon Apr 06 15:56:02 CST 2020
     */
    public String getSchoolYear() {
        return schoolYear;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column foreign_literature.school_year
     *
     * @param schoolYear the value for foreign_literature.school_year
     *
     * @mbg.generated Mon Apr 06 15:56:02 CST 2020
     */
    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear == null ? null : schoolYear.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column foreign_literature.gmt_create
     *
     * @return the value of foreign_literature.gmt_create
     *
     * @mbg.generated Mon Apr 06 15:56:02 CST 2020
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column foreign_literature.gmt_create
     *
     * @param gmtCreate the value for foreign_literature.gmt_create
     *
     * @mbg.generated Mon Apr 06 15:56:02 CST 2020
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column foreign_literature.gmt_modified
     *
     * @return the value of foreign_literature.gmt_modified
     *
     * @mbg.generated Mon Apr 06 15:56:02 CST 2020
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column foreign_literature.gmt_modified
     *
     * @param gmtModified the value for foreign_literature.gmt_modified
     *
     * @mbg.generated Mon Apr 06 15:56:02 CST 2020
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}