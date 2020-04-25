package com.ncu.graduation.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectSelectExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table project_select
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table project_select
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table project_select
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_select
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    public ProjectSelectExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_select
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_select
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_select
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_select
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_select
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_select
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_select
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_select
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
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
     * This method corresponds to the database table project_select
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project_select
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table project_select
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
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

        public Criteria andPnoIsNull() {
            addCriterion("pno is null");
            return (Criteria) this;
        }

        public Criteria andPnoIsNotNull() {
            addCriterion("pno is not null");
            return (Criteria) this;
        }

        public Criteria andPnoEqualTo(String value) {
            addCriterion("pno =", value, "pno");
            return (Criteria) this;
        }

        public Criteria andPnoNotEqualTo(String value) {
            addCriterion("pno <>", value, "pno");
            return (Criteria) this;
        }

        public Criteria andPnoGreaterThan(String value) {
            addCriterion("pno >", value, "pno");
            return (Criteria) this;
        }

        public Criteria andPnoGreaterThanOrEqualTo(String value) {
            addCriterion("pno >=", value, "pno");
            return (Criteria) this;
        }

        public Criteria andPnoLessThan(String value) {
            addCriterion("pno <", value, "pno");
            return (Criteria) this;
        }

        public Criteria andPnoLessThanOrEqualTo(String value) {
            addCriterion("pno <=", value, "pno");
            return (Criteria) this;
        }

        public Criteria andPnoLike(String value) {
            addCriterion("pno like", value, "pno");
            return (Criteria) this;
        }

        public Criteria andPnoNotLike(String value) {
            addCriterion("pno not like", value, "pno");
            return (Criteria) this;
        }

        public Criteria andPnoIn(List<String> values) {
            addCriterion("pno in", values, "pno");
            return (Criteria) this;
        }

        public Criteria andPnoNotIn(List<String> values) {
            addCriterion("pno not in", values, "pno");
            return (Criteria) this;
        }

        public Criteria andPnoBetween(String value1, String value2) {
            addCriterion("pno between", value1, value2, "pno");
            return (Criteria) this;
        }

        public Criteria andPnoNotBetween(String value1, String value2) {
            addCriterion("pno not between", value1, value2, "pno");
            return (Criteria) this;
        }

        public Criteria andSelectorNoIsNull() {
            addCriterion("selector_no is null");
            return (Criteria) this;
        }

        public Criteria andSelectorNoIsNotNull() {
            addCriterion("selector_no is not null");
            return (Criteria) this;
        }

        public Criteria andSelectorNoEqualTo(String value) {
            addCriterion("selector_no =", value, "selectorNo");
            return (Criteria) this;
        }

        public Criteria andSelectorNoNotEqualTo(String value) {
            addCriterion("selector_no <>", value, "selectorNo");
            return (Criteria) this;
        }

        public Criteria andSelectorNoGreaterThan(String value) {
            addCriterion("selector_no >", value, "selectorNo");
            return (Criteria) this;
        }

        public Criteria andSelectorNoGreaterThanOrEqualTo(String value) {
            addCriterion("selector_no >=", value, "selectorNo");
            return (Criteria) this;
        }

        public Criteria andSelectorNoLessThan(String value) {
            addCriterion("selector_no <", value, "selectorNo");
            return (Criteria) this;
        }

        public Criteria andSelectorNoLessThanOrEqualTo(String value) {
            addCriterion("selector_no <=", value, "selectorNo");
            return (Criteria) this;
        }

        public Criteria andSelectorNoLike(String value) {
            addCriterion("selector_no like", value, "selectorNo");
            return (Criteria) this;
        }

        public Criteria andSelectorNoNotLike(String value) {
            addCriterion("selector_no not like", value, "selectorNo");
            return (Criteria) this;
        }

        public Criteria andSelectorNoIn(List<String> values) {
            addCriterion("selector_no in", values, "selectorNo");
            return (Criteria) this;
        }

        public Criteria andSelectorNoNotIn(List<String> values) {
            addCriterion("selector_no not in", values, "selectorNo");
            return (Criteria) this;
        }

        public Criteria andSelectorNoBetween(String value1, String value2) {
            addCriterion("selector_no between", value1, value2, "selectorNo");
            return (Criteria) this;
        }

        public Criteria andSelectorNoNotBetween(String value1, String value2) {
            addCriterion("selector_no not between", value1, value2, "selectorNo");
            return (Criteria) this;
        }

        public Criteria andSelectorNameIsNull() {
            addCriterion("selector_name is null");
            return (Criteria) this;
        }

        public Criteria andSelectorNameIsNotNull() {
            addCriterion("selector_name is not null");
            return (Criteria) this;
        }

        public Criteria andSelectorNameEqualTo(String value) {
            addCriterion("selector_name =", value, "selectorName");
            return (Criteria) this;
        }

        public Criteria andSelectorNameNotEqualTo(String value) {
            addCriterion("selector_name <>", value, "selectorName");
            return (Criteria) this;
        }

        public Criteria andSelectorNameGreaterThan(String value) {
            addCriterion("selector_name >", value, "selectorName");
            return (Criteria) this;
        }

        public Criteria andSelectorNameGreaterThanOrEqualTo(String value) {
            addCriterion("selector_name >=", value, "selectorName");
            return (Criteria) this;
        }

        public Criteria andSelectorNameLessThan(String value) {
            addCriterion("selector_name <", value, "selectorName");
            return (Criteria) this;
        }

        public Criteria andSelectorNameLessThanOrEqualTo(String value) {
            addCriterion("selector_name <=", value, "selectorName");
            return (Criteria) this;
        }

        public Criteria andSelectorNameLike(String value) {
            addCriterion("selector_name like", value, "selectorName");
            return (Criteria) this;
        }

        public Criteria andSelectorNameNotLike(String value) {
            addCriterion("selector_name not like", value, "selectorName");
            return (Criteria) this;
        }

        public Criteria andSelectorNameIn(List<String> values) {
            addCriterion("selector_name in", values, "selectorName");
            return (Criteria) this;
        }

        public Criteria andSelectorNameNotIn(List<String> values) {
            addCriterion("selector_name not in", values, "selectorName");
            return (Criteria) this;
        }

        public Criteria andSelectorNameBetween(String value1, String value2) {
            addCriterion("selector_name between", value1, value2, "selectorName");
            return (Criteria) this;
        }

        public Criteria andSelectorNameNotBetween(String value1, String value2) {
            addCriterion("selector_name not between", value1, value2, "selectorName");
            return (Criteria) this;
        }

        public Criteria andIsSelectIsNull() {
            addCriterion("is_select is null");
            return (Criteria) this;
        }

        public Criteria andIsSelectIsNotNull() {
            addCriterion("is_select is not null");
            return (Criteria) this;
        }

        public Criteria andIsSelectEqualTo(Byte value) {
            addCriterion("is_select =", value, "isSelect");
            return (Criteria) this;
        }

        public Criteria andIsSelectNotEqualTo(Byte value) {
            addCriterion("is_select <>", value, "isSelect");
            return (Criteria) this;
        }

        public Criteria andIsSelectGreaterThan(Byte value) {
            addCriterion("is_select >", value, "isSelect");
            return (Criteria) this;
        }

        public Criteria andIsSelectGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_select >=", value, "isSelect");
            return (Criteria) this;
        }

        public Criteria andIsSelectLessThan(Byte value) {
            addCriterion("is_select <", value, "isSelect");
            return (Criteria) this;
        }

        public Criteria andIsSelectLessThanOrEqualTo(Byte value) {
            addCriterion("is_select <=", value, "isSelect");
            return (Criteria) this;
        }

        public Criteria andIsSelectIn(List<Byte> values) {
            addCriterion("is_select in", values, "isSelect");
            return (Criteria) this;
        }

        public Criteria andIsSelectNotIn(List<Byte> values) {
            addCriterion("is_select not in", values, "isSelect");
            return (Criteria) this;
        }

        public Criteria andIsSelectBetween(Byte value1, Byte value2) {
            addCriterion("is_select between", value1, value2, "isSelect");
            return (Criteria) this;
        }

        public Criteria andIsSelectNotBetween(Byte value1, Byte value2) {
            addCriterion("is_select not between", value1, value2, "isSelect");
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

        public Criteria andGmtCreateIsNull() {
            addCriterion("gmt_create is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("gmt_create is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(Date value) {
            addCriterion("gmt_create =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(Date value) {
            addCriterion("gmt_create <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(Date value) {
            addCriterion("gmt_create >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_create >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Date value) {
            addCriterion("gmt_create <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(Date value) {
            addCriterion("gmt_create <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<Date> values) {
            addCriterion("gmt_create in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<Date> values) {
            addCriterion("gmt_create not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(Date value1, Date value2) {
            addCriterion("gmt_create between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(Date value1, Date value2) {
            addCriterion("gmt_create not between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNull() {
            addCriterion("gmt_modified is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNotNull() {
            addCriterion("gmt_modified is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedEqualTo(Date value) {
            addCriterion("gmt_modified =", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotEqualTo(Date value) {
            addCriterion("gmt_modified <>", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThan(Date value) {
            addCriterion("gmt_modified >", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_modified >=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThan(Date value) {
            addCriterion("gmt_modified <", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThanOrEqualTo(Date value) {
            addCriterion("gmt_modified <=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIn(List<Date> values) {
            addCriterion("gmt_modified in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotIn(List<Date> values) {
            addCriterion("gmt_modified not in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedBetween(Date value1, Date value2) {
            addCriterion("gmt_modified between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotBetween(Date value1, Date value2) {
            addCriterion("gmt_modified not between", value1, value2, "gmtModified");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table project_select
     *
     * @mbg.generated do_not_delete_during_merge Mon Apr 20 10:53:33 CST 2020
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table project_select
     *
     * @mbg.generated Mon Apr 20 10:53:33 CST 2020
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