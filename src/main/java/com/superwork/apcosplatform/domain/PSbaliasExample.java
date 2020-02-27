package com.superwork.apcosplatform.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PSbaliasExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public PSbaliasExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSerialnumIsNull() {
            addCriterion("SERIALNUM is null");
            return (Criteria) this;
        }

        public Criteria andSerialnumIsNotNull() {
            addCriterion("SERIALNUM is not null");
            return (Criteria) this;
        }

        public Criteria andSerialnumEqualTo(String value) {
            addCriterion("SERIALNUM =", value, "serialnum");
            return (Criteria) this;
        }

        public Criteria andSerialnumNotEqualTo(String value) {
            addCriterion("SERIALNUM <>", value, "serialnum");
            return (Criteria) this;
        }

        public Criteria andSerialnumGreaterThan(String value) {
            addCriterion("SERIALNUM >", value, "serialnum");
            return (Criteria) this;
        }

        public Criteria andSerialnumGreaterThanOrEqualTo(String value) {
            addCriterion("SERIALNUM >=", value, "serialnum");
            return (Criteria) this;
        }

        public Criteria andSerialnumLessThan(String value) {
            addCriterion("SERIALNUM <", value, "serialnum");
            return (Criteria) this;
        }

        public Criteria andSerialnumLessThanOrEqualTo(String value) {
            addCriterion("SERIALNUM <=", value, "serialnum");
            return (Criteria) this;
        }

        public Criteria andSerialnumLike(String value) {
            addCriterion("SERIALNUM like", value, "serialnum");
            return (Criteria) this;
        }

        public Criteria andSerialnumNotLike(String value) {
            addCriterion("SERIALNUM not like", value, "serialnum");
            return (Criteria) this;
        }

        public Criteria andSerialnumIn(List<String> values) {
            addCriterion("SERIALNUM in", values, "serialnum");
            return (Criteria) this;
        }

        public Criteria andSerialnumNotIn(List<String> values) {
            addCriterion("SERIALNUM not in", values, "serialnum");
            return (Criteria) this;
        }

        public Criteria andSerialnumBetween(String value1, String value2) {
            addCriterion("SERIALNUM between", value1, value2, "serialnum");
            return (Criteria) this;
        }

        public Criteria andSerialnumNotBetween(String value1, String value2) {
            addCriterion("SERIALNUM not between", value1, value2, "serialnum");
            return (Criteria) this;
        }

        public Criteria andDeviceNameIsNull() {
            addCriterion("DEVICE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDeviceNameIsNotNull() {
            addCriterion("DEVICE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceNameEqualTo(String value) {
            addCriterion("DEVICE_NAME =", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotEqualTo(String value) {
            addCriterion("DEVICE_NAME <>", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameGreaterThan(String value) {
            addCriterion("DEVICE_NAME >", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameGreaterThanOrEqualTo(String value) {
            addCriterion("DEVICE_NAME >=", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameLessThan(String value) {
            addCriterion("DEVICE_NAME <", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameLessThanOrEqualTo(String value) {
            addCriterion("DEVICE_NAME <=", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameLike(String value) {
            addCriterion("DEVICE_NAME like", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotLike(String value) {
            addCriterion("DEVICE_NAME not like", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameIn(List<String> values) {
            addCriterion("DEVICE_NAME in", values, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotIn(List<String> values) {
            addCriterion("DEVICE_NAME not in", values, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameBetween(String value1, String value2) {
            addCriterion("DEVICE_NAME between", value1, value2, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotBetween(String value1, String value2) {
            addCriterion("DEVICE_NAME not between", value1, value2, "deviceName");
            return (Criteria) this;
        }

        public Criteria andArrt1IsNull() {
            addCriterion("ARRT1 is null");
            return (Criteria) this;
        }

        public Criteria andArrt1IsNotNull() {
            addCriterion("ARRT1 is not null");
            return (Criteria) this;
        }

        public Criteria andArrt1EqualTo(String value) {
            addCriterion("ARRT1 =", value, "arrt1");
            return (Criteria) this;
        }

        public Criteria andArrt1NotEqualTo(String value) {
            addCriterion("ARRT1 <>", value, "arrt1");
            return (Criteria) this;
        }

        public Criteria andArrt1GreaterThan(String value) {
            addCriterion("ARRT1 >", value, "arrt1");
            return (Criteria) this;
        }

        public Criteria andArrt1GreaterThanOrEqualTo(String value) {
            addCriterion("ARRT1 >=", value, "arrt1");
            return (Criteria) this;
        }

        public Criteria andArrt1LessThan(String value) {
            addCriterion("ARRT1 <", value, "arrt1");
            return (Criteria) this;
        }

        public Criteria andArrt1LessThanOrEqualTo(String value) {
            addCriterion("ARRT1 <=", value, "arrt1");
            return (Criteria) this;
        }

        public Criteria andArrt1Like(String value) {
            addCriterion("ARRT1 like", value, "arrt1");
            return (Criteria) this;
        }

        public Criteria andArrt1NotLike(String value) {
            addCriterion("ARRT1 not like", value, "arrt1");
            return (Criteria) this;
        }

        public Criteria andArrt1In(List<String> values) {
            addCriterion("ARRT1 in", values, "arrt1");
            return (Criteria) this;
        }

        public Criteria andArrt1NotIn(List<String> values) {
            addCriterion("ARRT1 not in", values, "arrt1");
            return (Criteria) this;
        }

        public Criteria andArrt1Between(String value1, String value2) {
            addCriterion("ARRT1 between", value1, value2, "arrt1");
            return (Criteria) this;
        }

        public Criteria andArrt1NotBetween(String value1, String value2) {
            addCriterion("ARRT1 not between", value1, value2, "arrt1");
            return (Criteria) this;
        }

        public Criteria andArrt2IsNull() {
            addCriterion("ARRT2 is null");
            return (Criteria) this;
        }

        public Criteria andArrt2IsNotNull() {
            addCriterion("ARRT2 is not null");
            return (Criteria) this;
        }

        public Criteria andArrt2EqualTo(String value) {
            addCriterion("ARRT2 =", value, "arrt2");
            return (Criteria) this;
        }

        public Criteria andArrt2NotEqualTo(String value) {
            addCriterion("ARRT2 <>", value, "arrt2");
            return (Criteria) this;
        }

        public Criteria andArrt2GreaterThan(String value) {
            addCriterion("ARRT2 >", value, "arrt2");
            return (Criteria) this;
        }

        public Criteria andArrt2GreaterThanOrEqualTo(String value) {
            addCriterion("ARRT2 >=", value, "arrt2");
            return (Criteria) this;
        }

        public Criteria andArrt2LessThan(String value) {
            addCriterion("ARRT2 <", value, "arrt2");
            return (Criteria) this;
        }

        public Criteria andArrt2LessThanOrEqualTo(String value) {
            addCriterion("ARRT2 <=", value, "arrt2");
            return (Criteria) this;
        }

        public Criteria andArrt2Like(String value) {
            addCriterion("ARRT2 like", value, "arrt2");
            return (Criteria) this;
        }

        public Criteria andArrt2NotLike(String value) {
            addCriterion("ARRT2 not like", value, "arrt2");
            return (Criteria) this;
        }

        public Criteria andArrt2In(List<String> values) {
            addCriterion("ARRT2 in", values, "arrt2");
            return (Criteria) this;
        }

        public Criteria andArrt2NotIn(List<String> values) {
            addCriterion("ARRT2 not in", values, "arrt2");
            return (Criteria) this;
        }

        public Criteria andArrt2Between(String value1, String value2) {
            addCriterion("ARRT2 between", value1, value2, "arrt2");
            return (Criteria) this;
        }

        public Criteria andArrt2NotBetween(String value1, String value2) {
            addCriterion("ARRT2 not between", value1, value2, "arrt2");
            return (Criteria) this;
        }

        public Criteria andArrt3IsNull() {
            addCriterion("ARRT3 is null");
            return (Criteria) this;
        }

        public Criteria andArrt3IsNotNull() {
            addCriterion("ARRT3 is not null");
            return (Criteria) this;
        }

        public Criteria andArrt3EqualTo(String value) {
            addCriterion("ARRT3 =", value, "arrt3");
            return (Criteria) this;
        }

        public Criteria andArrt3NotEqualTo(String value) {
            addCriterion("ARRT3 <>", value, "arrt3");
            return (Criteria) this;
        }

        public Criteria andArrt3GreaterThan(String value) {
            addCriterion("ARRT3 >", value, "arrt3");
            return (Criteria) this;
        }

        public Criteria andArrt3GreaterThanOrEqualTo(String value) {
            addCriterion("ARRT3 >=", value, "arrt3");
            return (Criteria) this;
        }

        public Criteria andArrt3LessThan(String value) {
            addCriterion("ARRT3 <", value, "arrt3");
            return (Criteria) this;
        }

        public Criteria andArrt3LessThanOrEqualTo(String value) {
            addCriterion("ARRT3 <=", value, "arrt3");
            return (Criteria) this;
        }

        public Criteria andArrt3Like(String value) {
            addCriterion("ARRT3 like", value, "arrt3");
            return (Criteria) this;
        }

        public Criteria andArrt3NotLike(String value) {
            addCriterion("ARRT3 not like", value, "arrt3");
            return (Criteria) this;
        }

        public Criteria andArrt3In(List<String> values) {
            addCriterion("ARRT3 in", values, "arrt3");
            return (Criteria) this;
        }

        public Criteria andArrt3NotIn(List<String> values) {
            addCriterion("ARRT3 not in", values, "arrt3");
            return (Criteria) this;
        }

        public Criteria andArrt3Between(String value1, String value2) {
            addCriterion("ARRT3 between", value1, value2, "arrt3");
            return (Criteria) this;
        }

        public Criteria andArrt3NotBetween(String value1, String value2) {
            addCriterion("ARRT3 not between", value1, value2, "arrt3");
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