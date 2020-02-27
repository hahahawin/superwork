package com.superwork.apcosplatform.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PWebProductCmdUpExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public PWebProductCmdUpExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(BigDecimal value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(BigDecimal value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(BigDecimal value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(BigDecimal value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<BigDecimal> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<BigDecimal> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNull() {
            addCriterion("PRODUCT_ID is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("PRODUCT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(BigDecimal value) {
            addCriterion("PRODUCT_ID =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(BigDecimal value) {
            addCriterion("PRODUCT_ID <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(BigDecimal value) {
            addCriterion("PRODUCT_ID >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PRODUCT_ID >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(BigDecimal value) {
            addCriterion("PRODUCT_ID <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PRODUCT_ID <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<BigDecimal> values) {
            addCriterion("PRODUCT_ID in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<BigDecimal> values) {
            addCriterion("PRODUCT_ID not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PRODUCT_ID between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PRODUCT_ID not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andEnableRegisterIsNull() {
            addCriterion("ENABLE_REGISTER is null");
            return (Criteria) this;
        }

        public Criteria andEnableRegisterIsNotNull() {
            addCriterion("ENABLE_REGISTER is not null");
            return (Criteria) this;
        }

        public Criteria andEnableRegisterEqualTo(String value) {
            addCriterion("ENABLE_REGISTER =", value, "enableRegister");
            return (Criteria) this;
        }

        public Criteria andEnableRegisterNotEqualTo(String value) {
            addCriterion("ENABLE_REGISTER <>", value, "enableRegister");
            return (Criteria) this;
        }

        public Criteria andEnableRegisterGreaterThan(String value) {
            addCriterion("ENABLE_REGISTER >", value, "enableRegister");
            return (Criteria) this;
        }

        public Criteria andEnableRegisterGreaterThanOrEqualTo(String value) {
            addCriterion("ENABLE_REGISTER >=", value, "enableRegister");
            return (Criteria) this;
        }

        public Criteria andEnableRegisterLessThan(String value) {
            addCriterion("ENABLE_REGISTER <", value, "enableRegister");
            return (Criteria) this;
        }

        public Criteria andEnableRegisterLessThanOrEqualTo(String value) {
            addCriterion("ENABLE_REGISTER <=", value, "enableRegister");
            return (Criteria) this;
        }

        public Criteria andEnableRegisterIn(List<String> values) {
            addCriterion("ENABLE_REGISTER in", values, "enableRegister");
            return (Criteria) this;
        }

        public Criteria andEnableRegisterNotIn(List<String> values) {
            addCriterion("ENABLE_REGISTER not in", values, "enableRegister");
            return (Criteria) this;
        }

        public Criteria andEnableRegisterBetween(String value1, String value2) {
            addCriterion("ENABLE_REGISTER between", value1, value2, "enableRegister");
            return (Criteria) this;
        }

        public Criteria andEnableRegisterNotBetween(String value1, String value2) {
            addCriterion("ENABLE_REGISTER not between", value1, value2, "enableRegister");
            return (Criteria) this;
        }

        public Criteria andEnableCmdIsNull() {
            addCriterion("ENABLE_CMD is null");
            return (Criteria) this;
        }

        public Criteria andEnableCmdIsNotNull() {
            addCriterion("ENABLE_CMD is not null");
            return (Criteria) this;
        }

        public Criteria andEnableCmdEqualTo(String value) {
            addCriterion("ENABLE_CMD =", value, "enableCmd");
            return (Criteria) this;
        }

        public Criteria andEnableCmdNotEqualTo(String value) {
            addCriterion("ENABLE_CMD <>", value, "enableCmd");
            return (Criteria) this;
        }

        public Criteria andEnableCmdGreaterThan(String value) {
            addCriterion("ENABLE_CMD >", value, "enableCmd");
            return (Criteria) this;
        }

        public Criteria andEnableCmdGreaterThanOrEqualTo(String value) {
            addCriterion("ENABLE_CMD >=", value, "enableCmd");
            return (Criteria) this;
        }

        public Criteria andEnableCmdLessThan(String value) {
            addCriterion("ENABLE_CMD <", value, "enableCmd");
            return (Criteria) this;
        }

        public Criteria andEnableCmdLessThanOrEqualTo(String value) {
            addCriterion("ENABLE_CMD <=", value, "enableCmd");
            return (Criteria) this;
        }

        public Criteria andEnableCmdIn(List<String> values) {
            addCriterion("ENABLE_CMD in", values, "enableCmd");
            return (Criteria) this;
        }

        public Criteria andEnableCmdNotIn(List<String> values) {
            addCriterion("ENABLE_CMD not in", values, "enableCmd");
            return (Criteria) this;
        }

        public Criteria andEnableCmdBetween(String value1, String value2) {
            addCriterion("ENABLE_CMD between", value1, value2, "enableCmd");
            return (Criteria) this;
        }

        public Criteria andEnableCmdNotBetween(String value1, String value2) {
            addCriterion("ENABLE_CMD not between", value1, value2, "enableCmd");
            return (Criteria) this;
        }

        public Criteria andCmdTypeIsNull() {
            addCriterion("CMD_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCmdTypeIsNotNull() {
            addCriterion("CMD_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCmdTypeEqualTo(String value) {
            addCriterion("CMD_TYPE =", value, "cmdType");
            return (Criteria) this;
        }

        public Criteria andCmdTypeNotEqualTo(String value) {
            addCriterion("CMD_TYPE <>", value, "cmdType");
            return (Criteria) this;
        }

        public Criteria andCmdTypeGreaterThan(String value) {
            addCriterion("CMD_TYPE >", value, "cmdType");
            return (Criteria) this;
        }

        public Criteria andCmdTypeGreaterThanOrEqualTo(String value) {
            addCriterion("CMD_TYPE >=", value, "cmdType");
            return (Criteria) this;
        }

        public Criteria andCmdTypeLessThan(String value) {
            addCriterion("CMD_TYPE <", value, "cmdType");
            return (Criteria) this;
        }

        public Criteria andCmdTypeLessThanOrEqualTo(String value) {
            addCriterion("CMD_TYPE <=", value, "cmdType");
            return (Criteria) this;
        }

        public Criteria andCmdTypeIn(List<String> values) {
            addCriterion("CMD_TYPE in", values, "cmdType");
            return (Criteria) this;
        }

        public Criteria andCmdTypeNotIn(List<String> values) {
            addCriterion("CMD_TYPE not in", values, "cmdType");
            return (Criteria) this;
        }

        public Criteria andCmdTypeBetween(String value1, String value2) {
            addCriterion("CMD_TYPE between", value1, value2, "cmdType");
            return (Criteria) this;
        }

        public Criteria andCmdTypeNotBetween(String value1, String value2) {
            addCriterion("CMD_TYPE not between", value1, value2, "cmdType");
            return (Criteria) this;
        }

        public Criteria andCmdCodeIsNull() {
            addCriterion("CMD_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCmdCodeIsNotNull() {
            addCriterion("CMD_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCmdCodeEqualTo(String value) {
            addCriterion("CMD_CODE =", value, "cmdCode");
            return (Criteria) this;
        }

        public Criteria andCmdCodeNotEqualTo(String value) {
            addCriterion("CMD_CODE <>", value, "cmdCode");
            return (Criteria) this;
        }

        public Criteria andCmdCodeGreaterThan(String value) {
            addCriterion("CMD_CODE >", value, "cmdCode");
            return (Criteria) this;
        }

        public Criteria andCmdCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CMD_CODE >=", value, "cmdCode");
            return (Criteria) this;
        }

        public Criteria andCmdCodeLessThan(String value) {
            addCriterion("CMD_CODE <", value, "cmdCode");
            return (Criteria) this;
        }

        public Criteria andCmdCodeLessThanOrEqualTo(String value) {
            addCriterion("CMD_CODE <=", value, "cmdCode");
            return (Criteria) this;
        }

        public Criteria andCmdCodeIn(List<String> values) {
            addCriterion("CMD_CODE in", values, "cmdCode");
            return (Criteria) this;
        }

        public Criteria andCmdCodeNotIn(List<String> values) {
            addCriterion("CMD_CODE not in", values, "cmdCode");
            return (Criteria) this;
        }

        public Criteria andCmdCodeBetween(String value1, String value2) {
            addCriterion("CMD_CODE between", value1, value2, "cmdCode");
            return (Criteria) this;
        }

        public Criteria andCmdCodeNotBetween(String value1, String value2) {
            addCriterion("CMD_CODE not between", value1, value2, "cmdCode");
            return (Criteria) this;
        }

        public Criteria andCmdNameIsNull() {
            addCriterion("CMD_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCmdNameIsNotNull() {
            addCriterion("CMD_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCmdNameEqualTo(String value) {
            addCriterion("CMD_NAME =", value, "cmdName");
            return (Criteria) this;
        }

        public Criteria andCmdNameNotEqualTo(String value) {
            addCriterion("CMD_NAME <>", value, "cmdName");
            return (Criteria) this;
        }

        public Criteria andCmdNameGreaterThan(String value) {
            addCriterion("CMD_NAME >", value, "cmdName");
            return (Criteria) this;
        }

        public Criteria andCmdNameGreaterThanOrEqualTo(String value) {
            addCriterion("CMD_NAME >=", value, "cmdName");
            return (Criteria) this;
        }

        public Criteria andCmdNameLessThan(String value) {
            addCriterion("CMD_NAME <", value, "cmdName");
            return (Criteria) this;
        }

        public Criteria andCmdNameLessThanOrEqualTo(String value) {
            addCriterion("CMD_NAME <=", value, "cmdName");
            return (Criteria) this;
        }

        public Criteria andCmdNameIn(List<String> values) {
            addCriterion("CMD_NAME in", values, "cmdName");
            return (Criteria) this;
        }

        public Criteria andCmdNameNotIn(List<String> values) {
            addCriterion("CMD_NAME not in", values, "cmdName");
            return (Criteria) this;
        }

        public Criteria andCmdNameBetween(String value1, String value2) {
            addCriterion("CMD_NAME between", value1, value2, "cmdName");
            return (Criteria) this;
        }

        public Criteria andCmdNameNotBetween(String value1, String value2) {
            addCriterion("CMD_NAME not between", value1, value2, "cmdName");
            return (Criteria) this;
        }

        public Criteria andCmdRegexIsNull() {
            addCriterion("CMD_REGEX is null");
            return (Criteria) this;
        }

        public Criteria andCmdRegexIsNotNull() {
            addCriterion("CMD_REGEX is not null");
            return (Criteria) this;
        }

        public Criteria andCmdRegexEqualTo(String value) {
            addCriterion("CMD_REGEX =", value, "cmdRegex");
            return (Criteria) this;
        }

        public Criteria andCmdRegexNotEqualTo(String value) {
            addCriterion("CMD_REGEX <>", value, "cmdRegex");
            return (Criteria) this;
        }

        public Criteria andCmdRegexGreaterThan(String value) {
            addCriterion("CMD_REGEX >", value, "cmdRegex");
            return (Criteria) this;
        }

        public Criteria andCmdRegexGreaterThanOrEqualTo(String value) {
            addCriterion("CMD_REGEX >=", value, "cmdRegex");
            return (Criteria) this;
        }

        public Criteria andCmdRegexLessThan(String value) {
            addCriterion("CMD_REGEX <", value, "cmdRegex");
            return (Criteria) this;
        }

        public Criteria andCmdRegexLessThanOrEqualTo(String value) {
            addCriterion("CMD_REGEX <=", value, "cmdRegex");
            return (Criteria) this;
        }

        public Criteria andCmdRegexIn(List<String> values) {
            addCriterion("CMD_REGEX in", values, "cmdRegex");
            return (Criteria) this;
        }

        public Criteria andCmdRegexNotIn(List<String> values) {
            addCriterion("CMD_REGEX not in", values, "cmdRegex");
            return (Criteria) this;
        }

        public Criteria andCmdRegexBetween(String value1, String value2) {
            addCriterion("CMD_REGEX between", value1, value2, "cmdRegex");
            return (Criteria) this;
        }

        public Criteria andCmdRegexNotBetween(String value1, String value2) {
            addCriterion("CMD_REGEX not between", value1, value2, "cmdRegex");
            return (Criteria) this;
        }

        public Criteria andCmdReadmeIsNull() {
            addCriterion("CMD_README is null");
            return (Criteria) this;
        }

        public Criteria andCmdReadmeIsNotNull() {
            addCriterion("CMD_README is not null");
            return (Criteria) this;
        }

        public Criteria andCmdReadmeEqualTo(String value) {
            addCriterion("CMD_README =", value, "cmdReadme");
            return (Criteria) this;
        }

        public Criteria andCmdReadmeNotEqualTo(String value) {
            addCriterion("CMD_README <>", value, "cmdReadme");
            return (Criteria) this;
        }

        public Criteria andCmdReadmeGreaterThan(String value) {
            addCriterion("CMD_README >", value, "cmdReadme");
            return (Criteria) this;
        }

        public Criteria andCmdReadmeGreaterThanOrEqualTo(String value) {
            addCriterion("CMD_README >=", value, "cmdReadme");
            return (Criteria) this;
        }

        public Criteria andCmdReadmeLessThan(String value) {
            addCriterion("CMD_README <", value, "cmdReadme");
            return (Criteria) this;
        }

        public Criteria andCmdReadmeLessThanOrEqualTo(String value) {
            addCriterion("CMD_README <=", value, "cmdReadme");
            return (Criteria) this;
        }

        public Criteria andCmdReadmeIn(List<String> values) {
            addCriterion("CMD_README in", values, "cmdReadme");
            return (Criteria) this;
        }

        public Criteria andCmdReadmeNotIn(List<String> values) {
            addCriterion("CMD_README not in", values, "cmdReadme");
            return (Criteria) this;
        }

        public Criteria andCmdReadmeBetween(String value1, String value2) {
            addCriterion("CMD_README between", value1, value2, "cmdReadme");
            return (Criteria) this;
        }

        public Criteria andCmdReadmeNotBetween(String value1, String value2) {
            addCriterion("CMD_README not between", value1, value2, "cmdReadme");
            return (Criteria) this;
        }

        public Criteria andCmdSuccessIdIsNull() {
            addCriterion("CMD_SUCCESS_ID is null");
            return (Criteria) this;
        }

        public Criteria andCmdSuccessIdIsNotNull() {
            addCriterion("CMD_SUCCESS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCmdSuccessIdEqualTo(String value) {
            addCriterion("CMD_SUCCESS_ID =", value, "cmdSuccessId");
            return (Criteria) this;
        }

        public Criteria andCmdSuccessIdNotEqualTo(String value) {
            addCriterion("CMD_SUCCESS_ID <>", value, "cmdSuccessId");
            return (Criteria) this;
        }

        public Criteria andCmdSuccessIdGreaterThan(String value) {
            addCriterion("CMD_SUCCESS_ID >", value, "cmdSuccessId");
            return (Criteria) this;
        }

        public Criteria andCmdSuccessIdGreaterThanOrEqualTo(String value) {
            addCriterion("CMD_SUCCESS_ID >=", value, "cmdSuccessId");
            return (Criteria) this;
        }

        public Criteria andCmdSuccessIdLessThan(String value) {
            addCriterion("CMD_SUCCESS_ID <", value, "cmdSuccessId");
            return (Criteria) this;
        }

        public Criteria andCmdSuccessIdLessThanOrEqualTo(String value) {
            addCriterion("CMD_SUCCESS_ID <=", value, "cmdSuccessId");
            return (Criteria) this;
        }

        public Criteria andCmdSuccessIdIn(List<String> values) {
            addCriterion("CMD_SUCCESS_ID in", values, "cmdSuccessId");
            return (Criteria) this;
        }

        public Criteria andCmdSuccessIdNotIn(List<String> values) {
            addCriterion("CMD_SUCCESS_ID not in", values, "cmdSuccessId");
            return (Criteria) this;
        }

        public Criteria andCmdSuccessIdBetween(String value1, String value2) {
            addCriterion("CMD_SUCCESS_ID between", value1, value2, "cmdSuccessId");
            return (Criteria) this;
        }

        public Criteria andCmdSuccessIdNotBetween(String value1, String value2) {
            addCriterion("CMD_SUCCESS_ID not between", value1, value2, "cmdSuccessId");
            return (Criteria) this;
        }

        public Criteria andCmdErrorIdIsNull() {
            addCriterion("CMD_ERROR_ID is null");
            return (Criteria) this;
        }

        public Criteria andCmdErrorIdIsNotNull() {
            addCriterion("CMD_ERROR_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCmdErrorIdEqualTo(String value) {
            addCriterion("CMD_ERROR_ID =", value, "cmdErrorId");
            return (Criteria) this;
        }

        public Criteria andCmdErrorIdNotEqualTo(String value) {
            addCriterion("CMD_ERROR_ID <>", value, "cmdErrorId");
            return (Criteria) this;
        }

        public Criteria andCmdErrorIdGreaterThan(String value) {
            addCriterion("CMD_ERROR_ID >", value, "cmdErrorId");
            return (Criteria) this;
        }

        public Criteria andCmdErrorIdGreaterThanOrEqualTo(String value) {
            addCriterion("CMD_ERROR_ID >=", value, "cmdErrorId");
            return (Criteria) this;
        }

        public Criteria andCmdErrorIdLessThan(String value) {
            addCriterion("CMD_ERROR_ID <", value, "cmdErrorId");
            return (Criteria) this;
        }

        public Criteria andCmdErrorIdLessThanOrEqualTo(String value) {
            addCriterion("CMD_ERROR_ID <=", value, "cmdErrorId");
            return (Criteria) this;
        }

        public Criteria andCmdErrorIdIn(List<String> values) {
            addCriterion("CMD_ERROR_ID in", values, "cmdErrorId");
            return (Criteria) this;
        }

        public Criteria andCmdErrorIdNotIn(List<String> values) {
            addCriterion("CMD_ERROR_ID not in", values, "cmdErrorId");
            return (Criteria) this;
        }

        public Criteria andCmdErrorIdBetween(String value1, String value2) {
            addCriterion("CMD_ERROR_ID between", value1, value2, "cmdErrorId");
            return (Criteria) this;
        }

        public Criteria andCmdErrorIdNotBetween(String value1, String value2) {
            addCriterion("CMD_ERROR_ID not between", value1, value2, "cmdErrorId");
            return (Criteria) this;
        }

        public Criteria andParamNumIsNull() {
            addCriterion("PARAM_NUM is null");
            return (Criteria) this;
        }

        public Criteria andParamNumIsNotNull() {
            addCriterion("PARAM_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andParamNumEqualTo(BigDecimal value) {
            addCriterion("PARAM_NUM =", value, "paramNum");
            return (Criteria) this;
        }

        public Criteria andParamNumNotEqualTo(BigDecimal value) {
            addCriterion("PARAM_NUM <>", value, "paramNum");
            return (Criteria) this;
        }

        public Criteria andParamNumGreaterThan(BigDecimal value) {
            addCriterion("PARAM_NUM >", value, "paramNum");
            return (Criteria) this;
        }

        public Criteria andParamNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PARAM_NUM >=", value, "paramNum");
            return (Criteria) this;
        }

        public Criteria andParamNumLessThan(BigDecimal value) {
            addCriterion("PARAM_NUM <", value, "paramNum");
            return (Criteria) this;
        }

        public Criteria andParamNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PARAM_NUM <=", value, "paramNum");
            return (Criteria) this;
        }

        public Criteria andParamNumIn(List<BigDecimal> values) {
            addCriterion("PARAM_NUM in", values, "paramNum");
            return (Criteria) this;
        }

        public Criteria andParamNumNotIn(List<BigDecimal> values) {
            addCriterion("PARAM_NUM not in", values, "paramNum");
            return (Criteria) this;
        }

        public Criteria andParamNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PARAM_NUM between", value1, value2, "paramNum");
            return (Criteria) this;
        }

        public Criteria andParamNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PARAM_NUM not between", value1, value2, "paramNum");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterionForJDBCDate("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterionForJDBCDate("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterionForJDBCDate("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterionForJDBCDate("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterionForJDBCDate("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("UPDATE_TIME not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andDownCmdIdIsNull() {
            addCriterion("DOWN_CMD_ID is null");
            return (Criteria) this;
        }

        public Criteria andDownCmdIdIsNotNull() {
            addCriterion("DOWN_CMD_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDownCmdIdEqualTo(String value) {
            addCriterion("DOWN_CMD_ID =", value, "downCmdId");
            return (Criteria) this;
        }

        public Criteria andDownCmdIdNotEqualTo(String value) {
            addCriterion("DOWN_CMD_ID <>", value, "downCmdId");
            return (Criteria) this;
        }

        public Criteria andDownCmdIdGreaterThan(String value) {
            addCriterion("DOWN_CMD_ID >", value, "downCmdId");
            return (Criteria) this;
        }

        public Criteria andDownCmdIdGreaterThanOrEqualTo(String value) {
            addCriterion("DOWN_CMD_ID >=", value, "downCmdId");
            return (Criteria) this;
        }

        public Criteria andDownCmdIdLessThan(String value) {
            addCriterion("DOWN_CMD_ID <", value, "downCmdId");
            return (Criteria) this;
        }

        public Criteria andDownCmdIdLessThanOrEqualTo(String value) {
            addCriterion("DOWN_CMD_ID <=", value, "downCmdId");
            return (Criteria) this;
        }

        public Criteria andDownCmdIdIn(List<String> values) {
            addCriterion("DOWN_CMD_ID in", values, "downCmdId");
            return (Criteria) this;
        }

        public Criteria andDownCmdIdNotIn(List<String> values) {
            addCriterion("DOWN_CMD_ID not in", values, "downCmdId");
            return (Criteria) this;
        }

        public Criteria andDownCmdIdBetween(String value1, String value2) {
            addCriterion("DOWN_CMD_ID between", value1, value2, "downCmdId");
            return (Criteria) this;
        }

        public Criteria andDownCmdIdNotBetween(String value1, String value2) {
            addCriterion("DOWN_CMD_ID not between", value1, value2, "downCmdId");
            return (Criteria) this;
        }

        public Criteria andSyncStatusIsNull() {
            addCriterion("SYNC_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andSyncStatusIsNotNull() {
            addCriterion("SYNC_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andSyncStatusEqualTo(String value) {
            addCriterion("SYNC_STATUS =", value, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusNotEqualTo(String value) {
            addCriterion("SYNC_STATUS <>", value, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusGreaterThan(String value) {
            addCriterion("SYNC_STATUS >", value, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusGreaterThanOrEqualTo(String value) {
            addCriterion("SYNC_STATUS >=", value, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusLessThan(String value) {
            addCriterion("SYNC_STATUS <", value, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusLessThanOrEqualTo(String value) {
            addCriterion("SYNC_STATUS <=", value, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusLike(String value) {
            addCriterion("SYNC_STATUS like", value, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusNotLike(String value) {
            addCriterion("SYNC_STATUS not like", value, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusIn(List<String> values) {
            addCriterion("SYNC_STATUS in", values, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusNotIn(List<String> values) {
            addCriterion("SYNC_STATUS not in", values, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusBetween(String value1, String value2) {
            addCriterion("SYNC_STATUS between", value1, value2, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusNotBetween(String value1, String value2) {
            addCriterion("SYNC_STATUS not between", value1, value2, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatus3IsNull() {
            addCriterion("SYNC_STATUS_3 is null");
            return (Criteria) this;
        }

        public Criteria andSyncStatus3IsNotNull() {
            addCriterion("SYNC_STATUS_3 is not null");
            return (Criteria) this;
        }

        public Criteria andSyncStatus3EqualTo(BigDecimal value) {
            addCriterion("SYNC_STATUS_3 =", value, "syncStatus3");
            return (Criteria) this;
        }

        public Criteria andSyncStatus3NotEqualTo(BigDecimal value) {
            addCriterion("SYNC_STATUS_3 <>", value, "syncStatus3");
            return (Criteria) this;
        }

        public Criteria andSyncStatus3GreaterThan(BigDecimal value) {
            addCriterion("SYNC_STATUS_3 >", value, "syncStatus3");
            return (Criteria) this;
        }

        public Criteria andSyncStatus3GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SYNC_STATUS_3 >=", value, "syncStatus3");
            return (Criteria) this;
        }

        public Criteria andSyncStatus3LessThan(BigDecimal value) {
            addCriterion("SYNC_STATUS_3 <", value, "syncStatus3");
            return (Criteria) this;
        }

        public Criteria andSyncStatus3LessThanOrEqualTo(BigDecimal value) {
            addCriterion("SYNC_STATUS_3 <=", value, "syncStatus3");
            return (Criteria) this;
        }

        public Criteria andSyncStatus3In(List<BigDecimal> values) {
            addCriterion("SYNC_STATUS_3 in", values, "syncStatus3");
            return (Criteria) this;
        }

        public Criteria andSyncStatus3NotIn(List<BigDecimal> values) {
            addCriterion("SYNC_STATUS_3 not in", values, "syncStatus3");
            return (Criteria) this;
        }

        public Criteria andSyncStatus3Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("SYNC_STATUS_3 between", value1, value2, "syncStatus3");
            return (Criteria) this;
        }

        public Criteria andSyncStatus3NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SYNC_STATUS_3 not between", value1, value2, "syncStatus3");
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