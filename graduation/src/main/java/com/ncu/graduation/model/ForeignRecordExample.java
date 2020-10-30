package com.ncu.graduation.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ForeignRecordExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table foreign_record
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table foreign_record
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table foreign_record
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table foreign_record
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    public ForeignRecordExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table foreign_record
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table foreign_record
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table foreign_record
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table foreign_record
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table foreign_record
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table foreign_record
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table foreign_record
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
     * This method corresponds to the database table foreign_record
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
     * This method corresponds to the database table foreign_record
     *
     * @mbg.generated Tue May 12 20:39:34 CST 2020
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table foreign_record
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
     * This class corresponds to the database table foreign_record
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

        public Criteria andForeignFileIsNull() {
            addCriterion("foreign_file is null");
            return (Criteria) this;
        }

        public Criteria andForeignFileIsNotNull() {
            addCriterion("foreign_file is not null");
            return (Criteria) this;
        }

        public Criteria andForeignFileEqualTo(String value) {
            addCriterion("foreign_file =", value, "foreignFile");
            return (Criteria) this;
        }

        public Criteria andForeignFileNotEqualTo(String value) {
            addCriterion("foreign_file <>", value, "foreignFile");
            return (Criteria) this;
        }

        public Criteria andForeignFileGreaterThan(String value) {
            addCriterion("foreign_file >", value, "foreignFile");
            return (Criteria) this;
        }

        public Criteria andForeignFileGreaterThanOrEqualTo(String value) {
            addCriterion("foreign_file >=", value, "foreignFile");
            return (Criteria) this;
        }

        public Criteria andForeignFileLessThan(String value) {
            addCriterion("foreign_file <", value, "foreignFile");
            return (Criteria) this;
        }

        public Criteria andForeignFileLessThanOrEqualTo(String value) {
            addCriterion("foreign_file <=", value, "foreignFile");
            return (Criteria) this;
        }

        public Criteria andForeignFileLike(String value) {
            addCriterion("foreign_file like", value, "foreignFile");
            return (Criteria) this;
        }

        public Criteria andForeignFileNotLike(String value) {
            addCriterion("foreign_file not like", value, "foreignFile");
            return (Criteria) this;
        }

        public Criteria andForeignFileIn(List<String> values) {
            addCriterion("foreign_file in", values, "foreignFile");
            return (Criteria) this;
        }

        public Criteria andForeignFileNotIn(List<String> values) {
            addCriterion("foreign_file not in", values, "foreignFile");
            return (Criteria) this;
        }

        public Criteria andForeignFileBetween(String value1, String value2) {
            addCriterion("foreign_file between", value1, value2, "foreignFile");
            return (Criteria) this;
        }

        public Criteria andForeignFileNotBetween(String value1, String value2) {
            addCriterion("foreign_file not between", value1, value2, "foreignFile");
            return (Criteria) this;
        }

        public Criteria andTranslationFileIsNull() {
            addCriterion("translation_file is null");
            return (Criteria) this;
        }

        public Criteria andTranslationFileIsNotNull() {
            addCriterion("translation_file is not null");
            return (Criteria) this;
        }

        public Criteria andTranslationFileEqualTo(String value) {
            addCriterion("translation_file =", value, "translationFile");
            return (Criteria) this;
        }

        public Criteria andTranslationFileNotEqualTo(String value) {
            addCriterion("translation_file <>", value, "translationFile");
            return (Criteria) this;
        }

        public Criteria andTranslationFileGreaterThan(String value) {
            addCriterion("translation_file >", value, "translationFile");
            return (Criteria) this;
        }

        public Criteria andTranslationFileGreaterThanOrEqualTo(String value) {
            addCriterion("translation_file >=", value, "translationFile");
            return (Criteria) this;
        }

        public Criteria andTranslationFileLessThan(String value) {
            addCriterion("translation_file <", value, "translationFile");
            return (Criteria) this;
        }

        public Criteria andTranslationFileLessThanOrEqualTo(String value) {
            addCriterion("translation_file <=", value, "translationFile");
            return (Criteria) this;
        }

        public Criteria andTranslationFileLike(String value) {
            addCriterion("translation_file like", value, "translationFile");
            return (Criteria) this;
        }

        public Criteria andTranslationFileNotLike(String value) {
            addCriterion("translation_file not like", value, "translationFile");
            return (Criteria) this;
        }

        public Criteria andTranslationFileIn(List<String> values) {
            addCriterion("translation_file in", values, "translationFile");
            return (Criteria) this;
        }

        public Criteria andTranslationFileNotIn(List<String> values) {
            addCriterion("translation_file not in", values, "translationFile");
            return (Criteria) this;
        }

        public Criteria andTranslationFileBetween(String value1, String value2) {
            addCriterion("translation_file between", value1, value2, "translationFile");
            return (Criteria) this;
        }

        public Criteria andTranslationFileNotBetween(String value1, String value2) {
            addCriterion("translation_file not between", value1, value2, "translationFile");
            return (Criteria) this;
        }

        public Criteria andIsPassIsNull() {
            addCriterion("is_pass is null");
            return (Criteria) this;
        }

        public Criteria andIsPassIsNotNull() {
            addCriterion("is_pass is not null");
            return (Criteria) this;
        }

        public Criteria andIsPassEqualTo(Byte value) {
            addCriterion("is_pass =", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassNotEqualTo(Byte value) {
            addCriterion("is_pass <>", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassGreaterThan(Byte value) {
            addCriterion("is_pass >", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_pass >=", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassLessThan(Byte value) {
            addCriterion("is_pass <", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassLessThanOrEqualTo(Byte value) {
            addCriterion("is_pass <=", value, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassIn(List<Byte> values) {
            addCriterion("is_pass in", values, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassNotIn(List<Byte> values) {
            addCriterion("is_pass not in", values, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassBetween(Byte value1, Byte value2) {
            addCriterion("is_pass between", value1, value2, "isPass");
            return (Criteria) this;
        }

        public Criteria andIsPassNotBetween(Byte value1, Byte value2) {
            addCriterion("is_pass not between", value1, value2, "isPass");
            return (Criteria) this;
        }

        public Criteria andTrialGradeIsNull() {
            addCriterion("trial_grade is null");
            return (Criteria) this;
        }

        public Criteria andTrialGradeIsNotNull() {
            addCriterion("trial_grade is not null");
            return (Criteria) this;
        }

        public Criteria andTrialGradeEqualTo(Byte value) {
            addCriterion("trial_grade =", value, "trialGrade");
            return (Criteria) this;
        }

        public Criteria andTrialGradeNotEqualTo(Byte value) {
            addCriterion("trial_grade <>", value, "trialGrade");
            return (Criteria) this;
        }

        public Criteria andTrialGradeGreaterThan(Byte value) {
            addCriterion("trial_grade >", value, "trialGrade");
            return (Criteria) this;
        }

        public Criteria andTrialGradeGreaterThanOrEqualTo(Byte value) {
            addCriterion("trial_grade >=", value, "trialGrade");
            return (Criteria) this;
        }

        public Criteria andTrialGradeLessThan(Byte value) {
            addCriterion("trial_grade <", value, "trialGrade");
            return (Criteria) this;
        }

        public Criteria andTrialGradeLessThanOrEqualTo(Byte value) {
            addCriterion("trial_grade <=", value, "trialGrade");
            return (Criteria) this;
        }

        public Criteria andTrialGradeIn(List<Byte> values) {
            addCriterion("trial_grade in", values, "trialGrade");
            return (Criteria) this;
        }

        public Criteria andTrialGradeNotIn(List<Byte> values) {
            addCriterion("trial_grade not in", values, "trialGrade");
            return (Criteria) this;
        }

        public Criteria andTrialGradeBetween(Byte value1, Byte value2) {
            addCriterion("trial_grade between", value1, value2, "trialGrade");
            return (Criteria) this;
        }

        public Criteria andTrialGradeNotBetween(Byte value1, Byte value2) {
            addCriterion("trial_grade not between", value1, value2, "trialGrade");
            return (Criteria) this;
        }

        public Criteria andTrialCommentIsNull() {
            addCriterion("trial_comment is null");
            return (Criteria) this;
        }

        public Criteria andTrialCommentIsNotNull() {
            addCriterion("trial_comment is not null");
            return (Criteria) this;
        }

        public Criteria andTrialCommentEqualTo(String value) {
            addCriterion("trial_comment =", value, "trialComment");
            return (Criteria) this;
        }

        public Criteria andTrialCommentNotEqualTo(String value) {
            addCriterion("trial_comment <>", value, "trialComment");
            return (Criteria) this;
        }

        public Criteria andTrialCommentGreaterThan(String value) {
            addCriterion("trial_comment >", value, "trialComment");
            return (Criteria) this;
        }

        public Criteria andTrialCommentGreaterThanOrEqualTo(String value) {
            addCriterion("trial_comment >=", value, "trialComment");
            return (Criteria) this;
        }

        public Criteria andTrialCommentLessThan(String value) {
            addCriterion("trial_comment <", value, "trialComment");
            return (Criteria) this;
        }

        public Criteria andTrialCommentLessThanOrEqualTo(String value) {
            addCriterion("trial_comment <=", value, "trialComment");
            return (Criteria) this;
        }

        public Criteria andTrialCommentLike(String value) {
            addCriterion("trial_comment like", value, "trialComment");
            return (Criteria) this;
        }

        public Criteria andTrialCommentNotLike(String value) {
            addCriterion("trial_comment not like", value, "trialComment");
            return (Criteria) this;
        }

        public Criteria andTrialCommentIn(List<String> values) {
            addCriterion("trial_comment in", values, "trialComment");
            return (Criteria) this;
        }

        public Criteria andTrialCommentNotIn(List<String> values) {
            addCriterion("trial_comment not in", values, "trialComment");
            return (Criteria) this;
        }

        public Criteria andTrialCommentBetween(String value1, String value2) {
            addCriterion("trial_comment between", value1, value2, "trialComment");
            return (Criteria) this;
        }

        public Criteria andTrialCommentNotBetween(String value1, String value2) {
            addCriterion("trial_comment not between", value1, value2, "trialComment");
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
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table foreign_record
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
     * This class corresponds to the database table foreign_record
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