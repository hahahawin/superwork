package com.superwork.apcosplatform.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PSmhSettingExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public PSmhSettingExample() {
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

        public Criteria andSettingIdIsNull() {
            addCriterion("SETTING_ID is null");
            return (Criteria) this;
        }

        public Criteria andSettingIdIsNotNull() {
            addCriterion("SETTING_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSettingIdEqualTo(BigDecimal value) {
            addCriterion("SETTING_ID =", value, "settingId");
            return (Criteria) this;
        }

        public Criteria andSettingIdNotEqualTo(BigDecimal value) {
            addCriterion("SETTING_ID <>", value, "settingId");
            return (Criteria) this;
        }

        public Criteria andSettingIdGreaterThan(BigDecimal value) {
            addCriterion("SETTING_ID >", value, "settingId");
            return (Criteria) this;
        }

        public Criteria andSettingIdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SETTING_ID >=", value, "settingId");
            return (Criteria) this;
        }

        public Criteria andSettingIdLessThan(BigDecimal value) {
            addCriterion("SETTING_ID <", value, "settingId");
            return (Criteria) this;
        }

        public Criteria andSettingIdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SETTING_ID <=", value, "settingId");
            return (Criteria) this;
        }

        public Criteria andSettingIdIn(List<BigDecimal> values) {
            addCriterion("SETTING_ID in", values, "settingId");
            return (Criteria) this;
        }

        public Criteria andSettingIdNotIn(List<BigDecimal> values) {
            addCriterion("SETTING_ID not in", values, "settingId");
            return (Criteria) this;
        }

        public Criteria andSettingIdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SETTING_ID between", value1, value2, "settingId");
            return (Criteria) this;
        }

        public Criteria andSettingIdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SETTING_ID not between", value1, value2, "settingId");
            return (Criteria) this;
        }

        public Criteria andSmarthomeAccountIsNull() {
            addCriterion("SMARTHOME_ACCOUNT is null");
            return (Criteria) this;
        }

        public Criteria andSmarthomeAccountIsNotNull() {
            addCriterion("SMARTHOME_ACCOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andSmarthomeAccountEqualTo(String value) {
            addCriterion("SMARTHOME_ACCOUNT =", value, "smarthomeAccount");
            return (Criteria) this;
        }

        public Criteria andSmarthomeAccountNotEqualTo(String value) {
            addCriterion("SMARTHOME_ACCOUNT <>", value, "smarthomeAccount");
            return (Criteria) this;
        }

        public Criteria andSmarthomeAccountGreaterThan(String value) {
            addCriterion("SMARTHOME_ACCOUNT >", value, "smarthomeAccount");
            return (Criteria) this;
        }

        public Criteria andSmarthomeAccountGreaterThanOrEqualTo(String value) {
            addCriterion("SMARTHOME_ACCOUNT >=", value, "smarthomeAccount");
            return (Criteria) this;
        }

        public Criteria andSmarthomeAccountLessThan(String value) {
            addCriterion("SMARTHOME_ACCOUNT <", value, "smarthomeAccount");
            return (Criteria) this;
        }

        public Criteria andSmarthomeAccountLessThanOrEqualTo(String value) {
            addCriterion("SMARTHOME_ACCOUNT <=", value, "smarthomeAccount");
            return (Criteria) this;
        }

        public Criteria andSmarthomeAccountLike(String value) {
            addCriterion("SMARTHOME_ACCOUNT like", value, "smarthomeAccount");
            return (Criteria) this;
        }

        public Criteria andSmarthomeAccountNotLike(String value) {
            addCriterion("SMARTHOME_ACCOUNT not like", value, "smarthomeAccount");
            return (Criteria) this;
        }

        public Criteria andSmarthomeAccountIn(List<String> values) {
            addCriterion("SMARTHOME_ACCOUNT in", values, "smarthomeAccount");
            return (Criteria) this;
        }

        public Criteria andSmarthomeAccountNotIn(List<String> values) {
            addCriterion("SMARTHOME_ACCOUNT not in", values, "smarthomeAccount");
            return (Criteria) this;
        }

        public Criteria andSmarthomeAccountBetween(String value1, String value2) {
            addCriterion("SMARTHOME_ACCOUNT between", value1, value2, "smarthomeAccount");
            return (Criteria) this;
        }

        public Criteria andSmarthomeAccountNotBetween(String value1, String value2) {
            addCriterion("SMARTHOME_ACCOUNT not between", value1, value2, "smarthomeAccount");
            return (Criteria) this;
        }

        public Criteria andSmarthomePwdIsNull() {
            addCriterion("SMARTHOME_PWD is null");
            return (Criteria) this;
        }

        public Criteria andSmarthomePwdIsNotNull() {
            addCriterion("SMARTHOME_PWD is not null");
            return (Criteria) this;
        }

        public Criteria andSmarthomePwdEqualTo(String value) {
            addCriterion("SMARTHOME_PWD =", value, "smarthomePwd");
            return (Criteria) this;
        }

        public Criteria andSmarthomePwdNotEqualTo(String value) {
            addCriterion("SMARTHOME_PWD <>", value, "smarthomePwd");
            return (Criteria) this;
        }

        public Criteria andSmarthomePwdGreaterThan(String value) {
            addCriterion("SMARTHOME_PWD >", value, "smarthomePwd");
            return (Criteria) this;
        }

        public Criteria andSmarthomePwdGreaterThanOrEqualTo(String value) {
            addCriterion("SMARTHOME_PWD >=", value, "smarthomePwd");
            return (Criteria) this;
        }

        public Criteria andSmarthomePwdLessThan(String value) {
            addCriterion("SMARTHOME_PWD <", value, "smarthomePwd");
            return (Criteria) this;
        }

        public Criteria andSmarthomePwdLessThanOrEqualTo(String value) {
            addCriterion("SMARTHOME_PWD <=", value, "smarthomePwd");
            return (Criteria) this;
        }

        public Criteria andSmarthomePwdLike(String value) {
            addCriterion("SMARTHOME_PWD like", value, "smarthomePwd");
            return (Criteria) this;
        }

        public Criteria andSmarthomePwdNotLike(String value) {
            addCriterion("SMARTHOME_PWD not like", value, "smarthomePwd");
            return (Criteria) this;
        }

        public Criteria andSmarthomePwdIn(List<String> values) {
            addCriterion("SMARTHOME_PWD in", values, "smarthomePwd");
            return (Criteria) this;
        }

        public Criteria andSmarthomePwdNotIn(List<String> values) {
            addCriterion("SMARTHOME_PWD not in", values, "smarthomePwd");
            return (Criteria) this;
        }

        public Criteria andSmarthomePwdBetween(String value1, String value2) {
            addCriterion("SMARTHOME_PWD between", value1, value2, "smarthomePwd");
            return (Criteria) this;
        }

        public Criteria andSmarthomePwdNotBetween(String value1, String value2) {
            addCriterion("SMARTHOME_PWD not between", value1, value2, "smarthomePwd");
            return (Criteria) this;
        }

        public Criteria andSfktIsNull() {
            addCriterion("SFKT is null");
            return (Criteria) this;
        }

        public Criteria andSfktIsNotNull() {
            addCriterion("SFKT is not null");
            return (Criteria) this;
        }

        public Criteria andSfktEqualTo(String value) {
            addCriterion("SFKT =", value, "sfkt");
            return (Criteria) this;
        }

        public Criteria andSfktNotEqualTo(String value) {
            addCriterion("SFKT <>", value, "sfkt");
            return (Criteria) this;
        }

        public Criteria andSfktGreaterThan(String value) {
            addCriterion("SFKT >", value, "sfkt");
            return (Criteria) this;
        }

        public Criteria andSfktGreaterThanOrEqualTo(String value) {
            addCriterion("SFKT >=", value, "sfkt");
            return (Criteria) this;
        }

        public Criteria andSfktLessThan(String value) {
            addCriterion("SFKT <", value, "sfkt");
            return (Criteria) this;
        }

        public Criteria andSfktLessThanOrEqualTo(String value) {
            addCriterion("SFKT <=", value, "sfkt");
            return (Criteria) this;
        }

        public Criteria andSfktLike(String value) {
            addCriterion("SFKT like", value, "sfkt");
            return (Criteria) this;
        }

        public Criteria andSfktNotLike(String value) {
            addCriterion("SFKT not like", value, "sfkt");
            return (Criteria) this;
        }

        public Criteria andSfktIn(List<String> values) {
            addCriterion("SFKT in", values, "sfkt");
            return (Criteria) this;
        }

        public Criteria andSfktNotIn(List<String> values) {
            addCriterion("SFKT not in", values, "sfkt");
            return (Criteria) this;
        }

        public Criteria andSfktBetween(String value1, String value2) {
            addCriterion("SFKT between", value1, value2, "sfkt");
            return (Criteria) this;
        }

        public Criteria andSfktNotBetween(String value1, String value2) {
            addCriterion("SFKT not between", value1, value2, "sfkt");
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

        public Criteria andAttr1Like(String value) {
            addCriterion("ATTR_1 like", value, "attr1");
            return (Criteria) this;
        }

        public Criteria andAttr1NotLike(String value) {
            addCriterion("ATTR_1 not like", value, "attr1");
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

        public Criteria andAttr2Like(String value) {
            addCriterion("ATTR_2 like", value, "attr2");
            return (Criteria) this;
        }

        public Criteria andAttr2NotLike(String value) {
            addCriterion("ATTR_2 not like", value, "attr2");
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

        public Criteria andAttr3Like(String value) {
            addCriterion("ATTR_3 like", value, "attr3");
            return (Criteria) this;
        }

        public Criteria andAttr3NotLike(String value) {
            addCriterion("ATTR_3 not like", value, "attr3");
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

        public Criteria andAttr4IsNull() {
            addCriterion("ATTR_4 is null");
            return (Criteria) this;
        }

        public Criteria andAttr4IsNotNull() {
            addCriterion("ATTR_4 is not null");
            return (Criteria) this;
        }

        public Criteria andAttr4EqualTo(String value) {
            addCriterion("ATTR_4 =", value, "attr4");
            return (Criteria) this;
        }

        public Criteria andAttr4NotEqualTo(String value) {
            addCriterion("ATTR_4 <>", value, "attr4");
            return (Criteria) this;
        }

        public Criteria andAttr4GreaterThan(String value) {
            addCriterion("ATTR_4 >", value, "attr4");
            return (Criteria) this;
        }

        public Criteria andAttr4GreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_4 >=", value, "attr4");
            return (Criteria) this;
        }

        public Criteria andAttr4LessThan(String value) {
            addCriterion("ATTR_4 <", value, "attr4");
            return (Criteria) this;
        }

        public Criteria andAttr4LessThanOrEqualTo(String value) {
            addCriterion("ATTR_4 <=", value, "attr4");
            return (Criteria) this;
        }

        public Criteria andAttr4Like(String value) {
            addCriterion("ATTR_4 like", value, "attr4");
            return (Criteria) this;
        }

        public Criteria andAttr4NotLike(String value) {
            addCriterion("ATTR_4 not like", value, "attr4");
            return (Criteria) this;
        }

        public Criteria andAttr4In(List<String> values) {
            addCriterion("ATTR_4 in", values, "attr4");
            return (Criteria) this;
        }

        public Criteria andAttr4NotIn(List<String> values) {
            addCriterion("ATTR_4 not in", values, "attr4");
            return (Criteria) this;
        }

        public Criteria andAttr4Between(String value1, String value2) {
            addCriterion("ATTR_4 between", value1, value2, "attr4");
            return (Criteria) this;
        }

        public Criteria andAttr4NotBetween(String value1, String value2) {
            addCriterion("ATTR_4 not between", value1, value2, "attr4");
            return (Criteria) this;
        }

        public Criteria andAttr6IsNull() {
            addCriterion("ATTR_6 is null");
            return (Criteria) this;
        }

        public Criteria andAttr6IsNotNull() {
            addCriterion("ATTR_6 is not null");
            return (Criteria) this;
        }

        public Criteria andAttr6EqualTo(String value) {
            addCriterion("ATTR_6 =", value, "attr6");
            return (Criteria) this;
        }

        public Criteria andAttr6NotEqualTo(String value) {
            addCriterion("ATTR_6 <>", value, "attr6");
            return (Criteria) this;
        }

        public Criteria andAttr6GreaterThan(String value) {
            addCriterion("ATTR_6 >", value, "attr6");
            return (Criteria) this;
        }

        public Criteria andAttr6GreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_6 >=", value, "attr6");
            return (Criteria) this;
        }

        public Criteria andAttr6LessThan(String value) {
            addCriterion("ATTR_6 <", value, "attr6");
            return (Criteria) this;
        }

        public Criteria andAttr6LessThanOrEqualTo(String value) {
            addCriterion("ATTR_6 <=", value, "attr6");
            return (Criteria) this;
        }

        public Criteria andAttr6Like(String value) {
            addCriterion("ATTR_6 like", value, "attr6");
            return (Criteria) this;
        }

        public Criteria andAttr6NotLike(String value) {
            addCriterion("ATTR_6 not like", value, "attr6");
            return (Criteria) this;
        }

        public Criteria andAttr6In(List<String> values) {
            addCriterion("ATTR_6 in", values, "attr6");
            return (Criteria) this;
        }

        public Criteria andAttr6NotIn(List<String> values) {
            addCriterion("ATTR_6 not in", values, "attr6");
            return (Criteria) this;
        }

        public Criteria andAttr6Between(String value1, String value2) {
            addCriterion("ATTR_6 between", value1, value2, "attr6");
            return (Criteria) this;
        }

        public Criteria andAttr6NotBetween(String value1, String value2) {
            addCriterion("ATTR_6 not between", value1, value2, "attr6");
            return (Criteria) this;
        }

        public Criteria andAttr7IsNull() {
            addCriterion("ATTR_7 is null");
            return (Criteria) this;
        }

        public Criteria andAttr7IsNotNull() {
            addCriterion("ATTR_7 is not null");
            return (Criteria) this;
        }

        public Criteria andAttr7EqualTo(String value) {
            addCriterion("ATTR_7 =", value, "attr7");
            return (Criteria) this;
        }

        public Criteria andAttr7NotEqualTo(String value) {
            addCriterion("ATTR_7 <>", value, "attr7");
            return (Criteria) this;
        }

        public Criteria andAttr7GreaterThan(String value) {
            addCriterion("ATTR_7 >", value, "attr7");
            return (Criteria) this;
        }

        public Criteria andAttr7GreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_7 >=", value, "attr7");
            return (Criteria) this;
        }

        public Criteria andAttr7LessThan(String value) {
            addCriterion("ATTR_7 <", value, "attr7");
            return (Criteria) this;
        }

        public Criteria andAttr7LessThanOrEqualTo(String value) {
            addCriterion("ATTR_7 <=", value, "attr7");
            return (Criteria) this;
        }

        public Criteria andAttr7Like(String value) {
            addCriterion("ATTR_7 like", value, "attr7");
            return (Criteria) this;
        }

        public Criteria andAttr7NotLike(String value) {
            addCriterion("ATTR_7 not like", value, "attr7");
            return (Criteria) this;
        }

        public Criteria andAttr7In(List<String> values) {
            addCriterion("ATTR_7 in", values, "attr7");
            return (Criteria) this;
        }

        public Criteria andAttr7NotIn(List<String> values) {
            addCriterion("ATTR_7 not in", values, "attr7");
            return (Criteria) this;
        }

        public Criteria andAttr7Between(String value1, String value2) {
            addCriterion("ATTR_7 between", value1, value2, "attr7");
            return (Criteria) this;
        }

        public Criteria andAttr7NotBetween(String value1, String value2) {
            addCriterion("ATTR_7 not between", value1, value2, "attr7");
            return (Criteria) this;
        }

        public Criteria andAttr8IsNull() {
            addCriterion("ATTR_8 is null");
            return (Criteria) this;
        }

        public Criteria andAttr8IsNotNull() {
            addCriterion("ATTR_8 is not null");
            return (Criteria) this;
        }

        public Criteria andAttr8EqualTo(String value) {
            addCriterion("ATTR_8 =", value, "attr8");
            return (Criteria) this;
        }

        public Criteria andAttr8NotEqualTo(String value) {
            addCriterion("ATTR_8 <>", value, "attr8");
            return (Criteria) this;
        }

        public Criteria andAttr8GreaterThan(String value) {
            addCriterion("ATTR_8 >", value, "attr8");
            return (Criteria) this;
        }

        public Criteria andAttr8GreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_8 >=", value, "attr8");
            return (Criteria) this;
        }

        public Criteria andAttr8LessThan(String value) {
            addCriterion("ATTR_8 <", value, "attr8");
            return (Criteria) this;
        }

        public Criteria andAttr8LessThanOrEqualTo(String value) {
            addCriterion("ATTR_8 <=", value, "attr8");
            return (Criteria) this;
        }

        public Criteria andAttr8Like(String value) {
            addCriterion("ATTR_8 like", value, "attr8");
            return (Criteria) this;
        }

        public Criteria andAttr8NotLike(String value) {
            addCriterion("ATTR_8 not like", value, "attr8");
            return (Criteria) this;
        }

        public Criteria andAttr8In(List<String> values) {
            addCriterion("ATTR_8 in", values, "attr8");
            return (Criteria) this;
        }

        public Criteria andAttr8NotIn(List<String> values) {
            addCriterion("ATTR_8 not in", values, "attr8");
            return (Criteria) this;
        }

        public Criteria andAttr8Between(String value1, String value2) {
            addCriterion("ATTR_8 between", value1, value2, "attr8");
            return (Criteria) this;
        }

        public Criteria andAttr8NotBetween(String value1, String value2) {
            addCriterion("ATTR_8 not between", value1, value2, "attr8");
            return (Criteria) this;
        }

        public Criteria andAttr9IsNull() {
            addCriterion("ATTR_9 is null");
            return (Criteria) this;
        }

        public Criteria andAttr9IsNotNull() {
            addCriterion("ATTR_9 is not null");
            return (Criteria) this;
        }

        public Criteria andAttr9EqualTo(String value) {
            addCriterion("ATTR_9 =", value, "attr9");
            return (Criteria) this;
        }

        public Criteria andAttr9NotEqualTo(String value) {
            addCriterion("ATTR_9 <>", value, "attr9");
            return (Criteria) this;
        }

        public Criteria andAttr9GreaterThan(String value) {
            addCriterion("ATTR_9 >", value, "attr9");
            return (Criteria) this;
        }

        public Criteria andAttr9GreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_9 >=", value, "attr9");
            return (Criteria) this;
        }

        public Criteria andAttr9LessThan(String value) {
            addCriterion("ATTR_9 <", value, "attr9");
            return (Criteria) this;
        }

        public Criteria andAttr9LessThanOrEqualTo(String value) {
            addCriterion("ATTR_9 <=", value, "attr9");
            return (Criteria) this;
        }

        public Criteria andAttr9Like(String value) {
            addCriterion("ATTR_9 like", value, "attr9");
            return (Criteria) this;
        }

        public Criteria andAttr9NotLike(String value) {
            addCriterion("ATTR_9 not like", value, "attr9");
            return (Criteria) this;
        }

        public Criteria andAttr9In(List<String> values) {
            addCriterion("ATTR_9 in", values, "attr9");
            return (Criteria) this;
        }

        public Criteria andAttr9NotIn(List<String> values) {
            addCriterion("ATTR_9 not in", values, "attr9");
            return (Criteria) this;
        }

        public Criteria andAttr9Between(String value1, String value2) {
            addCriterion("ATTR_9 between", value1, value2, "attr9");
            return (Criteria) this;
        }

        public Criteria andAttr9NotBetween(String value1, String value2) {
            addCriterion("ATTR_9 not between", value1, value2, "attr9");
            return (Criteria) this;
        }

        public Criteria andAttr5IsNull() {
            addCriterion("ATTR_5 is null");
            return (Criteria) this;
        }

        public Criteria andAttr5IsNotNull() {
            addCriterion("ATTR_5 is not null");
            return (Criteria) this;
        }

        public Criteria andAttr5EqualTo(String value) {
            addCriterion("ATTR_5 =", value, "attr5");
            return (Criteria) this;
        }

        public Criteria andAttr5NotEqualTo(String value) {
            addCriterion("ATTR_5 <>", value, "attr5");
            return (Criteria) this;
        }

        public Criteria andAttr5GreaterThan(String value) {
            addCriterion("ATTR_5 >", value, "attr5");
            return (Criteria) this;
        }

        public Criteria andAttr5GreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_5 >=", value, "attr5");
            return (Criteria) this;
        }

        public Criteria andAttr5LessThan(String value) {
            addCriterion("ATTR_5 <", value, "attr5");
            return (Criteria) this;
        }

        public Criteria andAttr5LessThanOrEqualTo(String value) {
            addCriterion("ATTR_5 <=", value, "attr5");
            return (Criteria) this;
        }

        public Criteria andAttr5Like(String value) {
            addCriterion("ATTR_5 like", value, "attr5");
            return (Criteria) this;
        }

        public Criteria andAttr5NotLike(String value) {
            addCriterion("ATTR_5 not like", value, "attr5");
            return (Criteria) this;
        }

        public Criteria andAttr5In(List<String> values) {
            addCriterion("ATTR_5 in", values, "attr5");
            return (Criteria) this;
        }

        public Criteria andAttr5NotIn(List<String> values) {
            addCriterion("ATTR_5 not in", values, "attr5");
            return (Criteria) this;
        }

        public Criteria andAttr5Between(String value1, String value2) {
            addCriterion("ATTR_5 between", value1, value2, "attr5");
            return (Criteria) this;
        }

        public Criteria andAttr5NotBetween(String value1, String value2) {
            addCriterion("ATTR_5 not between", value1, value2, "attr5");
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