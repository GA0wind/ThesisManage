package com.ncu.graduation.model;

import java.util.ArrayList;
import java.util.List;

public class ProjectPlanExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table project_plan
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table project_plan
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table project_plan
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_plan
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    public ProjectPlanExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_plan
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_plan
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_plan
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_plan
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_plan
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_plan
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_plan
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_plan
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_plan
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_plan
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table project_plan
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andProjectApplyTimeIsNull() {
            addCriterion("project_apply_time is null");
            return (Criteria) this;
        }

        public Criteria andProjectApplyTimeIsNotNull() {
            addCriterion("project_apply_time is not null");
            return (Criteria) this;
        }

        public Criteria andProjectApplyTimeEqualTo(String value) {
            addCriterion("project_apply_time =", value, "projectApplyTime");
            return (Criteria) this;
        }

        public Criteria andProjectApplyTimeNotEqualTo(String value) {
            addCriterion("project_apply_time <>", value, "projectApplyTime");
            return (Criteria) this;
        }

        public Criteria andProjectApplyTimeGreaterThan(String value) {
            addCriterion("project_apply_time >", value, "projectApplyTime");
            return (Criteria) this;
        }

        public Criteria andProjectApplyTimeGreaterThanOrEqualTo(String value) {
            addCriterion("project_apply_time >=", value, "projectApplyTime");
            return (Criteria) this;
        }

        public Criteria andProjectApplyTimeLessThan(String value) {
            addCriterion("project_apply_time <", value, "projectApplyTime");
            return (Criteria) this;
        }

        public Criteria andProjectApplyTimeLessThanOrEqualTo(String value) {
            addCriterion("project_apply_time <=", value, "projectApplyTime");
            return (Criteria) this;
        }

        public Criteria andProjectApplyTimeLike(String value) {
            addCriterion("project_apply_time like", value, "projectApplyTime");
            return (Criteria) this;
        }

        public Criteria andProjectApplyTimeNotLike(String value) {
            addCriterion("project_apply_time not like", value, "projectApplyTime");
            return (Criteria) this;
        }

        public Criteria andProjectApplyTimeIn(List<String> values) {
            addCriterion("project_apply_time in", values, "projectApplyTime");
            return (Criteria) this;
        }

        public Criteria andProjectApplyTimeNotIn(List<String> values) {
            addCriterion("project_apply_time not in", values, "projectApplyTime");
            return (Criteria) this;
        }

        public Criteria andProjectApplyTimeBetween(String value1, String value2) {
            addCriterion("project_apply_time between", value1, value2, "projectApplyTime");
            return (Criteria) this;
        }

        public Criteria andProjectApplyTimeNotBetween(String value1, String value2) {
            addCriterion("project_apply_time not between", value1, value2, "projectApplyTime");
            return (Criteria) this;
        }

        public Criteria andProjectSelectTimeIsNull() {
            addCriterion("project_select_time is null");
            return (Criteria) this;
        }

        public Criteria andProjectSelectTimeIsNotNull() {
            addCriterion("project_select_time is not null");
            return (Criteria) this;
        }

        public Criteria andProjectSelectTimeEqualTo(String value) {
            addCriterion("project_select_time =", value, "projectSelectTime");
            return (Criteria) this;
        }

        public Criteria andProjectSelectTimeNotEqualTo(String value) {
            addCriterion("project_select_time <>", value, "projectSelectTime");
            return (Criteria) this;
        }

        public Criteria andProjectSelectTimeGreaterThan(String value) {
            addCriterion("project_select_time >", value, "projectSelectTime");
            return (Criteria) this;
        }

        public Criteria andProjectSelectTimeGreaterThanOrEqualTo(String value) {
            addCriterion("project_select_time >=", value, "projectSelectTime");
            return (Criteria) this;
        }

        public Criteria andProjectSelectTimeLessThan(String value) {
            addCriterion("project_select_time <", value, "projectSelectTime");
            return (Criteria) this;
        }

        public Criteria andProjectSelectTimeLessThanOrEqualTo(String value) {
            addCriterion("project_select_time <=", value, "projectSelectTime");
            return (Criteria) this;
        }

        public Criteria andProjectSelectTimeLike(String value) {
            addCriterion("project_select_time like", value, "projectSelectTime");
            return (Criteria) this;
        }

        public Criteria andProjectSelectTimeNotLike(String value) {
            addCriterion("project_select_time not like", value, "projectSelectTime");
            return (Criteria) this;
        }

        public Criteria andProjectSelectTimeIn(List<String> values) {
            addCriterion("project_select_time in", values, "projectSelectTime");
            return (Criteria) this;
        }

        public Criteria andProjectSelectTimeNotIn(List<String> values) {
            addCriterion("project_select_time not in", values, "projectSelectTime");
            return (Criteria) this;
        }

        public Criteria andProjectSelectTimeBetween(String value1, String value2) {
            addCriterion("project_select_time between", value1, value2, "projectSelectTime");
            return (Criteria) this;
        }

        public Criteria andProjectSelectTimeNotBetween(String value1, String value2) {
            addCriterion("project_select_time not between", value1, value2, "projectSelectTime");
            return (Criteria) this;
        }

        public Criteria andTaskBookTimeIsNull() {
            addCriterion("task_book_time is null");
            return (Criteria) this;
        }

        public Criteria andTaskBookTimeIsNotNull() {
            addCriterion("task_book_time is not null");
            return (Criteria) this;
        }

        public Criteria andTaskBookTimeEqualTo(String value) {
            addCriterion("task_book_time =", value, "taskBookTime");
            return (Criteria) this;
        }

        public Criteria andTaskBookTimeNotEqualTo(String value) {
            addCriterion("task_book_time <>", value, "taskBookTime");
            return (Criteria) this;
        }

        public Criteria andTaskBookTimeGreaterThan(String value) {
            addCriterion("task_book_time >", value, "taskBookTime");
            return (Criteria) this;
        }

        public Criteria andTaskBookTimeGreaterThanOrEqualTo(String value) {
            addCriterion("task_book_time >=", value, "taskBookTime");
            return (Criteria) this;
        }

        public Criteria andTaskBookTimeLessThan(String value) {
            addCriterion("task_book_time <", value, "taskBookTime");
            return (Criteria) this;
        }

        public Criteria andTaskBookTimeLessThanOrEqualTo(String value) {
            addCriterion("task_book_time <=", value, "taskBookTime");
            return (Criteria) this;
        }

        public Criteria andTaskBookTimeLike(String value) {
            addCriterion("task_book_time like", value, "taskBookTime");
            return (Criteria) this;
        }

        public Criteria andTaskBookTimeNotLike(String value) {
            addCriterion("task_book_time not like", value, "taskBookTime");
            return (Criteria) this;
        }

        public Criteria andTaskBookTimeIn(List<String> values) {
            addCriterion("task_book_time in", values, "taskBookTime");
            return (Criteria) this;
        }

        public Criteria andTaskBookTimeNotIn(List<String> values) {
            addCriterion("task_book_time not in", values, "taskBookTime");
            return (Criteria) this;
        }

        public Criteria andTaskBookTimeBetween(String value1, String value2) {
            addCriterion("task_book_time between", value1, value2, "taskBookTime");
            return (Criteria) this;
        }

        public Criteria andTaskBookTimeNotBetween(String value1, String value2) {
            addCriterion("task_book_time not between", value1, value2, "taskBookTime");
            return (Criteria) this;
        }

        public Criteria andOpenReportTimeIsNull() {
            addCriterion("open_report_time is null");
            return (Criteria) this;
        }

        public Criteria andOpenReportTimeIsNotNull() {
            addCriterion("open_report_time is not null");
            return (Criteria) this;
        }

        public Criteria andOpenReportTimeEqualTo(String value) {
            addCriterion("open_report_time =", value, "openReportTime");
            return (Criteria) this;
        }

        public Criteria andOpenReportTimeNotEqualTo(String value) {
            addCriterion("open_report_time <>", value, "openReportTime");
            return (Criteria) this;
        }

        public Criteria andOpenReportTimeGreaterThan(String value) {
            addCriterion("open_report_time >", value, "openReportTime");
            return (Criteria) this;
        }

        public Criteria andOpenReportTimeGreaterThanOrEqualTo(String value) {
            addCriterion("open_report_time >=", value, "openReportTime");
            return (Criteria) this;
        }

        public Criteria andOpenReportTimeLessThan(String value) {
            addCriterion("open_report_time <", value, "openReportTime");
            return (Criteria) this;
        }

        public Criteria andOpenReportTimeLessThanOrEqualTo(String value) {
            addCriterion("open_report_time <=", value, "openReportTime");
            return (Criteria) this;
        }

        public Criteria andOpenReportTimeLike(String value) {
            addCriterion("open_report_time like", value, "openReportTime");
            return (Criteria) this;
        }

        public Criteria andOpenReportTimeNotLike(String value) {
            addCriterion("open_report_time not like", value, "openReportTime");
            return (Criteria) this;
        }

        public Criteria andOpenReportTimeIn(List<String> values) {
            addCriterion("open_report_time in", values, "openReportTime");
            return (Criteria) this;
        }

        public Criteria andOpenReportTimeNotIn(List<String> values) {
            addCriterion("open_report_time not in", values, "openReportTime");
            return (Criteria) this;
        }

        public Criteria andOpenReportTimeBetween(String value1, String value2) {
            addCriterion("open_report_time between", value1, value2, "openReportTime");
            return (Criteria) this;
        }

        public Criteria andOpenReportTimeNotBetween(String value1, String value2) {
            addCriterion("open_report_time not between", value1, value2, "openReportTime");
            return (Criteria) this;
        }

        public Criteria andForeignLiteratureTimeIsNull() {
            addCriterion("foreign_literature_time is null");
            return (Criteria) this;
        }

        public Criteria andForeignLiteratureTimeIsNotNull() {
            addCriterion("foreign_literature_time is not null");
            return (Criteria) this;
        }

        public Criteria andForeignLiteratureTimeEqualTo(String value) {
            addCriterion("foreign_literature_time =", value, "foreignLiteratureTime");
            return (Criteria) this;
        }

        public Criteria andForeignLiteratureTimeNotEqualTo(String value) {
            addCriterion("foreign_literature_time <>", value, "foreignLiteratureTime");
            return (Criteria) this;
        }

        public Criteria andForeignLiteratureTimeGreaterThan(String value) {
            addCriterion("foreign_literature_time >", value, "foreignLiteratureTime");
            return (Criteria) this;
        }

        public Criteria andForeignLiteratureTimeGreaterThanOrEqualTo(String value) {
            addCriterion("foreign_literature_time >=", value, "foreignLiteratureTime");
            return (Criteria) this;
        }

        public Criteria andForeignLiteratureTimeLessThan(String value) {
            addCriterion("foreign_literature_time <", value, "foreignLiteratureTime");
            return (Criteria) this;
        }

        public Criteria andForeignLiteratureTimeLessThanOrEqualTo(String value) {
            addCriterion("foreign_literature_time <=", value, "foreignLiteratureTime");
            return (Criteria) this;
        }

        public Criteria andForeignLiteratureTimeLike(String value) {
            addCriterion("foreign_literature_time like", value, "foreignLiteratureTime");
            return (Criteria) this;
        }

        public Criteria andForeignLiteratureTimeNotLike(String value) {
            addCriterion("foreign_literature_time not like", value, "foreignLiteratureTime");
            return (Criteria) this;
        }

        public Criteria andForeignLiteratureTimeIn(List<String> values) {
            addCriterion("foreign_literature_time in", values, "foreignLiteratureTime");
            return (Criteria) this;
        }

        public Criteria andForeignLiteratureTimeNotIn(List<String> values) {
            addCriterion("foreign_literature_time not in", values, "foreignLiteratureTime");
            return (Criteria) this;
        }

        public Criteria andForeignLiteratureTimeBetween(String value1, String value2) {
            addCriterion("foreign_literature_time between", value1, value2, "foreignLiteratureTime");
            return (Criteria) this;
        }

        public Criteria andForeignLiteratureTimeNotBetween(String value1, String value2) {
            addCriterion("foreign_literature_time not between", value1, value2, "foreignLiteratureTime");
            return (Criteria) this;
        }

        public Criteria andOralExaminationTimeIsNull() {
            addCriterion("oral_examination_time is null");
            return (Criteria) this;
        }

        public Criteria andOralExaminationTimeIsNotNull() {
            addCriterion("oral_examination_time is not null");
            return (Criteria) this;
        }

        public Criteria andOralExaminationTimeEqualTo(String value) {
            addCriterion("oral_examination_time =", value, "oralExaminationTime");
            return (Criteria) this;
        }

        public Criteria andOralExaminationTimeNotEqualTo(String value) {
            addCriterion("oral_examination_time <>", value, "oralExaminationTime");
            return (Criteria) this;
        }

        public Criteria andOralExaminationTimeGreaterThan(String value) {
            addCriterion("oral_examination_time >", value, "oralExaminationTime");
            return (Criteria) this;
        }

        public Criteria andOralExaminationTimeGreaterThanOrEqualTo(String value) {
            addCriterion("oral_examination_time >=", value, "oralExaminationTime");
            return (Criteria) this;
        }

        public Criteria andOralExaminationTimeLessThan(String value) {
            addCriterion("oral_examination_time <", value, "oralExaminationTime");
            return (Criteria) this;
        }

        public Criteria andOralExaminationTimeLessThanOrEqualTo(String value) {
            addCriterion("oral_examination_time <=", value, "oralExaminationTime");
            return (Criteria) this;
        }

        public Criteria andOralExaminationTimeLike(String value) {
            addCriterion("oral_examination_time like", value, "oralExaminationTime");
            return (Criteria) this;
        }

        public Criteria andOralExaminationTimeNotLike(String value) {
            addCriterion("oral_examination_time not like", value, "oralExaminationTime");
            return (Criteria) this;
        }

        public Criteria andOralExaminationTimeIn(List<String> values) {
            addCriterion("oral_examination_time in", values, "oralExaminationTime");
            return (Criteria) this;
        }

        public Criteria andOralExaminationTimeNotIn(List<String> values) {
            addCriterion("oral_examination_time not in", values, "oralExaminationTime");
            return (Criteria) this;
        }

        public Criteria andOralExaminationTimeBetween(String value1, String value2) {
            addCriterion("oral_examination_time between", value1, value2, "oralExaminationTime");
            return (Criteria) this;
        }

        public Criteria andOralExaminationTimeNotBetween(String value1, String value2) {
            addCriterion("oral_examination_time not between", value1, value2, "oralExaminationTime");
            return (Criteria) this;
        }

        public Criteria andThesisTimeIsNull() {
            addCriterion("thesis_time is null");
            return (Criteria) this;
        }

        public Criteria andThesisTimeIsNotNull() {
            addCriterion("thesis_time is not null");
            return (Criteria) this;
        }

        public Criteria andThesisTimeEqualTo(String value) {
            addCriterion("thesis_time =", value, "thesisTime");
            return (Criteria) this;
        }

        public Criteria andThesisTimeNotEqualTo(String value) {
            addCriterion("thesis_time <>", value, "thesisTime");
            return (Criteria) this;
        }

        public Criteria andThesisTimeGreaterThan(String value) {
            addCriterion("thesis_time >", value, "thesisTime");
            return (Criteria) this;
        }

        public Criteria andThesisTimeGreaterThanOrEqualTo(String value) {
            addCriterion("thesis_time >=", value, "thesisTime");
            return (Criteria) this;
        }

        public Criteria andThesisTimeLessThan(String value) {
            addCriterion("thesis_time <", value, "thesisTime");
            return (Criteria) this;
        }

        public Criteria andThesisTimeLessThanOrEqualTo(String value) {
            addCriterion("thesis_time <=", value, "thesisTime");
            return (Criteria) this;
        }

        public Criteria andThesisTimeLike(String value) {
            addCriterion("thesis_time like", value, "thesisTime");
            return (Criteria) this;
        }

        public Criteria andThesisTimeNotLike(String value) {
            addCriterion("thesis_time not like", value, "thesisTime");
            return (Criteria) this;
        }

        public Criteria andThesisTimeIn(List<String> values) {
            addCriterion("thesis_time in", values, "thesisTime");
            return (Criteria) this;
        }

        public Criteria andThesisTimeNotIn(List<String> values) {
            addCriterion("thesis_time not in", values, "thesisTime");
            return (Criteria) this;
        }

        public Criteria andThesisTimeBetween(String value1, String value2) {
            addCriterion("thesis_time between", value1, value2, "thesisTime");
            return (Criteria) this;
        }

        public Criteria andThesisTimeNotBetween(String value1, String value2) {
            addCriterion("thesis_time not between", value1, value2, "thesisTime");
            return (Criteria) this;
        }

        public Criteria andSchoolYearIsNull() {
            addCriterion("school_year is null");
            return (Criteria) this;
        }

        public Criteria andSchoolYearIsNotNull() {
            addCriterion("school_year is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolYearEqualTo(String value) {
            addCriterion("school_year =", value, "schoolYear");
            return (Criteria) this;
        }

        public Criteria andSchoolYearNotEqualTo(String value) {
            addCriterion("school_year <>", value, "schoolYear");
            return (Criteria) this;
        }

        public Criteria andSchoolYearGreaterThan(String value) {
            addCriterion("school_year >", value, "schoolYear");
            return (Criteria) this;
        }

        public Criteria andSchoolYearGreaterThanOrEqualTo(String value) {
            addCriterion("school_year >=", value, "schoolYear");
            return (Criteria) this;
        }

        public Criteria andSchoolYearLessThan(String value) {
            addCriterion("school_year <", value, "schoolYear");
            return (Criteria) this;
        }

        public Criteria andSchoolYearLessThanOrEqualTo(String value) {
            addCriterion("school_year <=", value, "schoolYear");
            return (Criteria) this;
        }

        public Criteria andSchoolYearLike(String value) {
            addCriterion("school_year like", value, "schoolYear");
            return (Criteria) this;
        }

        public Criteria andSchoolYearNotLike(String value) {
            addCriterion("school_year not like", value, "schoolYear");
            return (Criteria) this;
        }

        public Criteria andSchoolYearIn(List<String> values) {
            addCriterion("school_year in", values, "schoolYear");
            return (Criteria) this;
        }

        public Criteria andSchoolYearNotIn(List<String> values) {
            addCriterion("school_year not in", values, "schoolYear");
            return (Criteria) this;
        }

        public Criteria andSchoolYearBetween(String value1, String value2) {
            addCriterion("school_year between", value1, value2, "schoolYear");
            return (Criteria) this;
        }

        public Criteria andSchoolYearNotBetween(String value1, String value2) {
            addCriterion("school_year not between", value1, value2, "schoolYear");
            return (Criteria) this;
        }

        public Criteria andScoreCompositionIsNull() {
            addCriterion("score_composition is null");
            return (Criteria) this;
        }

        public Criteria andScoreCompositionIsNotNull() {
            addCriterion("score_composition is not null");
            return (Criteria) this;
        }

        public Criteria andScoreCompositionEqualTo(String value) {
            addCriterion("score_composition =", value, "scoreComposition");
            return (Criteria) this;
        }

        public Criteria andScoreCompositionNotEqualTo(String value) {
            addCriterion("score_composition <>", value, "scoreComposition");
            return (Criteria) this;
        }

        public Criteria andScoreCompositionGreaterThan(String value) {
            addCriterion("score_composition >", value, "scoreComposition");
            return (Criteria) this;
        }

        public Criteria andScoreCompositionGreaterThanOrEqualTo(String value) {
            addCriterion("score_composition >=", value, "scoreComposition");
            return (Criteria) this;
        }

        public Criteria andScoreCompositionLessThan(String value) {
            addCriterion("score_composition <", value, "scoreComposition");
            return (Criteria) this;
        }

        public Criteria andScoreCompositionLessThanOrEqualTo(String value) {
            addCriterion("score_composition <=", value, "scoreComposition");
            return (Criteria) this;
        }

        public Criteria andScoreCompositionLike(String value) {
            addCriterion("score_composition like", value, "scoreComposition");
            return (Criteria) this;
        }

        public Criteria andScoreCompositionNotLike(String value) {
            addCriterion("score_composition not like", value, "scoreComposition");
            return (Criteria) this;
        }

        public Criteria andScoreCompositionIn(List<String> values) {
            addCriterion("score_composition in", values, "scoreComposition");
            return (Criteria) this;
        }

        public Criteria andScoreCompositionNotIn(List<String> values) {
            addCriterion("score_composition not in", values, "scoreComposition");
            return (Criteria) this;
        }

        public Criteria andScoreCompositionBetween(String value1, String value2) {
            addCriterion("score_composition between", value1, value2, "scoreComposition");
            return (Criteria) this;
        }

        public Criteria andScoreCompositionNotBetween(String value1, String value2) {
            addCriterion("score_composition not between", value1, value2, "scoreComposition");
            return (Criteria) this;
        }

        public Criteria andGuidanceRecordTimeIsNull() {
            addCriterion("guidance_record_time is null");
            return (Criteria) this;
        }

        public Criteria andGuidanceRecordTimeIsNotNull() {
            addCriterion("guidance_record_time is not null");
            return (Criteria) this;
        }

        public Criteria andGuidanceRecordTimeEqualTo(String value) {
            addCriterion("guidance_record_time =", value, "guidanceRecordTime");
            return (Criteria) this;
        }

        public Criteria andGuidanceRecordTimeNotEqualTo(String value) {
            addCriterion("guidance_record_time <>", value, "guidanceRecordTime");
            return (Criteria) this;
        }

        public Criteria andGuidanceRecordTimeGreaterThan(String value) {
            addCriterion("guidance_record_time >", value, "guidanceRecordTime");
            return (Criteria) this;
        }

        public Criteria andGuidanceRecordTimeGreaterThanOrEqualTo(String value) {
            addCriterion("guidance_record_time >=", value, "guidanceRecordTime");
            return (Criteria) this;
        }

        public Criteria andGuidanceRecordTimeLessThan(String value) {
            addCriterion("guidance_record_time <", value, "guidanceRecordTime");
            return (Criteria) this;
        }

        public Criteria andGuidanceRecordTimeLessThanOrEqualTo(String value) {
            addCriterion("guidance_record_time <=", value, "guidanceRecordTime");
            return (Criteria) this;
        }

        public Criteria andGuidanceRecordTimeLike(String value) {
            addCriterion("guidance_record_time like", value, "guidanceRecordTime");
            return (Criteria) this;
        }

        public Criteria andGuidanceRecordTimeNotLike(String value) {
            addCriterion("guidance_record_time not like", value, "guidanceRecordTime");
            return (Criteria) this;
        }

        public Criteria andGuidanceRecordTimeIn(List<String> values) {
            addCriterion("guidance_record_time in", values, "guidanceRecordTime");
            return (Criteria) this;
        }

        public Criteria andGuidanceRecordTimeNotIn(List<String> values) {
            addCriterion("guidance_record_time not in", values, "guidanceRecordTime");
            return (Criteria) this;
        }

        public Criteria andGuidanceRecordTimeBetween(String value1, String value2) {
            addCriterion("guidance_record_time between", value1, value2, "guidanceRecordTime");
            return (Criteria) this;
        }

        public Criteria andGuidanceRecordTimeNotBetween(String value1, String value2) {
            addCriterion("guidance_record_time not between", value1, value2, "guidanceRecordTime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table project_plan
     *
     * @mbg.generated do_not_delete_during_merge Tue May 12 20:39:34 CST 2020
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table project_plan
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}