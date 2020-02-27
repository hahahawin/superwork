package com.superwork.apcosplatform.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PSbzcLxExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public PSbzcLxExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria implements Serializable {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andSbzcLxIdIsNull() {
            addCriterion("SBZC_LX_ID is null");
            return (Criteria) this;
        }

        public Criteria andSbzcLxIdIsNotNull() {
            addCriterion("SBZC_LX_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSbzcLxIdEqualTo(String value) {
            addCriterion("SBZC_LX_ID =", value, "sbzcLxId");
            return (Criteria) this;
        }

        public Criteria andSbzcLxIdNotEqualTo(String value) {
            addCriterion("SBZC_LX_ID <>", value, "sbzcLxId");
            return (Criteria) this;
        }

        public Criteria andSbzcLxIdGreaterThan(String value) {
            addCriterion("SBZC_LX_ID >", value, "sbzcLxId");
            return (Criteria) this;
        }

        public Criteria andSbzcLxIdGreaterThanOrEqualTo(String value) {
            addCriterion("SBZC_LX_ID >=", value, "sbzcLxId");
            return (Criteria) this;
        }

        public Criteria andSbzcLxIdLessThan(String value) {
            addCriterion("SBZC_LX_ID <", value, "sbzcLxId");
            return (Criteria) this;
        }

        public Criteria andSbzcLxIdLessThanOrEqualTo(String value) {
            addCriterion("SBZC_LX_ID <=", value, "sbzcLxId");
            return (Criteria) this;
        }

        public Criteria andSbzcLxIdLike(String value) {
            addCriterion("SBZC_LX_ID like", value, "sbzcLxId");
            return (Criteria) this;
        }

        public Criteria andSbzcLxIdNotLike(String value) {
            addCriterion("SBZC_LX_ID not like", value, "sbzcLxId");
            return (Criteria) this;
        }

        public Criteria andSbzcLxIdIn(List<String> values) {
            addCriterion("SBZC_LX_ID in", values, "sbzcLxId");
            return (Criteria) this;
        }

        public Criteria andSbzcLxIdNotIn(List<String> values) {
            addCriterion("SBZC_LX_ID not in", values, "sbzcLxId");
            return (Criteria) this;
        }

        public Criteria andSbzcLxIdBetween(String value1, String value2) {
            addCriterion("SBZC_LX_ID between", value1, value2, "sbzcLxId");
            return (Criteria) this;
        }

        public Criteria andSbzcLxIdNotBetween(String value1, String value2) {
            addCriterion("SBZC_LX_ID not between", value1, value2, "sbzcLxId");
            return (Criteria) this;
        }

        public Criteria andPLxIdIsNull() {
            addCriterion("P_LX_ID is null");
            return (Criteria) this;
        }

        public Criteria andPLxIdIsNotNull() {
            addCriterion("P_LX_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPLxIdEqualTo(String value) {
            addCriterion("P_LX_ID =", value, "pLxId");
            return (Criteria) this;
        }

        public Criteria andPLxIdNotEqualTo(String value) {
            addCriterion("P_LX_ID <>", value, "pLxId");
            return (Criteria) this;
        }

        public Criteria andPLxIdGreaterThan(String value) {
            addCriterion("P_LX_ID >", value, "pLxId");
            return (Criteria) this;
        }

        public Criteria andPLxIdGreaterThanOrEqualTo(String value) {
            addCriterion("P_LX_ID >=", value, "pLxId");
            return (Criteria) this;
        }

        public Criteria andPLxIdLessThan(String value) {
            addCriterion("P_LX_ID <", value, "pLxId");
            return (Criteria) this;
        }

        public Criteria andPLxIdLessThanOrEqualTo(String value) {
            addCriterion("P_LX_ID <=", value, "pLxId");
            return (Criteria) this;
        }

        public Criteria andPLxIdLike(String value) {
            addCriterion("P_LX_ID like", value, "pLxId");
            return (Criteria) this;
        }

        public Criteria andPLxIdNotLike(String value) {
            addCriterion("P_LX_ID not like", value, "pLxId");
            return (Criteria) this;
        }

        public Criteria andPLxIdIn(List<String> values) {
            addCriterion("P_LX_ID in", values, "pLxId");
            return (Criteria) this;
        }

        public Criteria andPLxIdNotIn(List<String> values) {
            addCriterion("P_LX_ID not in", values, "pLxId");
            return (Criteria) this;
        }

        public Criteria andPLxIdBetween(String value1, String value2) {
            addCriterion("P_LX_ID between", value1, value2, "pLxId");
            return (Criteria) this;
        }

        public Criteria andPLxIdNotBetween(String value1, String value2) {
            addCriterion("P_LX_ID not between", value1, value2, "pLxId");
            return (Criteria) this;
        }

        public Criteria andYtlxIsNull() {
            addCriterion("YTLX is null");
            return (Criteria) this;
        }

        public Criteria andYtlxIsNotNull() {
            addCriterion("YTLX is not null");
            return (Criteria) this;
        }

        public Criteria andYtlxEqualTo(String value) {
            addCriterion("YTLX =", value, "ytlx");
            return (Criteria) this;
        }

        public Criteria andYtlxNotEqualTo(String value) {
            addCriterion("YTLX <>", value, "ytlx");
            return (Criteria) this;
        }

        public Criteria andYtlxGreaterThan(String value) {
            addCriterion("YTLX >", value, "ytlx");
            return (Criteria) this;
        }

        public Criteria andYtlxGreaterThanOrEqualTo(String value) {
            addCriterion("YTLX >=", value, "ytlx");
            return (Criteria) this;
        }

        public Criteria andYtlxLessThan(String value) {
            addCriterion("YTLX <", value, "ytlx");
            return (Criteria) this;
        }

        public Criteria andYtlxLessThanOrEqualTo(String value) {
            addCriterion("YTLX <=", value, "ytlx");
            return (Criteria) this;
        }

        public Criteria andYtlxLike(String value) {
            addCriterion("YTLX like", value, "ytlx");
            return (Criteria) this;
        }

        public Criteria andYtlxNotLike(String value) {
            addCriterion("YTLX not like", value, "ytlx");
            return (Criteria) this;
        }

        public Criteria andYtlxIn(List<String> values) {
            addCriterion("YTLX in", values, "ytlx");
            return (Criteria) this;
        }

        public Criteria andYtlxNotIn(List<String> values) {
            addCriterion("YTLX not in", values, "ytlx");
            return (Criteria) this;
        }

        public Criteria andYtlxBetween(String value1, String value2) {
            addCriterion("YTLX between", value1, value2, "ytlx");
            return (Criteria) this;
        }

        public Criteria andYtlxNotBetween(String value1, String value2) {
            addCriterion("YTLX not between", value1, value2, "ytlx");
            return (Criteria) this;
        }

        public Criteria andMlmcIsNull() {
            addCriterion("MLMC is null");
            return (Criteria) this;
        }

        public Criteria andMlmcIsNotNull() {
            addCriterion("MLMC is not null");
            return (Criteria) this;
        }

        public Criteria andMlmcEqualTo(String value) {
            addCriterion("MLMC =", value, "mlmc");
            return (Criteria) this;
        }

        public Criteria andMlmcNotEqualTo(String value) {
            addCriterion("MLMC <>", value, "mlmc");
            return (Criteria) this;
        }

        public Criteria andMlmcGreaterThan(String value) {
            addCriterion("MLMC >", value, "mlmc");
            return (Criteria) this;
        }

        public Criteria andMlmcGreaterThanOrEqualTo(String value) {
            addCriterion("MLMC >=", value, "mlmc");
            return (Criteria) this;
        }

        public Criteria andMlmcLessThan(String value) {
            addCriterion("MLMC <", value, "mlmc");
            return (Criteria) this;
        }

        public Criteria andMlmcLessThanOrEqualTo(String value) {
            addCriterion("MLMC <=", value, "mlmc");
            return (Criteria) this;
        }

        public Criteria andMlmcIn(List<String> values) {
            addCriterion("MLMC in", values, "mlmc");
            return (Criteria) this;
        }

        public Criteria andMlmcNotIn(List<String> values) {
            addCriterion("MLMC not in", values, "mlmc");
            return (Criteria) this;
        }

        public Criteria andMlmcBetween(String value1, String value2) {
            addCriterion("MLMC between", value1, value2, "mlmc");
            return (Criteria) this;
        }

        public Criteria andMlmcNotBetween(String value1, String value2) {
            addCriterion("MLMC not between", value1, value2, "mlmc");
            return (Criteria) this;
        }

        public Criteria andMldmIsNull() {
            addCriterion("MLDM is null");
            return (Criteria) this;
        }

        public Criteria andMldmIsNotNull() {
            addCriterion("MLDM is not null");
            return (Criteria) this;
        }

        public Criteria andMldmEqualTo(String value) {
            addCriterion("MLDM =", value, "mldm");
            return (Criteria) this;
        }

        public Criteria andMldmNotEqualTo(String value) {
            addCriterion("MLDM <>", value, "mldm");
            return (Criteria) this;
        }

        public Criteria andMldmGreaterThan(String value) {
            addCriterion("MLDM >", value, "mldm");
            return (Criteria) this;
        }

        public Criteria andMldmGreaterThanOrEqualTo(String value) {
            addCriterion("MLDM >=", value, "mldm");
            return (Criteria) this;
        }

        public Criteria andMldmLessThan(String value) {
            addCriterion("MLDM <", value, "mldm");
            return (Criteria) this;
        }

        public Criteria andMldmLessThanOrEqualTo(String value) {
            addCriterion("MLDM <=", value, "mldm");
            return (Criteria) this;
        }

        public Criteria andMldmIn(List<String> values) {
            addCriterion("MLDM in", values, "mldm");
            return (Criteria) this;
        }

        public Criteria andMldmNotIn(List<String> values) {
            addCriterion("MLDM not in", values, "mldm");
            return (Criteria) this;
        }

        public Criteria andMldmBetween(String value1, String value2) {
            addCriterion("MLDM between", value1, value2, "mldm");
            return (Criteria) this;
        }

        public Criteria andMldmNotBetween(String value1, String value2) {
            addCriterion("MLDM not between", value1, value2, "mldm");
            return (Criteria) this;
        }

        public Criteria andSftyIsNull() {
            addCriterion("SFTY is null");
            return (Criteria) this;
        }

        public Criteria andSftyIsNotNull() {
            addCriterion("SFTY is not null");
            return (Criteria) this;
        }

        public Criteria andSftyEqualTo(BigDecimal value) {
            addCriterion("SFTY =", value, "sfty");
            return (Criteria) this;
        }

        public Criteria andSftyNotEqualTo(BigDecimal value) {
            addCriterion("SFTY <>", value, "sfty");
            return (Criteria) this;
        }

        public Criteria andSftyGreaterThan(BigDecimal value) {
            addCriterion("SFTY >", value, "sfty");
            return (Criteria) this;
        }

        public Criteria andSftyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SFTY >=", value, "sfty");
            return (Criteria) this;
        }

        public Criteria andSftyLessThan(BigDecimal value) {
            addCriterion("SFTY <", value, "sfty");
            return (Criteria) this;
        }

        public Criteria andSftyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SFTY <=", value, "sfty");
            return (Criteria) this;
        }

        public Criteria andSftyIn(List<BigDecimal> values) {
            addCriterion("SFTY in", values, "sfty");
            return (Criteria) this;
        }

        public Criteria andSftyNotIn(List<BigDecimal> values) {
            addCriterion("SFTY not in", values, "sfty");
            return (Criteria) this;
        }

        public Criteria andSftyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SFTY between", value1, value2, "sfty");
            return (Criteria) this;
        }

        public Criteria andSftyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SFTY not between", value1, value2, "sfty");
            return (Criteria) this;
        }

        public Criteria andBzIsNull() {
            addCriterion("BZ is null");
            return (Criteria) this;
        }

        public Criteria andBzIsNotNull() {
            addCriterion("BZ is not null");
            return (Criteria) this;
        }

        public Criteria andBzEqualTo(String value) {
            addCriterion("BZ =", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzNotEqualTo(String value) {
            addCriterion("BZ <>", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzGreaterThan(String value) {
            addCriterion("BZ >", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzGreaterThanOrEqualTo(String value) {
            addCriterion("BZ >=", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzLessThan(String value) {
            addCriterion("BZ <", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzLessThanOrEqualTo(String value) {
            addCriterion("BZ <=", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzIn(List<String> values) {
            addCriterion("BZ in", values, "bz");
            return (Criteria) this;
        }

        public Criteria andBzNotIn(List<String> values) {
            addCriterion("BZ not in", values, "bz");
            return (Criteria) this;
        }

        public Criteria andBzBetween(String value1, String value2) {
            addCriterion("BZ between", value1, value2, "bz");
            return (Criteria) this;
        }

        public Criteria andBzNotBetween(String value1, String value2) {
            addCriterion("BZ not between", value1, value2, "bz");
            return (Criteria) this;
        }

        public Criteria andAttr1IsNull() {
            addCriterion("ATTR_1 is null");
            return (Criteria) this;
        }

        public Criteria andAttr1IsNotNull() {
            addCriterion("ATTR_1 is not null");
            return (Criteria) this;
        }

        public Criteria andAttr1EqualTo(String value) {
            addCriterion("ATTR_1 =", value, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1NotEqualTo(String value) {
            addCriterion("ATTR_1 <>", value, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1GreaterThan(String value) {
            addCriterion("ATTR_1 >", value, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1GreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_1 >=", value, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1LessThan(String value) {
            addCriterion("ATTR_1 <", value, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1LessThanOrEqualTo(String value) {
            addCriterion("ATTR_1 <=", value, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1In(List<String> values) {
            addCriterion("ATTR_1 in", values, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1NotIn(List<String> values) {
            addCriterion("ATTR_1 not in", values, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1Between(String value1, String value2) {
            addCriterion("ATTR_1 between", value1, value2, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1NotBetween(String value1, String value2) {
            addCriterion("ATTR_1 not between", value1, value2, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr2IsNull() {
            addCriterion("ATTR_2 is null");
            return (Criteria) this;
        }

        public Criteria andAttr2IsNotNull() {
            addCriterion("ATTR_2 is not null");
            return (Criteria) this;
        }

        public Criteria andAttr2EqualTo(String value) {
            addCriterion("ATTR_2 =", value, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2NotEqualTo(String value) {
            addCriterion("ATTR_2 <>", value, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2GreaterThan(String value) {
            addCriterion("ATTR_2 >", value, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2GreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_2 >=", value, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2LessThan(String value) {
            addCriterion("ATTR_2 <", value, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2LessThanOrEqualTo(String value) {
            addCriterion("ATTR_2 <=", value, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2In(List<String> values) {
            addCriterion("ATTR_2 in", values, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2NotIn(List<String> values) {
            addCriterion("ATTR_2 not in", values, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2Between(String value1, String value2) {
            addCriterion("ATTR_2 between", value1, value2, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2NotBetween(String value1, String value2) {
            addCriterion("ATTR_2 not between", value1, value2, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr3IsNull() {
            addCriterion("ATTR_3 is null");
            return (Criteria) this;
        }

        public Criteria andAttr3IsNotNull() {
            addCriterion("ATTR_3 is not null");
            return (Criteria) this;
        }

        public Criteria andAttr3EqualTo(String value) {
            addCriterion("ATTR_3 =", value, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3NotEqualTo(String value) {
            addCriterion("ATTR_3 <>", value, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3GreaterThan(String value) {
            addCriterion("ATTR_3 >", value, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3GreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_3 >=", value, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3LessThan(String value) {
            addCriterion("ATTR_3 <", value, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3LessThanOrEqualTo(String value) {
            addCriterion("ATTR_3 <=", value, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3In(List<String> values) {
            addCriterion("ATTR_3 in", values, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3NotIn(List<String> values) {
            addCriterion("ATTR_3 not in", values, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3Between(String value1, String value2) {
            addCriterion("ATTR_3 between", value1, value2, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3NotBetween(String value1, String value2) {
            addCriterion("ATTR_3 not between", value1, value2, "attr3");
            return (Criteria) this;
        }

        public Criteria andCreatorIdIsNull() {
            addCriterion("CREATOR_ID is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIdIsNotNull() {
            addCriterion("CREATOR_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorIdEqualTo(String value) {
            addCriterion("CREATOR_ID =", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotEqualTo(String value) {
            addCriterion("CREATOR_ID <>", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdGreaterThan(String value) {
            addCriterion("CREATOR_ID >", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdGreaterThanOrEqualTo(String value) {
            addCriterion("CREATOR_ID >=", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdLessThan(String value) {
            addCriterion("CREATOR_ID <", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdLessThanOrEqualTo(String value) {
            addCriterion("CREATOR_ID <=", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdLike(String value) {
            addCriterion("CREATOR_ID like", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotLike(String value) {
            addCriterion("CREATOR_ID not like", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdIn(List<String> values) {
            addCriterion("CREATOR_ID in", values, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotIn(List<String> values) {
            addCriterion("CREATOR_ID not in", values, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdBetween(String value1, String value2) {
            addCriterion("CREATOR_ID between", value1, value2, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotBetween(String value1, String value2) {
            addCriterion("CREATOR_ID not between", value1, value2, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNull() {
            addCriterion("CREATED_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNotNull() {
            addCriterion("CREATED_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeEqualTo(Date value) {
            addCriterionForJDBCDate("CREATED_TIME =", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("CREATED_TIME <>", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("CREATED_TIME >", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CREATED_TIME >=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThan(Date value) {
            addCriterionForJDBCDate("CREATED_TIME <", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CREATED_TIME <=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIn(List<Date> values) {
            addCriterionForJDBCDate("CREATED_TIME in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("CREATED_TIME not in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CREATED_TIME between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CREATED_TIME not between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andEditorIdIsNull() {
            addCriterion("EDITOR_ID is null");
            return (Criteria) this;
        }

        public Criteria andEditorIdIsNotNull() {
            addCriterion("EDITOR_ID is not null");
            return (Criteria) this;
        }

        public Criteria andEditorIdEqualTo(String value) {
            addCriterion("EDITOR_ID =", value, "editorId");
            return (Criteria) this;
        }

        public Criteria andEditorIdNotEqualTo(String value) {
            addCriterion("EDITOR_ID <>", value, "editorId");
            return (Criteria) this;
        }

        public Criteria andEditorIdGreaterThan(String value) {
            addCriterion("EDITOR_ID >", value, "editorId");
            return (Criteria) this;
        }

        public Criteria andEditorIdGreaterThanOrEqualTo(String value) {
            addCriterion("EDITOR_ID >=", value, "editorId");
            return (Criteria) this;
        }

        public Criteria andEditorIdLessThan(String value) {
            addCriterion("EDITOR_ID <", value, "editorId");
            return (Criteria) this;
        }

        public Criteria andEditorIdLessThanOrEqualTo(String value) {
            addCriterion("EDITOR_ID <=", value, "editorId");
            return (Criteria) this;
        }

        public Criteria andEditorIdLike(String value) {
            addCriterion("EDITOR_ID like", value, "editorId");
            return (Criteria) this;
        }

        public Criteria andEditorIdNotLike(String value) {
            addCriterion("EDITOR_ID not like", value, "editorId");
            return (Criteria) this;
        }

        public Criteria andEditorIdIn(List<String> values) {
            addCriterion("EDITOR_ID in", values, "editorId");
            return (Criteria) this;
        }

        public Criteria andEditorIdNotIn(List<String> values) {
            addCriterion("EDITOR_ID not in", values, "editorId");
            return (Criteria) this;
        }

        public Criteria andEditorIdBetween(String value1, String value2) {
            addCriterion("EDITOR_ID between", value1, value2, "editorId");
            return (Criteria) this;
        }

        public Criteria andEditorIdNotBetween(String value1, String value2) {
            addCriterion("EDITOR_ID not between", value1, value2, "editorId");
            return (Criteria) this;
        }

        public Criteria andEditedTimeIsNull() {
            addCriterion("EDITED_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEditedTimeIsNotNull() {
            addCriterion("EDITED_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEditedTimeEqualTo(Date value) {
            addCriterionForJDBCDate("EDITED_TIME =", value, "editedTime");
            return (Criteria) this;
        }

        public Criteria andEditedTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("EDITED_TIME <>", value, "editedTime");
            return (Criteria) this;
        }

        public Criteria andEditedTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("EDITED_TIME >", value, "editedTime");
            return (Criteria) this;
        }

        public Criteria andEditedTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("EDITED_TIME >=", value, "editedTime");
            return (Criteria) this;
        }

        public Criteria andEditedTimeLessThan(Date value) {
            addCriterionForJDBCDate("EDITED_TIME <", value, "editedTime");
            return (Criteria) this;
        }

        public Criteria andEditedTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("EDITED_TIME <=", value, "editedTime");
            return (Criteria) this;
        }

        public Criteria andEditedTimeIn(List<Date> values) {
            addCriterionForJDBCDate("EDITED_TIME in", values, "editedTime");
            return (Criteria) this;
        }

        public Criteria andEditedTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("EDITED_TIME not in", values, "editedTime");
            return (Criteria) this;
        }

        public Criteria andEditedTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("EDITED_TIME between", value1, value2, "editedTime");
            return (Criteria) this;
        }

        public Criteria andEditedTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("EDITED_TIME not between", value1, value2, "editedTime");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdIsNull() {
            addCriterion("BELONG_ORG_ID is null");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdIsNotNull() {
            addCriterion("BELONG_ORG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdEqualTo(String value) {
            addCriterion("BELONG_ORG_ID =", value, "belongOrgId");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdNotEqualTo(String value) {
            addCriterion("BELONG_ORG_ID <>", value, "belongOrgId");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdGreaterThan(String value) {
            addCriterion("BELONG_ORG_ID >", value, "belongOrgId");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdGreaterThanOrEqualTo(String value) {
            addCriterion("BELONG_ORG_ID >=", value, "belongOrgId");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdLessThan(String value) {
            addCriterion("BELONG_ORG_ID <", value, "belongOrgId");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdLessThanOrEqualTo(String value) {
            addCriterion("BELONG_ORG_ID <=", value, "belongOrgId");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdLike(String value) {
            addCriterion("BELONG_ORG_ID like", value, "belongOrgId");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdNotLike(String value) {
            addCriterion("BELONG_ORG_ID not like", value, "belongOrgId");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdIn(List<String> values) {
            addCriterion("BELONG_ORG_ID in", values, "belongOrgId");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdNotIn(List<String> values) {
            addCriterion("BELONG_ORG_ID not in", values, "belongOrgId");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdBetween(String value1, String value2) {
            addCriterion("BELONG_ORG_ID between", value1, value2, "belongOrgId");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdNotBetween(String value1, String value2) {
            addCriterion("BELONG_ORG_ID not between", value1, value2, "belongOrgId");
            return (Criteria) this;
        }

        public Criteria andIconFileJsonIsNull() {
            addCriterion("ICON_FILE_JSON is null");
            return (Criteria) this;
        }

        public Criteria andIconFileJsonIsNotNull() {
            addCriterion("ICON_FILE_JSON is not null");
            return (Criteria) this;
        }

        public Criteria andIconFileJsonEqualTo(String value) {
            addCriterion("ICON_FILE_JSON =", value, "iconFileJson");
            return (Criteria) this;
        }

        public Criteria andIconFileJsonNotEqualTo(String value) {
            addCriterion("ICON_FILE_JSON <>", value, "iconFileJson");
            return (Criteria) this;
        }

        public Criteria andIconFileJsonGreaterThan(String value) {
            addCriterion("ICON_FILE_JSON >", value, "iconFileJson");
            return (Criteria) this;
        }

        public Criteria andIconFileJsonGreaterThanOrEqualTo(String value) {
            addCriterion("ICON_FILE_JSON >=", value, "iconFileJson");
            return (Criteria) this;
        }

        public Criteria andIconFileJsonLessThan(String value) {
            addCriterion("ICON_FILE_JSON <", value, "iconFileJson");
            return (Criteria) this;
        }

        public Criteria andIconFileJsonLessThanOrEqualTo(String value) {
            addCriterion("ICON_FILE_JSON <=", value, "iconFileJson");
            return (Criteria) this;
        }

        public Criteria andIconFileJsonIn(List<String> values) {
            addCriterion("ICON_FILE_JSON in", values, "iconFileJson");
            return (Criteria) this;
        }

        public Criteria andIconFileJsonNotIn(List<String> values) {
            addCriterion("ICON_FILE_JSON not in", values, "iconFileJson");
            return (Criteria) this;
        }

        public Criteria andIconFileJsonBetween(String value1, String value2) {
            addCriterion("ICON_FILE_JSON between", value1, value2, "iconFileJson");
            return (Criteria) this;
        }

        public Criteria andIconFileJsonNotBetween(String value1, String value2) {
            addCriterion("ICON_FILE_JSON not between", value1, value2, "iconFileJson");
            return (Criteria) this;
        }

        public Criteria andIconFileJsonCloseIsNull() {
            addCriterion("ICON_FILE_JSON_CLOSE is null");
            return (Criteria) this;
        }

        public Criteria andIconFileJsonCloseIsNotNull() {
            addCriterion("ICON_FILE_JSON_CLOSE is not null");
            return (Criteria) this;
        }

        public Criteria andIconFileJsonCloseEqualTo(String value) {
            addCriterion("ICON_FILE_JSON_CLOSE =", value, "iconFileJsonClose");
            return (Criteria) this;
        }

        public Criteria andIconFileJsonCloseNotEqualTo(String value) {
            addCriterion("ICON_FILE_JSON_CLOSE <>", value, "iconFileJsonClose");
            return (Criteria) this;
        }

        public Criteria andIconFileJsonCloseGreaterThan(String value) {
            addCriterion("ICON_FILE_JSON_CLOSE >", value, "iconFileJsonClose");
            return (Criteria) this;
        }

        public Criteria andIconFileJsonCloseGreaterThanOrEqualTo(String value) {
            addCriterion("ICON_FILE_JSON_CLOSE >=", value, "iconFileJsonClose");
            return (Criteria) this;
        }

        public Criteria andIconFileJsonCloseLessThan(String value) {
            addCriterion("ICON_FILE_JSON_CLOSE <", value, "iconFileJsonClose");
            return (Criteria) this;
        }

        public Criteria andIconFileJsonCloseLessThanOrEqualTo(String value) {
            addCriterion("ICON_FILE_JSON_CLOSE <=", value, "iconFileJsonClose");
            return (Criteria) this;
        }

        public Criteria andIconFileJsonCloseIn(List<String> values) {
            addCriterion("ICON_FILE_JSON_CLOSE in", values, "iconFileJsonClose");
            return (Criteria) this;
        }

        public Criteria andIconFileJsonCloseNotIn(List<String> values) {
            addCriterion("ICON_FILE_JSON_CLOSE not in", values, "iconFileJsonClose");
            return (Criteria) this;
        }

        public Criteria andIconFileJsonCloseBetween(String value1, String value2) {
            addCriterion("ICON_FILE_JSON_CLOSE between", value1, value2, "iconFileJsonClose");
            return (Criteria) this;
        }

        public Criteria andIconFileJsonCloseNotBetween(String value1, String value2) {
            addCriterion("ICON_FILE_JSON_CLOSE not between", value1, value2, "iconFileJsonClose");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfzwIsNull() {
            addCriterion("SBZCLX_SFZW is null");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfzwIsNotNull() {
            addCriterion("SBZCLX_SFZW is not null");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfzwEqualTo(String value) {
            addCriterion("SBZCLX_SFZW =", value, "sbzclxSfzw");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfzwNotEqualTo(String value) {
            addCriterion("SBZCLX_SFZW <>", value, "sbzclxSfzw");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfzwGreaterThan(String value) {
            addCriterion("SBZCLX_SFZW >", value, "sbzclxSfzw");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfzwGreaterThanOrEqualTo(String value) {
            addCriterion("SBZCLX_SFZW >=", value, "sbzclxSfzw");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfzwLessThan(String value) {
            addCriterion("SBZCLX_SFZW <", value, "sbzclxSfzw");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfzwLessThanOrEqualTo(String value) {
            addCriterion("SBZCLX_SFZW <=", value, "sbzclxSfzw");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfzwLike(String value) {
            addCriterion("SBZCLX_SFZW like", value, "sbzclxSfzw");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfzwNotLike(String value) {
            addCriterion("SBZCLX_SFZW not like", value, "sbzclxSfzw");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfzwIn(List<String> values) {
            addCriterion("SBZCLX_SFZW in", values, "sbzclxSfzw");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfzwNotIn(List<String> values) {
            addCriterion("SBZCLX_SFZW not in", values, "sbzclxSfzw");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfzwBetween(String value1, String value2) {
            addCriterion("SBZCLX_SFZW between", value1, value2, "sbzclxSfzw");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfzwNotBetween(String value1, String value2) {
            addCriterion("SBZCLX_SFZW not between", value1, value2, "sbzclxSfzw");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfcjmsIsNull() {
            addCriterion("SBZCLX_SFCJMS is null");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfcjmsIsNotNull() {
            addCriterion("SBZCLX_SFCJMS is not null");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfcjmsEqualTo(String value) {
            addCriterion("SBZCLX_SFCJMS =", value, "sbzclxSfcjms");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfcjmsNotEqualTo(String value) {
            addCriterion("SBZCLX_SFCJMS <>", value, "sbzclxSfcjms");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfcjmsGreaterThan(String value) {
            addCriterion("SBZCLX_SFCJMS >", value, "sbzclxSfcjms");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfcjmsGreaterThanOrEqualTo(String value) {
            addCriterion("SBZCLX_SFCJMS >=", value, "sbzclxSfcjms");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfcjmsLessThan(String value) {
            addCriterion("SBZCLX_SFCJMS <", value, "sbzclxSfcjms");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfcjmsLessThanOrEqualTo(String value) {
            addCriterion("SBZCLX_SFCJMS <=", value, "sbzclxSfcjms");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfcjmsLike(String value) {
            addCriterion("SBZCLX_SFCJMS like", value, "sbzclxSfcjms");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfcjmsNotLike(String value) {
            addCriterion("SBZCLX_SFCJMS not like", value, "sbzclxSfcjms");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfcjmsIn(List<String> values) {
            addCriterion("SBZCLX_SFCJMS in", values, "sbzclxSfcjms");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfcjmsNotIn(List<String> values) {
            addCriterion("SBZCLX_SFCJMS not in", values, "sbzclxSfcjms");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfcjmsBetween(String value1, String value2) {
            addCriterion("SBZCLX_SFCJMS between", value1, value2, "sbzclxSfcjms");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfcjmsNotBetween(String value1, String value2) {
            addCriterion("SBZCLX_SFCJMS not between", value1, value2, "sbzclxSfcjms");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfbmsIsNull() {
            addCriterion("SBZCLX_SFBMS is null");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfbmsIsNotNull() {
            addCriterion("SBZCLX_SFBMS is not null");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfbmsEqualTo(String value) {
            addCriterion("SBZCLX_SFBMS =", value, "sbzclxSfbms");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfbmsNotEqualTo(String value) {
            addCriterion("SBZCLX_SFBMS <>", value, "sbzclxSfbms");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfbmsGreaterThan(String value) {
            addCriterion("SBZCLX_SFBMS >", value, "sbzclxSfbms");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfbmsGreaterThanOrEqualTo(String value) {
            addCriterion("SBZCLX_SFBMS >=", value, "sbzclxSfbms");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfbmsLessThan(String value) {
            addCriterion("SBZCLX_SFBMS <", value, "sbzclxSfbms");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfbmsLessThanOrEqualTo(String value) {
            addCriterion("SBZCLX_SFBMS <=", value, "sbzclxSfbms");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfbmsLike(String value) {
            addCriterion("SBZCLX_SFBMS like", value, "sbzclxSfbms");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfbmsNotLike(String value) {
            addCriterion("SBZCLX_SFBMS not like", value, "sbzclxSfbms");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfbmsIn(List<String> values) {
            addCriterion("SBZCLX_SFBMS in", values, "sbzclxSfbms");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfbmsNotIn(List<String> values) {
            addCriterion("SBZCLX_SFBMS not in", values, "sbzclxSfbms");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfbmsBetween(String value1, String value2) {
            addCriterion("SBZCLX_SFBMS between", value1, value2, "sbzclxSfbms");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfbmsNotBetween(String value1, String value2) {
            addCriterion("SBZCLX_SFBMS not between", value1, value2, "sbzclxSfbms");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfwxIsNull() {
            addCriterion("SBZCLX_SFWX is null");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfwxIsNotNull() {
            addCriterion("SBZCLX_SFWX is not null");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfwxEqualTo(String value) {
            addCriterion("SBZCLX_SFWX =", value, "sbzclxSfwx");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfwxNotEqualTo(String value) {
            addCriterion("SBZCLX_SFWX <>", value, "sbzclxSfwx");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfwxGreaterThan(String value) {
            addCriterion("SBZCLX_SFWX >", value, "sbzclxSfwx");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfwxGreaterThanOrEqualTo(String value) {
            addCriterion("SBZCLX_SFWX >=", value, "sbzclxSfwx");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfwxLessThan(String value) {
            addCriterion("SBZCLX_SFWX <", value, "sbzclxSfwx");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfwxLessThanOrEqualTo(String value) {
            addCriterion("SBZCLX_SFWX <=", value, "sbzclxSfwx");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfwxLike(String value) {
            addCriterion("SBZCLX_SFWX like", value, "sbzclxSfwx");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfwxNotLike(String value) {
            addCriterion("SBZCLX_SFWX not like", value, "sbzclxSfwx");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfwxIn(List<String> values) {
            addCriterion("SBZCLX_SFWX in", values, "sbzclxSfwx");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfwxNotIn(List<String> values) {
            addCriterion("SBZCLX_SFWX not in", values, "sbzclxSfwx");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfwxBetween(String value1, String value2) {
            addCriterion("SBZCLX_SFWX between", value1, value2, "sbzclxSfwx");
            return (Criteria) this;
        }

        public Criteria andSbzclxSfwxNotBetween(String value1, String value2) {
            addCriterion("SBZCLX_SFWX not between", value1, value2, "sbzclxSfwx");
            return (Criteria) this;
        }

        public Criteria andSbzclxXzfsIsNull() {
            addCriterion("SBZCLX_XZFS is null");
            return (Criteria) this;
        }

        public Criteria andSbzclxXzfsIsNotNull() {
            addCriterion("SBZCLX_XZFS is not null");
            return (Criteria) this;
        }

        public Criteria andSbzclxXzfsEqualTo(String value) {
            addCriterion("SBZCLX_XZFS =", value, "sbzclxXzfs");
            return (Criteria) this;
        }

        public Criteria andSbzclxXzfsNotEqualTo(String value) {
            addCriterion("SBZCLX_XZFS <>", value, "sbzclxXzfs");
            return (Criteria) this;
        }

        public Criteria andSbzclxXzfsGreaterThan(String value) {
            addCriterion("SBZCLX_XZFS >", value, "sbzclxXzfs");
            return (Criteria) this;
        }

        public Criteria andSbzclxXzfsGreaterThanOrEqualTo(String value) {
            addCriterion("SBZCLX_XZFS >=", value, "sbzclxXzfs");
            return (Criteria) this;
        }

        public Criteria andSbzclxXzfsLessThan(String value) {
            addCriterion("SBZCLX_XZFS <", value, "sbzclxXzfs");
            return (Criteria) this;
        }

        public Criteria andSbzclxXzfsLessThanOrEqualTo(String value) {
            addCriterion("SBZCLX_XZFS <=", value, "sbzclxXzfs");
            return (Criteria) this;
        }

        public Criteria andSbzclxXzfsLike(String value) {
            addCriterion("SBZCLX_XZFS like", value, "sbzclxXzfs");
            return (Criteria) this;
        }

        public Criteria andSbzclxXzfsNotLike(String value) {
            addCriterion("SBZCLX_XZFS not like", value, "sbzclxXzfs");
            return (Criteria) this;
        }

        public Criteria andSbzclxXzfsIn(List<String> values) {
            addCriterion("SBZCLX_XZFS in", values, "sbzclxXzfs");
            return (Criteria) this;
        }

        public Criteria andSbzclxXzfsNotIn(List<String> values) {
            addCriterion("SBZCLX_XZFS not in", values, "sbzclxXzfs");
            return (Criteria) this;
        }

        public Criteria andSbzclxXzfsBetween(String value1, String value2) {
            addCriterion("SBZCLX_XZFS between", value1, value2, "sbzclxXzfs");
            return (Criteria) this;
        }

        public Criteria andSbzclxXzfsNotBetween(String value1, String value2) {
            addCriterion("SBZCLX_XZFS not between", value1, value2, "sbzclxXzfs");
            return (Criteria) this;
        }

        public Criteria andSbzclxJgcsIsNull() {
            addCriterion("SBZCLX_JGCS is null");
            return (Criteria) this;
        }

        public Criteria andSbzclxJgcsIsNotNull() {
            addCriterion("SBZCLX_JGCS is not null");
            return (Criteria) this;
        }

        public Criteria andSbzclxJgcsEqualTo(String value) {
            addCriterion("SBZCLX_JGCS =", value, "sbzclxJgcs");
            return (Criteria) this;
        }

        public Criteria andSbzclxJgcsNotEqualTo(String value) {
            addCriterion("SBZCLX_JGCS <>", value, "sbzclxJgcs");
            return (Criteria) this;
        }

        public Criteria andSbzclxJgcsGreaterThan(String value) {
            addCriterion("SBZCLX_JGCS >", value, "sbzclxJgcs");
            return (Criteria) this;
        }

        public Criteria andSbzclxJgcsGreaterThanOrEqualTo(String value) {
            addCriterion("SBZCLX_JGCS >=", value, "sbzclxJgcs");
            return (Criteria) this;
        }

        public Criteria andSbzclxJgcsLessThan(String value) {
            addCriterion("SBZCLX_JGCS <", value, "sbzclxJgcs");
            return (Criteria) this;
        }

        public Criteria andSbzclxJgcsLessThanOrEqualTo(String value) {
            addCriterion("SBZCLX_JGCS <=", value, "sbzclxJgcs");
            return (Criteria) this;
        }

        public Criteria andSbzclxJgcsLike(String value) {
            addCriterion("SBZCLX_JGCS like", value, "sbzclxJgcs");
            return (Criteria) this;
        }

        public Criteria andSbzclxJgcsNotLike(String value) {
            addCriterion("SBZCLX_JGCS not like", value, "sbzclxJgcs");
            return (Criteria) this;
        }

        public Criteria andSbzclxJgcsIn(List<String> values) {
            addCriterion("SBZCLX_JGCS in", values, "sbzclxJgcs");
            return (Criteria) this;
        }

        public Criteria andSbzclxJgcsNotIn(List<String> values) {
            addCriterion("SBZCLX_JGCS not in", values, "sbzclxJgcs");
            return (Criteria) this;
        }

        public Criteria andSbzclxJgcsBetween(String value1, String value2) {
            addCriterion("SBZCLX_JGCS between", value1, value2, "sbzclxJgcs");
            return (Criteria) this;
        }

        public Criteria andSbzclxJgcsNotBetween(String value1, String value2) {
            addCriterion("SBZCLX_JGCS not between", value1, value2, "sbzclxJgcs");
            return (Criteria) this;
        }

        public Criteria andSfyxhwpIsNull() {
            addCriterion("SFYXHWP is null");
            return (Criteria) this;
        }

        public Criteria andSfyxhwpIsNotNull() {
            addCriterion("SFYXHWP is not null");
            return (Criteria) this;
        }

        public Criteria andSfyxhwpEqualTo(String value) {
            addCriterion("SFYXHWP =", value, "sfyxhwp");
            return (Criteria) this;
        }

        public Criteria andSfyxhwpNotEqualTo(String value) {
            addCriterion("SFYXHWP <>", value, "sfyxhwp");
            return (Criteria) this;
        }

        public Criteria andSfyxhwpGreaterThan(String value) {
            addCriterion("SFYXHWP >", value, "sfyxhwp");
            return (Criteria) this;
        }

        public Criteria andSfyxhwpGreaterThanOrEqualTo(String value) {
            addCriterion("SFYXHWP >=", value, "sfyxhwp");
            return (Criteria) this;
        }

        public Criteria andSfyxhwpLessThan(String value) {
            addCriterion("SFYXHWP <", value, "sfyxhwp");
            return (Criteria) this;
        }

        public Criteria andSfyxhwpLessThanOrEqualTo(String value) {
            addCriterion("SFYXHWP <=", value, "sfyxhwp");
            return (Criteria) this;
        }

        public Criteria andSfyxhwpLike(String value) {
            addCriterion("SFYXHWP like", value, "sfyxhwp");
            return (Criteria) this;
        }

        public Criteria andSfyxhwpNotLike(String value) {
            addCriterion("SFYXHWP not like", value, "sfyxhwp");
            return (Criteria) this;
        }

        public Criteria andSfyxhwpIn(List<String> values) {
            addCriterion("SFYXHWP in", values, "sfyxhwp");
            return (Criteria) this;
        }

        public Criteria andSfyxhwpNotIn(List<String> values) {
            addCriterion("SFYXHWP not in", values, "sfyxhwp");
            return (Criteria) this;
        }

        public Criteria andSfyxhwpBetween(String value1, String value2) {
            addCriterion("SFYXHWP between", value1, value2, "sfyxhwp");
            return (Criteria) this;
        }

        public Criteria andSfyxhwpNotBetween(String value1, String value2) {
            addCriterion("SFYXHWP not between", value1, value2, "sfyxhwp");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion implements Serializable {
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