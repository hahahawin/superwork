package com.superwork.apcosplatform.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PYktZxjhExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public PYktZxjhExample() {
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

        public Criteria andZxjhIdIsNull() {
            addCriterion("ZXJH_ID is null");
            return (Criteria) this;
        }

        public Criteria andZxjhIdIsNotNull() {
            addCriterion("ZXJH_ID is not null");
            return (Criteria) this;
        }

        public Criteria andZxjhIdEqualTo(String value) {
            addCriterion("ZXJH_ID =", value, "zxjhId");
            return (Criteria) this;
        }

        public Criteria andZxjhIdNotEqualTo(String value) {
            addCriterion("ZXJH_ID <>", value, "zxjhId");
            return (Criteria) this;
        }

        public Criteria andZxjhIdGreaterThan(String value) {
            addCriterion("ZXJH_ID >", value, "zxjhId");
            return (Criteria) this;
        }

        public Criteria andZxjhIdGreaterThanOrEqualTo(String value) {
            addCriterion("ZXJH_ID >=", value, "zxjhId");
            return (Criteria) this;
        }

        public Criteria andZxjhIdLessThan(String value) {
            addCriterion("ZXJH_ID <", value, "zxjhId");
            return (Criteria) this;
        }

        public Criteria andZxjhIdLessThanOrEqualTo(String value) {
            addCriterion("ZXJH_ID <=", value, "zxjhId");
            return (Criteria) this;
        }

        public Criteria andZxjhIdLike(String value) {
            addCriterion("ZXJH_ID like", value, "zxjhId");
            return (Criteria) this;
        }

        public Criteria andZxjhIdNotLike(String value) {
            addCriterion("ZXJH_ID not like", value, "zxjhId");
            return (Criteria) this;
        }

        public Criteria andZxjhIdIn(List<String> values) {
            addCriterion("ZXJH_ID in", values, "zxjhId");
            return (Criteria) this;
        }

        public Criteria andZxjhIdNotIn(List<String> values) {
            addCriterion("ZXJH_ID not in", values, "zxjhId");
            return (Criteria) this;
        }

        public Criteria andZxjhIdBetween(String value1, String value2) {
            addCriterion("ZXJH_ID between", value1, value2, "zxjhId");
            return (Criteria) this;
        }

        public Criteria andZxjhIdNotBetween(String value1, String value2) {
            addCriterion("ZXJH_ID not between", value1, value2, "zxjhId");
            return (Criteria) this;
        }

        public Criteria andYptjhIdIsNull() {
            addCriterion("YPTJH_ID is null");
            return (Criteria) this;
        }

        public Criteria andYptjhIdIsNotNull() {
            addCriterion("YPTJH_ID is not null");
            return (Criteria) this;
        }

        public Criteria andYptjhIdEqualTo(String value) {
            addCriterion("YPTJH_ID =", value, "yptjhId");
            return (Criteria) this;
        }

        public Criteria andYptjhIdNotEqualTo(String value) {
            addCriterion("YPTJH_ID <>", value, "yptjhId");
            return (Criteria) this;
        }

        public Criteria andYptjhIdGreaterThan(String value) {
            addCriterion("YPTJH_ID >", value, "yptjhId");
            return (Criteria) this;
        }

        public Criteria andYptjhIdGreaterThanOrEqualTo(String value) {
            addCriterion("YPTJH_ID >=", value, "yptjhId");
            return (Criteria) this;
        }

        public Criteria andYptjhIdLessThan(String value) {
            addCriterion("YPTJH_ID <", value, "yptjhId");
            return (Criteria) this;
        }

        public Criteria andYptjhIdLessThanOrEqualTo(String value) {
            addCriterion("YPTJH_ID <=", value, "yptjhId");
            return (Criteria) this;
        }

        public Criteria andYptjhIdLike(String value) {
            addCriterion("YPTJH_ID like", value, "yptjhId");
            return (Criteria) this;
        }

        public Criteria andYptjhIdNotLike(String value) {
            addCriterion("YPTJH_ID not like", value, "yptjhId");
            return (Criteria) this;
        }

        public Criteria andYptjhIdIn(List<String> values) {
            addCriterion("YPTJH_ID in", values, "yptjhId");
            return (Criteria) this;
        }

        public Criteria andYptjhIdNotIn(List<String> values) {
            addCriterion("YPTJH_ID not in", values, "yptjhId");
            return (Criteria) this;
        }

        public Criteria andYptjhIdBetween(String value1, String value2) {
            addCriterion("YPTJH_ID between", value1, value2, "yptjhId");
            return (Criteria) this;
        }

        public Criteria andYptjhIdNotBetween(String value1, String value2) {
            addCriterion("YPTJH_ID not between", value1, value2, "yptjhId");
            return (Criteria) this;
        }

        public Criteria andZxjhMcIsNull() {
            addCriterion("ZXJH_MC is null");
            return (Criteria) this;
        }

        public Criteria andZxjhMcIsNotNull() {
            addCriterion("ZXJH_MC is not null");
            return (Criteria) this;
        }

        public Criteria andZxjhMcEqualTo(String value) {
            addCriterion("ZXJH_MC =", value, "zxjhMc");
            return (Criteria) this;
        }

        public Criteria andZxjhMcNotEqualTo(String value) {
            addCriterion("ZXJH_MC <>", value, "zxjhMc");
            return (Criteria) this;
        }

        public Criteria andZxjhMcGreaterThan(String value) {
            addCriterion("ZXJH_MC >", value, "zxjhMc");
            return (Criteria) this;
        }

        public Criteria andZxjhMcGreaterThanOrEqualTo(String value) {
            addCriterion("ZXJH_MC >=", value, "zxjhMc");
            return (Criteria) this;
        }

        public Criteria andZxjhMcLessThan(String value) {
            addCriterion("ZXJH_MC <", value, "zxjhMc");
            return (Criteria) this;
        }

        public Criteria andZxjhMcLessThanOrEqualTo(String value) {
            addCriterion("ZXJH_MC <=", value, "zxjhMc");
            return (Criteria) this;
        }

        public Criteria andZxjhMcLike(String value) {
            addCriterion("ZXJH_MC like", value, "zxjhMc");
            return (Criteria) this;
        }

        public Criteria andZxjhMcNotLike(String value) {
            addCriterion("ZXJH_MC not like", value, "zxjhMc");
            return (Criteria) this;
        }

        public Criteria andZxjhMcIn(List<String> values) {
            addCriterion("ZXJH_MC in", values, "zxjhMc");
            return (Criteria) this;
        }

        public Criteria andZxjhMcNotIn(List<String> values) {
            addCriterion("ZXJH_MC not in", values, "zxjhMc");
            return (Criteria) this;
        }

        public Criteria andZxjhMcBetween(String value1, String value2) {
            addCriterion("ZXJH_MC between", value1, value2, "zxjhMc");
            return (Criteria) this;
        }

        public Criteria andZxjhMcNotBetween(String value1, String value2) {
            addCriterion("ZXJH_MC not between", value1, value2, "zxjhMc");
            return (Criteria) this;
        }

        public Criteria andZxjhYptzhIsNull() {
            addCriterion("ZXJH_YPTZH is null");
            return (Criteria) this;
        }

        public Criteria andZxjhYptzhIsNotNull() {
            addCriterion("ZXJH_YPTZH is not null");
            return (Criteria) this;
        }

        public Criteria andZxjhYptzhEqualTo(String value) {
            addCriterion("ZXJH_YPTZH =", value, "zxjhYptzh");
            return (Criteria) this;
        }

        public Criteria andZxjhYptzhNotEqualTo(String value) {
            addCriterion("ZXJH_YPTZH <>", value, "zxjhYptzh");
            return (Criteria) this;
        }

        public Criteria andZxjhYptzhGreaterThan(String value) {
            addCriterion("ZXJH_YPTZH >", value, "zxjhYptzh");
            return (Criteria) this;
        }

        public Criteria andZxjhYptzhGreaterThanOrEqualTo(String value) {
            addCriterion("ZXJH_YPTZH >=", value, "zxjhYptzh");
            return (Criteria) this;
        }

        public Criteria andZxjhYptzhLessThan(String value) {
            addCriterion("ZXJH_YPTZH <", value, "zxjhYptzh");
            return (Criteria) this;
        }

        public Criteria andZxjhYptzhLessThanOrEqualTo(String value) {
            addCriterion("ZXJH_YPTZH <=", value, "zxjhYptzh");
            return (Criteria) this;
        }

        public Criteria andZxjhYptzhLike(String value) {
            addCriterion("ZXJH_YPTZH like", value, "zxjhYptzh");
            return (Criteria) this;
        }

        public Criteria andZxjhYptzhNotLike(String value) {
            addCriterion("ZXJH_YPTZH not like", value, "zxjhYptzh");
            return (Criteria) this;
        }

        public Criteria andZxjhYptzhIn(List<String> values) {
            addCriterion("ZXJH_YPTZH in", values, "zxjhYptzh");
            return (Criteria) this;
        }

        public Criteria andZxjhYptzhNotIn(List<String> values) {
            addCriterion("ZXJH_YPTZH not in", values, "zxjhYptzh");
            return (Criteria) this;
        }

        public Criteria andZxjhYptzhBetween(String value1, String value2) {
            addCriterion("ZXJH_YPTZH between", value1, value2, "zxjhYptzh");
            return (Criteria) this;
        }

        public Criteria andZxjhYptzhNotBetween(String value1, String value2) {
            addCriterion("ZXJH_YPTZH not between", value1, value2, "zxjhYptzh");
            return (Criteria) this;
        }

        public Criteria andZxjhZtIsNull() {
            addCriterion("ZXJH_ZT is null");
            return (Criteria) this;
        }

        public Criteria andZxjhZtIsNotNull() {
            addCriterion("ZXJH_ZT is not null");
            return (Criteria) this;
        }

        public Criteria andZxjhZtEqualTo(String value) {
            addCriterion("ZXJH_ZT =", value, "zxjhZt");
            return (Criteria) this;
        }

        public Criteria andZxjhZtNotEqualTo(String value) {
            addCriterion("ZXJH_ZT <>", value, "zxjhZt");
            return (Criteria) this;
        }

        public Criteria andZxjhZtGreaterThan(String value) {
            addCriterion("ZXJH_ZT >", value, "zxjhZt");
            return (Criteria) this;
        }

        public Criteria andZxjhZtGreaterThanOrEqualTo(String value) {
            addCriterion("ZXJH_ZT >=", value, "zxjhZt");
            return (Criteria) this;
        }

        public Criteria andZxjhZtLessThan(String value) {
            addCriterion("ZXJH_ZT <", value, "zxjhZt");
            return (Criteria) this;
        }

        public Criteria andZxjhZtLessThanOrEqualTo(String value) {
            addCriterion("ZXJH_ZT <=", value, "zxjhZt");
            return (Criteria) this;
        }

        public Criteria andZxjhZtLike(String value) {
            addCriterion("ZXJH_ZT like", value, "zxjhZt");
            return (Criteria) this;
        }

        public Criteria andZxjhZtNotLike(String value) {
            addCriterion("ZXJH_ZT not like", value, "zxjhZt");
            return (Criteria) this;
        }

        public Criteria andZxjhZtIn(List<String> values) {
            addCriterion("ZXJH_ZT in", values, "zxjhZt");
            return (Criteria) this;
        }

        public Criteria andZxjhZtNotIn(List<String> values) {
            addCriterion("ZXJH_ZT not in", values, "zxjhZt");
            return (Criteria) this;
        }

        public Criteria andZxjhZtBetween(String value1, String value2) {
            addCriterion("ZXJH_ZT between", value1, value2, "zxjhZt");
            return (Criteria) this;
        }

        public Criteria andZxjhZtNotBetween(String value1, String value2) {
            addCriterion("ZXJH_ZT not between", value1, value2, "zxjhZt");
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