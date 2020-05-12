package com.ncu.graduation.model;

import java.util.Date;

public class Student {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.id
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.sno
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    private String sno;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.sname
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    private String sname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.password
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.college
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    private String college;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.major
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    private String major;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.grade_class
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    private String gradeClass;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.school_year
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    private String schoolYear;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.score
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    private Byte score;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.group_num
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    private Integer groupNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.gmt_create
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    private Date gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column student.gmt_modified
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    private Date gmtModified;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.id
     *
     * @return the value of student.id
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.id
     *
     * @param id the value for student.id
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.sno
     *
     * @return the value of student.sno
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public String getSno() {
        return sno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.sno
     *
     * @param sno the value for student.sno
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public void setSno(String sno) {
        this.sno = sno == null ? null : sno.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.sname
     *
     * @return the value of student.sname
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public String getSname() {
        return sname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.sname
     *
     * @param sname the value for student.sname
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public void setSname(String sname) {
        this.sname = sname == null ? null : sname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.password
     *
     * @return the value of student.password
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.password
     *
     * @param password the value for student.password
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.college
     *
     * @return the value of student.college
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public String getCollege() {
        return college;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.college
     *
     * @param college the value for student.college
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public void setCollege(String college) {
        this.college = college == null ? null : college.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.major
     *
     * @return the value of student.major
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public String getMajor() {
        return major;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.major
     *
     * @param major the value for student.major
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.grade_class
     *
     * @return the value of student.grade_class
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public String getGradeClass() {
        return gradeClass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.grade_class
     *
     * @param gradeClass the value for student.grade_class
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public void setGradeClass(String gradeClass) {
        this.gradeClass = gradeClass == null ? null : gradeClass.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.school_year
     *
     * @return the value of student.school_year
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public String getSchoolYear() {
        return schoolYear;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.school_year
     *
     * @param schoolYear the value for student.school_year
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear == null ? null : schoolYear.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.score
     *
     * @return the value of student.score
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public Byte getScore() {
        return score;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.score
     *
     * @param score the value for student.score
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public void setScore(Byte score) {
        this.score = score;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.group_num
     *
     * @return the value of student.group_num
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public Integer getGroupNum() {
        return groupNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.group_num
     *
     * @param groupNum the value for student.group_num
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public void setGroupNum(Integer groupNum) {
        this.groupNum = groupNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.gmt_create
     *
     * @return the value of student.gmt_create
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.gmt_create
     *
     * @param gmtCreate the value for student.gmt_create
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column student.gmt_modified
     *
     * @return the value of student.gmt_modified
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column student.gmt_modified
     *
     * @param gmtModified the value for student.gmt_modified
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}