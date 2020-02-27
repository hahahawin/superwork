package com.superwork.apcosplatform.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PWebPropertyInfoExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public PWebPropertyInfoExample() {
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

        public Criteria andProductCodeIsNull() {
            addCriterion("PRODUCT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andProductCodeIsNotNull() {
            addCriterion("PRODUCT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andProductCodeEqualTo(String value) {
            addCriterion("PRODUCT_CODE =", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotEqualTo(String value) {
            addCriterion("PRODUCT_CODE <>", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeGreaterThan(String value) {
            addCriterion("PRODUCT_CODE >", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCT_CODE >=", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeLessThan(String value) {
            addCriterion("PRODUCT_CODE <", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeLessThanOrEqualTo(String value) {
            addCriterion("PRODUCT_CODE <=", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeLike(String value) {
            addCriterion("PRODUCT_CODE like", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotLike(String value) {
            addCriterion("PRODUCT_CODE not like", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeIn(List<String> values) {
            addCriterion("PRODUCT_CODE in", values, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotIn(List<String> values) {
            addCriterion("PRODUCT_CODE not in", values, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeBetween(String value1, String value2) {
            addCriterion("PRODUCT_CODE between", value1, value2, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotBetween(String value1, String value2) {
            addCriterion("PRODUCT_CODE not between", value1, value2, "productCode");
            return (Criteria) this;
        }

        public Criteria andPropertyNameIsNull() {
            addCriterion("PROPERTY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPropertyNameIsNotNull() {
            addCriterion("PROPERTY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyNameEqualTo(String value) {
            addCriterion("PROPERTY_NAME =", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameNotEqualTo(String value) {
            addCriterion("PROPERTY_NAME <>", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameGreaterThan(String value) {
            addCriterion("PROPERTY_NAME >", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROPERTY_NAME >=", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameLessThan(String value) {
            addCriterion("PROPERTY_NAME <", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameLessThanOrEqualTo(String value) {
            addCriterion("PROPERTY_NAME <=", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameLike(String value) {
            addCriterion("PROPERTY_NAME like", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameNotLike(String value) {
            addCriterion("PROPERTY_NAME not like", value, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameIn(List<String> values) {
            addCriterion("PROPERTY_NAME in", values, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameNotIn(List<String> values) {
            addCriterion("PROPERTY_NAME not in", values, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameBetween(String value1, String value2) {
            addCriterion("PROPERTY_NAME between", value1, value2, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyNameNotBetween(String value1, String value2) {
            addCriterion("PROPERTY_NAME not between", value1, value2, "propertyName");
            return (Criteria) this;
        }

        public Criteria andPropertyModelIsNull() {
            addCriterion("PROPERTY_MODEL is null");
            return (Criteria) this;
        }

        public Criteria andPropertyModelIsNotNull() {
            addCriterion("PROPERTY_MODEL is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyModelEqualTo(String value) {
            addCriterion("PROPERTY_MODEL =", value, "propertyModel");
            return (Criteria) this;
        }

        public Criteria andPropertyModelNotEqualTo(String value) {
            addCriterion("PROPERTY_MODEL <>", value, "propertyModel");
            return (Criteria) this;
        }

        public Criteria andPropertyModelGreaterThan(String value) {
            addCriterion("PROPERTY_MODEL >", value, "propertyModel");
            return (Criteria) this;
        }

        public Criteria andPropertyModelGreaterThanOrEqualTo(String value) {
            addCriterion("PROPERTY_MODEL >=", value, "propertyModel");
            return (Criteria) this;
        }

        public Criteria andPropertyModelLessThan(String value) {
            addCriterion("PROPERTY_MODEL <", value, "propertyModel");
            return (Criteria) this;
        }

        public Criteria andPropertyModelLessThanOrEqualTo(String value) {
            addCriterion("PROPERTY_MODEL <=", value, "propertyModel");
            return (Criteria) this;
        }

        public Criteria andPropertyModelLike(String value) {
            addCriterion("PROPERTY_MODEL like", value, "propertyModel");
            return (Criteria) this;
        }

        public Criteria andPropertyModelNotLike(String value) {
            addCriterion("PROPERTY_MODEL not like", value, "propertyModel");
            return (Criteria) this;
        }

        public Criteria andPropertyModelIn(List<String> values) {
            addCriterion("PROPERTY_MODEL in", values, "propertyModel");
            return (Criteria) this;
        }

        public Criteria andPropertyModelNotIn(List<String> values) {
            addCriterion("PROPERTY_MODEL not in", values, "propertyModel");
            return (Criteria) this;
        }

        public Criteria andPropertyModelBetween(String value1, String value2) {
            addCriterion("PROPERTY_MODEL between", value1, value2, "propertyModel");
            return (Criteria) this;
        }

        public Criteria andPropertyModelNotBetween(String value1, String value2) {
            addCriterion("PROPERTY_MODEL not between", value1, value2, "propertyModel");
            return (Criteria) this;
        }

        public Criteria andPropertyReadmeIsNull() {
            addCriterion("PROPERTY_README is null");
            return (Criteria) this;
        }

        public Criteria andPropertyReadmeIsNotNull() {
            addCriterion("PROPERTY_README is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyReadmeEqualTo(String value) {
            addCriterion("PROPERTY_README =", value, "propertyReadme");
            return (Criteria) this;
        }

        public Criteria andPropertyReadmeNotEqualTo(String value) {
            addCriterion("PROPERTY_README <>", value, "propertyReadme");
            return (Criteria) this;
        }

        public Criteria andPropertyReadmeGreaterThan(String value) {
            addCriterion("PROPERTY_README >", value, "propertyReadme");
            return (Criteria) this;
        }

        public Criteria andPropertyReadmeGreaterThanOrEqualTo(String value) {
            addCriterion("PROPERTY_README >=", value, "propertyReadme");
            return (Criteria) this;
        }

        public Criteria andPropertyReadmeLessThan(String value) {
            addCriterion("PROPERTY_README <", value, "propertyReadme");
            return (Criteria) this;
        }

        public Criteria andPropertyReadmeLessThanOrEqualTo(String value) {
            addCriterion("PROPERTY_README <=", value, "propertyReadme");
            return (Criteria) this;
        }

        public Criteria andPropertyReadmeLike(String value) {
            addCriterion("PROPERTY_README like", value, "propertyReadme");
            return (Criteria) this;
        }

        public Criteria andPropertyReadmeNotLike(String value) {
            addCriterion("PROPERTY_README not like", value, "propertyReadme");
            return (Criteria) this;
        }

        public Criteria andPropertyReadmeIn(List<String> values) {
            addCriterion("PROPERTY_README in", values, "propertyReadme");
            return (Criteria) this;
        }

        public Criteria andPropertyReadmeNotIn(List<String> values) {
            addCriterion("PROPERTY_README not in", values, "propertyReadme");
            return (Criteria) this;
        }

        public Criteria andPropertyReadmeBetween(String value1, String value2) {
            addCriterion("PROPERTY_README between", value1, value2, "propertyReadme");
            return (Criteria) this;
        }

        public Criteria andPropertyReadmeNotBetween(String value1, String value2) {
            addCriterion("PROPERTY_README not between", value1, value2, "propertyReadme");
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

        public Criteria andCmdIdEqualTo(String value) {
            addCriterion("CMD_ID =", value, "cmdId");
            return (Criteria) this;
        }

        public Criteria andCmdIdNotEqualTo(String value) {
            addCriterion("CMD_ID <>", value, "cmdId");
            return (Criteria) this;
        }

        public Criteria andCmdIdGreaterThan(String value) {
            addCriterion("CMD_ID >", value, "cmdId");
            return (Criteria) this;
        }

        public Criteria andCmdIdGreaterThanOrEqualTo(String value) {
            addCriterion("CMD_ID >=", value, "cmdId");
            return (Criteria) this;
        }

        public Criteria andCmdIdLessThan(String value) {
            addCriterion("CMD_ID <", value, "cmdId");
            return (Criteria) this;
        }

        public Criteria andCmdIdLessThanOrEqualTo(String value) {
            addCriterion("CMD_ID <=", value, "cmdId");
            return (Criteria) this;
        }

        public Criteria andCmdIdLike(String value) {
            addCriterion("CMD_ID like", value, "cmdId");
            return (Criteria) this;
        }

        public Criteria andCmdIdNotLike(String value) {
            addCriterion("CMD_ID not like", value, "cmdId");
            return (Criteria) this;
        }

        public Criteria andCmdIdIn(List<String> values) {
            addCriterion("CMD_ID in", values, "cmdId");
            return (Criteria) this;
        }

        public Criteria andCmdIdNotIn(List<String> values) {
            addCriterion("CMD_ID not in", values, "cmdId");
            return (Criteria) this;
        }

        public Criteria andCmdIdBetween(String value1, String value2) {
            addCriterion("CMD_ID between", value1, value2, "cmdId");
            return (Criteria) this;
        }

        public Criteria andCmdIdNotBetween(String value1, String value2) {
            addCriterion("CMD_ID not between", value1, value2, "cmdId");
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

        public Criteria andParamKeyLike(String value) {
            addCriterion("PARAM_KEY like", value, "paramKey");
            return (Criteria) this;
        }

        public Criteria andParamKeyNotLike(String value) {
            addCriterion("PARAM_KEY not like", value, "paramKey");
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

        public Criteria andSyncStatusEqualTo(BigDecimal value) {
            addCriterion("SYNC_STATUS =", value, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusNotEqualTo(BigDecimal value) {
            addCriterion("SYNC_STATUS <>", value, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusGreaterThan(BigDecimal value) {
            addCriterion("SYNC_STATUS >", value, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SYNC_STATUS >=", value, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusLessThan(BigDecimal value) {
            addCriterion("SYNC_STATUS <", value, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SYNC_STATUS <=", value, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusIn(List<BigDecimal> values) {
            addCriterion("SYNC_STATUS in", values, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusNotIn(List<BigDecimal> values) {
            addCriterion("SYNC_STATUS not in", values, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SYNC_STATUS between", value1, value2, "syncStatus");
            return (Criteria) this;
        }

        public Criteria andSyncStatusNotBetween(BigDecimal value1, BigDecimal value2) {
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