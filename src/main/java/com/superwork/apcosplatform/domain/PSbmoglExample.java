package com.superwork.apcosplatform.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PSbmoglExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public PSbmoglExample() {
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

        public Criteria andSbmoglIdIsNull() {
            addCriterion("SBMOGL_ID is null");
            return (Criteria) this;
        }

        public Criteria andSbmoglIdIsNotNull() {
            addCriterion("SBMOGL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSbmoglIdEqualTo(String value) {
            addCriterion("SBMOGL_ID =", value, "sbmoglId");
            return (Criteria) this;
        }

        public Criteria andSbmoglIdNotEqualTo(String value) {
            addCriterion("SBMOGL_ID <>", value, "sbmoglId");
            return (Criteria) this;
        }

        public Criteria andSbmoglIdGreaterThan(String value) {
            addCriterion("SBMOGL_ID >", value, "sbmoglId");
            return (Criteria) this;
        }

        public Criteria andSbmoglIdGreaterThanOrEqualTo(String value) {
            addCriterion("SBMOGL_ID >=", value, "sbmoglId");
            return (Criteria) this;
        }

        public Criteria andSbmoglIdLessThan(String value) {
            addCriterion("SBMOGL_ID <", value, "sbmoglId");
            return (Criteria) this;
        }

        public Criteria andSbmoglIdLessThanOrEqualTo(String value) {
            addCriterion("SBMOGL_ID <=", value, "sbmoglId");
            return (Criteria) this;
        }

        public Criteria andSbmoglIdLike(String value) {
            addCriterion("SBMOGL_ID like", value, "sbmoglId");
            return (Criteria) this;
        }

        public Criteria andSbmoglIdNotLike(String value) {
            addCriterion("SBMOGL_ID not like", value, "sbmoglId");
            return (Criteria) this;
        }

        public Criteria andSbmoglIdIn(List<String> values) {
            addCriterion("SBMOGL_ID in", values, "sbmoglId");
            return (Criteria) this;
        }

        public Criteria andSbmoglIdNotIn(List<String> values) {
            addCriterion("SBMOGL_ID not in", values, "sbmoglId");
            return (Criteria) this;
        }

        public Criteria andSbmoglIdBetween(String value1, String value2) {
            addCriterion("SBMOGL_ID between", value1, value2, "sbmoglId");
            return (Criteria) this;
        }

        public Criteria andSbmoglIdNotBetween(String value1, String value2) {
            addCriterion("SBMOGL_ID not between", value1, value2, "sbmoglId");
            return (Criteria) this;
        }

        public Criteria andSbmoglYmsidIsNull() {
            addCriterion("SBMOGL_YMSID is null");
            return (Criteria) this;
        }

        public Criteria andSbmoglYmsidIsNotNull() {
            addCriterion("SBMOGL_YMSID is not null");
            return (Criteria) this;
        }

        public Criteria andSbmoglYmsidEqualTo(String value) {
            addCriterion("SBMOGL_YMSID =", value, "sbmoglYmsid");
            return (Criteria) this;
        }

        public Criteria andSbmoglYmsidNotEqualTo(String value) {
            addCriterion("SBMOGL_YMSID <>", value, "sbmoglYmsid");
            return (Criteria) this;
        }

        public Criteria andSbmoglYmsidGreaterThan(String value) {
            addCriterion("SBMOGL_YMSID >", value, "sbmoglYmsid");
            return (Criteria) this;
        }

        public Criteria andSbmoglYmsidGreaterThanOrEqualTo(String value) {
            addCriterion("SBMOGL_YMSID >=", value, "sbmoglYmsid");
            return (Criteria) this;
        }

        public Criteria andSbmoglYmsidLessThan(String value) {
            addCriterion("SBMOGL_YMSID <", value, "sbmoglYmsid");
            return (Criteria) this;
        }

        public Criteria andSbmoglYmsidLessThanOrEqualTo(String value) {
            addCriterion("SBMOGL_YMSID <=", value, "sbmoglYmsid");
            return (Criteria) this;
        }

        public Criteria andSbmoglYmsidIn(List<String> values) {
            addCriterion("SBMOGL_YMSID in", values, "sbmoglYmsid");
            return (Criteria) this;
        }

        public Criteria andSbmoglYmsidNotIn(List<String> values) {
            addCriterion("SBMOGL_YMSID not in", values, "sbmoglYmsid");
            return (Criteria) this;
        }

        public Criteria andSbmoglYmsidBetween(String value1, String value2) {
            addCriterion("SBMOGL_YMSID between", value1, value2, "sbmoglYmsid");
            return (Criteria) this;
        }

        public Criteria andSbmoglYmsidNotBetween(String value1, String value2) {
            addCriterion("SBMOGL_YMSID not between", value1, value2, "sbmoglYmsid");
            return (Criteria) this;
        }

        public Criteria andSbmoglNameIsNull() {
            addCriterion("SBMOGL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSbmoglNameIsNotNull() {
            addCriterion("SBMOGL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSbmoglNameEqualTo(String value) {
            addCriterion("SBMOGL_NAME =", value, "sbmoglName");
            return (Criteria) this;
        }

        public Criteria andSbmoglNameNotEqualTo(String value) {
            addCriterion("SBMOGL_NAME <>", value, "sbmoglName");
            return (Criteria) this;
        }

        public Criteria andSbmoglNameGreaterThan(String value) {
            addCriterion("SBMOGL_NAME >", value, "sbmoglName");
            return (Criteria) this;
        }

        public Criteria andSbmoglNameGreaterThanOrEqualTo(String value) {
            addCriterion("SBMOGL_NAME >=", value, "sbmoglName");
            return (Criteria) this;
        }

        public Criteria andSbmoglNameLessThan(String value) {
            addCriterion("SBMOGL_NAME <", value, "sbmoglName");
            return (Criteria) this;
        }

        public Criteria andSbmoglNameLessThanOrEqualTo(String value) {
            addCriterion("SBMOGL_NAME <=", value, "sbmoglName");
            return (Criteria) this;
        }

        public Criteria andSbmoglNameIn(List<String> values) {
            addCriterion("SBMOGL_NAME in", values, "sbmoglName");
            return (Criteria) this;
        }

        public Criteria andSbmoglNameNotIn(List<String> values) {
            addCriterion("SBMOGL_NAME not in", values, "sbmoglName");
            return (Criteria) this;
        }

        public Criteria andSbmoglNameBetween(String value1, String value2) {
            addCriterion("SBMOGL_NAME between", value1, value2, "sbmoglName");
            return (Criteria) this;
        }

        public Criteria andSbmoglNameNotBetween(String value1, String value2) {
            addCriterion("SBMOGL_NAME not between", value1, value2, "sbmoglName");
            return (Criteria) this;
        }

        public Criteria andSbmoglZtIsNull() {
            addCriterion("SBMOGL_ZT is null");
            return (Criteria) this;
        }

        public Criteria andSbmoglZtIsNotNull() {
            addCriterion("SBMOGL_ZT is not null");
            return (Criteria) this;
        }

        public Criteria andSbmoglZtEqualTo(String value) {
            addCriterion("SBMOGL_ZT =", value, "sbmoglZt");
            return (Criteria) this;
        }

        public Criteria andSbmoglZtNotEqualTo(String value) {
            addCriterion("SBMOGL_ZT <>", value, "sbmoglZt");
            return (Criteria) this;
        }

        public Criteria andSbmoglZtGreaterThan(String value) {
            addCriterion("SBMOGL_ZT >", value, "sbmoglZt");
            return (Criteria) this;
        }

        public Criteria andSbmoglZtGreaterThanOrEqualTo(String value) {
            addCriterion("SBMOGL_ZT >=", value, "sbmoglZt");
            return (Criteria) this;
        }

        public Criteria andSbmoglZtLessThan(String value) {
            addCriterion("SBMOGL_ZT <", value, "sbmoglZt");
            return (Criteria) this;
        }

        public Criteria andSbmoglZtLessThanOrEqualTo(String value) {
            addCriterion("SBMOGL_ZT <=", value, "sbmoglZt");
            return (Criteria) this;
        }

        public Criteria andSbmoglZtIn(List<String> values) {
            addCriterion("SBMOGL_ZT in", values, "sbmoglZt");
            return (Criteria) this;
        }

        public Criteria andSbmoglZtNotIn(List<String> values) {
            addCriterion("SBMOGL_ZT not in", values, "sbmoglZt");
            return (Criteria) this;
        }

        public Criteria andSbmoglZtBetween(String value1, String value2) {
            addCriterion("SBMOGL_ZT between", value1, value2, "sbmoglZt");
            return (Criteria) this;
        }

        public Criteria andSbmoglZtNotBetween(String value1, String value2) {
            addCriterion("SBMOGL_ZT not between", value1, value2, "sbmoglZt");
            return (Criteria) this;
        }

        public Criteria andSbmoglLxIsNull() {
            addCriterion("SBMOGL_LX is null");
            return (Criteria) this;
        }

        public Criteria andSbmoglLxIsNotNull() {
            addCriterion("SBMOGL_LX is not null");
            return (Criteria) this;
        }

        public Criteria andSbmoglLxEqualTo(String value) {
            addCriterion("SBMOGL_LX =", value, "sbmoglLx");
            return (Criteria) this;
        }

        public Criteria andSbmoglLxNotEqualTo(String value) {
            addCriterion("SBMOGL_LX <>", value, "sbmoglLx");
            return (Criteria) this;
        }

        public Criteria andSbmoglLxGreaterThan(String value) {
            addCriterion("SBMOGL_LX >", value, "sbmoglLx");
            return (Criteria) this;
        }

        public Criteria andSbmoglLxGreaterThanOrEqualTo(String value) {
            addCriterion("SBMOGL_LX >=", value, "sbmoglLx");
            return (Criteria) this;
        }

        public Criteria andSbmoglLxLessThan(String value) {
            addCriterion("SBMOGL_LX <", value, "sbmoglLx");
            return (Criteria) this;
        }

        public Criteria andSbmoglLxLessThanOrEqualTo(String value) {
            addCriterion("SBMOGL_LX <=", value, "sbmoglLx");
            return (Criteria) this;
        }

        public Criteria andSbmoglLxIn(List<String> values) {
            addCriterion("SBMOGL_LX in", values, "sbmoglLx");
            return (Criteria) this;
        }

        public Criteria andSbmoglLxNotIn(List<String> values) {
            addCriterion("SBMOGL_LX not in", values, "sbmoglLx");
            return (Criteria) this;
        }

        public Criteria andSbmoglLxBetween(String value1, String value2) {
            addCriterion("SBMOGL_LX between", value1, value2, "sbmoglLx");
            return (Criteria) this;
        }

        public Criteria andSbmoglLxNotBetween(String value1, String value2) {
            addCriterion("SBMOGL_LX not between", value1, value2, "sbmoglLx");
            return (Criteria) this;
        }

        public Criteria andSbmoglTbIsNull() {
            addCriterion("SBMOGL_TB is null");
            return (Criteria) this;
        }

        public Criteria andSbmoglTbIsNotNull() {
            addCriterion("SBMOGL_TB is not null");
            return (Criteria) this;
        }

        public Criteria andSbmoglTbEqualTo(String value) {
            addCriterion("SBMOGL_TB =", value, "sbmoglTb");
            return (Criteria) this;
        }

        public Criteria andSbmoglTbNotEqualTo(String value) {
            addCriterion("SBMOGL_TB <>", value, "sbmoglTb");
            return (Criteria) this;
        }

        public Criteria andSbmoglTbGreaterThan(String value) {
            addCriterion("SBMOGL_TB >", value, "sbmoglTb");
            return (Criteria) this;
        }

        public Criteria andSbmoglTbGreaterThanOrEqualTo(String value) {
            addCriterion("SBMOGL_TB >=", value, "sbmoglTb");
            return (Criteria) this;
        }

        public Criteria andSbmoglTbLessThan(String value) {
            addCriterion("SBMOGL_TB <", value, "sbmoglTb");
            return (Criteria) this;
        }

        public Criteria andSbmoglTbLessThanOrEqualTo(String value) {
            addCriterion("SBMOGL_TB <=", value, "sbmoglTb");
            return (Criteria) this;
        }

        public Criteria andSbmoglTbIn(List<String> values) {
            addCriterion("SBMOGL_TB in", values, "sbmoglTb");
            return (Criteria) this;
        }

        public Criteria andSbmoglTbNotIn(List<String> values) {
            addCriterion("SBMOGL_TB not in", values, "sbmoglTb");
            return (Criteria) this;
        }

        public Criteria andSbmoglTbBetween(String value1, String value2) {
            addCriterion("SBMOGL_TB between", value1, value2, "sbmoglTb");
            return (Criteria) this;
        }

        public Criteria andSbmoglTbNotBetween(String value1, String value2) {
            addCriterion("SBMOGL_TB not between", value1, value2, "sbmoglTb");
            return (Criteria) this;
        }

        public Criteria andSbmoglQdfyIsNull() {
            addCriterion("SBMOGL_QDFY is null");
            return (Criteria) this;
        }

        public Criteria andSbmoglQdfyIsNotNull() {
            addCriterion("SBMOGL_QDFY is not null");
            return (Criteria) this;
        }

        public Criteria andSbmoglQdfyEqualTo(String value) {
            addCriterion("SBMOGL_QDFY =", value, "sbmoglQdfy");
            return (Criteria) this;
        }

        public Criteria andSbmoglQdfyNotEqualTo(String value) {
            addCriterion("SBMOGL_QDFY <>", value, "sbmoglQdfy");
            return (Criteria) this;
        }

        public Criteria andSbmoglQdfyGreaterThan(String value) {
            addCriterion("SBMOGL_QDFY >", value, "sbmoglQdfy");
            return (Criteria) this;
        }

        public Criteria andSbmoglQdfyGreaterThanOrEqualTo(String value) {
            addCriterion("SBMOGL_QDFY >=", value, "sbmoglQdfy");
            return (Criteria) this;
        }

        public Criteria andSbmoglQdfyLessThan(String value) {
            addCriterion("SBMOGL_QDFY <", value, "sbmoglQdfy");
            return (Criteria) this;
        }

        public Criteria andSbmoglQdfyLessThanOrEqualTo(String value) {
            addCriterion("SBMOGL_QDFY <=", value, "sbmoglQdfy");
            return (Criteria) this;
        }

        public Criteria andSbmoglQdfyIn(List<String> values) {
            addCriterion("SBMOGL_QDFY in", values, "sbmoglQdfy");
            return (Criteria) this;
        }

        public Criteria andSbmoglQdfyNotIn(List<String> values) {
            addCriterion("SBMOGL_QDFY not in", values, "sbmoglQdfy");
            return (Criteria) this;
        }

        public Criteria andSbmoglQdfyBetween(String value1, String value2) {
            addCriterion("SBMOGL_QDFY between", value1, value2, "sbmoglQdfy");
            return (Criteria) this;
        }

        public Criteria andSbmoglQdfyNotBetween(String value1, String value2) {
            addCriterion("SBMOGL_QDFY not between", value1, value2, "sbmoglQdfy");
            return (Criteria) this;
        }

        public Criteria andSbmoglJsfyIsNull() {
            addCriterion("SBMOGL_JSFY is null");
            return (Criteria) this;
        }

        public Criteria andSbmoglJsfyIsNotNull() {
            addCriterion("SBMOGL_JSFY is not null");
            return (Criteria) this;
        }

        public Criteria andSbmoglJsfyEqualTo(String value) {
            addCriterion("SBMOGL_JSFY =", value, "sbmoglJsfy");
            return (Criteria) this;
        }

        public Criteria andSbmoglJsfyNotEqualTo(String value) {
            addCriterion("SBMOGL_JSFY <>", value, "sbmoglJsfy");
            return (Criteria) this;
        }

        public Criteria andSbmoglJsfyGreaterThan(String value) {
            addCriterion("SBMOGL_JSFY >", value, "sbmoglJsfy");
            return (Criteria) this;
        }

        public Criteria andSbmoglJsfyGreaterThanOrEqualTo(String value) {
            addCriterion("SBMOGL_JSFY >=", value, "sbmoglJsfy");
            return (Criteria) this;
        }

        public Criteria andSbmoglJsfyLessThan(String value) {
            addCriterion("SBMOGL_JSFY <", value, "sbmoglJsfy");
            return (Criteria) this;
        }

        public Criteria andSbmoglJsfyLessThanOrEqualTo(String value) {
            addCriterion("SBMOGL_JSFY <=", value, "sbmoglJsfy");
            return (Criteria) this;
        }

        public Criteria andSbmoglJsfyIn(List<String> values) {
            addCriterion("SBMOGL_JSFY in", values, "sbmoglJsfy");
            return (Criteria) this;
        }

        public Criteria andSbmoglJsfyNotIn(List<String> values) {
            addCriterion("SBMOGL_JSFY not in", values, "sbmoglJsfy");
            return (Criteria) this;
        }

        public Criteria andSbmoglJsfyBetween(String value1, String value2) {
            addCriterion("SBMOGL_JSFY between", value1, value2, "sbmoglJsfy");
            return (Criteria) this;
        }

        public Criteria andSbmoglJsfyNotBetween(String value1, String value2) {
            addCriterion("SBMOGL_JSFY not between", value1, value2, "sbmoglJsfy");
            return (Criteria) this;
        }

        public Criteria andSbmoglQylxIsNull() {
            addCriterion("SBMOGL_QYLX is null");
            return (Criteria) this;
        }

        public Criteria andSbmoglQylxIsNotNull() {
            addCriterion("SBMOGL_QYLX is not null");
            return (Criteria) this;
        }

        public Criteria andSbmoglQylxEqualTo(String value) {
            addCriterion("SBMOGL_QYLX =", value, "sbmoglQylx");
            return (Criteria) this;
        }

        public Criteria andSbmoglQylxNotEqualTo(String value) {
            addCriterion("SBMOGL_QYLX <>", value, "sbmoglQylx");
            return (Criteria) this;
        }

        public Criteria andSbmoglQylxGreaterThan(String value) {
            addCriterion("SBMOGL_QYLX >", value, "sbmoglQylx");
            return (Criteria) this;
        }

        public Criteria andSbmoglQylxGreaterThanOrEqualTo(String value) {
            addCriterion("SBMOGL_QYLX >=", value, "sbmoglQylx");
            return (Criteria) this;
        }

        public Criteria andSbmoglQylxLessThan(String value) {
            addCriterion("SBMOGL_QYLX <", value, "sbmoglQylx");
            return (Criteria) this;
        }

        public Criteria andSbmoglQylxLessThanOrEqualTo(String value) {
            addCriterion("SBMOGL_QYLX <=", value, "sbmoglQylx");
            return (Criteria) this;
        }

        public Criteria andSbmoglQylxLike(String value) {
            addCriterion("SBMOGL_QYLX like", value, "sbmoglQylx");
            return (Criteria) this;
        }

        public Criteria andSbmoglQylxNotLike(String value) {
            addCriterion("SBMOGL_QYLX not like", value, "sbmoglQylx");
            return (Criteria) this;
        }

        public Criteria andSbmoglQylxIn(List<String> values) {
            addCriterion("SBMOGL_QYLX in", values, "sbmoglQylx");
            return (Criteria) this;
        }

        public Criteria andSbmoglQylxNotIn(List<String> values) {
            addCriterion("SBMOGL_QYLX not in", values, "sbmoglQylx");
            return (Criteria) this;
        }

        public Criteria andSbmoglQylxBetween(String value1, String value2) {
            addCriterion("SBMOGL_QYLX between", value1, value2, "sbmoglQylx");
            return (Criteria) this;
        }

        public Criteria andSbmoglQylxNotBetween(String value1, String value2) {
            addCriterion("SBMOGL_QYLX not between", value1, value2, "sbmoglQylx");
            return (Criteria) this;
        }

        public Criteria andSbmoglKzqyIsNull() {
            addCriterion("SBMOGL_KZQY is null");
            return (Criteria) this;
        }

        public Criteria andSbmoglKzqyIsNotNull() {
            addCriterion("SBMOGL_KZQY is not null");
            return (Criteria) this;
        }

        public Criteria andSbmoglKzqyEqualTo(String value) {
            addCriterion("SBMOGL_KZQY =", value, "sbmoglKzqy");
            return (Criteria) this;
        }

        public Criteria andSbmoglKzqyNotEqualTo(String value) {
            addCriterion("SBMOGL_KZQY <>", value, "sbmoglKzqy");
            return (Criteria) this;
        }

        public Criteria andSbmoglKzqyGreaterThan(String value) {
            addCriterion("SBMOGL_KZQY >", value, "sbmoglKzqy");
            return (Criteria) this;
        }

        public Criteria andSbmoglKzqyGreaterThanOrEqualTo(String value) {
            addCriterion("SBMOGL_KZQY >=", value, "sbmoglKzqy");
            return (Criteria) this;
        }

        public Criteria andSbmoglKzqyLessThan(String value) {
            addCriterion("SBMOGL_KZQY <", value, "sbmoglKzqy");
            return (Criteria) this;
        }

        public Criteria andSbmoglKzqyLessThanOrEqualTo(String value) {
            addCriterion("SBMOGL_KZQY <=", value, "sbmoglKzqy");
            return (Criteria) this;
        }

        public Criteria andSbmoglKzqyLike(String value) {
            addCriterion("SBMOGL_KZQY like", value, "sbmoglKzqy");
            return (Criteria) this;
        }

        public Criteria andSbmoglKzqyNotLike(String value) {
            addCriterion("SBMOGL_KZQY not like", value, "sbmoglKzqy");
            return (Criteria) this;
        }

        public Criteria andSbmoglKzqyIn(List<String> values) {
            addCriterion("SBMOGL_KZQY in", values, "sbmoglKzqy");
            return (Criteria) this;
        }

        public Criteria andSbmoglKzqyNotIn(List<String> values) {
            addCriterion("SBMOGL_KZQY not in", values, "sbmoglKzqy");
            return (Criteria) this;
        }

        public Criteria andSbmoglKzqyBetween(String value1, String value2) {
            addCriterion("SBMOGL_KZQY between", value1, value2, "sbmoglKzqy");
            return (Criteria) this;
        }

        public Criteria andSbmoglKzqyNotBetween(String value1, String value2) {
            addCriterion("SBMOGL_KZQY not between", value1, value2, "sbmoglKzqy");
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

        public Criteria andOutputwhidIsNull() {
            addCriterion("OUTPUTWHID is null");
            return (Criteria) this;
        }

        public Criteria andOutputwhidIsNotNull() {
            addCriterion("OUTPUTWHID is not null");
            return (Criteria) this;
        }

        public Criteria andOutputwhidEqualTo(String value) {
            addCriterion("OUTPUTWHID =", value, "outputwhid");
            return (Criteria) this;
        }

        public Criteria andOutputwhidNotEqualTo(String value) {
            addCriterion("OUTPUTWHID <>", value, "outputwhid");
            return (Criteria) this;
        }

        public Criteria andOutputwhidGreaterThan(String value) {
            addCriterion("OUTPUTWHID >", value, "outputwhid");
            return (Criteria) this;
        }

        public Criteria andOutputwhidGreaterThanOrEqualTo(String value) {
            addCriterion("OUTPUTWHID >=", value, "outputwhid");
            return (Criteria) this;
        }

        public Criteria andOutputwhidLessThan(String value) {
            addCriterion("OUTPUTWHID <", value, "outputwhid");
            return (Criteria) this;
        }

        public Criteria andOutputwhidLessThanOrEqualTo(String value) {
            addCriterion("OUTPUTWHID <=", value, "outputwhid");
            return (Criteria) this;
        }

        public Criteria andOutputwhidLike(String value) {
            addCriterion("OUTPUTWHID like", value, "outputwhid");
            return (Criteria) this;
        }

        public Criteria andOutputwhidNotLike(String value) {
            addCriterion("OUTPUTWHID not like", value, "outputwhid");
            return (Criteria) this;
        }

        public Criteria andOutputwhidIn(List<String> values) {
            addCriterion("OUTPUTWHID in", values, "outputwhid");
            return (Criteria) this;
        }

        public Criteria andOutputwhidNotIn(List<String> values) {
            addCriterion("OUTPUTWHID not in", values, "outputwhid");
            return (Criteria) this;
        }

        public Criteria andOutputwhidBetween(String value1, String value2) {
            addCriterion("OUTPUTWHID between", value1, value2, "outputwhid");
            return (Criteria) this;
        }

        public Criteria andOutputwhidNotBetween(String value1, String value2) {
            addCriterion("OUTPUTWHID not between", value1, value2, "outputwhid");
            return (Criteria) this;
        }

        public Criteria andOutputvoicerIsNull() {
            addCriterion("OUTPUTVOICER is null");
            return (Criteria) this;
        }

        public Criteria andOutputvoicerIsNotNull() {
            addCriterion("OUTPUTVOICER is not null");
            return (Criteria) this;
        }

        public Criteria andOutputvoicerEqualTo(String value) {
            addCriterion("OUTPUTVOICER =", value, "outputvoicer");
            return (Criteria) this;
        }

        public Criteria andOutputvoicerNotEqualTo(String value) {
            addCriterion("OUTPUTVOICER <>", value, "outputvoicer");
            return (Criteria) this;
        }

        public Criteria andOutputvoicerGreaterThan(String value) {
            addCriterion("OUTPUTVOICER >", value, "outputvoicer");
            return (Criteria) this;
        }

        public Criteria andOutputvoicerGreaterThanOrEqualTo(String value) {
            addCriterion("OUTPUTVOICER >=", value, "outputvoicer");
            return (Criteria) this;
        }

        public Criteria andOutputvoicerLessThan(String value) {
            addCriterion("OUTPUTVOICER <", value, "outputvoicer");
            return (Criteria) this;
        }

        public Criteria andOutputvoicerLessThanOrEqualTo(String value) {
            addCriterion("OUTPUTVOICER <=", value, "outputvoicer");
            return (Criteria) this;
        }

        public Criteria andOutputvoicerLike(String value) {
            addCriterion("OUTPUTVOICER like", value, "outputvoicer");
            return (Criteria) this;
        }

        public Criteria andOutputvoicerNotLike(String value) {
            addCriterion("OUTPUTVOICER not like", value, "outputvoicer");
            return (Criteria) this;
        }

        public Criteria andOutputvoicerIn(List<String> values) {
            addCriterion("OUTPUTVOICER in", values, "outputvoicer");
            return (Criteria) this;
        }

        public Criteria andOutputvoicerNotIn(List<String> values) {
            addCriterion("OUTPUTVOICER not in", values, "outputvoicer");
            return (Criteria) this;
        }

        public Criteria andOutputvoicerBetween(String value1, String value2) {
            addCriterion("OUTPUTVOICER between", value1, value2, "outputvoicer");
            return (Criteria) this;
        }

        public Criteria andOutputvoicerNotBetween(String value1, String value2) {
            addCriterion("OUTPUTVOICER not between", value1, value2, "outputvoicer");
            return (Criteria) this;
        }

        public Criteria andOutputspeedIsNull() {
            addCriterion("OUTPUTSPEED is null");
            return (Criteria) this;
        }

        public Criteria andOutputspeedIsNotNull() {
            addCriterion("OUTPUTSPEED is not null");
            return (Criteria) this;
        }

        public Criteria andOutputspeedEqualTo(String value) {
            addCriterion("OUTPUTSPEED =", value, "outputspeed");
            return (Criteria) this;
        }

        public Criteria andOutputspeedNotEqualTo(String value) {
            addCriterion("OUTPUTSPEED <>", value, "outputspeed");
            return (Criteria) this;
        }

        public Criteria andOutputspeedGreaterThan(String value) {
            addCriterion("OUTPUTSPEED >", value, "outputspeed");
            return (Criteria) this;
        }

        public Criteria andOutputspeedGreaterThanOrEqualTo(String value) {
            addCriterion("OUTPUTSPEED >=", value, "outputspeed");
            return (Criteria) this;
        }

        public Criteria andOutputspeedLessThan(String value) {
            addCriterion("OUTPUTSPEED <", value, "outputspeed");
            return (Criteria) this;
        }

        public Criteria andOutputspeedLessThanOrEqualTo(String value) {
            addCriterion("OUTPUTSPEED <=", value, "outputspeed");
            return (Criteria) this;
        }

        public Criteria andOutputspeedLike(String value) {
            addCriterion("OUTPUTSPEED like", value, "outputspeed");
            return (Criteria) this;
        }

        public Criteria andOutputspeedNotLike(String value) {
            addCriterion("OUTPUTSPEED not like", value, "outputspeed");
            return (Criteria) this;
        }

        public Criteria andOutputspeedIn(List<String> values) {
            addCriterion("OUTPUTSPEED in", values, "outputspeed");
            return (Criteria) this;
        }

        public Criteria andOutputspeedNotIn(List<String> values) {
            addCriterion("OUTPUTSPEED not in", values, "outputspeed");
            return (Criteria) this;
        }

        public Criteria andOutputspeedBetween(String value1, String value2) {
            addCriterion("OUTPUTSPEED between", value1, value2, "outputspeed");
            return (Criteria) this;
        }

        public Criteria andOutputspeedNotBetween(String value1, String value2) {
            addCriterion("OUTPUTSPEED not between", value1, value2, "outputspeed");
            return (Criteria) this;
        }

        public Criteria andOutputvoicelevelIsNull() {
            addCriterion("OUTPUTVOICELEVEL is null");
            return (Criteria) this;
        }

        public Criteria andOutputvoicelevelIsNotNull() {
            addCriterion("OUTPUTVOICELEVEL is not null");
            return (Criteria) this;
        }

        public Criteria andOutputvoicelevelEqualTo(String value) {
            addCriterion("OUTPUTVOICELEVEL =", value, "outputvoicelevel");
            return (Criteria) this;
        }

        public Criteria andOutputvoicelevelNotEqualTo(String value) {
            addCriterion("OUTPUTVOICELEVEL <>", value, "outputvoicelevel");
            return (Criteria) this;
        }

        public Criteria andOutputvoicelevelGreaterThan(String value) {
            addCriterion("OUTPUTVOICELEVEL >", value, "outputvoicelevel");
            return (Criteria) this;
        }

        public Criteria andOutputvoicelevelGreaterThanOrEqualTo(String value) {
            addCriterion("OUTPUTVOICELEVEL >=", value, "outputvoicelevel");
            return (Criteria) this;
        }

        public Criteria andOutputvoicelevelLessThan(String value) {
            addCriterion("OUTPUTVOICELEVEL <", value, "outputvoicelevel");
            return (Criteria) this;
        }

        public Criteria andOutputvoicelevelLessThanOrEqualTo(String value) {
            addCriterion("OUTPUTVOICELEVEL <=", value, "outputvoicelevel");
            return (Criteria) this;
        }

        public Criteria andOutputvoicelevelLike(String value) {
            addCriterion("OUTPUTVOICELEVEL like", value, "outputvoicelevel");
            return (Criteria) this;
        }

        public Criteria andOutputvoicelevelNotLike(String value) {
            addCriterion("OUTPUTVOICELEVEL not like", value, "outputvoicelevel");
            return (Criteria) this;
        }

        public Criteria andOutputvoicelevelIn(List<String> values) {
            addCriterion("OUTPUTVOICELEVEL in", values, "outputvoicelevel");
            return (Criteria) this;
        }

        public Criteria andOutputvoicelevelNotIn(List<String> values) {
            addCriterion("OUTPUTVOICELEVEL not in", values, "outputvoicelevel");
            return (Criteria) this;
        }

        public Criteria andOutputvoicelevelBetween(String value1, String value2) {
            addCriterion("OUTPUTVOICELEVEL between", value1, value2, "outputvoicelevel");
            return (Criteria) this;
        }

        public Criteria andOutputvoicelevelNotBetween(String value1, String value2) {
            addCriterion("OUTPUTVOICELEVEL not between", value1, value2, "outputvoicelevel");
            return (Criteria) this;
        }

        public Criteria andOutputhasmusicIsNull() {
            addCriterion("OUTPUTHASMUSIC is null");
            return (Criteria) this;
        }

        public Criteria andOutputhasmusicIsNotNull() {
            addCriterion("OUTPUTHASMUSIC is not null");
            return (Criteria) this;
        }

        public Criteria andOutputhasmusicEqualTo(String value) {
            addCriterion("OUTPUTHASMUSIC =", value, "outputhasmusic");
            return (Criteria) this;
        }

        public Criteria andOutputhasmusicNotEqualTo(String value) {
            addCriterion("OUTPUTHASMUSIC <>", value, "outputhasmusic");
            return (Criteria) this;
        }

        public Criteria andOutputhasmusicGreaterThan(String value) {
            addCriterion("OUTPUTHASMUSIC >", value, "outputhasmusic");
            return (Criteria) this;
        }

        public Criteria andOutputhasmusicGreaterThanOrEqualTo(String value) {
            addCriterion("OUTPUTHASMUSIC >=", value, "outputhasmusic");
            return (Criteria) this;
        }

        public Criteria andOutputhasmusicLessThan(String value) {
            addCriterion("OUTPUTHASMUSIC <", value, "outputhasmusic");
            return (Criteria) this;
        }

        public Criteria andOutputhasmusicLessThanOrEqualTo(String value) {
            addCriterion("OUTPUTHASMUSIC <=", value, "outputhasmusic");
            return (Criteria) this;
        }

        public Criteria andOutputhasmusicLike(String value) {
            addCriterion("OUTPUTHASMUSIC like", value, "outputhasmusic");
            return (Criteria) this;
        }

        public Criteria andOutputhasmusicNotLike(String value) {
            addCriterion("OUTPUTHASMUSIC not like", value, "outputhasmusic");
            return (Criteria) this;
        }

        public Criteria andOutputhasmusicIn(List<String> values) {
            addCriterion("OUTPUTHASMUSIC in", values, "outputhasmusic");
            return (Criteria) this;
        }

        public Criteria andOutputhasmusicNotIn(List<String> values) {
            addCriterion("OUTPUTHASMUSIC not in", values, "outputhasmusic");
            return (Criteria) this;
        }

        public Criteria andOutputhasmusicBetween(String value1, String value2) {
            addCriterion("OUTPUTHASMUSIC between", value1, value2, "outputhasmusic");
            return (Criteria) this;
        }

        public Criteria andOutputhasmusicNotBetween(String value1, String value2) {
            addCriterion("OUTPUTHASMUSIC not between", value1, value2, "outputhasmusic");
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