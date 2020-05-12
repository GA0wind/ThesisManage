package com.ncu.graduation.model;

import java.util.ArrayList;
import java.util.List;

public class CollegeExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table college
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table college
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table college
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table college
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public CollegeExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table college
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table college
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table college
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table college
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table college
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table college
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table college
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table college
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
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
     * This method corresponds to the database table college
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table college
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table college
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
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

        public Criteria andCollegeNoIsNull() {
            addCriterion("college_no is null");
            return (Criteria) this;
        }

        public Criteria andCollegeNoIsNotNull() {
            addCriterion("college_no is not null");
            return (Criteria) this;
        }

        public Criteria andCollegeNoEqualTo(String value) {
            addCriterion("college_no =", value, "collegeNo");
            return (Criteria) this;
        }

        public Criteria andCollegeNoNotEqualTo(String value) {
            addCriterion("college_no <>", value, "collegeNo");
            return (Criteria) this;
        }

        public Criteria andCollegeNoGreaterThan(String value) {
            addCriterion("college_no >", value, "collegeNo");
            return (Criteria) this;
        }

        public Criteria andCollegeNoGreaterThanOrEqualTo(String value) {
            addCriterion("college_no >=", value, "collegeNo");
            return (Criteria) this;
        }

        public Criteria andCollegeNoLessThan(String value) {
            addCriterion("college_no <", value, "collegeNo");
            return (Criteria) this;
        }

        public Criteria andCollegeNoLessThanOrEqualTo(String value) {
            addCriterion("college_no <=", value, "collegeNo");
            return (Criteria) this;
        }

        public Criteria andCollegeNoLike(String value) {
            addCriterion("college_no like", value, "collegeNo");
            return (Criteria) this;
        }

        public Criteria andCollegeNoNotLike(String value) {
            addCriterion("college_no not like", value, "collegeNo");
            return (Criteria) this;
        }

        public Criteria andCollegeNoIn(List<String> values) {
            addCriterion("college_no in", values, "collegeNo");
            return (Criteria) this;
        }

        public Criteria andCollegeNoNotIn(List<String> values) {
            addCriterion("college_no not in", values, "collegeNo");
            return (Criteria) this;
        }

        public Criteria andCollegeNoBetween(String value1, String value2) {
            addCriterion("college_no between", value1, value2, "collegeNo");
            return (Criteria) this;
        }

        public Criteria andCollegeNoNotBetween(String value1, String value2) {
            addCriterion("college_no not between", value1, value2, "collegeNo");
            return (Criteria) this;
        }

        public Criteria andCollegeNameIsNull() {
            addCriterion("college_name is null");
            return (Criteria) this;
        }

        public Criteria andCollegeNameIsNotNull() {
            addCriterion("college_name is not null");
            return (Criteria) this;
        }

        public Criteria andCollegeNameEqualTo(String value) {
            addCriterion("college_name =", value, "collegeName");
            return (Criteria) this;
        }

        public Criteria andCollegeNameNotEqualTo(String value) {
            addCriterion("college_name <>", value, "collegeName");
            return (Criteria) this;
        }

        public Criteria andCollegeNameGreaterThan(String value) {
            addCriterion("college_name >", value, "collegeName");
            return (Criteria) this;
        }

        public Criteria andCollegeNameGreaterThanOrEqualTo(String value) {
            addCriterion("college_name >=", value, "collegeName");
            return (Criteria) this;
        }

        public Criteria andCollegeNameLessThan(String value) {
            addCriterion("college_name <", value, "collegeName");
            return (Criteria) this;
        }

        public Criteria andCollegeNameLessThanOrEqualTo(String value) {
            addCriterion("college_name <=", value, "collegeName");
            return (Criteria) this;
        }

        public Criteria andCollegeNameLike(String value) {
            addCriterion("college_name like", value, "collegeName");
            return (Criteria) this;
        }

        public Criteria andCollegeNameNotLike(String value) {
            addCriterion("college_name not like", value, "collegeName");
            return (Criteria) this;
        }

        public Criteria andCollegeNameIn(List<String> values) {
            addCriterion("college_name in", values, "collegeName");
            return (Criteria) this;
        }

        public Criteria andCollegeNameNotIn(List<String> values) {
            addCriterion("college_name not in", values, "collegeName");
            return (Criteria) this;
        }

        public Criteria andCollegeNameBetween(String value1, String value2) {
            addCriterion("college_name between", value1, value2, "collegeName");
            return (Criteria) this;
        }

        public Criteria andCollegeNameNotBetween(String value1, String value2) {
            addCriterion("college_name not between", value1, value2, "collegeName");
            return (Criteria) this;
        }

        public Criteria andCollegeManagerNoIsNull() {
            addCriterion("college_manager_no is null");
            return (Criteria) this;
        }

        public Criteria andCollegeManagerNoIsNotNull() {
            addCriterion("college_manager_no is not null");
            return (Criteria) this;
        }

        public Criteria andCollegeManagerNoEqualTo(String value) {
            addCriterion("college_manager_no =", value, "collegeManagerNo");
            return (Criteria) this;
        }

        public Criteria andCollegeManagerNoNotEqualTo(String value) {
            addCriterion("college_manager_no <>", value, "collegeManagerNo");
            return (Criteria) this;
        }

        public Criteria andCollegeManagerNoGreaterThan(String value) {
            addCriterion("college_manager_no >", value, "collegeManagerNo");
            return (Criteria) this;
        }

        public Criteria andCollegeManagerNoGreaterThanOrEqualTo(String value) {
            addCriterion("college_manager_no >=", value, "collegeManagerNo");
            return (Criteria) this;
        }

        public Criteria andCollegeManagerNoLessThan(String value) {
            addCriterion("college_manager_no <", value, "collegeManagerNo");
            return (Criteria) this;
        }

        public Criteria andCollegeManagerNoLessThanOrEqualTo(String value) {
            addCriterion("college_manager_no <=", value, "collegeManagerNo");
            return (Criteria) this;
        }

        public Criteria andCollegeManagerNoLike(String value) {
            addCriterion("college_manager_no like", value, "collegeManagerNo");
            return (Criteria) this;
        }

        public Criteria andCollegeManagerNoNotLike(String value) {
            addCriterion("college_manager_no not like", value, "collegeManagerNo");
            return (Criteria) this;
        }

        public Criteria andCollegeManagerNoIn(List<String> values) {
            addCriterion("college_manager_no in", values, "collegeManagerNo");
            return (Criteria) this;
        }

        public Criteria andCollegeManagerNoNotIn(List<String> values) {
            addCriterion("college_manager_no not in", values, "collegeManagerNo");
            return (Criteria) this;
        }

        public Criteria andCollegeManagerNoBetween(String value1, String value2) {
            addCriterion("college_manager_no between", value1, value2, "collegeManagerNo");
            return (Criteria) this;
        }

        public Criteria andCollegeManagerNoNotBetween(String value1, String value2) {
            addCriterion("college_manager_no not between", value1, value2, "collegeManagerNo");
            return (Criteria) this;
        }

        public Criteria andCollegeManagerNameIsNull() {
            addCriterion("college_manager_name is null");
            return (Criteria) this;
        }

        public Criteria andCollegeManagerNameIsNotNull() {
            addCriterion("college_manager_name is not null");
            return (Criteria) this;
        }

        public Criteria andCollegeManagerNameEqualTo(String value) {
            addCriterion("college_manager_name =", value, "collegeManagerName");
            return (Criteria) this;
        }

        public Criteria andCollegeManagerNameNotEqualTo(String value) {
            addCriterion("college_manager_name <>", value, "collegeManagerName");
            return (Criteria) this;
        }

        public Criteria andCollegeManagerNameGreaterThan(String value) {
            addCriterion("college_manager_name >", value, "collegeManagerName");
            return (Criteria) this;
        }

        public Criteria andCollegeManagerNameGreaterThanOrEqualTo(String value) {
            addCriterion("college_manager_name >=", value, "collegeManagerName");
            return (Criteria) this;
        }

        public Criteria andCollegeManagerNameLessThan(String value) {
            addCriterion("college_manager_name <", value, "collegeManagerName");
            return (Criteria) this;
        }

        public Criteria andCollegeManagerNameLessThanOrEqualTo(String value) {
            addCriterion("college_manager_name <=", value, "collegeManagerName");
            return (Criteria) this;
        }

        public Criteria andCollegeManagerNameLike(String value) {
            addCriterion("college_manager_name like", value, "collegeManagerName");
            return (Criteria) this;
        }

        public Criteria andCollegeManagerNameNotLike(String value) {
            addCriterion("college_manager_name not like", value, "collegeManagerName");
            return (Criteria) this;
        }

        public Criteria andCollegeManagerNameIn(List<String> values) {
            addCriterion("college_manager_name in", values, "collegeManagerName");
            return (Criteria) this;
        }

        public Criteria andCollegeManagerNameNotIn(List<String> values) {
            addCriterion("college_manager_name not in", values, "collegeManagerName");
            return (Criteria) this;
        }

        public Criteria andCollegeManagerNameBetween(String value1, String value2) {
            addCriterion("college_manager_name between", value1, value2, "collegeManagerName");
            return (Criteria) this;
        }

        public Criteria andCollegeManagerNameNotBetween(String value1, String value2) {
            addCriterion("college_manager_name not between", value1, value2, "collegeManagerName");
            return (Criteria) this;
        }

        public Criteria andParentNoIsNull() {
            addCriterion("parent_no is null");
            return (Criteria) this;
        }

        public Criteria andParentNoIsNotNull() {
            addCriterion("parent_no is not null");
            return (Criteria) this;
        }

        public Criteria andParentNoEqualTo(String value) {
            addCriterion("parent_no =", value, "parentNo");
            return (Criteria) this;
        }

        public Criteria andParentNoNotEqualTo(String value) {
            addCriterion("parent_no <>", value, "parentNo");
            return (Criteria) this;
        }

        public Criteria andParentNoGreaterThan(String value) {
            addCriterion("parent_no >", value, "parentNo");
            return (Criteria) this;
        }

        public Criteria andParentNoGreaterThanOrEqualTo(String value) {
            addCriterion("parent_no >=", value, "parentNo");
            return (Criteria) this;
        }

        public Criteria andParentNoLessThan(String value) {
            addCriterion("parent_no <", value, "parentNo");
            return (Criteria) this;
        }

        public Criteria andParentNoLessThanOrEqualTo(String value) {
            addCriterion("parent_no <=", value, "parentNo");
            return (Criteria) this;
        }

        public Criteria andParentNoLike(String value) {
            addCriterion("parent_no like", value, "parentNo");
            return (Criteria) this;
        }

        public Criteria andParentNoNotLike(String value) {
            addCriterion("parent_no not like", value, "parentNo");
            return (Criteria) this;
        }

        public Criteria andParentNoIn(List<String> values) {
            addCriterion("parent_no in", values, "parentNo");
            return (Criteria) this;
        }

        public Criteria andParentNoNotIn(List<String> values) {
            addCriterion("parent_no not in", values, "parentNo");
            return (Criteria) this;
        }

        public Criteria andParentNoBetween(String value1, String value2) {
            addCriterion("parent_no between", value1, value2, "parentNo");
            return (Criteria) this;
        }

        public Criteria andParentNoNotBetween(String value1, String value2) {
            addCriterion("parent_no not between", value1, value2, "parentNo");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table college
     *
     * @mbg.generated do_not_delete_during_merge Sat May 09 00:13:38 CST 2020
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table college
     *
     * @mbg.generated Sat May 09 00:13:38 CST 2020
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