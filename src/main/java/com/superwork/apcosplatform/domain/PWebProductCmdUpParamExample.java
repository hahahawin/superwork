package com.superwork.apcosplatform.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PWebProductCmdUpParamExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public PWebProductCmdUpParamExample() {
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

        public Criteria andCmdIdIsNull() {
            addCriterion("CMD_ID is null");
            return (Criteria) this;
        }

        public Criteria andCmdIdIsNotNull() {
            addCriterion("CMD_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCmdIdEqualTo(BigDecimal value) {
            addCriterion("CMD_ID =", value, "cmdId");
            return (Criteria) this;
        }

        public Criteria andCmdIdNotEqualTo(BigDecimal value) {
            addCriterion("CMD_ID <>", value, "cmdId");
            return (Criteria) this;
        }

        public Criteria andCmdIdGreaterThan(BigDecimal value) {
            addCriterion("CMD_ID >", value, "cmdId");
            return (Criteria) this;
        }

        public Criteria andCmdIdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CMD_ID >=", value, "cmdId");
            return (Criteria) this;
        }

        public Criteria andCmdIdLessThan(BigDecimal value) {
            addCriterion("CMD_ID <", value, "cmdId");
            return (Criteria) this;
        }

        public Criteria andCmdIdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("CMD_ID <=", value, "cmdId");
            return (Criteria) this;
        }

        public Criteria andCmdIdIn(List<BigDecimal> values) {
            addCriterion("CMD_ID in", values, "cmdId");
            return (Criteria) this;
        }

        public Criteria andCmdIdNotIn(List<BigDecimal> values) {
            addCriterion("CMD_ID not in", values, "cmdId");
            return (Criteria) this;
        }

        public Criteria andCmdIdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CMD_ID between", value1, value2, "cmdId");
            return (Criteria) this;
        }

        public Criteria andCmdIdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CMD_ID not between", value1, value2, "cmdId");
            return (Criteria) this;
        }

        public Criteria andParamOrderIsNull() {
            addCriterion("PARAM_ORDER is null");
            return (Criteria) this;
        }

        public Criteria andParamOrderIsNotNull() {
            addCriterion("PARAM_ORDER is not null");
            return (Criteria) this;
        }

        public Criteria andParamOrderEqualTo(BigDecimal value) {
            addCriterion("PARAM_ORDER =", value, "paramOrder");
            return (Criteria) this;
        }

        public Criteria andParamOrderNotEqualTo(BigDecimal value) {
            addCriterion("PARAM_ORDER <>", value, "paramOrder");
            return (Criteria) this;
        }

        public Criteria andParamOrderGreaterThan(BigDecimal value) {
            addCriterion("PARAM_ORDER >", value, "paramOrder");
            return (Criteria) this;
        }

        public Criteria andParamOrderGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PARAM_ORDER >=", value, "paramOrder");
            return (Criteria) this;
        }

        public Criteria andParamOrderLessThan(BigDecimal value) {
            addCriterion("PARAM_ORDER <", value, "paramOrder");
            return (Criteria) this;
        }

        public Criteria andParamOrderLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PARAM_ORDER <=", value, "paramOrder");
            return (Criteria) this;
        }

        public Criteria andParamOrderIn(List<BigDecimal> values) {
            addCriterion("PARAM_ORDER in", values, "paramOrder");
            return (Criteria) this;
        }

        public Criteria andParamOrderNotIn(List<BigDecimal> values) {
            addCriterion("PARAM_ORDER not in", values, "paramOrder");
            return (Criteria) this;
        }

        public Criteria andParamOrderBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PARAM_ORDER between", value1, value2, "paramOrder");
            return (Criteria) this;
        }

        public Criteria andParamOrderNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PARAM_ORDER not between", value1, value2, "paramOrder");
            return (Criteria) this;
        }

        public Criteria andParamTypeIsNull() {
            addCriterion("PARAM_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andParamTypeIsNotNull() {
            addCriterion("PARAM_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andParamTypeEqualTo(String value) {
            addCriterion("PARAM_TYPE =", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeNotEqualTo(String value) {
            addCriterion("PARAM_TYPE <>", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeGreaterThan(String value) {
            addCriterion("PARAM_TYPE >", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PARAM_TYPE >=", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeLessThan(String value) {
            addCriterion("PARAM_TYPE <", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeLessThanOrEqualTo(String value) {
            addCriterion("PARAM_TYPE <=", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeLike(String value) {
            addCriterion("PARAM_TYPE like", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeNotLike(String value) {
            addCriterion("PARAM_TYPE not like", value, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeIn(List<String> values) {
            addCriterion("PARAM_TYPE in", values, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeNotIn(List<String> values) {
            addCriterion("PARAM_TYPE not in", values, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeBetween(String value1, String value2) {
            addCriterion("PARAM_TYPE between", value1, value2, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamTypeNotBetween(String value1, String value2) {
            addCriterion("PARAM_TYPE not between", value1, value2, "paramType");
            return (Criteria) this;
        }

        public Criteria andParamPosIsNull() {
            addCriterion("PARAM_POS is null");
            return (Criteria) this;
        }

        public Criteria andParamPosIsNotNull() {
            addCriterion("PARAM_POS is not null");
            return (Criteria) this;
        }

        public Criteria andParamPosEqualTo(BigDecimal value) {
            addCriterion("PARAM_POS =", value, "paramPos");
            return (Criteria) this;
        }

        public Criteria andParamPosNotEqualTo(BigDecimal value) {
            addCriterion("PARAM_POS <>", value, "paramPos");
            return (Criteria) this;
        }

        public Criteria andParamPosGreaterThan(BigDecimal value) {
            addCriterion("PARAM_POS >", value, "paramPos");
            return (Criteria) this;
        }

        public Criteria andParamPosGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PARAM_POS >=", value, "paramPos");
            return (Criteria) this;
        }

        public Criteria andParamPosLessThan(BigDecimal value) {
            addCriterion("PARAM_POS <", value, "paramPos");
            return (Criteria) this;
        }

        public Criteria andParamPosLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PARAM_POS <=", value, "paramPos");
            return (Criteria) this;
        }

        public Criteria andParamPosIn(List<BigDecimal> values) {
            addCriterion("PARAM_POS in", values, "paramPos");
            return (Criteria) this;
        }

        public Criteria andParamPosNotIn(List<BigDecimal> values) {
            addCriterion("PARAM_POS not in", values, "paramPos");
            return (Criteria) this;
        }

        public Criteria andParamPosBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PARAM_POS between", value1, value2, "paramPos");
            return (Criteria) this;
        }

        public Criteria andParamPosNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PARAM_POS not between", value1, value2, "paramPos");
            return (Criteria) this;
        }

        public Criteria andParamLenIsNull() {
            addCriterion("PARAM_LEN is null");
            return (Criteria) this;
        }

        public Criteria andParamLenIsNotNull() {
            addCriterion("PARAM_LEN is not null");
            return (Criteria) this;
        }

        public Criteria andParamLenEqualTo(BigDecimal value) {
            addCriterion("PARAM_LEN =", value, "paramLen");
            return (Criteria) this;
        }

        public Criteria andParamLenNotEqualTo(BigDecimal value) {
            addCriterion("PARAM_LEN <>", value, "paramLen");
            return (Criteria) this;
        }

        public Criteria andParamLenGreaterThan(BigDecimal value) {
            addCriterion("PARAM_LEN >", value, "paramLen");
            return (Criteria) this;
        }

        public Criteria andParamLenGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PARAM_LEN >=", value, "paramLen");
            return (Criteria) this;
        }

        public Criteria andParamLenLessThan(BigDecimal value) {
            addCriterion("PARAM_LEN <", value, "paramLen");
            return (Criteria) this;
        }

        public Criteria andParamLenLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PARAM_LEN <=", value, "paramLen");
            return (Criteria) this;
        }

        public Criteria andParamLenIn(List<BigDecimal> values) {
            addCriterion("PARAM_LEN in", values, "paramLen");
            return (Criteria) this;
        }

        public Criteria andParamLenNotIn(List<BigDecimal> values) {
            addCriterion("PARAM_LEN not in", values, "paramLen");
            return (Criteria) this;
        }

        public Criteria andParamLenBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PARAM_LEN between", value1, value2, "paramLen");
            return (Criteria) this;
        }

        public Criteria andParamLenNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PARAM_LEN not between", value1, value2, "paramLen");
            return (Criteria) this;
        }

        public Criteria andParamNameIsNull() {
            addCriterion("PARAM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andParamNameIsNotNull() {
            addCriterion("PARAM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andParamNameEqualTo(String value) {
            addCriterion("PARAM_NAME =", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameNotEqualTo(String value) {
            addCriterion("PARAM_NAME <>", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameGreaterThan(String value) {
            addCriterion("PARAM_NAME >", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameGreaterThanOrEqualTo(String value) {
            addCriterion("PARAM_NAME >=", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameLessThan(String value) {
            addCriterion("PARAM_NAME <", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameLessThanOrEqualTo(String value) {
            addCriterion("PARAM_NAME <=", value, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameIn(List<String> values) {
            addCriterion("PARAM_NAME in", values, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameNotIn(List<String> values) {
            addCriterion("PARAM_NAME not in", values, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameBetween(String value1, String value2) {
            addCriterion("PARAM_NAME between", value1, value2, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamNameNotBetween(String value1, String value2) {
            addCriterion("PARAM_NAME not between", value1, value2, "paramName");
            return (Criteria) this;
        }

        public Criteria andParamKeyIsNull() {
            addCriterion("PARAM_KEY is null");
            return (Criteria) this;
        }

        public Criteria andParamKeyIsNotNull() {
            addCriterion("PARAM_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andParamKeyEqualTo(String value) {
            addCriterion("PARAM_KEY =", value, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyNotEqualTo(String value) {
            addCriterion("PARAM_KEY <>", value, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyGreaterThan(String value) {
            addCriterion("PARAM_KEY >", value, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyGreaterThanOrEqualTo(String value) {
            addCriterion("PARAM_KEY >=", value, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyLessThan(String value) {
            addCriterion("PARAM_KEY <", value, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyLessThanOrEqualTo(String value) {
            addCriterion("PARAM_KEY <=", value, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyIn(List<String> values) {
            addCriterion("PARAM_KEY in", values, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyNotIn(List<String> values) {
            addCriterion("PARAM_KEY not in", values, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyBetween(String value1, String value2) {
            addCriterion("PARAM_KEY between", value1, value2, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyNotBetween(String value1, String value2) {
            addCriterion("PARAM_KEY not between", value1, value2, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamReadmeIsNull() {
            addCriterion("PARAM_README is null");
            return (Criteria) this;
        }

        public Criteria andParamReadmeIsNotNull() {
            addCriterion("PARAM_README is not null");
            return (Criteria) this;
        }

        public Criteria andParamReadmeEqualTo(String value) {
            addCriterion("PARAM_README =", value, "paramReadme");
            return (Criteria) this;
        }

        public Criteria andParamReadmeNotEqualTo(String value) {
            addCriterion("PARAM_README <>", value, "paramReadme");
            return (Criteria) this;
        }

        public Criteria andParamReadmeGreaterThan(String value) {
            addCriterion("PARAM_README >", value, "paramReadme");
            return (Criteria) this;
        }

        public Criteria andParamReadmeGreaterThanOrEqualTo(String value) {
            addCriterion("PARAM_README >=", value, "paramReadme");
            return (Criteria) this;
        }

        public Criteria andParamReadmeLessThan(String value) {
            addCriterion("PARAM_README <", value, "paramReadme");
            return (Criteria) this;
        }

        public Criteria andParamReadmeLessThanOrEqualTo(String value) {
            addCriterion("PARAM_README <=", value, "paramReadme");
            return (Criteria) this;
        }

        public Criteria andParamReadmeIn(List<String> values) {
            addCriterion("PARAM_README in", values, "paramReadme");
            return (Criteria) this;
        }

        public Criteria andParamReadmeNotIn(List<String> values) {
            addCriterion("PARAM_README not in", values, "paramReadme");
            return (Criteria) this;
        }

        public Criteria andParamReadmeBetween(String value1, String value2) {
            addCriterion("PARAM_README between", value1, value2, "paramReadme");
            return (Criteria) this;
        }

        public Criteria andParamReadmeNotBetween(String value1, String value2) {
            addCriterion("PARAM_README not between", value1, value2, "paramReadme");
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